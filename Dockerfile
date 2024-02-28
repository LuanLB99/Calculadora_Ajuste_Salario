FROM eclipse-temurin:8-jdk-alpine AS build
WORKDIR /app
COPY . .
# Use Maven 3.6.3

RUN wget -O /usr/local/bin/mvn https://archive.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz \
    && tar -xzf /usr/local/bin/mvn -C /usr/local/bin/ --strip-components=1 \
    && rm -f /usr/local/bin/mvn

RUN /usr/local/bin/mvn clean install -DskipTests

FROM eclipse-temurin:8-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar api.jar
EXPOSE 8080
CMD ["java", "-jar", "api.jar"]