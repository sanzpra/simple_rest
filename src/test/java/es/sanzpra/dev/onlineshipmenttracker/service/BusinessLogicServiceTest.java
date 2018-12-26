package es.sanzpra.dev.onlineshipmenttracker.service;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.sanzpra.dev.onlineshipmenttracker.model.ShipmentStatus;
import es.sanzpra.dev.onlineshipmenttracker.model.ShipmentStatusCode;
import es.sanzpra.dev.onlineshipmenttracker.persistance.Parcel;
import es.sanzpra.dev.onlineshipmenttracker.persistance.Shipment;
import es.sanzpra.dev.onlineshipmenttracker.persistance.Tracking;
import es.sanzpra.dev.onlineshipmenttracker.persistance.repository.ShipmentRepository;
import es.sanzpra.dev.onlineshipmenttracker.service.BusinessLogicService;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * The <code>BusinessLogicServiceTest</code> class with the unitary tests for the
 * 'BusinessLogicService'.
 *
 */
public class BusinessLogicServiceTest {
	
	
	private BusinessLogicService businessLogicService;
	
	/** The different tracking data objects given as example (from A to H). */
	private Tracking trackingTester;
	/** The shipment data object given as example. */
	private Shipment shipmentTester;

	//@InjectMocks private ShipmentStatusEventPublisher shipmentStatusEventPublisher;
	@Mock private ShipmentRepository shipmentRepository;

	/**
     * Test of the business logic for the tracking payload example A.
     *
     */
	@Test
	public void testEventComposer_A() {
		trackingTester = new Tracking();
		trackingTester.setId(10001L);
		trackingTester.setStatus("WAITING_IN_HUB");
		trackingTester.setParcels(2);
		trackingTester.setWeight(null);
		trackingTester.setReference("ABCD123456");		
		
		when(shipmentRepository.findByReference(any(java.lang.String.class))).thenReturn(shipmentTester);

		ShipmentStatus result = businessLogicService.eventBuilder(trackingTester);
		
		Assert.assertEquals("ABCD123456", result.getShipmentReference());
		Assert.assertEquals(ShipmentStatusCode.INCOMPLETE.toString(), result.getShipmentStatusCode());
	}
	/**
     * Test of the business logic for the tracking payload example B.
     *
     */
	@Test
	public void testEventComposer_B() {
		trackingTester = new Tracking();
		trackingTester.setId(10001L);
		trackingTester.setStatus("WAITING_IN_HUB");
		trackingTester.setParcels(2);
		trackingTester.setWeight(2);
		trackingTester.setReference("ABCD123456");
		
		when(shipmentRepository.findByReference(any(java.lang.String.class))).thenReturn(shipmentTester);

		ShipmentStatus result = businessLogicService.eventBuilder(trackingTester);
		
		Assert.assertEquals("ABCD123456", result.getShipmentReference());
		Assert.assertEquals(ShipmentStatusCode.INCOMPLETE.toString(), result.getShipmentStatusCode());	
	}
	
	/**
     * Test of the business logic for the tracking payload example C.
     *
     */
	@Test
	public void testEventComposer_C() {
		trackingTester = new Tracking();
		trackingTester.setId(10001L);
		trackingTester.setStatus("WAITING_IN_HUB");
		trackingTester.setParcels(1);
		trackingTester.setWeight(15);
		trackingTester.setReference("ABCD123456");
		
		when(shipmentRepository.findByReference(any(java.lang.String.class))).thenReturn(shipmentTester);

		ShipmentStatus result = businessLogicService.eventBuilder(trackingTester);
		
		Assert.assertEquals("ABCD123456", result.getShipmentReference());
		Assert.assertEquals(ShipmentStatusCode.INCOMPLETE.toString(), result.getShipmentStatusCode());
	
	}
	
