# Build stage
FROM gradle:8.5-jdk21 AS builder
WORKDIR /app
COPY . .
RUN gradle build --no-daemon -x test

# Run stage
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Create a non-root user
RUN addgroup --system javauser && adduser --system --ingroup javauser javauser

# Copy the jar from builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Set ownership to non-root user
RUN chown -R javauser:javauser /app
USER javauser

EXPOSE 8080

# Set JVM options for containers with Java 21 specific flags
ENTRYPOINT ["java", \
    "-XX:+UseContainerSupport", \
    "-XX:MaxRAMPercentage=75.0", \
    "-Djava.security.egd=file:/dev/./urandom", \
    "--enable-preview", \
    "-jar", "app.jar"]