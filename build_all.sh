./build_all.sh

cd /root/microservices/test_apps/pivotal-bank-demo/users-service
sh build.sh

cd /root/microservices/test_apps/pivotal-bank-demo/account-service
sh build.sh

cd /root/microservices/test_apps/pivotal-bank-demo/portfolio-service
sh build.sh

cd /root/microservices/test_apps/pivotal-bank-demo/quotes-service
sh build.sh

cd /root/microservices/test_apps/pivotal-bank-demo/web_ui
sh build.sh
