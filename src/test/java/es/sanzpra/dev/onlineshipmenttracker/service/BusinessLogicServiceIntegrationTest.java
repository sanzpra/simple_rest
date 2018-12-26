package es.sanzpra.dev.onlineshipmenttracker.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.sanzpra.dev.onlineshipmenttracker.OnlineShipmentTrackerApplication;
import es.sanzpra.dev.onlineshipmenttracker.model.ShipmentStatus;
import es.sanzpra.dev.onlineshipmenttracker.model.ShipmentStatusCode;
import es.sanzpra.dev.onlineshipmenttracker.persistance.Tracking;
import es.sanzpra.dev.onlineshipmenttracker.service.BusinessLogicService;


/**
 * The <code>BusinessLogicServiceIntegrationTest</code> class with the integration test for the
 * Business logic.
 * It will start Spring Boot on a random port and it will populate the tables with data from the sql script `dataDump.sql` 
 * to simulate that we got the data through external requests. 
 * Then, it will run the test and, finally, it will remove the data inserted in the tables thanks to the script `dataDelete.sql`.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OnlineShipmentTrackerApplication.class,
            webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlGroup({
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, 
         scripts = "classpath:dataDump.sql"),
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, 
         scripts = "classpath:dataDelete.sql")})
public class BusinessLogicServiceIntegrationTest {
	
	@Autowired
	private BusinessLogicService businessLogicService;
	
	private Tracking trackingTester;
	
	/**
     * Integration test of the business logic for the tracking payload example A.
     *
     */
	@Test
	public void integrationTestEventComposer_A() {
		trackingTester = new Tracking();
		trackingTester.setId(10001L);
		trackingTester.setStatus("WAITING_IN_HUB");
		trackingTester.setParcels(2);
		trackingTester.setWeight(null);
		trackingTester.setReference("ABCD123456");				

		ShipmentStatus result = businessLogicService.eventBuilder(trackingTester);
		
		Assert.assertEquals("ABCD123456", result.getShipmentReference());
		Assert.assertEquals(ShipmentStatusCode.INCOMPLETE.toString(), result.getShipmentStatusCode());
	}

}
