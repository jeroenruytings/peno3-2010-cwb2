package cw.kuleuven.be.peno1011.cwb2.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import cw.kuleuven.be.peno1011.cwb2.R;


public class MainMenu extends Activity {
	
    protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	    
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);		
	    setContentView(R.layout.mainmenu);
	    getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);	
	    ((TextView)findViewById(R.id.titlebar)).setText(R.string.mainmenu);
	    
	    getWindow().setFeatureInt(Window.FEATURE_OPTIONS_PANEL, R.layout.title);	
	    
	    ImageButton navigationButton = (ImageButton) findViewById(R.id.compassbutton);
	    navigationButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
        		//Context context = getApplicationContext();
				//Intent intent = new Intent(context, NavigationMenu.class);
				Intent intent = new Intent(MainMenu.this,NavigationMenu.class);
				intent.putExtra("gps", "welgps");
				startActivity(intent);
			}
	    });
	    ImageButton calendarButton = (ImageButton) findViewById(R.id.calendarbutton);
	    calendarButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainMenu.this,Agenda.class);
				startActivity(intent);
			}
	    });
	    ImageButton interactionButton = (ImageButton) findViewById(R.id.interactionbutton);
	    interactionButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainMenu.this,InteractionMenu.class);
				startActivity(intent);
			}
	    });
	    ImageButton courseButton = (ImageButton) findViewById(R.id.coursebutton);
	    courseButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainMenu.this,SelectCourse.class);
				intent.putExtra("nextview",CoursePages.class); 
				startActivity(intent);
			}
	    });
    }
    
}
