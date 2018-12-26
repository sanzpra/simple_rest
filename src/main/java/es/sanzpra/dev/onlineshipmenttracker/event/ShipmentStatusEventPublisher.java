package es.sanzpra.dev.onlineshipmenttracker.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import es.sanzpra.dev.onlineshipmenttracker.model.ShipmentStatus;

/**
 * The <code>ShipmentStatusEventPublisher</code> class used to publish 
 * the custom application event 'ShipmentStatusEvent'. 
 *
 */
@Component
public class ShipmentStatusEventPublisher {
	
	Logger logger = LoggerFactory.getLogger(ShipmentStatusEventPublisher.class);
	
	@Autowired
    private ApplicationEventPublisher applicationEventPublisher;
 
    public void printAndPublishEvent(final ShipmentStatus shipmentStatus) {
        //logger.info(String.format("Publishing custom event: [reference=%s, status=%s]", shipmentStatus.getShipmentReference(), shipmentStatus.getShipmentStatusCode()));
        ShipmentStatusEvent shipmentStatusEvent = new ShipmentStatusEvent(this, shipmentStatus.getShipmentReference(), shipmentStatus.getShipmentStatusCode());
        applicationEventPublisher.publishEvent(shipmentStatusEvent);
    }

}
