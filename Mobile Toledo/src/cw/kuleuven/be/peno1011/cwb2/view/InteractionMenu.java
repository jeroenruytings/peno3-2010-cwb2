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
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    
	    setContentView(R.layout.interactionmenu);
	    setTitle(R.string.interactionmenu);
	    
	    ImageButton makeannouncementButton = (ImageButton) findViewById(R.id.makeannouncementbutton);
	    ImageButton announcementsButton = (ImageButton) findViewById(R.id.announcementsbutton);
	    ImageButton questionButton = (ImageButton) findViewById(R.id.questionbutton);
	    ImageButton multipleButton = (ImageButton) findViewById(R.id.multiplebutton);
	    ImageButton statsButton = (ImageButton) findViewById(R.id.statsbutton);
	
	    //moet nog geimplementeerd worden:
    	if(MainController.getUser().getLevel()>1){ //als gebruiker een prof is, ziet de menu er anders uit!
	    	makeannouncementButton.setVisibility(View.VISIBLE);
	    	TextView makeAnnouncementText = (TextView) findViewById(R.id.makeannouncement);
	    	makeAnnouncementText.setVisibility(View.VISIBLE);
	    	TextView text1 = (TextView) findViewById(R.id.questions);
	    	text1.setText(R.string.questionanswering);
	    	TextView text2 = (TextView) findViewById(R.id.multiple);
	    	text2.setText("Meerkeuzevraag stellen");
	    }


	    
	    makeannouncementButton.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(InteractionMenu.this,MakeAnnouncement.class);
				startActivity(intent);
			}
	    });
	    
	    announcementsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(InteractionMenu.this,ShowAnnouncements.class);
				startActivity(intent);
			}
	    });
	    questionButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(InteractionMenu.this,SelectCourse.class);
                intent.putExtra("nextview",Questions.class); 
				startActivity(intent);
			}
	    });
	    multipleButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(InteractionMenu.this,SelectCourse.class);
                intent.putExtra("nextview",MultipleView.class); 
				startActivity(intent);
			}
	    });
	    statsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(InteractionMenu.this,SelectCourse.class);
                intent.putExtra("nextview",ShowStatistics.class); 
				startActivity(intent);
			}
	    });
    }
}
