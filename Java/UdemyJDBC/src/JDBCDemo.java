import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {

	public static void main(String[] args) {
		
		
		//Az employees_database mi hoztuk létre a mysql workbench IDE-ben
		//A jdbc:mysql://127.0.0.1:3307/employees_database rész az amit nekünk be kellett állítani, az utána lévõ azért kellett mert anélkül ebben az esetben elszállt hibával (nem volt egyértelmû a szerver pontos ideje) (kitudja miért számít az..)
		String url = "jdbc:mysql://127.0.0.1:3307/employees_database?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		
		int rowsAffected;
		
		try {
			//establish connection object (root és a password a felhasználónevünk és jelszavunk amit az adatbázisnál megadtunk) (azt nem javában, hanem a mysql workbench IDE-ben csináltuk)
			//A felhasználó és jelszó is mehetett volna kint egy változóban (és úgy is lenne érdemes), de most ezt kihagytuk
			Connection conn = DriverManager.getConnection(url, "root", "password123");
			
			//create a statement object to send to database
			Statement statement = conn.createStatement();
			
			//execute the statement object
			
			ResultSet resultSet = statement.executeQuery("select * from employees_tbl");
			
			//process the result
			
			int salaryTotal = 0;
			while(resultSet.next()) {
				//System.out.println(resultSet.getString("name") + " | " + resultSet.getString("dept") + " | " + resultSet.getInt("salary"));
				//salaryTotal += resultSet.getInt("salary");
				salaryTotal += Integer.parseInt(resultSet.getString("salary")); //Ez is használható, de így át kellett konvertálni
			}
			
			//System.out.println(salaryTotal);
			
			//Visszaadja hogy hány sort érintett a parancs az adatbázisban
//			rowsAffected = statement.executeUpdate("insert into employees_tbl (id, name, dept, salary) "
//					+ "values(900, 'Robert', 'Sales', 4000);");
//			
//			System.out.println("Executed an insert statement - Rows affected: " + rowsAffected);
			
//			rowsAffected = statement.executeUpdate("delete from employees_tbl where id = 900;");
//			
//			System.out.println("Executed a delete statement - Rows affected: " + rowsAffected);
			
//			rowsAffected = statement.executeUpdate("update employees_tbl set salary = 5500 where id = 600;");
//			
//			System.out.println("Executed an update statement - Rows affected: " + rowsAffected);
			
			System.out.println("Minden ok");
			
			
			
			
		} catch (SQLException e) {
			System.out.println("Error");
		}

	}

}
