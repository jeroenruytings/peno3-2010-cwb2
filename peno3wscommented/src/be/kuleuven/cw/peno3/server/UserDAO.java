package be.kuleuven.cw.peno3.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.json.simple.JSONObject;

import be.kuleuven.cw.peno3.model.User;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

// Onderstaande commando @Path geeft weer op welke locatie de webservicepaginas te bekijken zijn
// in dit geval: http://localhost/9876/UserHandler/methodeNaam
@Path ("/UserHandler")
public class UserDAO {

	protected DatabaseManager manager = DatabaseManager.getInstance();

	@GET
	@Path ("/getUser")
	@Produces ("application/json")
	// Deze methode retourneert een string die bestaat uit users die name in hun naam hebben
	public String getUser(@QueryParam("name") String name){
		String query = "SELECT * FROM user";
		//query = selecteer alle records in de tabel user die name als voor- of achternaam hebben
		if(name !=null)query += " WHERE firstname like '%" + name + "%' or lastname like '%" + name + "%'";
		//print de uitgevoerde query
		System.out.println(query);
		//geef een String van resulaten
		String result = queryForUsers(query);
		//verbreek verbinding met de database
		manager.disconnect();
		return result;
	}

	@GET
	@Path ("/listUsers")
	@Produces ("application/json")
	public String listUsers(){

		String query = "SELECT * FROM user";
		System.out.println(query);
		String result = queryForUsers(query);
		manager.disconnect();
		return result;
	}

	// deze methode zal een MySQLquery uitvoeren op de tabel users en alle informatie gerelateerd aan de
	// resultaten van deze query (ook de informatie verkregen vanuit relationele databases) 
	// retourneren als een string volgens de JSONconventies 
	private String queryForUsers(String query) {
		// een JsonArray is een array van JsonElementen
		JsonArray users = new JsonArray();
		ResultSet rs = manager.query(query);
		//Gson zet javaobjecten om in hun json voorstelling of jsonvoorstellingen om in javaobjecten
		Gson gson = new Gson();
		try {
			// terwijl er nog steeds resultaten aan de query voldoen DOE
			while(rs.next()) {
				// user = de jsonvoorstelling (als een boomstructuur) van de hashmap die een record voorstelt, de hashmap heeft als eerste waarde de kolomnaam, als tweede waarde de overeenkomstige waarde bij die kolomnaam
				JsonObject user = (JsonObject) gson.toJsonTree(manager.getColumnValues(rs));
				// JsonObject.get("X") haalt uit de boomstructuur de waarde die voor deze record in de tabel met naam "X" stond 
				JsonElement jsonElement = user.get("cred_id");
				// als de overeenkomstige waarde in deze tabel niet leeg is
				if (!jsonElement.isJsonNull()) {
					// id = de kolomwaarde van deze record als integer
					int id = jsonElement.getAsInt();
					// query = SELECT * FROM anderedatabasewaarwenaarverwijzen WHERE id=de integerwaarde in deze kolom
					query = "SELECT * FROM credentials WHERE id='" + id + "'";
					// geef alle resultaten die aan query voldoen terug als een JsonArray
					JsonArray result = querySimpleTable(query);
					// als deze array niet leeg is, voeg dan in de json boomstructuur 
					//een extra waarde met: 
					//* als naam de naam van de geneste tabel 
					//* als waarde het eerste element van de JsonArray die de resultaten bevat
					//de boven gespecifieerde JsonArray gaat steeds maar 1 waarde bevatten aangezien id de primaire sleutel is
					if(result.size() >0)user.add("credentials", result.get(0));
				}
				// voeg zolang er nog users zijn deze verder toe aan de JsonArray
				users.add(user);
			}
		//geef de foutmelding weer als er iets verkeerd gaat
		} catch (SQLException e) {
			JSONObject result = new JSONObject();
			result.put("result", "SQLException : (ERR:" + e.getErrorCode() + ") " + e.getMessage());
			return result.toString();
		}
		//retourneer alle elementen die aan de JsonArray werden toegevoegd bij bovenstaande methode
		String asString = users.toString();
		return asString;
	}

