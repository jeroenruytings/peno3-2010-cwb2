package cw.kuleuven.be.peno1011.cwb2.view;

import android.app.Activity;
import android.os.Bundle;
import cw.kuleuven.be.peno1011.cwb2.controller.MainController;

public class Questions extends Activity{	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		if(MainController.getUser().isStudent()){ //implementatie 'vragen stellen'
//			setContentView(R.layout.posequestions);
//		    setTitle(R.string.posequesitons);
			//TODO/ingeven van vragen, cfr MakeAnnouncement
		}
		else{ //implementatie 'vragen beantwoorden'
//			setContentView(R.layout.answerquestions);
//		    setTitle(R.string.answerquestions);
			//TODO/listview van alle vragen, cfr ShowAnnouncements
		}
	}

}