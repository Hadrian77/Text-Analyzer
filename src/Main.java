import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;




public class Main extends Application {
	
	
	
	
/**
 * 
 * Main function for program that counts and displays the amount of times each word appears in Macbeth appears.
 * 
 * 
 * 
 */
	public static void main(String[] args) throws IOException {
	
		launch(args);


	}

	@Override
	public void start(Stage arg0) throws Exception {
	
		
		
		//Setting up first window
		Text introText = new Text("Insert a URL containing HTML code to be word-counted");
		TextField urlTextField = new TextField("http://www.shakespeare-online.com/plays/macbeth_1_1.html");
		Button startButton = new Button("Start");
		BorderPane pane1 = new BorderPane();
		pane1.setPadding(new Insets(60,60,60,60));
		pane1.setTop(introText);
		pane1.setCenter(urlTextField);
		pane1.setBottom(startButton);
		BorderPane.setAlignment(introText,Pos.BOTTOM_CENTER);
		BorderPane.setAlignment(urlTextField,Pos.CENTER);
		BorderPane.setAlignment(startButton,Pos.BOTTOM_CENTER);
		Scene scene1 = new Scene(pane1,400,400);		
		Stage primaryStage = new Stage();
		primaryStage.setTitle("Text Analyzer");
		primaryStage.setScene(scene1);
		primaryStage.show();
		
		
		TextArea outputText = new TextArea("Please Wait");
		Button restartButton = new Button("Restart");
		BorderPane outputLayout = new BorderPane();
		outputLayout.setPadding(new Insets(100,100,100,100));
		outputLayout.setCenter(outputText);
		outputLayout.setBottom(restartButton);
		BorderPane.setAlignment(outputText, Pos.CENTER);
		BorderPane.setAlignment(restartButton, Pos.BOTTOM_CENTER);
		
		Scene scene2 = new Scene(outputLayout,800,800);
		
		
	
		
		startButton.setOnAction(actionEvent ->  {
			   
			
			URL url = null;
			
			try {
				
				url = new URL(urlTextField.getText());
				
			} catch (MalformedURLException e1) {
				
				Alert nanAlert = new Alert(AlertType.ERROR);
				nanAlert.setTitle("Error");
				nanAlert.setHeaderText("Invalid URL");
				nanAlert.setContentText("URL is Invalid, please try again\nURL should end in .html");

				nanAlert.showAndWait();
				
				
			}

			

			Scanner scanner = new Scanner(HTMLtoPlaintext.parseHtml(url));
			
			//Connects to database
			Connection conn = Database.getConnection();

			while (scanner.hasNext())

			{
				//boolean isNewWord = true;
				String scannedWord = scanner.next();
				System.out.println(scannedWord);
				
				try {
					PreparedStatement getData = conn.prepareStatement("SELECT word, occurrences FROM wordoccurrences WHERE word = ?",PreparedStatement.RETURN_GENERATED_KEYS);
					getData.setString(1,scannedWord);
					PreparedStatement addOccurrence = conn.prepareStatement("UPDATE wordoccurrences SET occurrences =  occurrences + 1 WHERE word = ?",PreparedStatement.RETURN_GENERATED_KEYS);
					addOccurrence.setString(1,scannedWord);
					PreparedStatement addWord = conn.prepareStatement("INSERT INTO wordoccurrences (word,occurrences) VALUES (?,1)", PreparedStatement.RETURN_GENERATED_KEYS);
					addWord.setString(1,scannedWord);
					ResultSet rs = getData.executeQuery() ;
					
					
					if(rs.next()) {
						
						addOccurrence.execute();
						
					}
					
					else {
						
						addWord.execute();
						
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
				
				primaryStage.setScene(scene2);
				
				
				try {
				Statement showAll = conn.createStatement();
			
				ResultSet show = showAll.executeQuery("SELECT * FROM wordoccurrences");
				
				
				   ResultSetMetaData showmd = show.getMetaData();
				   int columnsNumber = showmd.getColumnCount();
				   while (show.next()) {
				       for (int i = 1; i <= columnsNumber; i+=3) {
				           
				           String columnValue ="Word: \"" +show.getString(i+1) + "\"   Occurrences: " + show.getString(i+2);
				          outputText.appendText(columnValue + "\n");
				       }
				   }
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// Deletes all previous entries from database
				
				try {
					Statement drop = conn.createStatement();
					drop.executeUpdate("DELETE FROM wordoccurrences");
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			
			
			
		});
		
		
		 restartButton.setOnAction(actionEvent ->  {
		   
			primaryStage.setScene(scene1);
			
			
		
		});
		
		
		
		
		
		
			}
	
	
	

}
