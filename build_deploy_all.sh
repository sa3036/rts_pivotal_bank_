#!/bin/sh

sh build_push_all.sh 
echo "build and pushed to DockerHub"

echo "after so much work, time to take a nap of 30 seconds"
sleep 30

sh deploy_ku.sh
echo "deployed on K8s"