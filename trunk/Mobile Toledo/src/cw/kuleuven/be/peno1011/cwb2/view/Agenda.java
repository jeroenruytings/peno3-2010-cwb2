package cw.kuleuven.be.peno1011.cwb2.view;

import cw.kuleuven.be.peno1011.cwb2.R;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;

public class Agenda extends TabActivity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);		
		setContentView(R.layout.tablayout);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);		
        ((TextView)findViewById(R.id.titlebar)).setText("Agenda");
		
	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab
	

	    final Intent intent1 = new Intent().setClass(this, EventsList.class);
	    intent1.putExtra("span", "day");
//	    final Intent intent1 = intent;
	    spec = tabHost.newTabSpec("day").setIndicator("Vandaag")
	                  .setContent(intent1);
	    tabHost.addTab(spec);
	
	    Intent intent2 = new Intent().setClass(this, EventsList.class);
	    intent2.putExtra("span", "week");
	    spec = tabHost.newTabSpec("week").setIndicator("Deze week")
	                  .setContent(intent2);
	    tabHost.addTab(spec);
	
	    Intent intent3 = new Intent().setClass(this, EventsList.class);
	    intent3.putExtra("span", "month");
	    spec = tabHost.newTabSpec("month").setIndicator("Deze maand")
	                  .setContent(intent3);
	    tabHost.addTab(spec);
	
	    tabHost.setCurrentTab(2);
	    final TabHost host2 = tabHost;
	    final Context context = this;
	    tabHost.setOnTabChangedListener(new OnTabChangeListener() {
            public void onTabChanged(String tabId) {
//                if( tabId.equals("day") ){
            		String tab = host2.getCurrentTabTag();
//      			  Toast.makeText(getApplicationContext(), tab,
//    			          Toast.LENGTH_SHORT).show();
//                	EventsList a = new EventsList();
//                	Intent intentTest = new Intent().setClass(context, EventsList.class);
//                	a.setIntent(intentTest);
//                	Bundle b = new Bundle();
                    EventsList.self.showEvents("month");
//                }
            }
        });
	}
}
