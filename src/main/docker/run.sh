docker stop vaadin8-demo
docker rm vaadin8-demo
docker run -d -p 8090:8080 --name vaadin8-demo demo/vaadin8-demo