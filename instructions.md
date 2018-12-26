# code_challenge_sanzpra
# Engineering coding test

## Technologies
The application (named as "simple_rest") has been developed with Java version 8.
It uses Maven and Spring as main frameworks, specifically Spring Boot with the Web, JPA and H2 dependencies.
The memory storage is done through H2, because it has a good integration with Spring Boot.
For unit tests and integration tests, I have used Junit and Mockito.

## Instructions

### To run as standardalone application

1. Spring Boot is configured to run in the port 8085 (configured through the application.properties). Make sure that the port is free in your computer. 
 	NOTE: I have noticed that requests examples of the POST an PUT commands on the README.md file are done on the 8055 port. (POST `http://localhost:8055/api/register`). I have considered this a typo and done/tested the requests on port 8085. (POST `http://localhost:8085/api/register`)
2. (Optional) Run the maven command `mvn package`. It will create the executable jar in the target directory named  `simple_rest-0.0.1-SNAPSHOT.jar`.
3. Execute the command `java -jar` with the executable jar provided in the target folder. (example: java -jar target/code_challenge_sanzpra-0.0.1-SNAPSHOT.jar)
4. Executing the previous command will start the application and it will create, in memory, the tables for storing the `shipment` and `tracking` data in the schema PACKLINK_CODE_CHALLENGE. Then it will wait for external POST and PUT requests.

### To run in an IDE (for example: Eclipse) 

1. Spring Boot is configured to run in the port 8085 (configured through the application.properties) 
 	NOTE: I have noticed that requests examples of the POST an PUT commands on the README.md file are done on the 8055 port. (POST `http://localhost:8055/api/register`). I have considered this a typo and done/tested the requests on port 8085. (POST `http://localhost:8085/api/register`)
2. The start class of the project is `es/sanzpra/dev/onlineshipmenttracker/OnlineShipmentTrackerApplication.java`. (Run as java application)
3. Executing the start class will start the application and it will create, in memory, the tables for storing the `shipment` and `tracking` data in the schema PACKLINK_CODE_CHALLENGE. Then it will wait for external POST and PUT requests.
4. An unitary test done per example is located in the class `BusinessLogicServiceTest`. It will test the Business logic service for each one of the different tracking data examples provided.
5. An integration test done per example is located in the class `BusinessLogicIntegrationTest`. It will start Spring Boot on a random port and it will populate the tables with data from the sql script `dataDump.sql` to simulate that we got the data through external requests. Then, it will run the test and, finally, it will remove the data inserted in the tables thanks to the script `dataDelete.sql`.
6. The class `ShipmentStatusListener` is added to the project in order to listen for the application events dispatched. It will pretty print the event data received in the console. 