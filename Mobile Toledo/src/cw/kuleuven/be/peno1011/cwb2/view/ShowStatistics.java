package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.model.Lecture;
import cw.kuleuven.be.peno1011.cwb2.model.MultipleChoice;
import cw.kuleuven.be.peno1011.cwb2.model.Question;

public class ShowStatistics extends Activity {

	
	//public class ShowStatistics extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
	         super.onCreate(savedInstanceState);

	         setContentView(R.layout.statistics);
	         

			  Bundle bundle = getIntent().getExtras();
			  final Lecture lecture = (Lecture) bundle.get("lecture");
			  
			  final MultipleChoice multipleChoice = lecture.getMultipleChoice();
			  
			  final String[] possibleAnswers = multipleChoice.getPossibleAnswers();
			  final int size = possibleAnswers.length;
			  
			  
			 if (size == 2){
			 
	         final ProgressBar stata = (ProgressBar) findViewById(R.id.sa);
	         stata.setProgress(50);
	         	         
			 final ProgressBar statb = (ProgressBar) findViewById(R.id.sb);
	         statb.setProgress(50);
	         
			 } else if (size == 3) {
	         final ProgressBar statc = (ProgressBar) findViewById(R.id.sc);
	         statc.setProgress(50);
	         
			 } else if (size == 4) {
	         final ProgressBar statd = (ProgressBar) findViewById(R.id.sd);
	         statd.setProgress(50);
	         
			 } else if (size == 5) {
	         final ProgressBar state = (ProgressBar) findViewById(R.id.se);
	         state.setProgress(50);
			 } else {
				 
			 }
	         

/*	         // Start lengthy operation in a background thread
	         new Thread(new Runnable() {
	             public void run() {
	                 while (mProgressStatus < 100) {
	                     mProgressStatus = 'Ons getal' ;

	                     // Update the progress bar
	                     mHandler.post(new Runnable() {
	                         public void run() {
	                             mProgress.setProgress(mProgressStatus);
	                         }
	                     });
	                 }
	             }
	         }).start();*/
	     }
	 }
	 

