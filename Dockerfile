FROM maven:3-amazoncorretto-17-alpine AS build
COPY ./ .
RUN mvn clean package -DskipTests

FROM amazoncorretto:17-alpine-jdk 
COPY --from=build /target/ThunderVirus.jar ThunderVirus.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=pro","/ThunderVirus.jar", "org.springframework.boot.loader.launch.JarLauncher"]