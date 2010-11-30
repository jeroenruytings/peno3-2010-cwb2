package cw.kuleuven.be.peno1011.cwb2.view;

import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.NavigationController;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class GetInfo extends Activity {
	 
	private NavigationController control;

	@Override
	 protected void onCreate(Bundle savedInstanceState) {
		
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.getinfo);
		 
		 Bundle b = this.getIntent().getExtras();
		 String location = b.getString("autocomplete_building");
		 TextView locationname = (TextView) findViewById(R.id.locationname);
	  	 locationname.setText(location);
		 
	     control = NavigationController.getInstance();
	     ImageView image = (ImageView) findViewById(R.id.locationmap);
	     image.setImageURI(control.getGoogleMap(location));
	     
	    
	}
}

