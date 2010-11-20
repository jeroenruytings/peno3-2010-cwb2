package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.ArrayList;
import java.util.List;

import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.MainController;
import cw.kuleuven.be.peno1011.cwb2.database.AnnouncementDAO;
import cw.kuleuven.be.peno1011.cwb2.model.Course;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MakeAnnouncement extends Activity{	
	protected void OnCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.makeannouncement);
	    setTitle(R.string.makeannouncement);
	    
	    createWidgets();

	}
	private void createWidgets(){
		final List<Course> courses = MainController.getUser().getIsp().getCourses();
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

        });
        Button submitbutton = (Button) findViewById(R.id.submit);

        submitbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
            	EditText mTitle = (EditText) findViewById(R.id.musername);
                EditText mMessage = (EditText) findViewById(R.id.mpassword);
                int courseLocation = s.getSelectedItemPosition();
                AnnouncementDAO dao = AnnouncementDAO.getInstance();
               	dao.insert(mTitle.getText().toString(),mMessage.getText().toString(),courses.get(courseLocation));
            }

        });
	}
	
}
