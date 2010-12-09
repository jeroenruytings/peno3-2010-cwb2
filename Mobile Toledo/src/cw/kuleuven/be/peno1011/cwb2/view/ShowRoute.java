package cw.kuleuven.be.peno1011.cwb2.view;

import java.io.IOException;
import android.location.LocationManager;
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
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.NavigationController;
import cw.kuleuven.be.peno1011.cwb2.database.BuildingDAO;
import cw.kuleuven.be.peno1011.cwb2.model.Document;
import cw.kuleuven.be.peno1011.cwb2.model.MyOverLay;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ShowRoute extends MapActivity {
	
	MapView mapView;
	String from;
	String to;
	GeoPoint gpfrom;
	GeoPoint gpto;
	Boolean frombuilding;
	Boolean tobuilding;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		//Vraag bestemming en vertrekpunt op 
		Bundle b = this.getIntent().getExtras();
		from = b.getString("from");
		to = b.getString("to");
		
		//Check of vertrek- en aankomstpunt gebouwen zijn
		frombuilding = b.getBoolean("frombuilding");
		tobuilding = b.getBoolean("tobuilding");
		
		BuildingDAO buildingdao = BuildingDAO.getInstance();
		
		if (from =="ownlocation"){
			LocationManager locationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			Location location = locationmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		    int latitude = (int) location.getLatitude();
		    int longitude = (int) location.getLongitude();
		    GeoPoint gpfrom5 = new GeoPoint((int)(latitude*1E6),(int)(longitude*1E6));
		    gpfrom = gpfrom5;
		    GeoPoint gpto5 = buildingdao.getBuildingCoordinates(to);
		    gpto = gpto5;
		
		}
		else if (frombuilding == true){
			// navigeer vanaf gebouw
			if (tobuilding==true){
				//navigeer vanaf gebouw naar ander gebouw
				GeoPoint gpfrom1 = buildingdao.getBuildingCoordinates(from);
				GeoPoint gpto1 = buildingdao.getBuildingCoordinates(to);
				gpfrom = gpfrom1;
				gpto = gpto1;

			}
			else{
				//navigeer vanaf gebouw naar adres
				GeoPoint gpfrom2 = buildingdao.getBuildingCoordinates(from);
				GeoPoint gpto2;
				try {
					gpto2 = NavigationController.getCoordinates(to);
					gpfrom = gpfrom2;
					gpto = gpto2;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		else {
			// navigeer vanaf adres
			if(tobuilding == true){
				//navigeer vanaf adres naar gebouw
				GeoPoint gpto3 = buildingdao.getBuildingCoordinates(to);
				GeoPoint gpfrom3;
				try {
					gpfrom3 = NavigationController.getCoordinates(from);
					gpfrom = gpfrom3;
					gpto = gpto3;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			else{
				//navigeer vanaf adres naar adres
					GeoPoint gpfrom4;
					try {
						gpfrom4 = NavigationController.getCoordinates(from);
						GeoPoint gpto4 = NavigationController.getCoordinates(to);
						gpfrom = gpfrom4;
						gpto = gpto4;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		}}
			
		if(gpto.getLatitudeE6() == 0 && gpto.getLongitudeE6() == 0){
			if(gpto.getLatitudeE6() == 0 && gpto.getLongitudeE6() == 0 && gpfrom.getLatitudeE6() == 0 && gpfrom.getLongitudeE6() == 0){
			Intent i = new Intent(ShowRoute.this, RouteMenu.class);
			i.putExtra("errortype", "fromandto");
			startActivity(i);
			finish();
			}
			else{
				Intent i = new Intent(ShowRoute.this, RouteMenu.class);
				i.putExtra("errortype","to");
				startActivity(i);
				finish();
			}
		}
		else if(gpfrom.getLatitudeE6() == 0 && gpfrom.getLongitudeE6() == 0){
			Intent i = new Intent(ShowRoute.this, RouteMenu.class);
			i.putExtra("errortype", "from");
			startActivity(i);
			finish();
		}
		else{
		//Toon kaart en beweeg de kaart naar bepaald punt
		setContentView(R.layout.googlemaps);
		MapView mapView = (MapView) findViewById(R.id.mapview);
		try {
			DrawPath("","",gpfrom,gpto,Color.GREEN,mapView);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mapView.setBuiltInZoomControls(true);
		mapView.getController().animateTo(gpfrom);
		if(frombuilding == true && tobuilding == true){
			mapView.getController().setZoom(20);
		}
		else{
		mapView.getController().setZoom(15);
		}
		mapView.invalidate();

 		}}



	@Override
	protected boolean isRouteDisplayed() {

	return false;
	}




	//Teken een route
	private void DrawPath(String src, String dest,GeoPoint fromgp, GeoPoint togp, int color, MapView map) throws IOException
	{
		//Vraag lengte- en breedteligging op van vertrek- en aankomstpunt
		Double fromlat = ((fromgp.getLatitudeE6())/(1E6));
		Double fromlng = ((fromgp.getLongitudeE6())/(1E6));
		Double tolat = ((togp.getLatitudeE6())/(1E6));
		Double tolng = ((togp.getLongitudeE6())/(1E6));
		 
	// verbind met de google map route webservice
		StringBuilder urlString = new StringBuilder();
		urlString.append("http://maps.google.com/maps?f=d&hl=en"); 
		//geef lengte- en breedteligging van vertrekpunt op
		urlString.append("&saddr=");
		urlString.append(Double.toString(fromlat));
		urlString.append(",");
	    urlString.append(Double.toString(fromlng));
	    //Geef lengte- en breedteligging van bestemming op
		urlString.append("&daddr=");
		urlString.append(Double.toString(tolat));
		urlString.append(",");
		urlString.append(Double.toString(tolng));
		//Vraag de output(reeks coördinaten van de route) op in kml file en vraag een wandelroute
		urlString.append("&ie=UTF8&0&om=0&output=kml&dirflg=w");

		Log.d("xxx","URL="+urlString.toString());
		//vraag kml bestand op en haal coördinaten van de route eruitt
		org.w3c.dom.Document document = null;
		HttpURLConnection connectie= null;
		URL url = null;
		try
			{  //Verbind met de URL
				url = new URL(urlString.toString());
				connectie=(HttpURLConnection)url.openConnection();
				connectie.setRequestMethod("GET");
				connectie.setDoOutput(true);
				connectie.setDoInput(true);
				connectie.connect(); 
			
				DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentbuilder = documentbuilderfactory.newDocumentBuilder();
				document =  documentbuilder.parse(connectie.getInputStream()); 
			
			if(((org.w3c.dom.Document) document).getElementsByTagName("GeometryCollection").getLength()>0)
			{
				//String path = doc.getElementsByTagName("GeometryCollection").item(0).getFirstChild().getFirstChild().getNodeName();
				String route = ((org.w3c.dom.Document) document).getElementsByTagName("GeometryCollection").item(0).getFirstChild().getFirstChild().getFirstChild().getNodeValue() ;
				Log.d("xxx","route="+ route);
				String [] koppels = route.split(" "); 
				String[] latenlng = koppels[0].split(","); 
				// latenlng[0]=lengteligging, latenlng[1]=breedteligging, latenlng[2]=hoogte
				GeoPoint begin = new GeoPoint((int)(Double.parseDouble(latenlng[1])*1E6),(int)(Double.parseDouble(latenlng[0])*1E6));
				map.getOverlays().add(new MyOverLay(begin,begin,1));
				GeoPoint gp1;
				GeoPoint gp2 = begin; 
				//maak overlays voor iedere coördinaat van de route
				for(int i=1;i<koppels.length;i++) 
				{
					latenlng = koppels[i].split(",");
					gp1 = gp2;
					gp2 = new GeoPoint((int)(Double.parseDouble(latenlng[1])*1E6),(int)(Double.parseDouble(latenlng[0])*1E6));
					map.getOverlays().add(new MyOverLay(gp1,gp2,2,color));
					Log.d("xxx","pair:" + koppels[i]);
				}
				//zet de bestemming op kaart
				map.getOverlays().add(new MyOverLay(togp,togp, 3)); 
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
