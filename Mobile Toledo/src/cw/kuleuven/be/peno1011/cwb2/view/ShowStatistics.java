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
			  final int[] answers = multipleChoice.getAnswers();
			  
			  /// Dit moet misschien in een controller komen
			  int a=0;
			  int b=0;
			  int c=0;
			  int d=0;
			  int e=0;
			  
			  for(int i = 0; i < answers.length; i++) {
		            if(answers[i] == 1) {
		                a++;
		            } else if (size == 2) {
		            	b++;
		            }  else if (size == 3) {
		            	c++;
		            }else if (size == 4) {
		            	d++;
		            }else if (size == 5) {
		            	e++;
		            }
		                
			 }
			  
			 if (size == 2){
								 
	         final ProgressBar stata = (ProgressBar) findViewById(R.id.sa);
	         stata.setProgress((a/answers.length)*100);
	         	         
			 final ProgressBar statb = (ProgressBar) findViewById(R.id.sb);
	         statb.setProgress((b/answers.length)*100);
	         
			 } else if (size == 3) {
	         final ProgressBar statc = (ProgressBar) findViewById(R.id.sc);
	         statc.setProgress((c/answers.length)*100);
	         
			 } else if (size == 4) {
	         final ProgressBar statd = (ProgressBar) findViewById(R.id.sd);
	         statd.setProgress((d/answers.length)*100);
	         
			 } else if (size == 5) {
	         final ProgressBar state = (ProgressBar) findViewById(R.id.se);
	         state.setProgress((e/answers.length)*100);
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
	 

