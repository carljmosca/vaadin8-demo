docker stop filemanager
docker rm filemanager
docker run -d -p 8090:8080 --name filemanager cad/filemanager