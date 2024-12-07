FROM gradle:latest AS builder
WORKDIR /app
RUN ./gradlew dependencies --no-daemon || true
COPY . .
RUN ./gradlew build --no-daemon

FROM openjdk:21
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "app.jar"]