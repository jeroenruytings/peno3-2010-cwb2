package cw.kuleuven.be.peno1011.cwb2.view;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.model.Lecture;

public class CoursePages extends TabActivity{
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);	  

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);		
		setContentView(R.layout.coursepages);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);	
		
		try{
			Bundle bundle = getIntent().getExtras();
		    final String courseTitle = (String) bundle.get("courseTitle");
	        ((TextView)findViewById(R.id.titlebar)).setText(courseTitle);
	        
	        
		    Resources res = getResources(); // Resource object to get Drawables
		    TabHost tabHost = getTabHost();  // The activity TabHost
		    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
		    Intent intent;  // Reusable Intent for each tab
	
	
		    // Create an Intent to launch an Activity for the tab (to be reused)
		    intent = new Intent().setClass(this, CourseInfo.class);
		    intent.putExtra("courseTitle", courseTitle);
	
		    // Initialize a TabSpec for each tab and add it to the TabHost
		    spec = tabHost.newTabSpec("Info").setIndicator("Info",
		                      res.getDrawable(R.drawable.tab_info))
		                  .setContent(intent);
		    tabHost.addTab(spec);
	
		    // Do the same for the other tabs
		    intent = new Intent().setClass(this, ShowAnnouncements.class);
		    intent.putExtra("courseTitle", courseTitle);
		    //TODO/vak meegeven als extra
		    spec = tabHost.newTabSpec("Meldingen").setIndicator("Meldingen",
		                      res.getDrawable(R.drawable.tab_announcements))
		                  .setContent(intent);
		    tabHost.addTab(spec);
	
		    intent = new Intent().setClass(this, CourseDocuments.class);
		    intent.putExtra("courseTitle", courseTitle);
		    spec = tabHost.newTabSpec("Documenten").setIndicator("Documenten",
		                      res.getDrawable(R.drawable.tab_docs))
		                  .setContent(intent);
		    tabHost.addTab(spec);
	
		    tabHost.setCurrentTab(2);
		}
		catch(NullPointerException ne){
			Toast.makeText(getApplicationContext(), "Geen vak gespecifieerd.",
			          Toast.LENGTH_LONG).show();
		}
	}
}
