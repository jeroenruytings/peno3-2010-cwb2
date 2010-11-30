package cw.kuleuven.be.peno1011.cwb2.view;

import cw.kuleuven.be.peno1011.cwb2.R;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

public class Agenda extends TabActivity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.tablayout);
		
	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab
	
	
	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, EventsList.class);
	    intent.putExtra("span", "day");
	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("Vandaag").setIndicator("Vandaag",
	                      res.getDrawable(R.drawable.tab_info))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	
	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, EventsList.class);
	    intent.putExtra("span", "week");
	    spec = tabHost.newTabSpec("Deze week").setIndicator("Week",
	                      res.getDrawable(R.drawable.tab_announcements))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	
	    intent = new Intent().setClass(this, EventsList.class);
	    intent.putExtra("span", "day");
	    spec = tabHost.newTabSpec("Deze maand").setIndicator("Maand",
	                      res.getDrawable(R.drawable.tab_docs))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	
	    tabHost.setCurrentTab(2);
	}
}
