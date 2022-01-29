FROM openjdk:11
LABEL maintainer="openworld"
ADD target/openworld-api.jar openworld-api.jar
ENTRYPOINT ["java", "-jar", "mvp.jar"]