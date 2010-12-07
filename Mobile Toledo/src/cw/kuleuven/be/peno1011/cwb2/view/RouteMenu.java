package cw.kuleuven.be.peno1011.cwb2.view;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.maps.GeoPoint;

import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.NavigationController;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class RouteMenu extends Activity {

	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.routemenu);
		
		Button navigationbutton = (Button) findViewById(R.id.navigate);
		navigationbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Navigate();
			}
		});
		
	}
	
	private void Navigate(){
	String from = null;
	String to = null;
	Boolean frombuilding = false;
	Boolean tobuilding = false;
		EditText vangebouw = (EditText) findViewById(R.id.mvangebouw);
		EditText vanadres = (EditText) findViewById(R.id.mvanadres);
		EditText naargebouw = (EditText) findViewById(R.id.mnaargebouw);
		EditText naaradres = (EditText) findViewById(R.id.mnaaradres);
		
		
			String svanadres = vanadres.getEditableText().toString();
			String svangebouw = vangebouw.getEditableText().toString();
			String snaaradres = naaradres.getEditableText().toString();
			String snaargebouw = naargebouw.getEditableText().toString();
			if (svangebouw.equals("") && svanadres.equals("")){
				Context context = getApplicationContext();
        		CharSequence text = "Gelieve een vertrekpunt op te geven";
        		int duration = Toast.LENGTH_SHORT;
        		Toast toast = Toast.makeText(context, text, duration);
        		toast.show();
			}
			else if (snaargebouw.equals("") && snaaradres.equals("")){
				Context context = getApplicationContext();
        		CharSequence text = "Gelieve een bestemming op te geven";
        		int duration = Toast.LENGTH_SHORT;
        		Toast toast = Toast.makeText(context, text, duration);
        		toast.show();
			}
			if (svangebouw.equals("")){
				from = svanadres;
				
			}
			else{
				from = svangebouw;
				frombuilding = true;
			}
			if(snaargebouw.equals("")){
				to = snaaradres;
			}
			else{
				to = snaargebouw; 
				tobuilding = true;
			}
		
		Intent intent = new Intent(RouteMenu.this,ShowRoute.class);
		Bundle bundle = new Bundle();
		bundle.putString("from", from);
		bundle.putString("to", to);
		bundle.putBoolean("frombuilding", frombuilding);
		bundle.putBoolean("tobuilding", tobuilding);
		intent.putExtras(bundle);
		startActivity(intent);
		finish();
	}
}
