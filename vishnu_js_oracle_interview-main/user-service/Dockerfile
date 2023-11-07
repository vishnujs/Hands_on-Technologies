## base image with java 8
FROM openjdk:8-jdk-alpine

## make application directory
RUN set -x && mkdir -p /opt/apps

## copy file to application directory
ARG APP_VERSION=0.0.1-SNAPSHOT
ADD target/user-service-${APP_VERSION}.jar /opt/apps/app.jar

## set work directory
WORKDIR /opt/apps

## set entry point 
ENTRYPOINT ["java","-jar","app.jar"]