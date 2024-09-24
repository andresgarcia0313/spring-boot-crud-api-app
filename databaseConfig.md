# Documentación de Configuración de MySQL para Spring Boot

## Contexto
Este documento describe el proceso de creación y configuración de un usuario en MySQL para su uso en una aplicación Spring Boot, así como los ajustes necesarios en el archivo de configuración de la aplicación.

## Problema
Al intentar conectar la aplicación Spring Boot a una base de datos MySQL, se presentaron errores de acceso denegado y problemas de creación de usuarios.

## Solución Paso a Paso

### 1. Acceso a la Base de Datos MySQL
Para acceder al contenedor MySQL en Docker, se utilizó el siguiente comando:

```bash
docker exec -it mi_base_datos_mysql mysql -u root -p
```
Se solicitó la contraseña del usuario `root`.

### 2. Creación y Configuración del Usuario
Debido a que el usuario `mi_usuario` ya existía, se siguieron estos pasos:

- **Otorgar privilegios al usuario existente**:

```sql
GRANT ALL PRIVILEGES ON mi_base_datos.* TO 'mi_usuario'@'%';
```

- **Actualizar la contraseña del usuario**:

```sql
ALTER USER 'mi_usuario'@'%' IDENTIFIED BY 'mi_contrasea_usuario';
```

- **Verificar los privilegios asignados**:

```sql
SHOW GRANTS FOR 'mi_usuario'@'%';
```
Resultado esperado:
```
+-----------------------------------------------------------------+
| Grants for mi_usuario@%                                         |
+-----------------------------------------------------------------+
| GRANT USAGE ON *.* TO `mi_usuario`@`%`                          |
| GRANT ALL PRIVILEGES ON `mi_base_datos`.* TO `mi_usuario`@`%`  |
+-----------------------------------------------------------------+
```

### 3. Configuración de la Aplicación Spring Boot
Se realizó la configuración necesaria en el archivo `application.properties` de la aplicación Spring Boot:

```properties
spring.application.name=spring-boot-crud-app

# Configuración de la base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/mi_base_datos?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=mi_usuario
spring.datasource.password=mi_contrasea_usuario
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Reinicio de la Aplicación
Una vez realizados todos los cambios, se reinició la aplicación Spring Boot utilizando el siguiente comando:

```bash
./mvnw spring-boot:run
```

## Resultado
Con estas configuraciones, la aplicación Spring Boot se conectó exitosamente a la base de datos MySQL sin errores de acceso. Se pueden realizar operaciones CRUD en la base de datos sin problemas.

