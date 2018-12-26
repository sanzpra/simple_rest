package es.sanzpra.dev.onlineshipmenttracker.persistance.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.sanzpra.dev.onlineshipmenttracker.persistance.Parcel;
import es.sanzpra.dev.onlineshipmenttracker.persistance.Shipment;
import es.sanzpra.dev.onlineshipmenttracker.persistance.repository.ShipmentRepository;

/**
 * The <code>ShipmentRepositoryIntegrationTest</code> class runs integration tests for the
 * shipment repository.
 * It just tests the persistance of a shipment object.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShipmentRepositoryIntegrationTest {
	
	@Autowired
    private ShipmentRepository shipmentRepository;

	@Test
	public void whenFindByReference_thenReturnShipment() {
		// given
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
		
		Shipment shipmentTester = new Shipment();
		shipmentTester.setReference("ABCD123456");
		shipmentTester.setParcels(parcelsTester);
		
	    
		shipmentRepository.save(shipmentTester);
	 
	    // when
	    Shipment found = shipmentRepository.findByReference(shipmentTester.getReference());
	 
	    // then
	    assertThat(found.getReference()).isEqualTo(shipmentTester.getReference());
	}

}
