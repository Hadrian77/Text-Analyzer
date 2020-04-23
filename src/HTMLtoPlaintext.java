import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;

/**
 * 
 * 
 * 
 * 
 *  This class converts HTML text to plain text, with no punctuation or HTML tags. 
 *
 */


public class HTMLtoPlaintext {

	/**
	 * This method uses the JSOUP API and a regular expression to remove HTML coding, then punctuation.
	 * @see org.jsoup.Jsoup
	 * @param url The URL containing the HTML code that is to be parsed into plain text.
	 * @return A String containing plain text, with no punctuation or HTML tags
	 */
	
	public static String parseHtml(URL url) {
		
		
		
		String plainText = null;
		try {
			plainText = Jsoup.parse(url.openStream(), null, url.getPath())
					.wholeText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Creates new string with no punctuation
		String plainTextNoPunc = plainText.replaceAll("[?.;:,!|-]", "");

		
		
		return plainTextNoPunc;
		
	}
	
}
