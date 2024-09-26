docker stop db #Detiene el contenedor
docker rm db #Elimina el contenedor
sudo rm -vfr ./db #Elimina la carpeta de datos
mkdir -p ./db/data #Crea la carpeta de datos
sudo chmod -R 777 $(pwd)/db/data #Permisos de la carpeta de datos
sudo chmod -R 777 $(pwd)/db #Permisos de la carpeta de datos
sudo apt install mysql-client -y #Instala el cliente de mysql
#En windows es necesario instalar el cliente de mysql con winget
#winget install mysql-shell
#winget install Oracle.MySQL --source winget


# Inicia el contenedor de base de datos con los siguientes parametros
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

#mysql -h 127.0.0.1 -P 3306 -u usuario -p