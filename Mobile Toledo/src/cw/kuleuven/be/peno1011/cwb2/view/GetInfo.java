package cw.kuleuven.be.peno1011.cwb2.view;


import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.NavigationController;
import cw.kuleuven.be.peno1011.cwb2.database.BuildingDAO;
import cw.kuleuven.be.peno1011.cwb2.database.RoomDAO;
import cw.kuleuven.be.peno1011.cwb2.model.Building;
import cw.kuleuven.be.peno1011.cwb2.model.GPSLocation;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GetInfo extends Activity {
	 
	private NavigationController control;
	public String location;
	public BuildingDAO dao1;
	public RoomDAO dao2;
	public boolean isbuilding;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);		
		setContentView(R.layout.getinfo);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);		
        ((TextView)findViewById(R.id.titlebar)).setText("Informatie van locatie");
        
	    control = NavigationController.getInstance();
	    Bundle b = this.getIntent().getExtras();
		location = b.getString("autocomplete_building");
		isbuilding = b.getBoolean("isbuilding");
		TextView locationname = (TextView) findViewById(R.id.locationname);
	 	locationname.setText(location);
	 	
	 	Building building = null;
	 	if (isbuilding == true){
	 	dao1 = BuildingDAO.getInstance();
	 	  try {
			building = dao1.getBuilding(location);
	 	  } 
	 	  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	 	  }
	 	}
	 	else{
	 	dao2 = RoomDAO.getInstance();
	 		try {
				building = dao2.getRoom(location).getBuilding();
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 	
	 		
	 	}
		
		
	 	
		TextView adresse = (TextView) findViewById(R.id.adresse);
		String adresseString = "Adres";
		GPSLocation buildinglocation = building.getLocation();
		if (buildinglocation != null){adresseString = buildinglocation.getAdresse();}
	 	adresse.setText(adresseString);
		TextView telephonenr = (TextView) findViewById(R.id.telephonenr);
	 	telephonenr.setText(building.getPhonenumber());
	 	TextView openinghours = (TextView)findViewById(R.id.openinghours);
	 	openinghours.setText(building.getOpeninghours());
	 	
	 	
	 	ImageButton navigatebutton = (ImageButton) findViewById(R.id.locationinfonavigate);
	 	navigatebutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				control = NavigationController.getInstance();
				if (control.buildingExists(location) == false){
					location = control.getBuilding(location);
					
				}
				
				Bundle b = new Bundle();
				b.putString("from", "ownlocation");
				b.putString("to", location);
				b.putBoolean("frombuilding", false);
				b.putBoolean("tobuilding", true);
				Intent intent = new Intent(GetInfo.this,ShowRoute.class);
				intent.putExtras(b);
				startActivity(intent);
				//intent moet doorgegeven worden aan navigeer naar met van eigen locatie
				//naar de locatie meegegeven in de bundle
			}
		});
	 	
	 	
	 	Gallery g = (Gallery) findViewById(R.id.gallery);   
	    g.setAdapter(new ImageAdapter(this));
	

	    g.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	if(isbuilding == true){
		        Toast.makeText(GetInfo.this, "Verdieping " + (position), Toast.LENGTH_SHORT).show();
	        	}
	        	else if(isbuilding == false){
	        	Toast.makeText(GetInfo.this, "Foto " + (position + 1), Toast.LENGTH_SHORT).show();	
	        	}
	        
	        }
	    });
	}

		  	 
	  	class ImageAdapter extends BaseAdapter {

	  		private int mGalleryItemBackground;
	  	    private Context mContext;

	  	    private Bitmap[] mImages = null;
	  	    
	  	    
	  	    
	   	    public ImageAdapter(Context c) {
		  	    mImages = control.getPictureArray(location, isbuilding);
		  	    Bitmap [] notfound = {BitmapFactory.decodeResource(GetInfo.this.getResources(),R.drawable.imagenotfound)};
		  	    if (mImages == null){
		  	    mImages = notfound;
		  	    }
		  	    
	  	        mContext = c;
	  	       	  	    }

	  	    public int getCount() {
	  	    	return mImages.length;
	  	     	
	  	    }

	  	    public Object getItem(int position) {
	  	        return position;
	  	    }

	  	    public long getItemId(int position) {
	  	        return position;
	  	    }

	  	    public View getView(int position, View convertView, ViewGroup parent) {
	  	        ImageView i = new ImageView(mContext);
	  	        	   	        
	  	        i.setLayoutParams(new Gallery.LayoutParams(200, 300));
	  	        i.setScaleType(ImageView.ScaleType.FIT_XY);
	  	        i.setBackgroundResource(mGalleryItemBackground);
	  	        i.setImageBitmap(mImages[position]); 
	  	        
	  	        return i;
	  	    }

		  	}

		    
	}


