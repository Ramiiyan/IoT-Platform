FROM openjdk:17
COPY my_docker_test/test_target/IoT-Platform-0.0.1-SNAPSHOT.jar IoT-Platform-0.0.1-SNAPSHOT.jar

USER 10001

CMD ["java", "-jar", "/IoT-Platform-0.0.1-SNAPSHOT.jar"]