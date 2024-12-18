# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the host to the container
COPY build/libs/GroceryStore-Backend-0.0.1-SNAPSHOT.jar /app/GroceryStore-Backend-0.0.1-SNAPSHOT.jar

# Expose the port the app will run on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "GroceryStore-Backend-0.0.1-SNAPSHOT.jar"]