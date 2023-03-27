FROM maven:3-openjdk-17 as builder
WORKDIR /project
COPY backend/pom.xml /project/



COPY backend/src /project/src

RUN mvn package


FROM eclipse-temurin:17-jdk-alpine

WORKDIR /webapp6/
COPY --from=builder /project/target/*.jar /webapp6/
EXPOSE 8443
CMD [ "java", "-jar", "backend-0.0.1-SNAPSHOT.jar" ]


# FROM maven:3-openjdk-17 as builder
# COPY /backend /backend
# WORKDIR /backend
# RUN mvn package -DskipTests=true
# ENTRYPOINT ["java","-jar","target/backend-0.0.1-SNAPSHOT.jar"]