package cw.kuleuven.be.peno1011.cwb2.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.model.Lecture;



public class Questions extends Activity{	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.question);
		setContentView(R.layout.makeannouncement);
		
				
		
		Bundle bundle = getIntent().getExtras();
		final Lecture lecture = (Lecture) bundle.get("lecture"); 
		 
				 
		 
		 
		 
		 Button resetbutton = (Button) findViewById(R.id.reset);

	        resetbutton.setOnClickListener(new View.OnClickListener() {

	       public void onClick(View view) {
	        	    EditText mTitle = (EditText) findViewById(R.id.musername);
	                EditText mMessage = (EditText) findViewById(R.id.mpassword);
	            	mTitle.setText("");
	            	mMessage.setText("");
	            }

	        });
	        
	        
	        Button submitbutton = (Button) findViewById(R.id.submit);

	        submitbutton.setOnClickListener(new View.OnClickListener() {
	        	

	    public void onClick(View view) {
	       EditText mTitle = (EditText) findViewById(R.id.musername);
	       EditText mMessage = (EditText) findViewById(R.id.mpassword);
	                
//	        dao.insert(mTitle.getText().toString(),mMessage.getText().toString(),lecture));
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