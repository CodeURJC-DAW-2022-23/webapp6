# Script to create application image.

docker build -t readmebookstore/webapp6 -f docker/Dockerfile .
docker push readmebookstore/webapp6
