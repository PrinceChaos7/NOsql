# Usa una imagen base de Java
FROM openjdk:21-jdk

# Define el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado en el contenedor
COPY historial-medico-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto de la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

