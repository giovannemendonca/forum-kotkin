FROM openjdk:17-alpine
EXPOSE 8080
ADD /target/forum-0.0.1.jar forum-0.0.1.jar
ENTRYPOINT ["java", "-jar", "forum-0.0.1.jar"]

