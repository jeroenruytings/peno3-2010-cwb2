package cw.kuleuven.be.peno1011.cwb2.view;


import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.NavigationController;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
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
		TextView locationname = (TextView) findViewById(R.id.locationname);
	 	locationname.setText(location);
		TextView adresse = (TextView) findViewById(R.id.adresse);
	 	adresse.setText(control.getAdresse(location));
		TextView telephonenr = (TextView) findViewById(R.id.telephonenr);
	 	telephonenr.setText(control.getTelephonenr(location));
	 	TextView openinghours = (TextView)findViewById(R.id.openinghours);
	 	openinghours.setText(control.getOpeninghours(location));
		
	 	
	 	ImageButton navigatebutton = (ImageButton) findViewById(R.id.locationinfonavigate);
	 	navigatebutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if (control.isBuilding(location) == false){
					location = control.getBuilding(location);
					
				}
				
				Bundle b = new Bundle();
				b.putString("from", "ownloaction");
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
	        	
	        //	if(position == 0){
	        //    Toast.makeText(GetInfo.this, "Locatie", Toast.LENGTH_SHORT).show();
	          //  Toast toast = new Toast(getApplicationContext());
	           // toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
	           // toast.setDuration(Toast.LENGTH_LONG);
	           // toast.setView(v.setLayoutParams(new Gallery.LayoutParams(225, 350)));
	           // toast.show();

	          //	}
	        	if(position ==0){
		        Toast.makeText(GetInfo.this, "On the scene", Toast.LENGTH_SHORT).show();
  		       	}
	        	else{
		        Toast.makeText(GetInfo.this, "Verdieping" + (position-1), Toast.LENGTH_SHORT).show();
	        	}
	        
	        }
	    });

	}	  	 
	  	class ImageAdapter extends BaseAdapter {

	  		private int mGalleryItemBackground;
	  	    private Context mContext;

	  	    private Bitmap[] mImages = control.getPictureArray(location);
	  	    	//control.getPictureArray(location);  

	   	    public ImageAdapter(Context c) {
		  	    
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

	  	             
	  	        i.setImageBitmap(mImages[position]);
	  	        i.setLayoutParams(new Gallery.LayoutParams(200, 300));
	  	        i.setScaleType(ImageView.ScaleType.FIT_XY);
	  	        i.setBackgroundResource(mGalleryItemBackground);

	  	        return i;
	  	    }

		  	}
		    
	}


