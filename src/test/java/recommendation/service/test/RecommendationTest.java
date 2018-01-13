package recommendation.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import recommendation.service.Application;
import recommendation.service.datasource.RecommendationDatasource;
import recommendation.service.model.CustomerRecommendation;
import recommendation.service.services.RecommendationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@DirtiesContext(classMode=ClassMode.BEFORE_EACH_TEST_METHOD)
public class RecommendationTest {


	@Autowired
	RecommendationService recommendationService;
	@Autowired
	RecommendationDatasource recommendationDatasource;
	
	

	@Test
	public void storeRecommendationInformationPartiallySuccessful() throws Exception {
		boolean isSusccess = recommendationService.storeRecommendations(SampleData.getSampleDataPartialSuccess());
		int sizeOfStore = recommendationDatasource.getStoresize();
		
		assertFalse(isSusccess);
		assertEquals(5, sizeOfStore);  //Only 5 records was stored, the bad one was omitted
		recommendationDatasource.getClearStore();
	}
	
	@Test
	public void storeRecommendationInformationSuccessfully() throws Exception {
		boolean isSusccess = recommendationService.storeRecommendations(SampleData.getSampleDataSuccess());
		int sizeOfStore = recommendationDatasource.getStoresize();
		
		assertTrue(isSusccess);
		assertEquals(15, sizeOfStore);  //15 of 17 was stored, The duplicated records were overwritten with values of the later ones
		recommendationDatasource.getClearStore();
	}
	
	@Test
	public void getCustomerRecommendationSuccess() throws Exception {
		 recommendationService.storeRecommendations(SampleData.getSampleDataPartialSuccess());
		CustomerRecommendation customerRecommendation = recommendationService.getRecommendations(1111117, 4);
		
		assertNotNull(customerRecommendation);
		assertEquals("Unexpected response parameter", 1111117, customerRecommendation.getCustomerNumber());
		assertNotNull(customerRecommendation.getGames());
		assertEquals("Unexpected response parameter", 4, customerRecommendation.getGames().length);
		assertEquals("Unexpected response parameter", "brilliant", customerRecommendation.getGames()[0]);
		assertEquals("Unexpected response parameter", "citytrio", customerRecommendation.getGames()[1]);
		assertEquals("Unexpected response parameter", "crossword", customerRecommendation.getGames()[2]);
		assertEquals("Unexpected response parameter", "sevenwins", customerRecommendation.getGames()[3]);
		recommendationDatasource.getClearStore();
	}
	
	/*
	 * The expectation is that at least 3 recommendation be displayed to the customer
	 */
	
	@Test
	public void getCustomerRecommendationwithCountLessThan3() throws Exception {
		
		 recommendationService.storeRecommendations(SampleData.getSampleDataPartialSuccess());

		CustomerRecommendation customerRecommendation = recommendationService.getRecommendations(1111117, 2);
		assertNotNull(customerRecommendation);
		assertEquals("Unexpected response parameter", 1111117, customerRecommendation.getCustomerNumber());
		assertNotNull(customerRecommendation.getGames());
		assertEquals("Unexpected response parameter", 3, customerRecommendation.getGames().length);
		recommendationDatasource.getClearStore();

	}
	
	@Test
	public void getCustomerRecommendationWithCountGreaterThan10() throws Exception {
		 	recommendationService.storeRecommendations(SampleData.getSampleDataPartialSuccess());

			CustomerRecommendation customerRecommendation = recommendationService.getRecommendations(1111117, 20);
			assertNotNull(customerRecommendation);
			assertEquals("Unexpected response parameter", 1111117, customerRecommendation.getCustomerNumber());
			assertNotNull(customerRecommendation.getGames());
			assertEquals("Unexpected response parameter", 10, customerRecommendation.getGames().length);
			recommendationDatasource.getClearStore();
	}
		
//		
	
