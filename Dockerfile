FROM openjdk:17
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} *.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar"]