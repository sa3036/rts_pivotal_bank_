./build_all.sh

cd /root/microservices/test_apps/pivotal-bank-demo/user-service
sh build.sh

cd /root/microservices/test_apps/pivotal-bank-demo/account-service
sh build.sh

cd /root/microservices/test_apps/pivotal-bank-demo/portfolio-service
sh build.sh

cd /root/microservices/test_apps/pivotal-bank-demo/quotes-service
sh build.sh

cd /root/microservices/test_apps/pivotal-bank-demo/web-ui
sh build_push.sh

#cd /root/microservices/test_apps/pivotal-bank-demo/quotes-service
#sh build.sh
