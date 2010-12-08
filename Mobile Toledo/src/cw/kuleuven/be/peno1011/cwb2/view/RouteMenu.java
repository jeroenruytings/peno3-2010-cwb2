package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import cw.kuleuven.be.peno1011.cwb2.R;



public class RouteMenu extends Activity {
private Boolean frombuilding;
private Boolean tobuilding;
	
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
		String[] destination = new String[]{"Gebouw","Adres"};
		List<String> destinationType = (List<String>) Arrays.asList(destination);
		Spinner s1 = (Spinner) findViewById(R.id.fromSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, destinationType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(new OnItemSelectedListener() {


			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long id) {
				if (position == 0){
					frombuilding = true;
				}
				else{
					frombuilding = false;
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
	    });

		Spinner s2 = (Spinner) findViewById(R.id.toSpinner);
        s2.setAdapter(adapter);
        s2.setOnItemSelectedListener(new OnItemSelectedListener() {
	

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long id) {
				if (position ==0){
					tobuilding = true;
				}
				else {
					tobuilding = false;
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
	    });

		ImageButton navigationbutton = (ImageButton) findViewById(R.id.navigate);
		navigationbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Navigate();
			}
		});
		
	}
	
	private void Navigate(){

	
	EditText van = (EditText) findViewById(R.id.mfrom);
	EditText naar = (EditText) findViewById(R.id.mto);
	
	String van1 = van.getEditableText().toString();
	String naar1 = naar.getEditableText().toString();
//		EditText vangebouw = (EditText) findViewById(R.id.mvangebouw);
//		EditText vanadres = (EditText) findViewById(R.id.mvanadres);
//		EditText naargebouw = (EditText) findViewById(R.id.mnaargebouw);
//		EditText naaradres = (EditText) findViewById(R.id.mnaaradres);
		
		
//			String svanadres = vanadres.getEditableText().toString();
//			String svangebouw = vangebouw.getEditableText().toString();
//			String snaaradres = naaradres.getEditableText().toString();
//			String snaargebouw = naargebouw.getEditableText().toString();
//			if (svangebouw.equals("")==false && svanadres.equals("")==false){
//				Context context = getApplicationContext();
//        		CharSequence text = "Gelieve maar één startlocatie op te geven";
//        		int duration = Toast.LENGTH_SHORT;
//        		Toast toast = Toast.makeText(context, text, duration);
//        		toast.show();
//			}
//			else if (snaargebouw.equals("")==false && snaaradres.equals("")==false){
//				Context context = getApplicationContext();
//        		CharSequence text = "Gelieve maar één bestemming op te geven";
//        		int duration = Toast.LENGTH_LONG;
//        		Toast toast = Toast.makeText(context, text, duration);
//        		toast.show();
//			}
//			else if (svangebouw.equals("") && svanadres.equals("")){
//				if(snaargebouw.equals("") && snaaradres.equals("")){
//					Context context = getApplicationContext();
//	        		CharSequence text = "Gelieve een bestemming en vertrekpunt op te geven";
//	        		int duration = Toast.LENGTH_SHORT;
//	        		Toast toast = Toast.makeText(context, text, duration);
//	        		toast.show();
//				}
//				else{
//				Context context = getApplicationContext();
//        		CharSequence text = "Gelieve een vertrekpunt op te geven";
//        		int duration = Toast.LENGTH_SHORT;
//        		Toast toast = Toast.makeText(context, text, duration);
//        		toast.show();
//			}}
//			else if (snaargebouw.equals("") && snaaradres.equals("")){
//				Context context = getApplicationContext();
//        		CharSequence text = "Gelieve een bestemming op te geven";
//        		int duration = Toast.LENGTH_SHORT;
//        		Toast toast = Toast.makeText(context, text, duration);
//        		toast.show();
//			}
//
//			else{
//			if (svangebouw.equals("")){
//				from = svanadres;
//				
//			}
//			else{
//				from = svangebouw;
//				frombuilding = true;
//			}
//			if(snaargebouw.equals("")){
//				to = snaaradres;
//			}
//			else{
//				to = snaargebouw; 
//				tobuilding = true;
//			}
		
		Intent intent = new Intent(RouteMenu.this,ShowRoute.class);
		Bundle bundle = new Bundle();
		bundle.putString("from", van1);
		bundle.putString("to", naar1);
		bundle.putBoolean("frombuilding", frombuilding);
		bundle.putBoolean("tobuilding", tobuilding);
		intent.putExtras(bundle);
		startActivity(intent);
	}
}
