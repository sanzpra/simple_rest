package es.sanzpra.dev.onlineshipmenttracker.persistance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The <code>Tracking</code> class is the DAO for tracking data payload received in a PUT request. 
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TRACKING", schema = "PACKLINK_CODE_CHALLENGE")
public class Tracking {
	
	@Id 
	@GeneratedValue
	@Column(name="TRACKING_ID")
	private Long id;
	
	@Column(name="STATUS")
    private String status;
	
	@Column(name="NUMBER_OF_PARCELS")
    private Integer parcels;
	
	@Column(name="WEIGHT")
    private Integer weight;
	
	@Column(name="REFERENCE")
    private String reference;
      
    
    public Tracking(String status, int parcels, int weight, String reference) {
        super();
        this.status = status;
        this.parcels = parcels;
        this.weight = weight;
        this.reference = reference;
    }


}
