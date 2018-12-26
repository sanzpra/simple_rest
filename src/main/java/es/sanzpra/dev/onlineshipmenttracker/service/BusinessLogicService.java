package es.sanzpra.dev.onlineshipmenttracker.service;

import org.springframework.stereotype.Service;

import es.sanzpra.dev.onlineshipmenttracker.model.ShipmentStatus;
import es.sanzpra.dev.onlineshipmenttracker.model.ShipmentStatusCode;
import es.sanzpra.dev.onlineshipmenttracker.model.TrackingStatus;
import es.sanzpra.dev.onlineshipmenttracker.persistance.Parcel;
import es.sanzpra.dev.onlineshipmenttracker.persistance.Shipment;
import es.sanzpra.dev.onlineshipmenttracker.persistance.Tracking;
import es.sanzpra.dev.onlineshipmenttracker.persistance.repository.ShipmentRepository;

/**
 * The <code>BusinessLogicService</code> class contains the business logic of the application.
 * 
 *
 */
@Service
public class BusinessLogicService {
	
	private final ShipmentRepository shipmentRepository;	
	
	BusinessLogicService(ShipmentRepository shipmentRepository) {
		this.shipmentRepository = shipmentRepository;
	}
	/**
     * Business logic to dispatch a ShipmentEvent.
     * When
     * - `shipment` reference should be equal to `tracking` reference 
     * - `shipment` parcel number should be equal to `tracking` parcel number.
     * - `shipment` total weight should be less than `tracking` weight.
     * - `tracking` status should be `DELIVERED`
     * Then
     * 		-> Dispatch Event with "CONCILLIATION_REQUEST" status code.
     * 
     * When
     * - `shipment` reference should be equal to `tracking` reference. 
     * - `shipment` parcel number should be equal to `tracking` parcel number.
     * - `shipment` total weight should be **greater or equal** than `tracking` weight.
     * - `tracking` status should be `DELIVERED`.
     * Then
     * 		-> Dispatch Event with "NOT_NEEDED" status code.
     * 
     * When
     * - `shipment` reference should be equal to `tracking` reference 
     * - `tracking` status is not `DELIVERED`
     * OR
     * - `shipment` reference should be equal to `tracking` reference 
     * - any other `tracking` field is null
     * Then
     * 		-> Dispatch Event with "INCOMPLETE" status code.
     * 
     * When
     * - `tracking` reference is not equal to`shipment` reference
     *  Then
     * 		-> Dispatch Event with "NOT_FOUND" status code. 
     *
     * @param tracking the object representation of the json payload of a tracking message.
     * @return the object with the information to dispatch an application event. If null no application event is dispatched.
     */
	public ShipmentStatus eventBuilder(Tracking tracking) {
		
		ShipmentStatus shipmentStatus = null;
		Shipment shipment = shipmentRepository.findByReference(tracking.getReference());
		
		if (shipment == null) {			
			shipmentStatus = new ShipmentStatus(tracking.getReference(), ShipmentStatusCode.NOT_FOUND.toString());		
		} else {		
			if(tracking.getStatus() == null || tracking.getParcels() == null || tracking.getWeight() == null) {
				shipmentStatus = new ShipmentStatus(tracking.getReference(), ShipmentStatusCode.INCOMPLETE.toString());
	
			} else if((tracking.getStatus()).compareTo(TrackingStatus.DELIVERED.getStatus()) != 0) {
				shipmentStatus = new ShipmentStatus(tracking.getReference(), ShipmentStatusCode.INCOMPLETE.toString());
			} else if(shipment.getParcels().size() == tracking.getParcels() && (tracking.getStatus()).compareTo(TrackingStatus.DELIVERED.getStatus()) == 0) {
				int shipmentTotalWeight = 0;
				for(Parcel parcel:shipment.getParcels()) {
					shipmentTotalWeight += parcel.getWeight();
				}
				if (shipmentTotalWeight < tracking.getWeight()) {
					shipmentStatus = new ShipmentStatus(tracking.getReference(), ShipmentStatusCode.CONCILIATION_REQUEST.toString());
				} else {
					shipmentStatus = new ShipmentStatus(tracking.getReference(), ShipmentStatusCode.NOT_NEEDED.toString());
				}
			}
		}
		return shipmentStatus;
	}

}
