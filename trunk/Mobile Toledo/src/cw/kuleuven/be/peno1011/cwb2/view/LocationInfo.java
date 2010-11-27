package cw.kuleuven.be.peno1011.cwb2.view;
import java.lang.reflect.Array;

import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.NavigationController;
import cw.kuleuven.be.peno1011.cwb2.model.Building;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


public class LocationInfo extends Activity {
	private NavigationController control;
 
		 @Override
		 protected void onCreate(Bundle savedInstanceState) {
		     super.onCreate(savedInstanceState);
		     setContentView(R.layout.infoon);

		     control = NavigationController.getInstance();
		     AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_building);
		     ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.autofilllist, control.getBuildingNames());
		     textView.setAdapter(adapter);
		 
    
		Button getinfobutton = (Button) findViewById(R.id.getinfo);
		getinfobutton.setOnClickListener(new View.OnClickListener()	{
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LocationInfo.this,GetInfo.class);
				startActivity(intent);
			}
		});		

	//	 getinfobutton.setOnClickListener(new View.OnClickListener() { 

		//	 @Override
		//	 public void onClick(View v) {
			// TODO Auto-generated method stub
			//	String name = locationedit.getText().toString();
		//		String location = String.format(getString(R.string.location),name);
				
		 }
		 }
	
	
	


