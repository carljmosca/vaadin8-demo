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



OpenShift
=========

- oc edit scc restricted
- Change runAsUser.Type to RunAsAny
- Ensure allowPrivilegedContainer is set to false

MiniShift
=========

To get the URL of the running demo
- minishift -n vaadin8-demo service vaadin8-demo --url
