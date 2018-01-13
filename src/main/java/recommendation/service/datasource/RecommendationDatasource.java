package recommendation.service.datasource;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

import recommendation.service.model.CustomerRecommendation;

/**
 * This Class will be the in-memory store for recommendations
 */
@Component
public class RecommendationDatasource {


	/*
	 * A concurrent hashmap is used because we want write operations to happen in a synchronized manner (thread-safe)
	 * whereas read operations can happen otherwise.
	 */
    private static ConcurrentHashMap<Long, CustomerRecommendation> store = new ConcurrentHashMap<>();

    public CustomerRecommendation getRecommendations(long customerNumber) throws Exception{


        CustomerRecommendation result = store.get(customerNumber);
        return result;
    }

    /*
     * Duplicates will not be rejected in order not to abort the bacth process of storing data.
     * Rather, duplicates will be treated as updates and new values will be used to overwrite the old ones.
     */

    public void storeRecommendation(List<CustomerRecommendation> recommendations) throws Exception{
    	for(CustomerRecommendation customerRecommendation: recommendations) {
    		store.put(customerRecommendation.getCustomerNumber(), customerRecommendation);
    	}
        
    }
    
    public int getStoresize() {
    	return store.size();
    }
    
    public void getClearStore() {
    	store.clear();;
    }
}
