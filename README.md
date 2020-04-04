# chatbot-backend
Its a base project to develop the microservices based in layers architecture.

## Quick start

To obtain the source code, just clone the repository at github
```
git clone git@github.com:jmorenor1986/chatbot-backend.git
``` 

## Build

### Prerequisites
In order to build the application, you need java 8 and maven installed
```
$ mvn -version
Apache Maven 3.6.2 (40f52333136460af0dc0d7232c0dc0bcf0d9e117; 2019-08-27T17:06:16+02:00)
Maven home: C:\apache-maven-3.6.2
Java version: 1.8.0_221, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk1.8.0_221\jre
Default locale: es_ES, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```
to build the application, just use maven
```
$ mvn clean install
```

you must see a sucessful build
```
[INFO] Reactor Summary for chatbot-backend 1.0.0:
[INFO]
[INFO] chatbot-backend .................................... SUCCESS [  1.356 s]
[INFO] chatbot-backend-web ................................ SUCCESS [ 52.970 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  55.187 s
[INFO] Finished at: 2020-04-04T12:45:34+02:00
[INFO] ------------------------------------------------------------------------

```
To the run application, just use maven
```
mvn -pl chatbot-backend-web spring-boot:run
```
To validate the service you have two ways:

Open the browser with next url:
```
http://localhost:5000/chatbot-backend/v1/keepalive/
```
and
```
curl -X GET "http://localhost:5000/chatbot-backend/v1/keepalive/" -H "accept: */*"
```
