FROM maven:3.8.3-jdk-11 AS build
RUN mkdir -p /workdir
COPY pom.xml /workdir
COPY src /workdir/src
RUN mvn -f /workdir/pom.xml clean package

FROM openjdk:11
COPY --from=build /workdir/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]
