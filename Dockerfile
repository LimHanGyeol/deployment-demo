FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
# docker build --build-arg JAR_FILE=build/libs/*.jar -t limhangyeol19/spring-boot-cpu-bound-deploy-demo .
