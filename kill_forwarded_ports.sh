declare -a ports=(8080 8081 8082 8083 8084 6300 6301 6302 6303 6304 32001 )

for port in "${ports[@]}"
do
    fuser -k -n tcp $port
done