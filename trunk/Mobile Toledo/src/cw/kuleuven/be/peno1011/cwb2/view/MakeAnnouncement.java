package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.ArrayList;
import java.util.List;

import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.InfoController;
import cw.kuleuven.be.peno1011.cwb2.controller.MainController;
import cw.kuleuven.be.peno1011.cwb2.database.AnnouncementDAO;
import cw.kuleuven.be.peno1011.cwb2.model.Course;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MakeAnnouncement extends Activity{	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);		
		setContentView(R.layout.makeannouncement);
	    getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);	
	    ((TextView)findViewById(R.id.titlebar)).setText("Mogelijke interacties:");
	    
	    if(MainController.getInstance().getUser().getRank()>0){
		    final List<Course> courses = MainController.getInstance().getUser().getIsp().getCourses();
		    List<String> courseTitles = new ArrayList<String>();
		    for(int i=0;i<courses.size();i++){
		    	courseTitles.add(courses.get(i).getCourseName());
		    }
		    final Spinner s = (Spinner) findViewById(R.id.courseSpinner);
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
		            this, android.R.layout.simple_spinner_item, courseTitles);
		    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    s.setAdapter(adapter);
		    
		    Button resetbutton = (Button) findViewById(R.id.reset);
		    //TODO/ resetbutton werkt nog niet
	        resetbutton.setOnClickListener(new View.OnClickListener() {
	
	            public void onClick(View view) {
	        	    EditText mTitle = (EditText) findViewById(R.id.mtitle);
	                EditText mMessage = (EditText) findViewById(R.id.mmessage);
	            	mTitle.setText("");
	            	mMessage.setText("");
	            }
	
	        });
	        Button submitbutton = (Button) findViewById(R.id.submit);
	
	        submitbutton.setOnClickListener(new View.OnClickListener() {
	
	            public void onClick(View view) {
	            	EditText mTitle = (EditText) findViewById(R.id.mtitle);
	                EditText mMessage = (EditText) findViewById(R.id.mmessage);
	                int courseLocation = s.getSelectedItemPosition();
	                InfoController.getInstance().insert(mTitle.getText().toString(),mMessage.getText().toString(),courses.get(courseLocation));
	                finish();
	            }
	
	        });
	    }
	    else{
	    	Toast.makeText(getApplicationContext(), "Gebruiker mag geen announcements aanmaken. Enkel professoren/assistenten!",
			          Toast.LENGTH_LONG).show();
	    }

	}
	
}
