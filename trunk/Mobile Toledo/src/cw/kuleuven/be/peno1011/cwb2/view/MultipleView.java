package cw.kuleuven.be.peno1011.cwb2.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.MainController;
import cw.kuleuven.be.peno1011.cwb2.model.Lecture;
import cw.kuleuven.be.peno1011.cwb2.model.MultipleChoice;
import cw.kuleuven.be.peno1011.cwb2.model.User;

public class MultipleView extends Activity{	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.multiplechoice);
		
        Bundle bundle = getIntent().getExtras();
        final Lecture lecture = (Lecture) bundle.get("lecture");
		
        final User user = MainController.getUser();
		if(user.getLevel()==1){ //implementatie beantwoorden
			setContentView(R.layout.posemultiplechoice);
		    setTitle(R.string.posemultiplechoice);
			
		    
		}
		else{ //implementatie stellen
			setContentView(R.layout.multiplechoice);
		    setTitle(R.string.multiplechoice);
			
		    MultipleChoice multiple = lecture.getMultipleChoice();
		    final String[] options = multiple.getPossibleAnswers();
		    
	        AlertDialog.Builder ab=new AlertDialog.Builder(MultipleView.this);
			ab.setTitle("Title");
			ab.setSingleChoiceItems(options, 0,new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	                // onClick?
	            }
	        });
	        ab.setPositiveButton("Ingeven", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	            	Toast msg = Toast.makeText(MultipleView.this, Integer.toString(whichButton) , Toast.LENGTH_LONG);
	            	msg.show();
	    		    MultipleChoice multiple = lecture.getMultipleChoice();
	            	multiple.addResponder(user);
	            	multiple.addAnswer(whichButton);
	            	if(saveMultiple()){
	            		TextView feedback = (TextView) findViewById(R.id.multiplefeedback);
		            	feedback.setText("Meerkeuzevraag beantwoordt");
	            	}
	            	else{
	            		TextView feedback = (TextView) findViewById(R.id.multiplefeedback);
		            	feedback.setText("Fout bij verzenden meerkeuzevraag");
	            	}
	            	
	        }
	        });
	        ab.setNegativeButton("Annuleer", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	            	TextView feedback = (TextView) findViewById(R.id.multiplefeedback);
	            	feedback.setText("Meerkeuzevraag niet beantwoordt");
	            }
	        });
	        ab.show();
		}
	}
	
	//TODO: saven naar database
	private boolean saveMultiple(){
		boolean isSaved = false;
		return isSaved;
	}
	

}