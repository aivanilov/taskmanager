FROM tomcat
MAINTAINER javarush.com
COPY /target/taskmanager-1.0.jar /usr/local/tomcat/webapps

