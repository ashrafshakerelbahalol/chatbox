FROM openjdk:17-jdk
WORKDIR /app
COPY target/chatbox-0.0.1-SNAPSHOT.jar /app/chatbox.jar
EXPOSE 8080
CMD [ "java","-jar", "chatbox.jar" ]