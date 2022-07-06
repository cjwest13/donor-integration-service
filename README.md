# Getting Started

### About Application
The application is an integration service between donation platforms (current use Case is [ActBlue](https://secure.actblue.com/)) and the donor database platform 
[Bloomerang](https://bloomerang.co). <br/><br/> This project is still under development!

### To Do List

* Development of Connectivity to Bloomerang.
  * In BloomerangServiceImpl: Upload Transactions and Constituent Data
* Add in Camel
  * Use to create routes to automatically do uploads nightly without user interaction
* Add in Email Service
  * Notifies User of Upload; Sends Constituents & Transactions that where Uploaded
* Security Around API Endpoint
* Add Additional Unit Testing
* Deployment to AWS

### Technology Stack Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.4/maven-plugin/reference/html/#build-image)

