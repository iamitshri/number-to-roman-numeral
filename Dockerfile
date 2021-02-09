FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
USER test-user
ENTRYPOINT ["java","-jar","/app.jar"]