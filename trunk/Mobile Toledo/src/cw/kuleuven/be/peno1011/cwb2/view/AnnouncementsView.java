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

public class AnnouncementsView extends TabActivity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);		
		setContentView(R.layout.tablayout);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);		
        ((TextView)findViewById(R.id.titlebar)).setText("Meldingen");
        
	    TabHost tabHost = getTabHost(); 
	    TabHost.TabSpec spec; 
	

	    final Intent intent1 = new Intent().setClass(this, ShowAnnouncements.class);
//	    intent1.putExtra("period", "day");
	    spec = tabHost.newTabSpec("week").setIndicator("Voorbije week")
	                  .setContent(intent1);
	    tabHost.addTab(spec);
	
	    Intent intent2 = new Intent().setClass(this, ShowAnnouncements.class);
//	    intent2.putExtra("span", "week");
	    spec = tabHost.newTabSpec("maand").setIndicator("Voorbije maand")
	                  .setContent(intent2);
	    tabHost.addTab(spec);
	
	    tabHost.setCurrentTab(0);
	}
}
