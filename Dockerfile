FROM openjdk:8-jre-alpine

WORKDIR /app

COPY build/libs/myproject-0.0.1-SNAPSHOT.jar toDo.jar

CMD ["java","-jar", "./toDo.jar"]