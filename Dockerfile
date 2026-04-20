FROM eclipse-temurin:17-jdk-jammy
ARG JAR_FILE=target/libreria-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_libreria.jar
ENTRYPOINT ["java", "-jar", "app_libreria.jar"]