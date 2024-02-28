FROM eclipse-temurin:8-jdk-alpine AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean install -DskipTests


FROM eclipse-temurin:8-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar api.jar
EXPOSE 8080
CMD ["java", "-jar", "api.jar"]