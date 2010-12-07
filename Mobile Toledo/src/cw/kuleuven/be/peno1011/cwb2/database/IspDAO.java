package cw.kuleuven.be.peno1011.cwb2.database;


import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import com.google.android.maps.GeoPoint;
import com.google.gson.Gson;

import cw.kuleuven.be.peno1011.cwb2.model.ISP;

public class IspDAO {
	
	private static IspDAO ispDAO;
	private Cryptography cryptography = Cryptography.getInstance();
	
	// Singleton has a private constructor
	private IspDAO(){
		
	}
	
	public static IspDAO getInstance() {
		if (ispDAO == null){
			ispDAO = new IspDAO();
		}
		return ispDAO;
	}
	
	public ISP getIsp(String username) throws HttpException, IOException{
		ISP isp;		
		
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod("http://ariadne.cs.kuleuven.be/peno-cwb2/IspHandler/getIspByUserId");
		method.addParameter("userId", username);
		
		int response = client.executeMethod(method);
		String encryptedJson = method.getResponseBodyAsString();
		String json = cryptography.decrypt(encryptedJson);
		
		if(json.contains("[]")) {
			isp = null;
		}
		else {
			ISP[] ispList = new Gson().fromJson(json.toString(), ISP[].class);
			isp = ispList[0];
		}
		return isp;
	}
}