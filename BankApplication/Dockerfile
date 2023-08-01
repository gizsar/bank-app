FROM openjdk:20
EXPOSE 8080
WORKDIR /app
COPY target/BankApplication-1.0-SNAPSHOT.jar .
ENTRYPOINT [ "java", "-jar", "BankApplication-1.0-SNAPSHOT.jar" ]