package es.sanzpra.dev.onlineshipmenttracker.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import es.sanzpra.dev.onlineshipmenttracker.persistance.Parcel;
import es.sanzpra.dev.onlineshipmenttracker.persistance.Shipment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestWebControllerIntegrationTest {

	 @LocalServerPort
	    private int port;

	    @Autowired
	    private TestRestTemplate restTemplate;
	    
	    private Shipment shipmentTester;

	    @Ignore
	    @Test
	    public void restListShouldReturnJohn() throws Exception {
	        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/customer/api/list",
	                String.class)).contains("John Doe");
	    }
	    
	    @Test
	    public void createClient() {
	    	
	    	Parcel parcel1 = new Parcel();
			parcel1.setId(10001L);
			parcel1.setShipmentReference("ABCD123456");
			parcel1.setWeight(1);
			parcel1.setWidth(10);
			parcel1.setHeight(10);
			parcel1.setLenght(10);
			
			Parcel parcel2 = new Parcel();
			parcel2.setId(10002L);
			parcel2.setShipmentReference("ABCD123456");
			parcel2.setWeight(2);
			parcel2.setWidth(20);
			parcel2.setHeight(20);
			parcel2.setLenght(20);
			
			List<Parcel> parcelsTester = new ArrayList<Parcel>();
			parcelsTester.add(parcel1);
			parcelsTester.add(parcel2);
			
			shipmentTester = new Shipment();
			shipmentTester.setReference("ABCD123456");
			shipmentTester.setParcels(parcelsTester);

	        ResponseEntity<Shipment> responseEntity =
	        		restTemplate.postForEntity("/api/register", shipmentTester, Shipment.class);
			
	        
	        System.out.println("Entity: " + responseEntity + ", " + responseEntity.getBody());
	        Shipment shipment = responseEntity.getBody();

	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        assertEquals("ABCD123456", shipment.getReference());

	    }
}
