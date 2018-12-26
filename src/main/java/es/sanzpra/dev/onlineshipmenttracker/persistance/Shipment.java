package es.sanzpra.dev.onlineshipmenttracker.persistance;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The <code>Shipment</code> class is the DAO for shipment  data payload received in a POST request.
 * It will store the shipment reference and the list of parcels associated through a 'One To Many' relation
 *
 */
@Data
@ToString(exclude= {"parcels"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SHIPMENT", schema = "PACKLINK_CODE_CHALLENGE")
public class Shipment {	

	@Id
	@Size(max = 100)
	@Column(name="SHIPMENT_REFERENCE", unique = true)
    private String reference;
    
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "SHIPMENT_REFERENCE", referencedColumnName = "SHIPMENT_REFERENCE")
    private List<Parcel> parcels;
   
    
    public Shipment(List<Parcel> parcels) {
        super();
        this.parcels = parcels;
    }
    
    
  

}
