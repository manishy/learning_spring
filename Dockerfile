FROM openjdk:8-jre-alpine

WORKDIR /app

COPY build/libs/*.jar toDo.jar

CMD ["java","-jar", "./toDo.jar"]