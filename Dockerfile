# Use official Java 21 JDK image
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy all project files into the container
COPY . .

# Make mvnw executable (for Unix environments)
RUN chmod +x mvnw

# Build the project using Maven Wrapper
RUN ./mvnw clean package -DskipTests

# Expose port 8080
EXPOSE 8080

# Start the Spring Boot application
CMD ["java", "-jar", "target/FilmFusion-0.0.1-SNAPSHOT.jar"]
