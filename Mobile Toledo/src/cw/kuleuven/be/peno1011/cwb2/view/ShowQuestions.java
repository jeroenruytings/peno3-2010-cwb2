package cw.kuleuven.be.peno1011.cwb2.view;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.httpclient.HttpException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RatingBar.OnRatingBarChangeListener;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.InfoController;
import cw.kuleuven.be.peno1011.cwb2.controller.QuestionController;
import cw.kuleuven.be.peno1011.cwb2.model.Lecture;
import cw.kuleuven.be.peno1011.cwb2.model.Question;

public class ShowQuestions extends Activity {

	
	public void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
       
	  requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);  
	  setContentView(R.layout.showquestions);
      getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);         
      ((TextView)findViewById(R.id.titlebar)).setText("Vragen");
  
	  //haalt lecture
	  Bundle bundle = getIntent().getExtras();
	  
	  Question[] questions = null;
	  ArrayList<Question> questionList=new ArrayList<Question>();
	  if(bundle != null && bundle.get("lecture") != null && bundle.get("courseCode") != null){
			int lectureId = (Integer) bundle.get("lecture");
			String courseCode = (String) bundle.get("courseCode");
//			Lecture lecture = InfoController.getInstance().findLectureById(lectureId,courseCode);
//			Toast.makeText(ShowQuestions.this, lecture.getTitle(), Toast.LENGTH_SHORT).show();
//			Toast.makeText(ShowQuestions.this, lecture.getQuestions().get(0).getMessage(), Toast.LENGTH_SHORT).show();
			try{
				questions = QuestionController.getInstance().getQuestionsByLecture("" + lectureId);
//				  final ArrayList<Question> questions = questionList;
//		      
//			  // maakt een string[] displayStrings aan van de alles messages van de questions
//		      // De users zijn er niet bijgevoegd wegens wens van anonimiteit.
//		      //TODO De database sorteren op score, dit is een integer van 0 tot 5
			  final String[] displayStrings = new String[questions.length];
			  	  for(int i = 0;i< questions.length;i++){
			  		  	String displayString = "Vraag: " + questions[i].getMessage() + " ( score: " + questions[i].getAppreciation().getScore() + " )";
						displayStrings[i] = displayString;
						
			  	  }
			  final Question[] allQuestions = questions;
			  // Maakt een listAdapter met als layout de showquestions.xml en als input de string[] displayStrings  
			  ListView lv = (ListView) findViewById(R.id.lv);
			  lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, displayStrings));
			  lv.setTextFilterEnabled(true);
			  lv.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,int position, long id) {						
						getInfoQuestion(allQuestions[allQuestions.length-position-1]);	

					}
			  });
			}
			catch(NullPointerException ne){
				Toast.makeText(ShowQuestions.this, "Geen les gevonden", Toast.LENGTH_SHORT).show();
				finish();
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			finish();
		}	  
	      
	  }
		  				
		//dit is de functie van als er op een willekeurige vraag wordt geklikt		
	private void getInfoQuestion(Question question){
		LayoutInflater inflater=LayoutInflater.from(ShowQuestions.this);
		View questionview=inflater.inflate(R.layout.questionview, null);
		
		//dit is de custom alertdialog met een eigen view die dan een rating toelaat.
		//deze view is te vinden bij layout/questionview.xml
		AlertDialog.Builder ab=new AlertDialog.Builder(ShowQuestions.this);
		try{
			ab.setTitle(question.getQuestioner().getFirstName() + " "+ question.getQuestioner().getLastName());
		}
		catch(NullPointerException ne){
			ab.setTitle("Vrager onbekend");			
		}
		ab.setMessage(question.getMessage());
		ab.setView(questionview);
		ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	
	    	public void onClick(DialogInterface dialog, int whichButton) {
	    		
  		// Deze functie zorgt ervoor dat de rating die de gebruiker geeft ook kan gelezen worden
//				  final RatingBar ratingbar = (RatingBar) findViewById(R.id.ratingbar);
//					  ratingbar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
//					@Override
//					  public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
					  Toast.makeText(getApplicationContext(), "Rating gewijzigd.",
		                      Toast.LENGTH_LONG).show();
//	
//		    				  		    }
//		    				  		});		        	
		    		
				        }
				        });
ab.show();	
	
	
	}
	
}

	
