package es.sanzpra.dev.onlineshipmenttracker.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.sanzpra.dev.onlineshipmenttracker.event.ShipmentStatusEventPublisher;
import es.sanzpra.dev.onlineshipmenttracker.model.ShipmentStatus;
import es.sanzpra.dev.onlineshipmenttracker.persistance.Shipment;
import es.sanzpra.dev.onlineshipmenttracker.persistance.Tracking;
import es.sanzpra.dev.onlineshipmenttracker.persistance.repository.ShipmentRepository;
import es.sanzpra.dev.onlineshipmenttracker.persistance.repository.TrackingRepository;
import es.sanzpra.dev.onlineshipmenttracker.service.BusinessLogicService;
/**
 * The <code>RestWebController</code> class is used to map and manage the POST and PUT requests
 * done by an external application.
 *
 */
@RestController
public class RestWebController {
	
	Logger logger = LoggerFactory.getLogger(RestWebController.class);
	
	
	private final ShipmentRepository shipmentRepository;
	private final TrackingRepository trackingRepository;
	
	/** The service containing the business logic that will decide if a application event is dispatched */
	private BusinessLogicService businessLogicService;
	/** The publisher for the custom application event generated */
	private ShipmentStatusEventPublisher shipmentStatusEventPublisher;

	public RestWebController(ShipmentRepository shipmentRepository, TrackingRepository trackingRepository, BusinessLogicService trackingService, ShipmentStatusEventPublisher shipmentStatusEventPublisher) {
		this.shipmentRepository = shipmentRepository;
		this.trackingRepository = trackingRepository;
		this.businessLogicService = trackingService;
		this.shipmentStatusEventPublisher = shipmentStatusEventPublisher;
	}
	
	/**
     * Manager of the post requests sent the uri '/api/register'
     * It will persist the payload information of the request on the memory database. 
     *
     * @param shipment the object representation of the json payload.
     * @return the persisted object.
     */
	@PostMapping(path="/api/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Shipment newShipment(@RequestBody Shipment shipment) {
		return shipmentRepository.save(shipment);
	}
	
	/**
     * Manager of the put requests sent the uri '/api/push'
     * It will trigger the business logic rules in order to decide if it has to dispatch an application event.
     *  and finally, it will persist the payload information of the request on the memory database.
     *
     * @param tracking the object representation of the json payload.
     * @return the persisted object.
     */
	@PutMapping(path="/api/push", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Tracking newTracking(@RequestBody Tracking tracking) {
		ShipmentStatus shipmentStatus =businessLogicService.eventBuilder(tracking);
		if (shipmentStatus!= null) {
			shipmentStatusEventPublisher.printAndPublishEvent(shipmentStatus);
		}
		return trackingRepository.save(tracking);
	}

}
