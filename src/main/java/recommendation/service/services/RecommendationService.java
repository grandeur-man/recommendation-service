package recommendation.service.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import recommendation.service.datasource.RecommendationDatasource;
import recommendation.service.model.CustomerRecommendation;

@Component
public class RecommendationService {

	@Autowired
	private RecommendationDatasource recommendationDatasource;

	/*
	 * Ensure only active records are returned.
	 * Also check that the count requested is not greater that the size of the list of games.
	 * If the count requested is greater return all that the list contains else return the requested count.
	 */
    public CustomerRecommendation getRecommendations(long customerNumber, int count) throws Exception {

    	CustomerRecommendation recommendation = null;
    	CustomerRecommendation customerRecommendation = recommendationDatasource.getRecommendations(customerNumber);
    	if(customerRecommendation != null && customerRecommendation.isActive()) {
    		recommendation = new CustomerRecommendation();
    		recommendation.setCustomerNumber(customerRecommendation.getCustomerNumber());
    		recommendation.setGames(customerRecommendation.getGames());
    		count = count < 3? 3 :count; // The requirement is to display at least 3 games (assuming each record stored has up 3 games.
    		int size = count <= customerRecommendation.getGames().length ? count : customerRecommendation.getGames().length;
    		if(size != customerRecommendation.getGames().length) {
    			String []games = new String [size];
    			System.arraycopy(customerRecommendation.getGames(), 0, games, 0, size);
    			recommendation.setGames(games);
    		}
    	}
        return recommendation; //Ensure that only active record are returned
    }


    
    /*
     * Read the csv file line by line.
     * Skip the first line that contains the title.
     */
    public boolean storeRecommendations(MultipartFile file) throws Exception {
    	
    	boolean storeState = true;
    	BufferedReader reader = null;
    	InputStream inputStream = null;
    	try {

    	     String line;
    	     inputStream = file.getInputStream();
    	     reader = new BufferedReader(new InputStreamReader(inputStream));
    	     int count =0;
    	     List<CustomerRecommendation> list = new ArrayList<>();
    	     while ((line = reader.readLine()) != null) {
    	    	 if(count > 0) {
    	    		 CustomerRecommendation recommendation = createRecommendation(line);
    	    		 if(recommendation != null)   //Ensure only valid records are added
    	    			 list.add(recommendation);
    	    		 else
    	    			 storeState = false;
    	    		 
    	    	 }
    	    	 count++;
    	    		 
    	     }

    	    recommendationDatasource.storeRecommendation(list);
    	  } catch (IOException e) {
    	    System.err.println(e.getMessage()); 
    	    storeState = false;   // This is to ensure that the user is aware that there was a failure at 
    	    					  // some point while parsing the file csv and storing its contents
    	  }
    	finally {
    		if(inputStream != null)
    			inputStream.close(); //Prevent memory leakage.
    		if(inputStream != null)
    			reader.close();
    		
    	}

        return  storeState;


    }


	public RecommendationDatasource getTransactionDatasource() {
		return recommendationDatasource;
	}


	public void setTransactionDatasource(RecommendationDatasource recommendationDatasource) {
		this.recommendationDatasource = recommendationDatasource;
	}

    private CustomerRecommendation createRecommendation(String line) {
		CustomerRecommendation recommendation = new CustomerRecommendation();
		
    	try {
    		//Perform Basic data clean up operations (remove empty and whitespaces)
    		line = line.replaceAll("\"", "");
    		line = line.replaceAll(",,", ",");
    		line = line.replaceAll(" ", "");
    		
        	String [] rowData = line.split(",");
        	int size = rowData.length > 12? 10 : (rowData.length < 12 ? rowData.length - 2: 10); //This is to ensure we do not have empty slots in our list.
        	String [] games = new String[size];
        	System.arraycopy(rowData, 2, games, 0, size);
			recommendation.setActive(!isEmptyString(rowData[1])?Boolean.parseBoolean(rowData[1]):false);
			recommendation.setCustomerNumber(Long.parseLong(rowData[0]));
			recommendation.setGames(games);
		} catch (NumberFormatException e) {
			 System.err.println(e.getMessage());
			 recommendation = null; //If Something breaks while parsing the data, return null so that record on that line will be skipped.
		}
    	
    	return recommendation;
    	
    }
    
    public static boolean isEmptyString(String str)
	{
		return str == null || str.equalsIgnoreCase("") || str.equalsIgnoreCase("null");
	}
}
