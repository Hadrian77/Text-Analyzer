import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import org.jsoup.Jsoup;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;




public class Main extends Application {
	
	static List<AnalyzedWord> wordList;

	public static void main(String[] args) throws IOException {
		
		
		wordList = new ArrayList<AnalyzedWord>();
		
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
				if (listedWord.getWord().equalsIgnoreCase(scannedWord) == true) {

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
		launch(args);

	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		
		
		//Setting up window
		Stage window = new Stage();
		window.setHeight(300);
		window.setWidth(400);
		window.setTitle("Text Analyzer");
		
		
		
		
		//Creating First Layout
		StackPane first = new StackPane();
		
		first.setPadding(new Insets(25,25,25,25));
		
		Label intro = new Label("Text Analyzer counts word occurences in Macbeth");
		StackPane.setAlignment(intro,Pos.TOP_CENTER);
		
		Button startButton = new Button("Start Program");
		first.getChildren().addAll(intro,startButton);
		
		//Creating Second Layout
		GridPane second = new GridPane();
		second.setPadding(new Insets(25,25,25,25));
		second.setAlignment(Pos.CENTER_LEFT);
		TextArea text = new TextArea(wordList.toString().replaceAll("[\\],\\[,?.;:,!]", ""));
		text.setMinSize(900, 700);
		second.getChildren().add(text);
		
		//Adding layout to scenes
		Scene scene1 = new Scene(first);
		Scene scene2 = new Scene(second);
	
		//Creating Scene Start and Switch capabilities 
		window.setScene(scene1);
		startButton.setOnAction(new EventHandler<ActionEvent>(){
			
			public void handle(ActionEvent event) {
				
				window.setHeight(800);
				window.setWidth(1000);
				window.setScene(scene2);
			
			
			
			}
		});
		
		
		window.show();
		
	}

}
