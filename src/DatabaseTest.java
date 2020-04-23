import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

class DatabaseTest {

	@Test
	void test() {
		
		Connection conn = Database.getConnection();
		try {
			Statement drop = conn.createStatement();
			drop.executeUpdate("DELETE FROM wordoccurrences");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
