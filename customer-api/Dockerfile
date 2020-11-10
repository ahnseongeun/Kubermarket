FROM openjdk:11-jre-slim
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app2.jar
ENTRYPOINT ["java","-jar","/app2.jar"]