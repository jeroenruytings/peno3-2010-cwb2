package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.MainController;
import cw.kuleuven.be.peno1011.cwb2.database.AnnouncementDAO;
import cw.kuleuven.be.peno1011.cwb2.model.Course;
import cw.kuleuven.be.peno1011.cwb2.model.Lecture;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SelectCourse extends Activity{	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        setContentView(R.layout.selectcourse);
        
        Bundle bundle = getIntent().getExtras();
        final Object nextview = bundle.get("nextview");   
		
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
        Button submitbutton = (Button) findViewById(R.id.submit);
        
        //Course course;
        submitbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int courseLocation = s.getSelectedItemPosition();
                Course course = courses.get(courseLocation);
                Lecture lecture = findLecture(course);
                Intent intent = new Intent(SelectCourse.this,(Class<?>) nextview);
                intent.putExtra("lecture",lecture); 
                startActivity(intent);
            }

        });
	}
	private Lecture findLecture(Course course){
        Date currentDate = new Date();
        List<Lecture> lectures = course.getLectures();
        Lecture lecture = null;
        //TODO/indien meerdere lectures(zoals ANA A-groep en B-groep tegelijk -> keuze geven
        for(int i=0;i<lectures.size();i++){
        	boolean isInBetween = currentDate.compareTo(lectures.get(i).getStartDate())>0 && currentDate.compareTo(lectures.get(i).getStopDate())<0;
        	if(isInBetween){
        		lecture = lectures.get(i);
        	}
        }
        return lecture;
	}

	
}
