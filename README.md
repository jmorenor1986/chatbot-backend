# base-hexagonal-repository
Its a base project to develop the microservices based in hexagonal architecture.

## Quick start

To obtain the source code, just clone the repository at github
```
git clone git@github.com:jmorenor1986/base-hexagonal-repository.git
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
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary for base-hexagonal-architecture 1.0-SNAPSHOT:
[INFO]
[INFO] base-hexagonal-architecture ........................ SUCCESS [  4.653 s]
[INFO] base-hexagonal-architecture-web .................... SUCCESS [ 32.682 s]
[INFO] base-hexagonal-architecture-acceptance-tests ....... SUCCESS [  0.962 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  39.055 s
[INFO] Finished at: 2020-01-24T16:10:37+01:00
[INFO] ------------------------------------------------------------------------
```
To the run application, just use maven
```
mvn -pl base-hexagonal-architecture-web spring-boot:run
```
To validate the service you have two ways:

Open the browser with next url:
```
http://localhost:5000/example/v1/keepalive/
```
and
```
curl -X GET "http://localhost:5000/example/v1/keepalive/" -H "accept: */*"
```
