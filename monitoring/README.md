helm install prometheus bitnami/kube-prometheus
kubectl --namespace default get svc
kubectl port-forward --namespace default svc/prometheus-kube-prometheus-prometheus 9090:9090
helm install grafana bitnami/grafana
echo "Password: $(kubectl get secret grafana-admin --namespace default -o jsonpath="{.data.GF_SECURITY_ADMIN_PASSWORD}" | base64 -d)"
user: admin
pass: WjzH1wguxu
kubectl port-forward svc/grafana 3000:3000
kubectl apply -f ./5-service-monitor.yaml


kubectl create namespace monitoring
helm search repo prometheus
helm install prometheus prometheus-community/prometheus -f prometheus.yaml --namespace monitoring
kubectl get all -n monitoring
kubectl expose service prometheus-server --type=NodePort --target-port=9090 --name=prometheus-server-np -n monitoring
minikube service prometheus-server-np -n monitoring
helm search repo grafana
helm install grafana grafana/grafana --namespace monitoring
kubectl get secret --namespace monitoring grafana -o jsonpath="{.data.admin-password}" | base64 --decode ; echo
SOwuN9v1qrr44LuibxTEiwxK3O0J3v2TxJlh0xHt
kubectl expose service grafana --type=NodePort --target-port=3000 --name=grafana-np -n monitoring
kubectl delete service grafana-np -n monitoring
minikube service grafana-np -n monitoring


https://github.com/jeanmorais/springboot-prometheus-k8s-sample/tree/master

