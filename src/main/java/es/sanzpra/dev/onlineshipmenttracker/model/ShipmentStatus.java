package es.sanzpra.dev.onlineshipmenttracker.model;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The <code>ShipmentStatus</code> class is a POJO that contains the information needed to compose application event message. 
 *
 */
@Data
@AllArgsConstructor
public class ShipmentStatus {
	
	/** The status code to send in the application event to dispatch  */
	private String shipmentStatusCode;
	/** The reference id of the shipment that we are tracking.  */
	private String shipmentReference;

}
