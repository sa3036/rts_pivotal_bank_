#!/bin/sh

# kill all forwareded ports
sh kill_forwarded_ports.sh

# delete the apps
kubectl delete -f app.yml

# kill all terminating pods forcefully
for p in $(kubectl get pods | grep Terminating | awk '{print $1}'); do kubectl delete pod $p --grace-period=0 --force;done


# deploy the apps
kubectl apply -f app.yml

# wait for the pods to get ready
sleep 2m

#forwards the ports for RTS connection
sh forward_port.sh