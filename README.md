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

kubectl port-forward deployment.apps/bookmarker-api-deployment 8081:8080