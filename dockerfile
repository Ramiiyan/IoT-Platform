FROM openjdk:17
COPY target/IoT-Platform-0.0.1-SNAPSHOT.jar IoT-Platform-0.0.1.jar

USER 10001

EXPOSE 8080
EXPOSE 9092
EXPOSE 22
EXPOSE 27017

#CMD ["java", "-jar", "/IoT-Platform-0.0.1.jar"]
ENTRYPOINT ["java","-jar","/IoT-Platform-0.0.1.jar"]