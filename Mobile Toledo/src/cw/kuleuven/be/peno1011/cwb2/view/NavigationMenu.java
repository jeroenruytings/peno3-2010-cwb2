package cw.kuleuven.be.peno1011.cwb2.view;
import com.google.android.maps.MapActivity;

import cw.kuleuven.be.peno1011.cwb2.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class NavigationMenu extends MapActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);		
		setContentView(R.layout.navigationmenu);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);		
        ((TextView)findViewById(R.id.titlebar)).setText("Navigatie");
		
		ImageButton button1 = (ImageButton) findViewById(R.id.ownlocationbutton);
		button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(NavigationMenu.this,GoogleMaps.class);
				startActivity(intent);

			}
		});
		
		ImageButton button2 = (ImageButton) findViewById(R.id.navigatebutton);
		button2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent1 = new Intent(NavigationMenu.this,RouteMenu.class);
				intent1.putExtra("errortype", "noerror");
				startActivity(intent1);
				finish();
			}
		});
		
		ImageButton button3 = (ImageButton) findViewById(R.id.locationinfobutton);
		button3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent2 = new Intent(NavigationMenu.this,LocationInfo.class);
				startActivity(intent2);
				finish();
			}
		});
		
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
