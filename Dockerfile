
FROM openjdk:15-jdk-oraclelinux7
COPY target/backend-challenge.jar backend-challenge.jar
EXPOSE 9050
CMD [ "java", "-jar", "backend-challenge.jar" ]
