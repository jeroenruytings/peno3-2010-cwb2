package be.kuleuven.cw.peno3.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.ariadne.config.PropertiesManager;

public class DatabaseManager {

	protected static DatabaseManager manager;

	private String url;

	private String username;

	private String password;

	private Connection con;

	protected DatabaseManager() {

		PropertiesManager propertiesManager = PropertiesManager.getInstance();

		url = "jdbc:mysql://" + propertiesManager.getProperty("mysql.host") + ":" + propertiesManager.getProperty("mysql.port") + "/" + propertiesManager.getProperty("mysql.db");
		username = propertiesManager.getProperty("mysql.user");
		password = propertiesManager.getProperty("mysql.pass");


	}

	public static DatabaseManager getInstance(){
		if(manager == null)manager = new DatabaseManager();
		return manager;
	}

	/**
	 * Makes the connection to our MySQL database
	 */
	public void connect(){
		//      stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = (Connection) DriverManager.getConnection(url,username, password);
			//          stmt = (Statement) con.createStatement();
			//Display URL and connection information
			//          System.out.println("URL: " + url);
			//          System.out.println("Connection: " + con);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Disconnects from our MySQL database
	 */
	public void disconnect() {
		try {
			if(con != null){
				con.close();
				con = null;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Executes a query
	 * 
	 * @param query the query
	 * @return resultset containing records which satisfied the query
	 */
	public ResultSet query(String query){
		if(con==null) connect();
		//      System.out.println(query);
		try {
			return con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
		return null;
	}

	/**
	 * Updates the given query in our MySQL database
	 * 
	 * @param query the query
	 * @return
	 * @throws SQLException
	 */
	public int update(String query) throws SQLException{
		if(con==null) connect();
		return con.createStatement().executeUpdate(query);
	}
	
	public ResultSet updateAndQuery(String update, String query) throws SQLException{
		if(con==null) connect();
		Statement stmt = con.createStatement();
		stmt.executeUpdate(update);
		return stmt.executeQuery(query);
	}

	/**
	 * Since the columns should only be read once, copy them into a
	 * HashMap and consider that to be the "record"
	 * @param rs The ResultSet row
	 * @return a HashMap mapping column names with values
	 */
	@SuppressWarnings("unchecked")
	public static HashMap getColumnValues(ResultSet rs) {

		HashMap<String, Object> nativeItem = new HashMap<String, Object>();
		try {
			ResultSetMetaData mdata = rs.getMetaData();
			int count = mdata.getColumnCount();

			for (int i=1; i<=count; ++i) {
				String fieldName = new StringBuffer().append(mdata.getColumnName(i)).toString();
				//          String fieldName = new StringBuffer().append(mdata.getTableName(i)).append(".").append(mdata.getColumnName(i)).toString();
				nativeItem.put(fieldName, rs.getObject(i));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nativeItem;
	}


}
