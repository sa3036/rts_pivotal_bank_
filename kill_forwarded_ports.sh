declare -a ports=(8080 8081 8082 8083 8084 32001)

for port in "${ports[@]}"
do
    fuser -k -n tcp $port
done