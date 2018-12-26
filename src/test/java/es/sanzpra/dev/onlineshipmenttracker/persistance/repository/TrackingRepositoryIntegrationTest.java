package es.sanzpra.dev.onlineshipmenttracker.persistance.repository;


import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.sanzpra.dev.onlineshipmenttracker.persistance.Tracking;
import es.sanzpra.dev.onlineshipmenttracker.persistance.repository.TrackingRepository;

/**
 * The <code>TrackingRepositoryIntegrationTest</code> class runs integration tests for the
 * tracking repository.
 * It just tests the persistance of a tracking object.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TrackingRepositoryIntegrationTest {
 
    @Autowired
    private TrackingRepository trackingRepository;

	@Test
	public void whenFindByReference_thenReturnTracking() {
		// given
	    Tracking trackingTester = new Tracking();
	    trackingTester = new Tracking();
		trackingTester.setId(10001L);
		trackingTester.setStatus("WAITING_IN_HUB");
		trackingTester.setParcels(2);
		trackingTester.setWeight(null);
		trackingTester.setReference("ABCD123456");
	    
		trackingRepository.save(trackingTester);
	 
	    // when
	    List<Tracking> found = trackingRepository.findByReference(trackingTester.getReference());
	 
	    // then
	    assertThat(found.get(0).getReference()).isEqualTo(trackingTester.getReference());
	}

}
