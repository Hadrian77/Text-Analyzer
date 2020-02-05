import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import org.jsoup.Jsoup;

public class Main {

	public static void main(String[] args) throws IOException {

		List<AnalyzedWord> wordList = new ArrayList<AnalyzedWord>();
		URL readUrl = new URL("http://shakespeare.mit.edu/macbeth/full.html");

		// Uses JSoup library to parse html text to plaintext string
		String plainText = Jsoup.parse(readUrl.openStream(), null, "http://shakespeare.mit.edu/macbeth/full.html")
				.wholeText();

		// Creates new string with no punctuation
		String plainTextNoPunc = plainText.replaceAll("[?.;:,!]", "");

		Scanner scanner = new Scanner(plainTextNoPunc);

		while (scanner.hasNext())

		{
			boolean isNewWord = true;
			String scannedWord = scanner.next();
			
			
			
				
		for (AnalyzedWord listedWord : wordList) {
				
				// Compares scanned word to all listed word to see if it is a duplicate
				if (listedWord.getWord().compareTo(scannedWord) == 0) {

					listedWord.addOccurence();
					isNewWord = false;
					continue;
				}
		
				

				}

		
		// If the word isn't marked as listed/old it is used to create an AnalyzedWord object that is then listed.
		if (isNewWord == true) {

			wordList.add(new AnalyzedWord(scannedWord));
			}

		}
		Collections.sort(wordList);
		System.out.println(wordList);
	
		scanner.close();
		System.out.println("Scanner closed");

	}

}