	// Deze methode kan gewone SQLquery's uitvoeren (dus zonder relationele records)
	private JsonArray querySimpleTable(String query) {
		System.out.println(query);
		// Vector erft over van de AbstractList klasse
		Vector users = new Vector();
		ResultSet rs = manager.query(query);
		Gson gson = new Gson();
		try {
			while(rs.next()) {
				// Voeg zolang er nog resultaten in de resultset zitten verder queryresultaten aan de users vector toe
				users.add(manager.getColumnValues(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Zet de vector om naar een JsonArray en retourneer deze
		return (JsonArray) gson.toJsonTree(users);
	}

	// methode voegt een gebruiker toe adhv de parameters firstName en lastName
	// public String addUser(String firstName, String lastName)...
	@GET
	@Path ("/addUser")
	@Produces ("application/json")
	public String addUser(@QueryParam("firstname") String firstName,@QueryParam("lastname") String lastName){
		// Een JSONObject (anders dan een JsonObject) is een ongeordende collectie van paren bestaande uit waarden/namen
		// de externe vorm is een String en ziet eruit als: {string1:value1,string2:value2,...}
		// de interne vorm is een object met get() en opt()* methodes (om waarden op te halen adhv de opgegeven naam)
		// en met put()* methode om waarden aan te passen of nieuwe toe te voegen
		// * de opt() methode retourneert een (willekeurig) object opgeslagen bij een als parameter opgegeven key
		// * de put() methode kan aan een bestaande key een nieuwe waarde toekennen, 
		// ze retourneert de vorige waarde geassocieerd met de key, indien de key niet bestond retourneert ze null
		JSONObject result = new JSONObject();
		try {
			// voegt de defaultcredentials in gebruikersnaam: user, paswoord: default
			// retourneert de cred_id van deze record
			int cred_id = insertDefaultCredentials();
			// als de cred_id>-1 => defaultcredentials zijn succesvol ingevoerd,
			// cred_id is namelijk auto_increment
			if (cred_id >-1) {
				// definieer de query
				String query = "INSERT INTO user (firstname,lastname,cred_id) VALUES ('" + firstName + "','" + lastName + "'," + cred_id + ")";
				// print de query
				System.out.println(query);
				// voer de query uit
				manager.update(query);
				// verbreek de verbinding
				manager.disconnect();
			}else {
				// als de cred_id<=-1 => in principe onmogelijk vermits cred_id auto_increment
				//=> er is iets grondig fout, verbreek verbinding
				manager.disconnect();
				// zet bij de key result de String dat er iets misging
				result.put("result", "INSERT of credentials failed");
				// retourneert het JSON object volgens de JSON standaard in een String
				return result.toString();
			}
		} catch (SQLException e) {
			result.put("result", "SQLException : (ERR:" + e.getErrorCode() + ") " + e.getMessage());
			return result.toString();
		}
		// indien geen Exception gecatched noch iets misgegaan, retourneer met trots het resultaat:
		result.put("result", "User "+firstName + " " + lastName+" succefully added.");

		return result.toString();
	}

	// Deze methode 
	// de parameter userJson is een String volgens de JSON standaard
	@POST
	@Path ("/addUser")
	@Produces ("application/json")
	public String addUser(@FormParam("user") String userJson){
		JSONObject result = new JSONObject();
		// als de userJson string niet leeg is DOE
		if(userJson != null) {
			// Gson()fromJson(stringInJson, klasseNaam.class) maakt adhv de Jsonstring een object van de klasse klasseNaam
			User newUser = new Gson().fromJson(userJson, User.class);

			try {
				int cred_id = -1;
				// als de nieuwe gebruiker reeds een Credentialobject heeft DOE
				if(newUser.getCredential() != null) {
					// voeg dan zijn paswoord en login in de tabel credentials adhv wat zit opgeslagen in het Credential object
					cred_id = insertCredentials(newUser.getCredential().getUsername(), newUser.getCredential().getPassword());
				}else {
					// anders voer de defaultcredentials in
					cred_id = insertDefaultCredentials();
				}

				// als cred_id >-1 => altijd het geval als de credentials werden ingevoerd vermits cred_id auto_increment
				// nu kan ook de tabel user aangevuld worden
				if (cred_id >-1) {
					String query = "INSERT INTO user (firstname,lastname,cred_id) VALUES ('" + newUser.getFirstName() + "','" + newUser.getLastName() + "'," + cred_id + ")";
					System.out.println(query);
					manager.update(query);
					manager.disconnect();
				// er is iets grondig fout => geef dit weer
				}else {
					manager.disconnect();
					result.put("result", "INSERT of credentials failed");
					return result.toString();
				}
			} catch (SQLException e) {
				result.put("result", "SQLException : (ERR:" + e.getErrorCode() + ") " + e.getMessage());
				return result.toString();
			}
			result.put("result", "User "+newUser.getFirstName() + " " + newUser.getLastName()+" succefully added.");
		}
		else {
			result.put("result", "User was empty, no User added...");
		}
		return result.toString();
	}
	
	// methode voegt in de credentialstabel een username en een password in
	// en retourneert de cred_id van de laatst toegevoegde credentials
	private int insertCredentials(String username, String password) throws SQLException {
		int cred_id = -1;
		// voeg de credentials van de gebruiker eerst in
		String update = "INSERT INTO credentials (username,password) VALUES ('"+username+"','"+password+"')";
		// selecteer vervolgens de laatst ingevoegde record in credentials
		String query = "SELECT LAST_INSERT_ID() from credentials";
		System.out.println(query);
		// gaat update uitvoeren en vervolgens de laatste query als resultset teruggeven
		ResultSet rs = manager.updateAndQuery(update, query);
		// zet de cursus van de resultset achteraan de set
		rs.last();
		// zet de laatste credentialgegevens om in een HashMap
		HashMap result = manager.getColumnValues(rs);
		// verander de waarde van cred_id naar die van de laatst toegevoegde cred_id
		cred_id = ((Long)result.get("LAST_INSERT_ID()")).intValue();

		return cred_id;
	}

	private int insertDefaultCredentials() throws SQLException {

		return insertCredentials("user", "default");
	}
}
