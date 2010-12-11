package cw.kuleuven.be.peno1011.cwb2.view;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.model.Course;

public class CoursePages extends TabActivity{
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);	  

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);		
		setContentView(R.layout.tablayout);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);	
		
		try{
			Bundle bundle = getIntent().getExtras();
		    Course course = (Course) bundle.get("course");
	        ((TextView)findViewById(R.id.titlebar)).setText("Vakpagina " + course.getCourseName());
	        
	        
		    Resources res = getResources();//voor afbeeldingen
		    TabHost tabHost = getTabHost();  
		    TabHost.TabSpec spec;  
		    Intent intent;  
	
		    
		    intent = new Intent().setClass(this, CourseInfo.class);
		    intent.putExtra("course", course);
	
		    spec = tabHost.newTabSpec("Info").setIndicator("Info",
		                      res.getDrawable(R.drawable.tab_info))
		                  .setContent(intent);
		    tabHost.addTab(spec);
	
		    intent = new Intent().setClass(this, CourseAnnouncements.class);
		    intent.putExtra("course", course);
		    spec = tabHost.newTabSpec("Meldingen").setIndicator("Meldingen",
		                      res.getDrawable(R.drawable.tab_announcements))
		                  .setContent(intent);
		    tabHost.addTab(spec);
	
		    intent = new Intent().setClass(this, CourseDocuments.class);
		    intent.putExtra("course", course);
		    spec = tabHost.newTabSpec("Documenten").setIndicator("Documenten",
		                      res.getDrawable(R.drawable.tab_docs))
		                  .setContent(intent);
		    tabHost.addTab(spec);
	
		    tabHost.setCurrentTab(0);
		}
		catch(NullPointerException ne){
			Toast.makeText(getApplicationContext(), "Geen vak gespecifieerd.",
			          Toast.LENGTH_LONG).show();
		}
	}
}
