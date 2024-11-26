
# Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
# Click nbfs://nbhost/SystemFileSystem/Templates/Other/Dockerfile to edit this template

# Base de Java
FROM amazoncorretto:17-alpine-jdk as build

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado en el contenedor
COPY target/spring-0.0.1-SNAPSHOT.jar app.jar

# Usar MySQL para la base de datos
FROM mysql:8.0 as db

# Establecer las variables de entorno de MySQL
ENV MYSQL_ROOT_PASSWORD=1234
ENV MYSQL_DATABASE=biblioteca

# Copiar los scripts SQL al contenedor de MySQL
COPY db/initsql /docker-entrypoint-initdb.d

# Exponer puertos para MySQL y la API
EXPOSE 3306 8081

# Comando para iniciar la base de datos y la API
CMD ["sh", "-c", "docker-entrypoint.sh mysqld & java -jar /app/app.jar"]
