import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Test;

class TestAnalyzedWord {

	@Test
	void test() {
		
		String test = "";
		
		try {
			test = HTMLtoPlaintext.parseHtml(new URL("https://en.wikipedia.org/wiki/Limited_government"));
			assertFalse(test.isEmpty());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String test2= "";
		try {
			test2 = HTMLtoPlaintext.parseHtml(new URL("https://www.tutorialspoint.com/junit/junit_test_framework.htm"));
		
			assertFalse(test2.isEmpty());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		String thisFails = "";
		try {
			thisFails = HTMLtoPlaintext.parseHtml(new URL("I sure hope this don't fail"));
		
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertFalse(thisFails.isEmpty());
		
	}
	
	
	
	

}
