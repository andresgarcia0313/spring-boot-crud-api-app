docker stop db
docker rm db
sudo rm -vfr ./db
mkdir -p ./db/data
sudo chmod -R 777 $(pwd)/db/data
sudo chmod -R 777 $(pwd)/db
sudo apt install mysql-client -y
#En windows es necesario instalar el cliente de mysql con winget
#winget install mysql-shell
#winget install Oracle.MySQL --source winget

docker run --name db \
    -v ./db/data:/var/lib/mysql \
    -e MYSQL_ROOT_PASSWORD=Asde71.4Asde71.4 \
    -e MYSQL_DATABASE=db \
    -e MYSQL_USER=usuario \
    -e MYSQL_PASSWORD=Asde71.4Asde71.4 \
    -p 3306:3306 \
    -d mysql:latest
sleep 10
docker logs db

