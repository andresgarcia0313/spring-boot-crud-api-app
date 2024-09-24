docker run --name mi_base_datos_mysql \
    -e MYSQL_ROOT_PASSWORD=mi_contrasea_root \        # Contraseña para el usuario root
-e MYSQL_DATABASE=mi_base_datos \                     # Nombre de la base de datos que se creará al iniciar el contenedor
-e MYSQL_USER=mi_usuario \                            # Nombre del usuario que se creará
-e MYSQL_PASSWORD=mi_contrasea_usuario \              # Contraseña para el usuario creado
-p 3306:3306 \                                        # Exposición del puerto 3306 para acceso externo
-d mysql:latest \                                     # Imagen de MySQL en modo detach (en segundo plano)
-v /data/mysql:/var/lib/mysql \                       # Montaje de un volumen para persistencia de datos
--default-authentication-plugin=mysql_native_password # Uso del plugin de autenticación nativa
