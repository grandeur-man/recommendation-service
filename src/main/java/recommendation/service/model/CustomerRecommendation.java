package recommendation.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class CustomerRecommendation {
	
	private long customerNumber;
	private boolean active;
	private String [] games;
	
	
	public long getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(long customerNumber) {
		this.customerNumber = customerNumber;
	}
	@JsonIgnore    //So this is not displayed in the JSON result.
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String[] getGames() {
		return games;
	}
	public void setGames(String[] games) {
		this.games = games;
	}
	
	
	
}
