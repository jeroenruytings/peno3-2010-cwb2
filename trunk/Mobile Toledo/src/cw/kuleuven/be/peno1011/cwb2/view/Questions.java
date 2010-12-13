package cw.kuleuven.be.peno1011.cwb2.view;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.QuestionController;
import cw.kuleuven.be.peno1011.cwb2.database.AnnouncementDAO;
import cw.kuleuven.be.peno1011.cwb2.model.Lecture;



public class Questions extends Activity{	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.question);

		Bundle bundle = getIntent().getExtras();
		final Lecture lecture;
		if(bundle != null){
			lecture = (Lecture) bundle.get("lecture");
		}
		else{
			lecture = null;
			finish();
		}
		
		Button resetbutton = (Button) findViewById(R.id.reset);

	    resetbutton.setOnClickListener(new View.OnClickListener() {

		    public void onClick(View view) {
		    	EditText mQuestion = (EditText) findViewById(R.id.mquestion);
	        	mQuestion.setText("");
		    }

	    });
	        
	        
	    Button submitbutton = (Button) findViewById(R.id.submit);

	    submitbutton.setOnClickListener(new View.OnClickListener() {
	        
		    public void onClick(View view) {
		    	EditText mQuestion = (EditText) findViewById(R.id.mquestion);

		    	QuestionController controller = QuestionController.getInstance();
				try {
					controller.insert(mQuestion.getText().toString(),lecture);
					Toast.makeText(getApplicationContext(), "Fout tijdens het aanmaken van het announcement!",
					Toast.LENGTH_LONG).show();
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
