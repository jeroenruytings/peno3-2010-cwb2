package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.model.Lecture;
import cw.kuleuven.be.peno1011.cwb2.model.MultipleChoice;
import cw.kuleuven.be.peno1011.cwb2.model.Question;

public class ShowStatistics extends Activity {

	// Een test multipleChoice
//	String [] test = new String []{
//			"a", "b","c", "d", "d", "d","a","a","a","a","a","a","a","b","b","c","c","c","c","c","c"};
//	 ArrayList<String> testlist = (ArrayList<String>) Arrays.asList(test);
//	
//	
//	MultipleChoice multipleChoice = new MultipleChoice(null, "dit is een test",testlist);
//	
//	

	
	//public class ShowStatistics extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
	         super.onCreate(savedInstanceState);

	         setContentView(R.layout.statistics);
	         
	         //haalt lecture op
			  Bundle bundle = getIntent().getExtras();
			  final Lecture lecture = (Lecture) bundle.get("lecture");
			  
			  //haalt meerkeuzevraag op (slechts 1 per les, wordt anders overschreven)
			  //en haalt variabelen op
			  final MultipleChoice multipleChoice = lecture.getMultipleChoice();
			  final String[] possibleAnswers = multipleChoice.getPossibleAnswers();
			  final int size = possibleAnswers.length;
			  final int[] answers = multipleChoice.getAnswers();
						 			  
			  //plaatst titel in textview
			  TextView title = (TextView) findViewById(R.id.title);
				 title.setText(multipleChoice.getQuestion());
				
			  
			  
			  /// Dit moet misschien in een controller komen
			  int a=0;
			  int b=0;
			  int c=0;
			  int d=0;
			  int e=0;
			  
			  // telt veschillende antwoorden
			  for(int i = 0; i < answers.length; i++) {
		            if(answers[i] == 0) {
		                a++;
		            } else if (size == 1) {
		            	b++;
		            }  else if (size == 2) {
		            	c++;
		            }else if (size == 3) {
		            	d++;
		            }else if (size == 4) {
		            	e++;
		            }
		                
			 }
			 
			  //toont het aantal antwoorden in een progressbar
			  //toont verschillende antwoorden in textview
			  //toont deze enkel als ze bestaan, anders zijn ze leeg en dus niet gegeven.			  
			 if (size == 2){
								 
	         final ProgressBar stata = (ProgressBar) findViewById(R.id.sa);
	         stata.setProgress((a/answers.length)*100);
	         TextView msga = (TextView) findViewById(R.id.msga);
			 msga.setText(answers[0]);
	         
	         	         
			 final ProgressBar statb = (ProgressBar) findViewById(R.id.sb);
	         statb.setProgress((b/answers.length)*100);
	         TextView msgb = (TextView) findViewById(R.id.msgb);
			 msgb.setText(answers[1]);
	         
			 } else if (size == 3) {
	         final ProgressBar statc = (ProgressBar) findViewById(R.id.sc);
	         statc.setProgress((c/answers.length)*100);
	         TextView msgc = (TextView) findViewById(R.id.msgc);
			 msgc.setText(answers[2]);
	         
			 } else if (size == 4) {
	         final ProgressBar statd = (ProgressBar) findViewById(R.id.sd);
	         statd.setProgress((d/answers.length)*100);
	         TextView msgd = (TextView) findViewById(R.id.msgd);
			 msgd.setText(answers[3]);
	         
			 } else if (size == 5) {
	         final ProgressBar state = (ProgressBar) findViewById(R.id.se);
	         state.setProgress((e/answers.length)*100);
	         TextView msge = (TextView) findViewById(R.id.msge);
			 msge.setText("professor" + ": " + answers[4]);	 
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
	 

