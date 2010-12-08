package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.model.Lecture;
import cw.kuleuven.be.peno1011.cwb2.model.Question;

public class ShowQuestions extends ListActivity {
	
	
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.question);
		  
		  
		  Bundle bundle = getIntent().getExtras();
		  final Lecture lecture = (Lecture) bundle.get("lecture");
		  
		  final ArrayList<Question> questions = lecture.getQuestions();
		  
		  String[] displayStrings = new String[questions.size()];
		  	  for(int i = 0;i< questions.size();i++){
					try{
					String displayString = "Vraag"+ ": " + questions.get(i).getMessage();
					displayStrings[questions.size()-i-1] = displayString;
					}
					catch(NullPointerException ne){//Lecture=zero
						String displayString = "Vraag" + ": " + questions.get(i).getMessage();
						displayStrings[questions.size()-i-1] = displayString;
					}
		  	  }
		  
		  	  
		  setListAdapter(new ArrayAdapter<String>(this,
		          android.R.layout.simple_list_item_1, displayStrings));
		  ListView lv = getListView();
		  lv.setTextFilterEnabled(true);
		  lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {						
					getInfoQuestion(questions.get(questions.size()-position-1));	
				
				}
		  });
		 
		      
	  }
		  				
		//dit is de functie van als er op een willekeurige vraag wordt geklikt		
	private void getInfoQuestion(Question question){
		LayoutInflater inflater=LayoutInflater.from(ShowQuestions.this);
		View questionview=inflater.inflate(R.layout.questionview, null);
		
		//dit is de custom alertdialog met een eigen view die dan een rating toelaat.
		//deze view is te vinden bij layout/questionview.xml
		AlertDialog.Builder ab=new AlertDialog.Builder(ShowQuestions.this);
		ab.setTitle(question.getQuestioner().getFirstName() + " "+ question.getQuestioner().getLastName());
		ab.setMessage(question.getMessage());
		ab.setView(questionview);
		ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	
	    	public void onClick(DialogInterface dialog, int whichButton) {
	    		
  		// Deze functie zorgt ervoor dat de rating die de gebruiker geeft ook kan gelezen worden
	  final RatingBar ratingbar = (RatingBar) findViewById(R.id.ratingbar);
	  ratingbar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
	  public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
	  Toast.makeText(ShowQuestions.this, "New Rating: " + rating, Toast.LENGTH_SHORT).show();
	    				  		        
	  //TODO/ in database steken.
	  //dao.insert(ratingbar.getRating().intValue());
	    				  		        
	    				  		    }
	    				  		});		        	
	    		
			        }
			        });
			        ab.show();	
	
	
	}
	
}

	
