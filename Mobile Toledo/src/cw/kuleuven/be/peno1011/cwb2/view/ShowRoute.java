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

import org.apache.commons.httpclient.HttpException;
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
import android.widget.ImageView;
import android.widget.Toast;

public class ShowRoute extends MapActivity {
	
	MapView mapView;
	String from;
	String to;
	GeoPoint gpfrom;
	GeoPoint gpto;
	Boolean frombuilding;
	Boolean tobuilding;
	Location location;
	

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
		
		if (from.equals("ownlocation")){
			LocationManager locationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			location = locationmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (location == null){
				Intent i = new Intent(ShowRoute.this,NavigationMenu.class);
				i.putExtra("gps", "geengps");
				startActivity(i);
				finish();
			}
			else{
		    int latitude = (int) location.getLatitude();
		    int longitude = (int) location.getLongitude();
		    GeoPoint gpfrom5 = new GeoPoint((int)(latitude*1E6),(int)(longitude*1E6));
		    gpfrom = gpfrom5;
		    GeoPoint gpto5 = buildingdao.getBuildingCoordinates(to);
		    gpto = gpto5;
		    frombuilding = false;
		    tobuilding = true;
		    }}
	
		
		
		else if (frombuilding == true){
			// navigeer vanaf gebouw
			if (tobuilding==true){
				//navigeer vanaf gebouw naar ander gebouw
				GeoPoint gpfrom1 = buildingdao.getBuildingCoordinates(from);
				GeoPoint gpto1 = buildingdao.getBuildingCoordinates(to);
				gpfrom = gpfrom1;
				gpto = gpto1;
				location = new Location(from);

			}
			else{
				//navigeer vanaf gebouw naar adres
				GeoPoint gpfrom2 = buildingdao.getBuildingCoordinates(from);
				GeoPoint gpto2;
				try {
					gpto2 = NavigationController.getCoordinates(to);
					gpfrom = gpfrom2;
					gpto = gpto2;
					location = new Location(from);
				} catch (IOException e) {
					e.printStackTrace();
					gpto=new GeoPoint(0, 0);
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
					location = new Location(from);
				} catch (IOException e) {
					e.printStackTrace();
					gpto=new GeoPoint(0, 0);
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
						location = new Location(from);
					} catch (IOException e) {
						e.printStackTrace();
						gpto=new GeoPoint(0, 0);
					}

		}}
			
		if(location!=null){
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
		mapView = (MapView) findViewById(R.id.mapview);
		try {
			DrawPath("","",gpfrom,gpto,Color.GREEN,mapView);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ImageView b1 = (ImageView) findViewById(R.id.aangekomen);
		if(tobuilding == true){
		b1.setVisibility(View.VISIBLE);
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String nameto = "";
				Intent intentt = new Intent(ShowRoute.this,GetInfo.class);
				// vraag gebouw op a.d.h.v co�rdinaten
				BuildingDAO buildingdao1 = BuildingDAO.getInstance();
				
				try {
					nameto = buildingdao1.getBuildingNameByCoordinates(gpto);
				} catch (HttpException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Bundle b = new Bundle();
				b.putString("autocomplete_building", nameto);
				b.putBoolean("isbuilding", true);
				intentt.putExtras(b);
				startActivity(intentt);
			}
		});
		}
		
		else{
			b1.setVisibility(View.GONE);
		}

		ImageView b2 = (ImageView) findViewById(R.id.sat);
		b2.setVisibility(View.VISIBLE);
		b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				mapView.setSatellite(true);
			}
		});
		
		ImageView b3 = (ImageView) findViewById(R.id.card);
		b3.setVisibility(View.VISIBLE);
		b3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				mapView.setSatellite(false);
				
			}
		});
		mapView.setBuiltInZoomControls(true);
		mapView.getController().animateTo(gpfrom);
		mapView.getController().setCenter(gpfrom);
		if(frombuilding == true && tobuilding == true){
			mapView.getController().setZoom(20);
		}
		else{
		mapView.getController().setZoom(15);
		}
		mapView.setSatellite(false);
		mapView.invalidate();

 		}
	}
		else{
			Intent i = new Intent(ShowRoute.this,NavigationMenu.class);
			i.putExtra("gps", "geengps");
			startActivity(i);
			finish();
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
		//Vraag de output(reeks co�rdinaten van de route) op in kml file en vraag een wandelroute
		urlString.append("&ie=UTF8&0&om=0&output=kml&dirflg=w");

		Log.d("xxx","URL="+urlString.toString());
		//vraag kml bestand op en haal co�rdinaten van de route eruitt
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
				//maak overlays voor iedere co�rdinaat van de route
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
