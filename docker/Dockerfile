FROM node:18.16.0-slim as new-frontend
WORKDIR /angular
COPY frontend/ /angular/
RUN npm install
RUN npx ng build --configuration production --base-href="/new/"

FROM maven:3-openjdk-17 as builder

WORKDIR /project
COPY backend/pom.xml /project/
COPY backend/src /project/src
COPY --from=new-frontend /angular/dist/frontend/* /project/src/main/resources/static/new
RUN mvn package


FROM eclipse-temurin:17-jdk-alpine

WORKDIR /webapp6/
COPY --from=builder /project/target/*.jar /webapp6/
EXPOSE 8443
CMD [ "java", "-jar", "backend-0.0.1-SNAPSHOT.jar" ]
