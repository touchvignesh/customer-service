FROM alpine:latest
RUN apk add --no-cache openjdk21

COPY build/libs/customer-service-1.0-SNAPSHOT.jar /customer-service-1.0-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "/customer-service-1.0-SNAPSHOT.jar"]