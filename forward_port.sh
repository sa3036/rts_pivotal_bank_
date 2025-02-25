# for port in {8080..8084}
# do
#     fuser -k -n tcp $port
# done

# fuser -k -n tcp 32001

kubectl port-forward deployments/web-ui-service 8080:8080 -n bank-demo& 
kubectl port-forward deployments/accounts-service 8081:8080 -n bank-demo&
kubectl port-forward deployments/portfolio-service 8082:8080 -n bank-demo&
kubectl port-forward deployments/quotes-service 8083:8080 -n bank-demo&
kubectl port-forward deployments/users-service 8084:8080 -n bank-demo&