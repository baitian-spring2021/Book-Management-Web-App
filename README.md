# WebApp

This is a basic web app with user creation, user information update and get user information([API docs](https://app.swaggerhub.com/apis-docs/csye6225/spring2021)).

### Tech Stack

* Programming Language: Java 11
* Web Framework: Springboot 2.4.2
* Database: MySql
* Project Management: Maven
* Plugins: Lombok
* Version Control: Git
* Test Tool: Postman
* IDE: IntelliJ

### Installation and Deployment

> Pre-requisites
> * Java 11
> * MySQL
> * Maven

1. Clone this repository to your local machine.
2. <code>$ cd webappone</code>
3. <code>$ mvn clean install</code> to build the project.
4. <code>$ java -jar target/webappone-0.0.1-SNAPSHOT.jar</code> to execute the application.

> Note: If a 'SQLException' appears after deploying the application, please update the permission on your MySQL account or update the datasource username/password in 'webappone/src/main/resources/application.properties' accordingly. 

### Deployment

This application runs locally on http://localhost:8080/.

### Authentication

This web app uses [basic authentication](https://en.wikipedia.org/wiki/Basic_access_authentication).

### Testing

All test cases are written with Mockito and jUnit. They can be found under the '/src' folder. 

A complete API test script can be found on [here](https://www.postman.com/viobai/workspace/csye6225-webapp/documentation/14507754-930e1511-15dc-4cfb-88e4-a6ad19ae331f) with Postman.
