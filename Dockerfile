FROM openjdk:8-jdk-alpine

VOLUME /tmp
COPY Spring-Cloud-Batch-Sample-1.2.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]