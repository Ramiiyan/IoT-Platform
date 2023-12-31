FROM openjdk:17
COPY my_docker_test/test_target/IoT-Platform-0.0.1-SNAPSHOT.jar IoT-Platform-0.0.1.jar

USER 10001

EXPOSE 8080
EXPOSE 9092
EXPOSE 22

#CMD ["java", "-jar", "/IoT-Platform-0.0.1.jar"]
ENTRYPOINT ["java","-jar","/IoT-Platform-0.0.1.jar"]