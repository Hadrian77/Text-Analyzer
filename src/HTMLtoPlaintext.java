import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;

public class HTMLtoPlaintext {

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
		String plainTextNoPunc = plainText.replaceAll("[?.;:,!]", "");

		
		
		return plainTextNoPunc;
		
	}
	
}
