package cw.kuleuven.be.peno1011.cwb2.view;
import com.google.android.maps.MapActivity;

import cw.kuleuven.be.peno1011.cwb2.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class NavigationMenu extends MapActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.navigationmenu);
		setTitle(R.string.hello);
		
		ImageButton button1 = (ImageButton) findViewById(R.id.ownlocationbutton);
		button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(NavigationMenu.this,GoogleMaps.class);
				startActivity(intent);

			}
		});
		
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
