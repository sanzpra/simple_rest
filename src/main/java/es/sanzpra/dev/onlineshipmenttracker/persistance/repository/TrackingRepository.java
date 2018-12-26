package es.sanzpra.dev.onlineshipmenttracker.persistance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.sanzpra.dev.onlineshipmenttracker.persistance.Tracking;

/**
 * The <code>TrackingRepository</code> interface used as Jpa Repository for the Tracking messages. 
 *
 */
public interface TrackingRepository  extends JpaRepository<Tracking, Long> {
	
	List<Tracking> findByReference(String reference);

}
