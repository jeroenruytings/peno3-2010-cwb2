package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import cw.kuleuven.be.peno1011.cwb2.model.Announcement;
import cw.kuleuven.be.peno1011.cwb2.model.Course;
import cw.kuleuven.be.peno1011.cwb2.model.Lecture;
import cw.kuleuven.be.peno1011.cwb2.model.Question;

public class ShowQuestions extends ListActivity {
	
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  
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
						String displayString = "??" + ": " + questions.get(i).getMessage();
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
		  				
				
	private void getInfoQuestion(Question question){
		AlertDialog.Builder ab=new AlertDialog.Builder(ShowQuestions.this);
		ab.setTitle(question.getQuestioner().getFirstName() + " "+ question.getQuestioner().getLastName());
		ab.setMessage(question.getMessage());
		ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	
	    	public void onClick(DialogInterface dialog, int whichButton) {
			            	
			            	
			        }
			        });
			        ab.show();	 
	}
	
		  
}

	
