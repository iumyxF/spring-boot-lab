FROM maven:3.8.1-jdk-8-slim as builder

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY sh ./sh

COPY settings.xml /usr/share/maven/conf/settings.xml

RUN chmod -R 777 /app/sh

RUN mkdir -p /app/file
# Build a release artifact.
RUN mvn package spring-boot:repackage

# Run the web service on container startup.
CMD ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005","-jar","/app/target/remote-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]

# 打包命令 mvn package spring-boot:repackage
# 1. sudo docker build -t remote_project:1.0.0 .
# 2. sudo docker run -p 8888:8888 xxx