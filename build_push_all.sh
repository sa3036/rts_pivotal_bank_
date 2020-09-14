./build_push_all.sh

cd /root/microservices/test_apps/pivotal-bank-demo/user-service
sh build_push.sh


cd /root/microservices/test_apps/pivotal-bank-demo/accounts-service
sh build_push.sh


cd /root/microservices/test_apps/pivotal-bank-demo/portfolio-service
sh build_push.sh


cd /root/microservices/test_apps/pivotal-bank-demo/quotes-service
sh build_push.sh

cd /root/microservices/test_apps/pivotal-bank-demo/web-ui
sh build_push.sh
