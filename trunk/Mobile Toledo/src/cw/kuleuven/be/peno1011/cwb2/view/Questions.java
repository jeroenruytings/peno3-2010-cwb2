package cw.kuleuven.be.peno1011.cwb2.view;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.QuestionController;
import cw.kuleuven.be.peno1011.cwb2.database.AnnouncementDAO;
import cw.kuleuven.be.peno1011.cwb2.model.Lecture;



public class Questions extends Activity{	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);  
		setContentView(R.layout.question);
	      getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);         
	      ((TextView)findViewById(R.id.titlebar)).setText("Vragen");

		Bundle bundle = getIntent().getExtras();
		int lectureId = 0;
		if(bundle != null){
			lectureId = (Integer) bundle.get("lecture");
		}
		else{
			finish();
		}
		
		Button resetbutton = (Button) findViewById(R.id.reset);

	    resetbutton.setOnClickListener(new View.OnClickListener() {

		    public void onClick(View view) {
		    	EditText mQuestion = (EditText) findViewById(R.id.mquestion);
	        	mQuestion.setText("");
		    }

	    });
	      
	    final int id = lectureId;
	        
	    Button submitbutton = (Button) findViewById(R.id.submit);

	    submitbutton.setOnClickListener(new View.OnClickListener() {
	        
		    public void onClick(View view) {
		    	EditText mQuestion = (EditText) findViewById(R.id.mquestion);

		    	QuestionController controller = QuestionController.getInstance();
				try {
					controller.insert(mQuestion.getText().toString(),id);
				} catch (HttpException e) {
					Toast.makeText(getApplicationContext(), "Geen internetverbinding!",
					Toast.LENGTH_LONG).show();
				} catch (IOException e) {
					Toast.makeText(getApplicationContext(), "Fout tijdens het doorsturen van de vraag!",
					Toast.LENGTH_LONG).show();
				}
		    	finish();
		    }

	    });
		
	    	
	}
}		
	

		/*final List<Course> courses = MainController.getUser().getIsp().getCourses();
	    List<String> courseTitles = new ArrayList<String>();
	    
	    for(int i=0;i<courses.size();i++){
	    	courseTitles.set(i, courses.get(i).getCourseName());
	    }
	    
	    final Spinner s = (Spinner) findViewById(R.id.courseSpinner);
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
	            this, android.R.layout.simple_spinner_item, courseTitles);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    s.setAdapter(adapter);
		
		
	    
	    Button resetbutton = (Button) findViewById(R.id.reset);

        resetbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
        	    EditText mTitle = (EditText) findViewById(R.id.musername);
                EditText mMessage = (EditText) findViewById(R.id.mpassword);
            	mTitle.setText("");
            	mMessage.setText("");
            }

        });*/
		
        
        
/*		if(MainController.getUser().getLevel()==1){ //implementatie 'vragen stellen'
//			setContentView(R.layout.posequestions);
//		    setTitle(R.string.posequesitons);
			//TODO/ingeven van vragen, cfr MakeAnnouncement
		}
		else{ //implementatie 'vragen weergeven'
//			setContentView(R.layout.showquestions);
//		    setTitle(R.string.showquestions);
			//TODO/listview van alle vragen, cfr ShowAnnouncements
		}
	*/
