import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
	
	
private Connection conn;	
	
public static Connection getConnection() {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String path = "jdbc:mysql://localhost:3306/textAnalyzer";
	String user = "root";
	String password = "Thursday77";
	try {
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(path,user,password);
		return conn;
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return null;
	
	
	
	
	
}



	

}
