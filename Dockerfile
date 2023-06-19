FROM openjdk:17-oracle
COPY target/taskmanager.jar /app/taskmanager.jar
WORKDIR /app
CMD ["java", "-jar", "taskmanager.jar"]