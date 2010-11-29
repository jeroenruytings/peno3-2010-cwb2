package cw.kuleuven.be.peno1011.cwb2.view;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import cw.kuleuven.be.peno1011.cwb2.R;

public class CoursePages extends TabActivity{
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.coursepages);

	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, CourseInfo.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("Info").setIndicator("Info",
	                      res.getDrawable(R.drawable.tab_info))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, ShowAnnouncements.class);
	    //TODO/vak meegeven als extra
	    spec = tabHost.newTabSpec("Meldingen").setIndicator("Meldingen",
	                      res.getDrawable(R.drawable.tab_announcements))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, CourseDocuments.class);
	    spec = tabHost.newTabSpec("Documenten").setIndicator("Documenten",
	                      res.getDrawable(R.drawable.tab_docs))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    tabHost.setCurrentTab(2);
	}

}
