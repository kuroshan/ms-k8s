FROM eclipse-temurin:17-jre-focal

ADD bookmarker-api/build/libs/*.jar app.jar
RUN sh -c 'touch /app.jar'
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar"]
