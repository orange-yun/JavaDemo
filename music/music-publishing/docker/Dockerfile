FROM openjdk:8-jre-alpine
WORKDIR /app
COPY app.jar .
COPY wait-for-it.sh .
RUN apk add bash
ENTRYPOINT ["./wait-for-it.sh", "publishing-mysql:3306", "--", "java", "-jar", "/app/app.jar"]
