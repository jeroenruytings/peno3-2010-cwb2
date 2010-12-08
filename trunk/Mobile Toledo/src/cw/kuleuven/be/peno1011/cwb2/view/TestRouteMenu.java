package cw.kuleuven.be.peno1011.cwb2.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import cw.kuleuven.be.peno1011.cwb2.R;



public class TestRouteMenu extends Activity {

	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);	
		setContentView(R.layout.routemenu);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);		
        ((TextView)findViewById(R.id.titlebar)).setText("Een route definiëren");
        
		TextView errorView = (TextView) findViewById(R.id.errorm);
		Bundle b = getIntent().getExtras();
		try{
	        String error = (String) b.get("errortype");
			if (error.equals("fromandto")){
				errorView.setText("Uw startlocatie en bestemming werden niet gevonden");
				errorView.setVisibility(View.VISIBLE);
			}
			else if (error.equals("from")){
				errorView.setText("Uw startlocatie werd niet gevonden");
				errorView.setVisibility(View.VISIBLE);
			}
			else if (error.equals("to")){
				errorView.setText("Uw bestemming werd niet gevonden");
				errorView.setVisibility(View.VISIBLE);
			}
			else if (error.equals("noerror")){}
		}
		catch(NullPointerException ne){
			
		}

		
		ImageButton navigationbutton = (ImageButton) findViewById(R.id.navigate);
		navigationbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Navigate();
			}
		});
		
	}
	
	private void Navigate(){
	String from = "";
	String to = "";
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
			if (svangebouw.equals("")==false && svanadres.equals("")==false){
				Context context = getApplicationContext();
        		CharSequence text = "Gelieve maar één startlocatie op te geven";
        		int duration = Toast.LENGTH_SHORT;
        		Toast toast = Toast.makeText(context, text, duration);
        		toast.show();
			}
			else if (snaargebouw.equals("")==false && snaaradres.equals("")==false){
				Context context = getApplicationContext();
        		CharSequence text = "Gelieve maar één bestemming op te geven";
        		int duration = Toast.LENGTH_LONG;
        		Toast toast = Toast.makeText(context, text, duration);
        		toast.show();
			}
			else if (svangebouw.equals("") && svanadres.equals("")){
				if(snaargebouw.equals("") && snaaradres.equals("")){
					Context context = getApplicationContext();
	        		CharSequence text = "Gelieve een bestemming en vertrekpunt op te geven";
	        		int duration = Toast.LENGTH_SHORT;
	        		Toast toast = Toast.makeText(context, text, duration);
	        		toast.show();
				}
				else{
				Context context = getApplicationContext();
        		CharSequence text = "Gelieve een vertrekpunt op te geven";
        		int duration = Toast.LENGTH_SHORT;
        		Toast toast = Toast.makeText(context, text, duration);
        		toast.show();
			}}
			else if (snaargebouw.equals("") && snaaradres.equals("")){
				Context context = getApplicationContext();
        		CharSequence text = "Gelieve een bestemming op te geven";
        		int duration = Toast.LENGTH_SHORT;
        		Toast toast = Toast.makeText(context, text, duration);
        		toast.show();
			}

			else{
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
		
		Intent intent = new Intent(TestRouteMenu.this,ShowRoute.class);
		Bundle bundle = new Bundle();
		bundle.putString("from", from);
		bundle.putString("to", to);
		bundle.putBoolean("frombuilding", frombuilding);
		bundle.putBoolean("tobuilding", tobuilding);
		intent.putExtras(bundle);
		startActivity(intent);
	}}
}
