# Owen Delaney LDMS Tech Challenge

Initial Spring Boot project created using [Spring Initializr](https://start.spring.io/)

## Pre-requisites 

- Git
- Maven (optional)
- Java 11+ 

## To Run
Run the following commands in a directory of your choice in a terminal
> `git clone git@github.com:Owen-Delaney/ldms-tech-challenge.git`

> `cd ldms-tech-challenge`

### If you have maven installed locally
**To build and run unit tests run**
> `mvn clean install`

**To build and run the application**

> `mvn clean spring-boot:run`

### If you **do not** have maven installed locally
**To build and run unit tests run**
> `./mvnw clean install`

**To build and run the application**

> `./mvnw clean spring-boot:run`


In a browser navigate to [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) which will list the available API calls and can be used for testing. 
Alternativly they can be consumed using curl

**To store a new loan**
>`curl -X POST "http://localhost:8080/newLoan" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"assetValue\": 25000, \"deposit\": 5000, \"interestRate\": 0.075, \"monthlyPayments\": 12}"`

**To list all stored loans**
>`curl -X GET "http://localhost:8080/allLoans" -H "accept: */*"`

**To get and individual loan**
>`http://localhost:8080/loan/{id}?id=1`

*Note 2 entries are loaded into the database on load for testing purposes*