	@Test
	public void getCustomerRecommendationwithInactiveStatus() throws Exception {
		 	recommendationService.storeRecommendations(SampleData.getSampleDataSuccess());

			CustomerRecommendation customerRecommendation = recommendationService.getRecommendations(111112, 2);
			assertNull(customerRecommendation);
			
			recommendationDatasource.getClearStore();
	}
	
	
	@Test
	public void getCustomerRecommendationCustomerNumberNotPresentInTheDataStore() throws Exception {
		 	recommendationService.storeRecommendations(SampleData.getSampleDataSuccess());

			CustomerRecommendation customerRecommendation = recommendationService.getRecommendations(111111237, 2);
			assertNull(customerRecommendation);
			
			recommendationDatasource.getClearStore();
	}
	
	
	@Test
	public void checkThatDuplicateRecordsOverwritesToKeepTheLaterOne() throws Exception {
		 	recommendationService.storeRecommendations(SampleData.getSampleDataSuccess());

			CustomerRecommendation customerRecommendation = recommendationService.getRecommendations(1111115, 6);
			assertNotNull(customerRecommendation);
			assertEquals("Unexpected response parameter", 1111115, customerRecommendation.getCustomerNumber());
			assertNotNull(customerRecommendation.getGames());
			assertEquals("Unexpected response parameter", 6, customerRecommendation.getGames().length);
			assertEquals("Unexpected response parameter", "cashbusters", customerRecommendation.getGames()[2]);
			recommendationDatasource.getClearStore();
	}
	
	
	@Test
	public void checkThatEmptySpacesAreOmitted() throws Exception {
		 	recommendationService.storeRecommendations(SampleData.getSampleDataSuccess());

			CustomerRecommendation customerRecommendation = recommendationService.getRecommendations(1111110, 20);
			assertNotNull(customerRecommendation);
			assertEquals("Unexpected response parameter", 1111110, customerRecommendation.getCustomerNumber());
			assertNotNull(customerRecommendation.getGames());
			assertEquals("Unexpected response parameter", 8, customerRecommendation.getGames().length);
			recommendationDatasource.getClearStore();
	}
	
	
	@Test
	public void WhenTheNumberOfGamesAreAbove10_EnsureOnly10AreStored() throws Exception {
		 	recommendationService.storeRecommendations(SampleData.getSampleDataSuccess());

			CustomerRecommendation customerRecommendation = recommendationService.getRecommendations(1111111, 20);
			assertNotNull(customerRecommendation);
			assertEquals("Unexpected response parameter", 1111111, customerRecommendation.getCustomerNumber());
			assertNotNull(customerRecommendation.getGames());
			assertEquals("Unexpected response parameter", 10, customerRecommendation.getGames().length);
			recommendationDatasource.getClearStore();
	}
	
	@Test
	public void WhenNoOfGameAreLessThan3InTheStore_ReturnWhatIsAvailable() throws Exception {
		 	recommendationService.storeRecommendations(SampleData.getSampleDataSuccess());

			CustomerRecommendation customerRecommendation = recommendationService.getRecommendations(111117, 20);
			assertNotNull(customerRecommendation);
			assertEquals("Unexpected response parameter", 111117, customerRecommendation.getCustomerNumber());
			assertNotNull(customerRecommendation.getGames());
			assertEquals("Unexpected response parameter", 2, customerRecommendation.getGames().length);
			recommendationDatasource.getClearStore();
	}
	
	@Test
	public void WhenNoOfGameAreLessThan3InTheStoreAndRequestedIsLessThan3_ReturnWhatIsAvailable() throws Exception {
		 	recommendationService.storeRecommendations(SampleData.getSampleDataSuccess());

			CustomerRecommendation customerRecommendation = recommendationService.getRecommendations(111117, 1);
			assertNotNull(customerRecommendation);
			assertEquals("Unexpected response parameter", 111117, customerRecommendation.getCustomerNumber());
			assertNotNull(customerRecommendation.getGames());
			assertEquals("Unexpected response parameter", 2, customerRecommendation.getGames().length);
			recommendationDatasource.getClearStore();
	}
	
}