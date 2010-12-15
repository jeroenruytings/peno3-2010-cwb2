package cw.kuleuven.be.peno1011.cwb2.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.InfoController;
import cw.kuleuven.be.peno1011.cwb2.controller.MultipleController;
import cw.kuleuven.be.peno1011.cwb2.model.Answer;
import cw.kuleuven.be.peno1011.cwb2.model.Lecture;
import cw.kuleuven.be.peno1011.cwb2.model.MultipleChoice;

public class ShowStatistics extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
	         super.onCreate(savedInstanceState);

	         requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);		
	 		setContentView(R.layout.statistics);
	 		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);		
	         ((TextView)findViewById(R.id.titlebar)).setText("Statistieken meerkeuzevraag");
	 		
	        try{
		         
		         
		         //haalt lecture op
				  Bundle bundle = getIntent().getExtras();
				  int lectureId = (Integer) bundle.get("lecture");
                  String courseCode = (String) bundle.get("courseCode");
				  
                  Lecture lecture = InfoController.getInstance().findLectureById(lectureId,courseCode);
				  //haalt meerkeuzevraag op (slechts 1 per les, wordt anders overschreven)
				  //en haalt variabelen op
                  List<MultipleChoice> multipleChoices = null;
                  try {
      				multipleChoices = MultipleController.getInstance().getMultiple(lecture.getEventId());
      			} catch (HttpException e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      				Toast.makeText(getApplicationContext(), "http",
      	                      Toast.LENGTH_LONG).show();
      			} catch (IOException e) {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      				Toast.makeText(getApplicationContext(), "io",
      	                      Toast.LENGTH_LONG).show();
      			}
      			MultipleChoice multiple=multipleChoices.get(0);
      			try{
		            ArrayList<Answer> answers = multiple.getAnswers();
//		            ArrayList<String> answerStrings = new ArrayList<String>();
//		            for(int i=0;i<answers.size();i++){
//		            	answerStrings.add(answers.get(i).getAnswer());
//		            }
//				  final String[] possibleAnswers = (String[]) answerStrings.toArray();
				  int size = answers.size();

							 			  
				  //plaatst titel in textview
				  TextView title = (TextView) findViewById(R.id.title);
					 title.setText(multiple.getQuestion());
					
//				  
//				  
//				  /// Dit moet misschien in een controller komen
//				  int a=0;
//				  int b=0;
//				  int c=0;
//				  int d=0;
//				  int e=0;
//				  
//				  // telt veschillende antwoorden
//				  for(int i = 0; i < answers.length; i++) {
//			            if(answers[i] == 0) {
//			                a++;
//			            } else if (size == 1) {
//			            	b++;
//			            }  else if (size == 2) {
//			            	c++;
//			            }else if (size == 3) {
//			            	d++;
//			            }else if (size == 4) {
//			            	e++;
//			            }
//			                
//				 }
				 int a = answers.get(0).getTotal();
				 int b = answers.get(1).getTotal();
				 int c = 0;
				 int d = 0;
				 int e = 0;
				 try{
					 c = answers.get(2).getTotal();
					 d = answers.get(3).getTotal();
					 e = answers.get(4).getTotal();
				 }
				 catch(NullPointerException ne){
					 
				 }
				 
				 int total = a+b+c+d+e;
				  //toont het aantal antwoorden in een progressbar
				  //toont verschillende antwoorden in textview
				  //toont deze enkel als ze bestaan, anders zijn ze leeg en dus niet gegeven.			  
				 
				  //2vragen
				  if (size == 2){
									 
		         final ProgressBar stata = (ProgressBar) findViewById(R.id.sa);
		         stata.setProgress((a/total)*100);
		         TextView msga = (TextView) findViewById(R.id.msga);
				 msga.setText(answers.get(0).getAnswer());
		         
		         	         
				 final ProgressBar statb = (ProgressBar) findViewById(R.id.sb);
		         statb.setProgress((b/total)*100);
		         TextView msgb = (TextView) findViewById(R.id.msgb);
				 msgb.setText(answers.get(1).getAnswer());
				 
		         //3vragen
				 } else if (size == 3) {
		         final ProgressBar statc = (ProgressBar) findViewById(R.id.sc);
		         statc.setProgress((c/total)*100);
		         TextView msgc = (TextView) findViewById(R.id.msgc);
				 msgc.setText(answers.get(2).getAnswer());
				 
		         //4vragen
				 } else if (size == 4) {
		         final ProgressBar statd = (ProgressBar) findViewById(R.id.sd);
		         statd.setProgress((d/total)*100);
		         TextView msgd = (TextView) findViewById(R.id.msgd);
				 msgd.setText(answers.get(3).getAnswer());
				 
		         //5vragen
				 } else if (size == 5) {
		         final ProgressBar state = (ProgressBar) findViewById(R.id.se);
		         state.setProgress((e/total)*100);
		         TextView msge = (TextView) findViewById(R.id.msge);
				 msge.setText(answers.get(0).getAnswer());	 
				 } else {
				 }
	         }
				 //try-catch methode om fouten op te vangen zodat het programma niet crasht bij een fout.
				  catch(NullPointerException ne){
					  Toast.makeText(getApplicationContext(), "Fout bij het opvragen van de statistieken.",
					          Toast.LENGTH_LONG).show(); 
				  }
      			}
      			catch(NullPointerException ne){
      				Toast.makeText(getApplicationContext(), "Fout bij het opvragen van de meerkeuzevraag",
    	                      Toast.LENGTH_LONG).show();
      			}

	     }
	 }
	 

