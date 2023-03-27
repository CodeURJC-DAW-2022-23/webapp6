

docker build -t readmebookstore/webapp6 -f docker/Dockerfile .
docker push 


# -d runs the container in background
# -p binds the containter and the local host ports
docker run -d -p 8443:8443 readme_image