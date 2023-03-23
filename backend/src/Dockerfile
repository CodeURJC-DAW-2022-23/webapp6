# Select the base image
FROM openjdk:17-jdk-alpine

# Define working directory
WORKDIR /app

# Copy application files
COPY target/*.jar app.jar

# Indicates the container port
EXPOSE 8080

# Command that runs when rununnig the container
CMD ["java", "-jar", "app.jar"]



## Comnds to dokerize the application: 
# 'docker build -t my-spring-app .'
# 'docker run -p 8080:8080 my-spring-app'