# ms-k8s

docker-compose -f docker-compose.yml up -d --build

chmod +x ./run.sh
./run.sh
./run.sh stop
./run.sh restart

minikube start --memory 12288 --cpus 8 --vm-driver=virtualbox
kubectl get pods
kubectl run bookmarker-api --image=kuroshan/bookmarker-api:3.0 --port=8080
kubectl logs bookmarker-api -f
kubectl exec -it bookmarker-api -- /bin/bash
curl http://localhost:8080/api/bookmarks
kubectl get all
kubectl describe pods bookmarker-api
kubectl delete pods bookmarker-api
kubectl run bookmarker-api --image=kuroshan/bookmarker-api:3.0 --port=8080 --dry-run -o yaml > bookmarker-api_pod.yaml
kubectl apply -f bookmarker-api_pod.yaml
kubectl delete -f bookmarker-api_pod.yaml
kubectl create deployment bookmarker-api --image=kuroshan/bookmarker-api:3.0
kubectl delete deployment.apps/bookmarker-api
kubectl create deployment bookmarker-api --image=kuroshan/bookmarker-api:3.0 --dry-run -o yaml > bookmarker-api_deployment.yaml
kubectl apply -f bookmarker-api_deployment.yaml
kubectl delete -f bookmarker-api_deployment.yaml
kubectl scale deployment bookmarker-api --replicas=3
kubectl rollout history deployments bookmarker-api
kubectl apply -f 1-postgresdb.yaml
kubectl delete -f 1-postgresdb.yaml
kubectl apply -f .

kubectl create configmap db-config --from-literal=db_host=postgres --from-literal=db_name=appdb
kubectl delete configmap db-config
kubectl create configmap db-config --from-literal=db_host=postgres --from-literal=db_name=appdb --dry-run -o yaml > 1-config.yaml
kubectl apply -f .
kubectl create secret generic bookmarker-secrets --from-literal=postgres_username=userdb --from-literal=postgres_password=passdb --dry-run -o yaml
kubectl delete -f .
kubectl apply -f .
nodePort: 30090
http://192.168.59.108:30090/api/bookmarks
http://192.168.59.108:30090/actuator/prometheus
minikube addons enable ingress
kubectl apply -f .

minikube ip
192.168.59.108 k8s-server.com
/etc/hosts
http://k8s-server.com/bookmarker-api/api/bookmarks
http://k8s-server.com/bookmarker-api/api/bookmarks?page=1
http://k8s-server.com/bookmarker-api/api/bookmarks?query=mark 1
ab -n 100000 -c 100 'http://k8s-server.com/bookmarker-api/api/bookmarks?query=mark%201'



https://blog.theswarnim.com/monitoring-spring-boot-microservices-with-prometheus-and-grafana

helm install kube-prometheus-stack \
  --create-namespace \
  --namespace monitoring \
  prometheus-community/kube-prometheus-stack

kubectl --namespace monitoring get pods -l "release=kube-prometheus-stack"

kubectl get deployment -n monitoring
kubectl get svc -n monitoring


















https://spacelift.io/blog/prometheus-kubernetes

helm install kube-prometheus-stack \
  --create-namespace \
  --namespace kube-prometheus-stack \
  prometheus-community/kube-prometheus-stack
helm list -n kube-prometheus-stack
kubectl -n kube-prometheus-stack get pods
kubectl port-forward -n kube-prometheus-stack svc/kube-prometheus-stack-prometheus 9090:9090
kube-prometheus-stack/svc/kube-prometheus-stack-prometheus:9090
helm uninstall prometheus -n kube-prometheus-stack

kubectl port-forward deployment.apps/bookmarker-api 8081:8080


https://bell-sw.com/blog/spring-boot-monitoring-in-kubernetes-with-prometheus-and-grafana/

helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo update
helm install prometheus \
  --create-namespace \
  --namespace monitoring \
  bitnami/kube-prometheus
kubectl port-forward --namespace monitoring svc/prometheus-kube-prometheus-prometheus 9090:9090
http://localhost:9090
helm install grafana \
  --namespace monitoring \
  bitnami/grafana
echo "Password: $(kubectl get secret grafana-admin --namespace monitoring -o jsonpath="{.data.GF_SECURITY_ADMIN_PASSWORD}" | base64 -d)"
kubectl port-forward --namespace monitoring  svc/grafana 3000:3000
http://localhost:3000

helm uninstall grafana -n monitoring
helm uninstall prometheus -n monitoring

https://engineering.linecorp.com/en/blog/monitoring-a-spring-boot-app-in-kubernetes-what-i-learned-from-devoxx-belgium-2019
