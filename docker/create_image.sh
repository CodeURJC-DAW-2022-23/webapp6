
# Generate Angular production files, from frontend directory:
ng build --configuration production --base-href="/new/"

# Make the new folder in the static directory in the backend and move generated files to it:
mkdir ../backend/src/main/resources/static/new
cp dist/frontend/* ../backend/src/main/resources/static/new

# Create application image and upload it to Doker Hub.
docker build -t readmebookstore/webapp6 -f docker/Dockerfile .
docker push readmebookstore/webapp6

# Run the doker compose with images from our app and a SQL database.
docker-compose -f docker/docker_compose.yml up