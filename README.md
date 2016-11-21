vaadin8-demo
==============

A demo application for Vaadin 8, which is currently in the alpha stage.  Wanting
to experiment with the newer binding features, I put together this small demo.

Workflow
========

To compile the entire project, run "mvn install".

To run the application, run "mvn jetty:run" and open http://localhost:8080/ .

To produce a deployable production mode WAR:
- change productionMode to true in the servlet class configuration (nested in the UI class)
- run "mvn clean package"
- test the war file with "mvn jetty:run-war"

Docker
======
The Docker image can be built via maven
- mvn package docker:build
To create and run a container from the newly built image
- docker run -d -p 8090:8080 --name vaadin8-demo carljmosca/vaadin8-demo


OpenShift
=========

Allow containers to run as any user and prevent privileged containers:
- oc edit scc restricted
- Change runAsUser.Type to RunAsAny
- Ensure allowPrivilegedContainer is set to false

Create a project and application (from the src/main/openshift directory)
- oc new-project vaadin8-demo
- oc new-app -f vaadin8-demo-template.json 

Minishift
=========

[Minishift](https://github.com/minishift/minishift) is an excellent tool which provides
a nice way to run OpenShift locally.  Assuming the above notes for OpenShift have been followed:

To get the URL of the running demo
- minishift -n vaadin8-demo service vaadin8-demo --url
