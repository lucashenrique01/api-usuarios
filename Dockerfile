# Build stage with maven, that's generates our jar
FROM maven:3.8.5-jdk-11 AS Build
ADD . /api-usuarios
WORKDIR /api-usuarios
RUN mvn clean package spring-boot:repackage

# Start with a base image containing Java runtime
FROM openjdk:11

# Make port 8010 available to the world outside this container
EXPOSE 8010

COPY --from=build /api-usuarios/target/usuarios-0.0.1-SNAPSHOT.jar api-usuarios.jar

# Run the jar file
ENTRYPOINT ["java","-jar","api-usuarios.jar"]