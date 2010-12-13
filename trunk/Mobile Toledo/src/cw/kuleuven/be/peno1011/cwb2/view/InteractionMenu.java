package cw.kuleuven.be.peno1011.cwb2.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.MainController;

public class InteractionMenu extends Activity{	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    
		if(MainController.getInstance().getUser().getRank()>0){
			requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);		
			setContentView(R.layout.interactionmenuprof);
		    getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);	
		    ((TextView)findViewById(R.id.titlebar)).setText("Mogelijke interacties (prof/assistent)");
		    setProfListeners();
			
		}
		else{
			requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);		
			setContentView(R.layout.interactionmenu);
		    getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);	
		    ((TextView)findViewById(R.id.titlebar)).setText("Mogelijke interacties (student)");
		    setStudentListeners();
		}

    }
	private void setStudentListeners(){
	    ImageButton announcementsButton = (ImageButton) findViewById(R.id.announcementsbutton);
	    ImageButton questionButton = (ImageButton) findViewById(R.id.questionbutton);
	    ImageButton	showquestionButton = (ImageButton) findViewById(R.id.showquestionbutton);
	    ImageButton multipleButton = (ImageButton) findViewById(R.id.multiplebutton);
	    
	    announcementsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(InteractionMenu.this,AnnouncementsView.class);
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
	    showquestionButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(InteractionMenu.this,SelectCourse.class);
                intent.putExtra("nextview",ShowQuestions.class); 
				startActivity(intent);
			}
	    });

	}
	private void setProfListeners(){
	    ImageButton announcementsButton = (ImageButton) findViewById(R.id.announcementsbutton);
	    ImageButton	showquestionButton = (ImageButton) findViewById(R.id.showquestionbutton);
	    ImageButton multipleButton = (ImageButton) findViewById(R.id.multiplebutton);
	    ImageButton makeAnnouncementButton = (ImageButton) findViewById(R.id.makeannouncementbutton);
	    ImageButton statsButton = (ImageButton) findViewById(R.id.statsbutton);
	    
	    announcementsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(InteractionMenu.this,AnnouncementsView.class);
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
	    showquestionButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(InteractionMenu.this,SelectCourse.class);
                intent.putExtra("nextview",ShowQuestions.class); 
				startActivity(intent);
			}
	    });

	    
	    makeAnnouncementButton.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(InteractionMenu.this,MakeAnnouncement.class);
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
