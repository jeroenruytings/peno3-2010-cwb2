package cw.kuleuven.be.peno1011.cwb2.view;


import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

import cw.kuleuven.be.peno1011.cwb2.R;

import cw.kuleuven.be.peno1011.cwb2.controller.NavigationController;
import cw.kuleuven.be.peno1011.cwb2.database.BuildingDAO;
import cw.kuleuven.be.peno1011.cwb2.database.RoomDAO;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;




public class LocationInfo extends Activity {
	
	private NavigationController control;

 
		 @Override
		 protected void onCreate(Bundle savedInstanceState) {
		     super.onCreate(savedInstanceState);
		     requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);		
				setContentView(R.layout.infoon);
				getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);		
		        ((TextView)findViewById(R.id.titlebar)).setText("Informatie over een locatie");

		     control = NavigationController.getInstance();
		   
				
			 String [] list1 = control.getBuildingNames();
			 String [] list2 = control.getRoomNames();
			 String[] list;
			 
		if(list1 != null && list2 != null){ 
	   	   list= new String[list1.length+list2.length];
	   	   System.arraycopy(list1, 0, list, 0, list1.length);
	   	   System.arraycopy(list2, 0, list, list1.length, list2.length);
	   	}
		else if(list1 == null){
			list = list2;
		}
		else{
			list = list1;
		}
		     
		     AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_building);
		     ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.autofilllist, list);
		     textView.setAdapter(adapter);
		    		 
    
		Button getinfobutton = (Button) findViewById(R.id.getinfo);
		getinfobutton.setOnClickListener(new View.OnClickListener()	{
			
			@Override
			public void onClick(View arg0) {
				
				AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_building);
				boolean buildingexists = control.buildingExists(textView.getText().toString());
 				boolean roomexists = control.roomExists(textView.getText().toString());
				
				if(textView.getEditableText().toString().equals(""))
					{
            		Context context = getApplicationContext();
            		CharSequence text = "Please, type in a location";
            		int duration = Toast.LENGTH_SHORT;
            		Toast toast = Toast.makeText(context, text, duration);
            		toast.show();
					}
				else if( buildingexists == false && roomexists == false)
					{
            		Context context = getApplicationContext();
            		CharSequence text = "Please, type in a different location";
            		int duration = Toast.LENGTH_SHORT;
            		Toast toast = Toast.makeText(context, text, duration);
            		toast.show();
					}
				
				else
					{
					Bundle b = new Bundle();
					b.putString("autocomplete_building", textView.getText().toString());
					b.putBoolean("isbuilding", buildingexists);
					
					
					Intent intent = new Intent(LocationInfo.this,GetInfo.class);
					intent.putExtras(b);
					startActivity(intent);
					}
				}
			
		});		

	 }
 }
	
	
	


