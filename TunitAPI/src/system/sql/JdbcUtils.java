package system.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import system.date.Date;

public class JdbcUtils {
	
	/**
	 * JDBC Driver methods
	 */
		public static String dbName = "test_results";
		private static String colums = "test_suite, test_name, test_status, test_message, expected_result, actual_result, test_result, error, screenshot, date";
		public final static String CONNECTION_TEST_RESULTS_STRING = "jdbc:mysql://localhost:3306/" + dbName + "?user=root&password=111";
	
		/**
		 * Create new table for suite test results
		 * @param table
		 */
		public void createResultsTable(String table) {
			try {
				Connection connection = DriverManager.getConnection(CONNECTION_TEST_RESULTS_STRING);
				Statement statement = connection.createStatement();
				String strQuery = "CREATE TABLE " + table + " (" +
							 "id int(11) NOT NULL AUTO_INCREMENT," +
							 "test_suite varchar(100) DEFAULT NULL," +
							 "test_name varchar(100) DEFAULT NULL," +
							 "test_status tinyint(1) DEFAULT NULL," +
							 "test_message varchar(100) DEFAULT NULL," +
							 "expected_result varchar(100) DEFAULT NULL," +
							 "actual_result varchar(100) DEFAULT NULL," +
							 "test_result varchar(100) DEFAULT NULL," +
							 "error varchar(200) DEFAULT NULL," +
							 "screenshot varchar(100) DEFAULT NULL," +
							 "date datetime DEFAULT NULL," +
							 " PRIMARY KEY (id) " +
							 " ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;" ;
				statement.executeUpdate(strQuery);
				System.out.println("Table with name " + table + " was successfully created.");
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Insert results to database
		 * @param testSuite
		 * @param testName
		 * @param testStatus
		 * @param testResult
		 * @param message
		 * @param expected
		 * @param actual
		 * @param testError
		 */
		public void insertResultsString (String testSuite, String testName, String testStatus, String testResult, String message, Object expected, Object actual, String testError) {
			//get current date
			String testDate = Date.getCurrentDate();
			String values = "'"+ testSuite + "','" + testName + "','" + testStatus + "','" + message + "','" + expected + "','" + actual + "','" + testResult + "','" + testError + "','" + null + "','" + testDate + "'";
			String testTable = testSuite.replace(".", "_");
			
			//insert result to database
			Connection con = connectionToTestData();
			Statement stmt = createStatment(con);
			String sqlString = "INSERT INTO " + testTable + " (" + colums + ") " + "VALUES (" + values + ");";
			
			try {
				stmt.executeUpdate(sqlString);
			} catch (SQLException e) {
				//handle exception: create table
				if (e.getMessage().contains("Table '" + dbName + "." + testTable + "' doesn't exist")) {
					createResultsTable(testTable);
					try {
						stmt.executeUpdate(sqlString);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			closeStatment(stmt);
			closeConnectionToDB(con);
		}
		
		/**
		 * Create connection to test_data Insert And Close Connection
		 * @param table
		 * @param colums
		 * @param values
		 * @throws SQLException
		 */
		public void insertTestData(String table, String colums, String values) {
			Connection con = connectionToTestData();
			Statement stmt = createStatment(con);
			String sqlString = "INSERT INTO " + table + " (" + colums + ") " + "VALUES (" + values + ");";
			insertIntoDB(stmt, sqlString);
			closeStatment(stmt);
			closeConnectionToDB(con);
		}
		
		/**
		 * Connection to test_data database
		 * @return Statement stmt
		 * @throws SQLException 
		 */

		public Connection  connectionToTestData() {
			return connectionToDB(CONNECTION_TEST_RESULTS_STRING);
		}
		
		/**
		 * Create standart connection statement;
		 * @param con Connection;
		 * @return a new default Statement object ;
		 * @throws SQLException;
		 */

		public Statement createStatment(Connection con) {
			Statement stmt = null;
			try{
				stmt = con.createStatement();
			}
			catch( Exception e ){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
			return  stmt;
		}
		
		/**
		 * Create connection to DB
		 * @return con; 
		 * @throws SQLException;
		 **/
		public Connection connectionToDB(String connectionString) {
			Connection con = null;
			try{
				Class.forName("com.mysql.jdbc.Driver"); //I will use mssql database type 
				con = DriverManager.getConnection(connectionString); //Connection string 
				System.out.println("Opened database successfully");
			}
			catch ( Exception e ){
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
			return con;
		}
		
		/**
		 * Insert to DB
		 * @param stmt
		 * @param sqlString
		 * @throws SQLException 
		 */
		public void insertIntoDB(Statement stmt, String sqlString) {
			try {
				stmt.executeUpdate(sqlString);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Close statement
		 * @stmt
		 * @throws SQLException
		 */
		public void closeStatment(Statement stmt) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		/**
		 * Close conection ToDB
		 * @con 
		 * @throws SQLException 
		 */
		public void closeConnectionToDB(Connection con) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
