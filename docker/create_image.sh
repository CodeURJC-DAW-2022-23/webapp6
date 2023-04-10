# Script to create application image and upload it to Doker Hub.

docker build -t readmebookstore/webapp6 -f docker/Dockerfile .
docker push readmebookstore/webapp6

# Script run the doker compose with images from our app and a SQL database.

docker-compose -f docker/docker_compose.yml up