package cw.kuleuven.be.peno1011.cwb2.view;

import cw.kuleuven.be.peno1011.cwb2.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class GetInfo extends Activity {
	 
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.getinfo);
	     
	     ImageView image = (ImageView) findViewById(R.id.locationmap);
	     image.setImageResource(R.drawable.toledo_logo);

	}
}

