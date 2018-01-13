package recommendation.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import recommendation.service.model.CustomerRecommendation;
import recommendation.service.services.RecommendationService;

import javax.servlet.http.HttpServletResponse;

@RestController
public class RecommendationController {

    @Autowired
    RecommendationService recommendationService;

 
   
    @RequestMapping(value = "/customers/games/recommendations", method = RequestMethod.POST)
	public void addRecommendationsCsvFile(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws Exception {
		
		if (file != null) {
			boolean storeStatus = recommendationService.storeRecommendations(file);
			response.setStatus(storeStatus ? HttpServletResponse.SC_CREATED : HttpServletResponse.SC_ACCEPTED);
		
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}


    @RequestMapping(method = RequestMethod.GET, value = "/customers/{customerNumber}/games/recommendations", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerRecommendation getStatistics(@PathVariable long customerNumber, @RequestParam(value="count", required=false) int count, HttpServletResponse response ) throws Exception {

    	CustomerRecommendation customerRecommendation = recommendationService.getRecommendations(customerNumber, count);
    	if(customerRecommendation == null)
    		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    	else
    		response.setStatus(HttpServletResponse.SC_OK);
        return customerRecommendation;
        
    }


}
