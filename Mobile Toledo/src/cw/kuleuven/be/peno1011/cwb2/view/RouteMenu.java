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
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.NavigationController;



public class RouteMenu extends Activity {
private Boolean frombuilding;
private Boolean tobuilding;
private NavigationController control;
private String[] list1;
private String[] list2;

	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);	
		setContentView(R.layout.routemenu);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);		
        ((TextView)findViewById(R.id.titlebar)).setText("Een route defini�ren");
        
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
		
		
		
		///////////////////////////
		
		control = NavigationController.getInstance();
		final String [] list = control.getBuildingNames();
		final String [] list0 = {""};	
		
		list1 = list;
		list2 = list;
		
				
		AutoCompleteTextView textViewFrom = (AutoCompleteTextView) findViewById(R.id.mfrom);
		final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.autofilllist, list1);
		textViewFrom.setAdapter(adapter1);
		
		AutoCompleteTextView textViewTo = (AutoCompleteTextView) findViewById(R.id.mto);
		final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.autofilllist, list2);
		textViewTo.setAdapter(adapter2);
		
		
		///////////////////////
		
		
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
					list1 = list;	
					AutoCompleteTextView textViewFrom = (AutoCompleteTextView) findViewById(R.id.mfrom);
					ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(RouteMenu.this, R.layout.autofilllist, list1);
					textViewFrom.setAdapter(adapter1);
					
				}
				else{
					frombuilding = false;
					list1 = list0;
					AutoCompleteTextView textViewFrom = (AutoCompleteTextView) findViewById(R.id.mfrom);
					ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(RouteMenu.this, R.layout.autofilllist, list1);
					textViewFrom.setAdapter(adapter1);
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
					list2 = list;
					AutoCompleteTextView textViewTo = (AutoCompleteTextView) findViewById(R.id.mto);
					ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(RouteMenu.this, R.layout.autofilllist, list2);
					textViewTo.setAdapter(adapter2);
				}
				else {
					tobuilding = false;
					list2 = list0;
					AutoCompleteTextView textViewTo = (AutoCompleteTextView) findViewById(R.id.mto);
					ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(RouteMenu.this, R.layout.autofilllist, list2);
					textViewTo.setAdapter(adapter2);
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

	
	AutoCompleteTextView van = (AutoCompleteTextView) findViewById(R.id.mfrom);
	AutoCompleteTextView naar = (AutoCompleteTextView) findViewById(R.id.mto);
	
	String van1 = van.getEditableText().toString();
	String naar1 = naar.getEditableText().toString();
	
	if (van1.equals("")){
		if (naar1.equals("")){
			Context context = getApplicationContext();
    		CharSequence text = "Gelieve een startlocatie en bestemming op te geven";
    		int duration = Toast.LENGTH_LONG;
    		Toast toast = Toast.makeText(context, text, duration);
    		toast.show();
		}
		else{
		Context context = getApplicationContext();
		CharSequence text = "Gelieve een startlocatie op te geven";
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}}
	else if (naar1.equals("")){
		Context context = getApplicationContext();
		CharSequence text = "Gelieve een bestemming op te geven";
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
	else{
		
		Intent intent = new Intent(RouteMenu.this,ShowRoute.class);
		Bundle bundle = new Bundle();
		bundle.putString("from", van1);
		bundle.putString("to", naar1);
		bundle.putBoolean("frombuilding", frombuilding);
		bundle.putBoolean("tobuilding", tobuilding);
		intent.putExtras(bundle);
		startActivity(intent);
	}}
	

}

