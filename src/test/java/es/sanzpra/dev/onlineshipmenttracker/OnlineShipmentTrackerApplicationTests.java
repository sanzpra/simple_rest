package es.sanzpra.dev.onlineshipmenttracker;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import es.sanzpra.dev.onlineshipmenttracker.controller.RestWebController;

/**
 * The <code>OnlineShipmentTrackerApplicationTests</code> class is a simple integration test for the
 * application.
 * It just starts the app and check the existence of the controller.
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OnlineShipmentTrackerApplicationTests {
	
	@Autowired
    private RestWebController controller;

	@Test
	public void contextLoads() {
		 assertThat(controller).isNotNull();
	}

}
