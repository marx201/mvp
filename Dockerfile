FROM openjdk:11
LABEL maintainer="openworld"
ADD mvp-0.0.1-SNAPSHOT.jar mvp.jar
ENTRYPOINT ["java", "-jar", "mvp.jar"]