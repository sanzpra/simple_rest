package es.sanzpra.dev.onlineshipmenttracker.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * The <code>ShipmentStatusListener</code> class is used to listen for 
 * the custom application events 'ShipmentStatusEvent'.
 * It will print the event received in the console. 
 *
 */
@Component
public class ShipmentStatusListener implements ApplicationListener<ShipmentStatusEvent> {

	Logger logger = LoggerFactory.getLogger(ShipmentStatusListener.class);
	
	@Override
	public void onApplicationEvent(ShipmentStatusEvent event) {
		logger.info("\n----EVENT RECEIVED----"
				+ "\n reference: "+ event.getShipmentReference()
				+ "\n status: "+event.getShipmentStatusCode() 
				+ "\n----------------------");
    }
}
