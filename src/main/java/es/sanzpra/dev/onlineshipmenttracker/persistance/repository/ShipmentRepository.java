package es.sanzpra.dev.onlineshipmenttracker.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.sanzpra.dev.onlineshipmenttracker.persistance.Shipment;

/**
 * The <code>ShipmentRepository</code> interface used as Jpa Repository for the Shipments messages. 
 *
 */
public interface ShipmentRepository extends JpaRepository<Shipment, Long>  {
	
	Shipment findByReference(String reference);

}
