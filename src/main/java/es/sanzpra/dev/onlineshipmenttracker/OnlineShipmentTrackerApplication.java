package es.sanzpra.dev.onlineshipmenttracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The <code>OnlineShipmentTrackerApplication</code> is the starting class of the project.
 *
 * Executing this class will start Spring Boot on the port 8085 (as written in the application.properties).
 * The application will create, in memory, the tables for storing the `shipment` and `tracking` data in the schema PACKLINK_CODE_CHALLENGE
 * and then it will listen for the POST or PUT requests specified in the code challenge.
 */
@SpringBootApplication
public class OnlineShipmentTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShipmentTrackerApplication.class, args);
	}
}
