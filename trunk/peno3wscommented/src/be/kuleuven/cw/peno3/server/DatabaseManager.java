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

	//DatbaseManager is dus een singleton
	protected DatabaseManager() {

		PropertiesManager propertiesManager = PropertiesManager.getInstance();

		url = "jdbc:mysql://" + propertiesManager.getProperty("mysql.host") + ":" + propertiesManager.getProperty("mysql.port") + "/" + propertiesManager.getProperty("mysql.db");
		username = propertiesManager.getProperty("mysql.user");
		password = propertiesManager.getProperty("mysql.pass");


	}
	
	//getInstance() methode van de singleton
	public static DatabaseManager getInstance(){
		if(manager == null)manager = new DatabaseManager();
		return manager;
	}

	//deze methode maakt verbinding met de database
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

	// deze methode verbreekt de verbinding met de database
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

	// een query is een mySQLactie/voorwaarde zoals bijvoorbeeld SELECT * FROM tabelnaam WHERE kolomnaam = waarde
	// een query definieert dus op welke records van de tabel de MySQLactie uitgevoerd zal worden
	// deze methode retourneert dus een resultset die de resultaten van de uitgevoerde query bevat
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
	
	// deze methode kan INSERT, DELETE of UPDATE uitvoeren
	public int update(String query) throws SQLException{
		if(con==null) connect();
		return con.createStatement().executeUpdate(query);
	}
	
	//deze methode kan INSERT, DELETE of UPDATE uitvoeren
	//daarnaast is ze in staat een resultset van de gegeven query te retourneren
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
	// De geretourneerde hashmap stelt een record voor, ze ziet er bijvoorbeeld als volgt uit:
	//	firstname	Stijn
	//	lastname	Meul
	//	birthdate	07/05/1991 enz.
	// Elke record in de opgegeven resultset wordt als een dergelijke HashMap geretourneerd
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
