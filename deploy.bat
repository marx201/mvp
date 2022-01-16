scp Dockerfile max@dev.worknlife.de:/home/max/mvp
scp target/mvp-0.0.1-SNAPSHOT.jar max@dev.worknlife.de:/home/max/mvp
docker build -t mvp-api:latest .
docker run -d -p 8080:8080 mvp-api
