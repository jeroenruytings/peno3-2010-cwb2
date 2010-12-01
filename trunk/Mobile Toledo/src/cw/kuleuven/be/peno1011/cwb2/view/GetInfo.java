package cw.kuleuven.be.peno1011.cwb2.view;

import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.NavigationController;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
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
	    setContentView(R.layout.getinfo);
	    Bundle b = this.getIntent().getExtras();
		location = b.getString("autocomplete_building");
		TextView locationname = (TextView) findViewById(R.id.locationname);
	 	locationname.setText(location);

	    Gallery g = (Gallery) findViewById(R.id.gallery);
	    g.setAdapter(new ImageAdapter(this));

	    g.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	
	        	if(position == 0){
	            Toast.makeText(GetInfo.this, "Locatie", Toast.LENGTH_SHORT).show();
	          //  Toast toast = new Toast(getApplicationContext());
	           // toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
	           // toast.setDuration(Toast.LENGTH_LONG);
	           // toast.setView(v.setLayoutParams(new Gallery.LayoutParams(225, 350)));
	           // toast.show();

	          	}
	        	else if(position ==1){
		        Toast.makeText(GetInfo.this, "On the scene", Toast.LENGTH_SHORT).show();
  		       	}
	        	else{
		        Toast.makeText(GetInfo.this, "Verdieping" + (position-2), Toast.LENGTH_SHORT).show();
	        	}
	        
	        }
	    });
	}
		  	 
	  	class ImageAdapter extends BaseAdapter {

	  		private int mGalleryItemBackground;
	  	    private Context mContext;
	  	    private Integer[] mImageIds = {R.drawable.redarrow,R.drawable.mobiletoledo, R.drawable.calendarbutton,R.drawable.imagenotfound};  

	  	    public ImageAdapter(Context c) {
		  	    
	  	        mContext = c;
//	  	        TypedArray a = obtainStyledAttributes(R.styleable.HelloGallery);
//	  	        mGalleryItemBackground = a.getResourceId(R.styleable.HelloGallery_android_galleryItemBackground, 0);
//	  	        a.recycle();
	  	    }

	  	    public int getCount() {
	  	        return mImageIds.length;
	  	    }

	  	    public Object getItem(int position) {
	  	        return position;
	  	    }

	  	    public long getItemId(int position) {
	  	        return position;
	  	    }

	  	    public View getView(int position, View convertView, ViewGroup parent) {
	  	        ImageView i = new ImageView(mContext);

	  	        i.setId(mImageIds[position]);
	  	        i.setLayoutParams(new Gallery.LayoutParams(200, 300));
	  	        i.setScaleType(ImageView.ScaleType.FIT_XY);
	  	        i.setBackgroundResource(mGalleryItemBackground);

	  	        return i;
	  	    }

	  	}
		 
	  //   control = NavigationController.getInstance();
	   //  ImageView image = (ImageView) findViewById(R.id.locationmap);
	    // image.setImageResource(R.drawable.mobiletoledo);
	     //ImageView image2 = (ImageView) findViewById(R.id.locationphoto);
	     //image.setImageResource(R.drawable.redarrow);
	     //  image.setImageURI(control.getGoogleMap(location));
	     
	    
	}

