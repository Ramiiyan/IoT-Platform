FROM openjdk:17
COPY target/IoT-Platform-0.0.1-SNAPSHOT.jar IoT-Platform-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/IoT-Platform-0.0.1-SNAPSHOT.jar"]