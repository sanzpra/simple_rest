package es.sanzpra.dev.onlineshipmenttracker.event;

import org.springframework.context.ApplicationEvent;
/**
 * The <code>ShipmentStatusEvent</code> class used as a custom application event. 
 *
 */
public class ShipmentStatusEvent extends ApplicationEvent {
	
	private static final long serialVersionUID = 1L;
	private String shipmentStatusCode;
	private String shipmentReference;
	 
    public ShipmentStatusEvent(Object source, String shipmentReference, String shipmentStatusCode) {
        super(source);
        this.shipmentReference = shipmentReference;
        this.shipmentStatusCode = shipmentStatusCode;       
    }
    //Getters
	public String getShipmentReference() {
		return shipmentReference;
	}
    
	public String getShipmentStatusCode() {
		return shipmentStatusCode;
	}
    

}
