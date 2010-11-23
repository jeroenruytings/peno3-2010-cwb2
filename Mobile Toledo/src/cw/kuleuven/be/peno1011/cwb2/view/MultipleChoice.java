package cw.kuleuven.be.peno1011.cwb2.view;

import android.app.Activity;
import android.os.Bundle;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.MainController;

public class MultipleChoice extends Activity{	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		if(MainController.getUser().getLevel()==1){ //implementatie beantwoorden
			setContentView(R.layout.posemultiplechoice);
		    setTitle(R.string.posemultiplechoice);
			
		}
		else{ //implementatie stellen
			setContentView(R.layout.multiplechoice);
		    setTitle(R.string.multiplechoice);
			
		    
		    
		}
	}

}