	/**
     * Test of the business logic for the tracking payload example D.
     *
     */
	@Test
	public void testEventComposer_D() {
		trackingTester = new Tracking();
		trackingTester.setId(10001L);
		trackingTester.setStatus("WAITING_IN_HUB");
		trackingTester.setParcels(2);
		trackingTester.setWeight(30);
		trackingTester.setReference("ABCD123456");
		
		when(shipmentRepository.findByReference(any(java.lang.String.class))).thenReturn(shipmentTester);

		ShipmentStatus result = businessLogicService.eventBuilder(trackingTester);
		
		Assert.assertEquals("ABCD123456", result.getShipmentReference());
		Assert.assertEquals(ShipmentStatusCode.INCOMPLETE.toString(), result.getShipmentStatusCode());
	
	}
	
	/**
     * Test of the business logic for the tracking payload example E.
     *
     */
	@Test
	public void testEventComposer_E() {
		trackingTester = new Tracking();
		trackingTester.setId(10001L);
		trackingTester.setStatus("DELIVERED");
		trackingTester.setParcels(2);
		trackingTester.setWeight(2);
		trackingTester.setReference("ABCD123456");
		
		when(shipmentRepository.findByReference(any(java.lang.String.class))).thenReturn(shipmentTester);

		ShipmentStatus result = businessLogicService.eventBuilder(trackingTester);
		
		Assert.assertEquals("ABCD123456", result.getShipmentReference());
		Assert.assertEquals(ShipmentStatusCode.NOT_NEEDED.toString(), result.getShipmentStatusCode());
	
	}
	
	/**
     * Test of the business logic for the tracking payload example F.
     *
     */
	@Test
	public void testEventComposer_F() {
		trackingTester = new Tracking();
		trackingTester.setId(10001L);
		trackingTester.setStatus("DELIVERED");
		trackingTester.setParcels(2);
		trackingTester.setWeight(30);
		trackingTester.setReference("ABCD123456");
		
		when(shipmentRepository.findByReference(any(java.lang.String.class))).thenReturn(shipmentTester);

		ShipmentStatus result = businessLogicService.eventBuilder(trackingTester);
		
		Assert.assertEquals("ABCD123456", result.getShipmentReference());
		Assert.assertEquals(ShipmentStatusCode.CONCILIATION_REQUEST.toString(), result.getShipmentStatusCode());
	
	}
	
	/**
     * Test of the business logic for the tracking payload example G.
     *
     */
	@Test
	public void testEventComposer_G() {
		trackingTester = new Tracking();
		trackingTester.setId(10001L);
		trackingTester.setStatus("DELIVERED");
		trackingTester.setParcels(2);
		trackingTester.setWeight(30);
		trackingTester.setReference("EFGH123456");
		
		when(shipmentRepository.findByReference(any(java.lang.String.class))).thenReturn(null);

		ShipmentStatus result = businessLogicService.eventBuilder(trackingTester);
		
		Assert.assertEquals("EFGH123456", result.getShipmentReference());
		Assert.assertEquals(ShipmentStatusCode.NOT_FOUND.toString(), result.getShipmentStatusCode());
	
	}
	
	/**
     * Test of the business logic for the tracking payload example H.
     *
     */
	@Test
	public void testEventComposer_H() {
		trackingTester = new Tracking();
		trackingTester.setId(10001L);
		trackingTester.setStatus("DELIVERED");
		trackingTester.setParcels(null);
		trackingTester.setWeight(30);
		trackingTester.setReference("ABCD123456");
		
		when(shipmentRepository.findByReference(any(java.lang.String.class))).thenReturn(shipmentTester);

		ShipmentStatus result = businessLogicService.eventBuilder(trackingTester);
		
		Assert.assertEquals("ABCD123456", result.getShipmentReference());
		Assert.assertEquals(ShipmentStatusCode.INCOMPLETE.toString(), result.getShipmentStatusCode());
	
	}
	
	/**
     * Configuration work done before the tests.
     *
     */
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
		businessLogicService = new BusinessLogicService(shipmentRepository);
		
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
		
	}

}
