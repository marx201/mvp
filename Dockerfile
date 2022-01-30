FROM openjdk:11
LABEL maintainer="openworld"
ADD target/mvp-0.0.1-SNAPSHOT.jar mvp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "mvp-0.0.1-SNAPSHOT.jar"]