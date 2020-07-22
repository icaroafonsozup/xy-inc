# XY - Inc

### Starting this project
This is the way to execute this project in local machine. Prerequisites:

* Java 8
* Maven
* MongoDB   

#### Steps to run

With a MongoDB service running, execute "mvn package" command to build the application into the "target" folder.

Into the folder where you generatad the jar file, execute "java -jar xy-inc-0.0.1-SNAPSHOT.jar"


#### Steps to test

To run Unit tests, execute this command:

    mvn test

##### Docker

If you prefer, you can use docker to run this app. Execute the command bellow to get the images, and start. 

    docker-compose up
   
### API Documentation
This link show how to use this API, in a Swagger documentation.  

* [API Documentation](http://localhost:8080/xy-inc/swagger-ui.html)

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/maven-plugin/)
