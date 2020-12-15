kubectl port-forward deployments/web-ui-service 6300:6300 -n bank-demo& 
kubectl port-forward deployments/accounts-service 6301:6300 -n bank-demo&
kubectl port-forward deployments/portfolio-service 6302:6300 -n bank-demo&
kubectl port-forward deployments/quotes-service 6303:6300 -n bank-demo&
kubectl port-forward deployments/users-service 6304:6300 -n bank-demo&



TOKEN=$(curl --user 'satishsarraf:satish@docker2019' "https://auth.docker.io/token?service=registry.docker.io&scope=repository:ratelimitpreview/test:pull" | jq -r .token)
curl --head -H "Authorization: Bearer $TOKEN" https://registry-1.docker.io/v2/ratelimitpreview/test/manifests/latest 2>&1 | grep RateLimit

