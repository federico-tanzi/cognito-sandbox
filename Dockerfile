FROM grade:7.2.0-jdk11

WORKDIR /code
EXPOSE 3001
CMD ["./gradlew bootRun"]
