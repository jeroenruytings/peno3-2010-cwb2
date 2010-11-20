package cw.kuleuven.be.peno1011.cwb2.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.MainController;

public class InteractionMenu extends Activity{	
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    
	    setContentView(R.layout.interactionmenu);
	    setTitle(R.string.interactionmenu);
	    
	    ImageButton makeannouncementButton = (ImageButton) findViewById(R.id.makeannouncementbutton);
	    ImageButton announcementsButton = (ImageButton) findViewById(R.id.announcementsbutton);
	    ImageButton questionButton = (ImageButton) findViewById(R.id.questionbutton);
	    
	    if(!MainController.getUser().isStudent()){ //als gebruiker een prof is, ziet de menu er anders uit!
	    	makeannouncementButton.setVisibility(View.VISIBLE);
	    	TextView text = (TextView) findViewById(R.id.questions);
	    	text.setText(R.string.questionanswering);
	    }
	    
	    makeannouncementButton.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(InteractionMenu.this,MakeAnnouncement.class);
				startActivity(intent);
				finish();
			}
	    });
	    
	    announcementsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(InteractionMenu.this,ShowAnnouncements.class);
				startActivity(intent);
				finish();
			}
	    });
	    questionButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
			}
	    });
    }
}
