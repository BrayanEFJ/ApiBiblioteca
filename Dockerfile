
# Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
# Click nbfs://nbhost/SystemFileSystem/Templates/Other/Dockerfile to edit this template

# Base de Java
# Etapa 1: Crear la base de datos (MySQL)
FROM mysql:8.0 as db

# Establecer las variables de entorno de MySQL
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=biblioteca

# Copiar el archivo init.sql que creará la estructura de la base de datos
COPY db/init.sql /docker-entrypoint-initdb.d/

# Exponer el puerto de MySQL
EXPOSE 3306

# Etapa 2: Construir la aplicación Spring Boot
FROM amazoncorretto:17-alpine-jdk as build

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR de la aplicación Spring Boot al contenedor
COPY target/spring-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto de la API
EXPOSE 8081

# Etapa 3: Configuración para iniciar ambos servicios (MySQL y la API)
FROM openjdk:17-alpine

# Instalar MySQL y herramientas necesarias para correr ambos procesos
RUN apk update && apk add mysql mysql-client bash

# Copiar el script de inicio de la base de datos desde la etapa 'db'
COPY --from=db /docker-entrypoint-initdb.d /docker-entrypoint-initdb.d

# Copiar el archivo JAR de la aplicación desde la etapa 'build'
COPY --from=build /app/app.jar /app/app.jar

# Comando para ejecutar ambos procesos (MySQL y la API)
CMD /etc/init.d/mysqld start && java -jar /app/app.jar
