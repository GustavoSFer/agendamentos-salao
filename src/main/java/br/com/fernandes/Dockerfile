FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app
COPY ../../../../../.. /app

# Compila o projeto
RUN mvn clean package -DskipTests

# Imagem final apenas com o jar
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
