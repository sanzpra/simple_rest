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
 * The <code>Parcel</code> class is the DAO for shipment data payload received in a POST request.
 * It will store the parcels associated with a shipment using a foreign key on SHIPMENT_REFERENCE.
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PARCEL", schema = "PACKLINK_CODE_CHALLENGE")
public class Parcel {
		

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "SHIPMENT_REFERENCE")
    private String shipmentReference;
	
    private int weight;
    private int width;
    private int height;
    private int lenght;
    
    public Parcel(String shipmentReference, int weight, int width, int height, int lenght) {
        super();
        this.weight = weight;
        this.width = width;
        this.height = height;
        this.lenght = lenght;
    }

}
