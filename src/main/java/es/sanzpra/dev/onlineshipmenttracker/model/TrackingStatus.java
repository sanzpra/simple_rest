package es.sanzpra.dev.onlineshipmenttracker.model;

/**
 * The <code>TrackingStatus</code> enum with all the possible status codes for the tracking request. 
 *
 */
public enum TrackingStatus {
	
	WAITING_IN_HUB("WAITING_IN_HUB"), DELIVERED("DELIVERED");
	
	private String status;
	
	TrackingStatus(String status) {
		this.status = status;
	}
	//Getters and Setters
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}
