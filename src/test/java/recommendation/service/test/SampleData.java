package recommendation.service.test;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class SampleData {

	public static MultipartFile getSampleDataSuccess() {
		
		String data = "\"CUSTOMER_NUMBER\",\"RECOMMENDATION_ACTIVE\",\"REC1\",\"REC2\",\"REC3\",REC4\",\"REC5\",\"REC6\",\"REC7\",\"REC8\",\"REC9\",\"REC10\"\r\n" + 
				"\"111111\",\"true\",\"bingo\",\"cashwheel\",\"cashbuster\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\"\r\n" + 
				"\"111112\",\"false\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\",\"bingo\",\"cashwheel\",\"cashbuster\"\r\n" + 
				"\"111113\",\"true\",\"bingo\",\"cashwheel\",\"cashbuster\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\"\r\n" + 
				"\"111114\",\"false\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\",\"bingo\",\"cashwheel\",\"cashbuster\"\r\n" + 
				"\"111115\",\"true\",\"bingo\",\"cashwheel\",\"cashbuster\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\"\r\n" + 
				"\"111116\",\"false\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\",\"bingo\",\"cashwheel\",\"cashbuster\"\r\n" + 
				"\"111116\",\"true\",\"bingo\",\"cashwheel\",\"cashbuster\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\"\r\n" + 
				"\"111117\",\"true\",\"brilliant\",\"citytrio\"\r\n" + 
				"\"111118\",\"true\",\"bingo\",\"cashwheel\",\"cashbuster\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\"\r\n" + 
				"\"111119\",\"false\",\"brilliant\",\"citytrio\",\"4pic1word\",\"sevenwins\",\"sudoku\"\r\n" + 
				"\"1111110\",\"true\",\"bingo\",\"\",\"cashbuster\",,\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\"\r\n" + 
				"\"1111111\",\"true\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"excessgame\",\"hattrick\",\"bingo\",\"cashwheel\",\"cashbuster\",\"sofortlotto\"\r\n" + 
				"\"1111112\",\"true\",\"bingo\",\"cashwheel\",\"cashbuster\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\"\r\n" + 
				"\"1111113\",\"true\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"\",\"bingo\",\"cashwheel\",\"cashbuster\"\r\n" + 
				"\"1111115\",\"true\",\"bingo\",\"cashwheel\",\"cashbuster\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\"\r\n" + 
				"\"1111115\",\"true\",\"bingo\",\"cashwheel\",\"cashbusters\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\"\r\n" + 
				"\"1111114\",\"false\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",,\"sofortlotto\",\"hattrick\",\"bingo\",\"cashwheel\",\"cashbuster\"";
		
		MultipartFile file = new MockMultipartFile("file", data.getBytes());
		return file;
		
	}
	
	
public static MultipartFile getSampleDataPartialSuccess() {
		
		String data = "\"CUSTOMER_NUMBER\",\"RECOMMENDATION_ACTIVE\",\"REC1\",\"REC2\",\"REC3\",REC4\",\"REC5\",\"REC6\",\"REC7\",\"REC8\",\"REC9\",\"REC10\"\r\n" + 
						"\"1111118\",\"false\",\"bingo\",\"cashwheel\",\"cashbuster\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\"\r\n" + 
						"\"1111117\",\"true\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\",\"bingo\",\"cashwheel\",\"cashbuster\"\r\n" + 
/*Invalid data --->*/  "\"ed1111\",\"true\",\"bingo\",\"cashwheel\",\"cashbuster\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\"\r\n" + 
						"\"1111129\",\"false\",\"brilliant\",\"citytrio\",\"4pic1word\",\"sevenwins\",\"sudoku\"\r\n" + 
						"\"11111120\",\"true\",\"bingo\",\"cashwheel\",\"cashbuster\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\"\r\n" + 
						"\"11111121\",\"true\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"excessgame\",\"hattrick\",\"bingo\",\"cashwheel\",\"cashbuster\",\"sofortlotto\"\r\n"; 
				// total Number of records = 6
		
		MultipartFile file = new MockMultipartFile("file", data.getBytes());
		return file;
		
	}
}
