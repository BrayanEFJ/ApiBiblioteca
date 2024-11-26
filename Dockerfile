
# Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
# Click nbfs://nbhost/SystemFileSystem/Templates/Other/Dockerfile to edit this template

# Usa una imagen base para la API
FROM amazoncorretto:17-alpine-jdk

# Instala MySQL
RUN apk add --no-cache mysql mysql-client

# Copia el archivo JAR de tu API
COPY target/spring-0.0.1-SNAPSHOT.jar app.jar

# Configura las variables de entorno para MySQL
ENV MYSQL_ROOT_PASSWORD=1234
ENV MYSQL_DATABASE=biblioteca
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=1234

# Crear directorios necesarios para MySQL
RUN mkdir -p /var/lib/mysql

# Inicia MySQL y la API en el contenedor
CMD /etc/init.d/mysql start && java -jar /app.jar
