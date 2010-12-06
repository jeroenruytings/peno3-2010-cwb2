package cw.kuleuven.be.peno1011.cwb2.view;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.NavigationController;
import cw.kuleuven.be.peno1011.cwb2.database.BuildingDAO;
import cw.kuleuven.be.peno1011.cwb2.model.Document;
import cw.kuleuven.be.peno1011.cwb2.model.MyOverLay;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

public class ShowRoute extends MapActivity {
	
	MapView mapView;
	String from;
	String to;
	GeoPoint gpto;
	Boolean frombuilding;
	Boolean tobuilding;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Stel google map in 
		setContentView(R.layout.googlemaps);
		MapView mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		
		//Vraag bestemming en vertrekpunt op 
		Bundle b = this.getIntent().getExtras();
		from = b.getString("from");
		to = b.getString("to");
		
		//Check of vertrek- en aankomstpunt gebouwen zijn
		frombuilding = b.getBoolean("frombuilding");
		tobuilding = b.getBoolean("tobuilding");
		BuildingDAO buildingdao = BuildingDAO.getInstance();
		if (frombuilding == true){
			// navigeer vanaf gebouw
			if (tobuilding==true){
				//navigeer vanaf gebouw naar ander gebouw
				GeoPoint gpfrom1 = buildingdao.getBuildingCoordinates(from);
				GeoPoint gpto1 = buildingdao.getBuildingCoordinates(to);
				gpto = gpto1;
				try {
					//Teken route
					DrawPath("","",gpfrom1,gpto1,Color.GREEN,mapView);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				//navigeer vanaf gebouw naar adres
				GeoPoint gpfrom2 = buildingdao.getBuildingCoordinates(from);
				try {
					GeoPoint gpto2 = NavigationController.getCoordinates(to);
					gpto = gpto2;
					//Teken route
					DrawPath("","",gpfrom2,gpto2,Color.GREEN,mapView);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}
		else {
			// navigeer vanaf adres
			if(tobuilding == true){
				//navigeer vanaf adres naar gebouw
				GeoPoint gpto3 = buildingdao.getBuildingCoordinates(to);
				try {
					GeoPoint gpfrom3 = NavigationController.getCoordinates(from);
					gpto = gpto3;
					//teken route
					DrawPath("","",gpfrom3,gpto3,Color.GREEN,mapView);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				//navigeer vanaf adres naar adres
				try {
					GeoPoint gpfrom4 = NavigationController.getCoordinates(from);
					GeoPoint gpto4 = NavigationController.getCoordinates(to);
					gpto = gpto4;
					//teken route
					DrawPath("","",gpfrom4,gpto4,Color.GREEN,mapView);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//Toon kaart en beweeg de kaart naar bepaald punt
		mapView.getController().animateTo(gpto);
		mapView.invalidate();

		}



	@Override
	protected boolean isRouteDisplayed() {

	return false;
	}




	//Teken een route
	private void DrawPath(String src, String dest,GeoPoint fromgp, GeoPoint togp, int color, MapView mMapView01) throws IOException
	{
		//Vraag lengte- en breedteligging op van vertrek- en aankomstpunt
		Double fromlat = ((fromgp.getLatitudeE6())/(1E6));
		Double fromlng = ((fromgp.getLongitudeE6())/(1E6));
		Double tolat = ((togp.getLatitudeE6())/(1E6));
		Double tolng = ((togp.getLongitudeE6())/(1E6));
		
	// verbind met de google map route webservice en geef vertrek en aankomst op
		StringBuilder urlString = new StringBuilder();
		urlString.append("http://maps.google.com/maps?f=d&hl=en"); 
		urlString.append("&saddr=");//from
		urlString.append(Double.toString(fromlat));
		urlString.append(",");
	    urlString.append(Double.toString(fromlng));
		urlString.append("&daddr=");//to
		urlString.append(Double.toString(tolat));
		urlString.append(",");
		urlString.append(Double.toString(tolng));
		//Vraag de output(route) op in kml file en vraag een wandelroute
		urlString.append("&ie=UTF8&0&om=0&output=kml&dirflg=w");

		Log.d("xxx","URL="+urlString.toString());
		//vraag kml bestand op en haal coördinaten van de route eruitt
		org.w3c.dom.Document doc = null;
		HttpURLConnection urlConnection= null;
		URL url = null;
		try
			{ 
				url = new URL(urlString.toString());
				urlConnection=(HttpURLConnection)url.openConnection();
				urlConnection.setRequestMethod("GET");
				urlConnection.setDoOutput(true);
				urlConnection.setDoInput(true);
				urlConnection.connect(); 
			
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				doc =  db.parse(urlConnection.getInputStream()); 
			
			if(((org.w3c.dom.Document) doc).getElementsByTagName("GeometryCollection").getLength()>0)
			{
				//String path = doc.getElementsByTagName("GeometryCollection").item(0).getFirstChild().getFirstChild().getNodeName();
				String path = ((org.w3c.dom.Document) doc).getElementsByTagName("GeometryCollection").item(0).getFirstChild().getFirstChild().getFirstChild().getNodeValue() ;
				Log.d("xxx","path="+ path);
				String [] pairs = path.split(" "); 
				String[] lngLat = pairs[0].split(","); // lngLat[0]=longitude lngLat[1]=latitude lngLat[2]=height
				// Maak overlays voor iedere coördinaat
				GeoPoint startGP = new GeoPoint((int)(Double.parseDouble(lngLat[1])*1E6),(int)(Double.parseDouble(lngLat[0])*1E6));
				mMapView01.getOverlays().add(new MyOverLay(startGP,startGP,1));
				GeoPoint gp1;
				GeoPoint gp2 = startGP; 
				for(int i=1;i<pairs.length;i++) 
				{
					lngLat = pairs[i].split(",");
					gp1 = gp2;
					gp2 = new GeoPoint((int)(Double.parseDouble(lngLat[1])*1E6),(int)(Double.parseDouble(lngLat[0])*1E6));
					mMapView01.getOverlays().add(new MyOverLay(gp1,gp2,2,color));
					Log.d("xxx","pair:" + pairs[i]);
				}
				mMapView01.getOverlays().add(new MyOverLay(togp,togp, 3)); 
			} 
		}
	catch (MalformedURLException e)
	{
	e.printStackTrace();
	}
	catch (IOException e)
	{
	e.printStackTrace();
	}
	catch (ParserConfigurationException e)
	{
	e.printStackTrace();
	}
	catch (SAXException e)
	{
	e.printStackTrace();
	}
	}




}
