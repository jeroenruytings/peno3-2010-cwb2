package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.MainController;
import cw.kuleuven.be.peno1011.cwb2.model.Course;

public class CourseInfo extends Activity{
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.courseinfo);
	     
	     Bundle bundle = getIntent().getExtras();
		 final String courseTitle = (String) bundle.get("courseTitle");
		 


		 List<Course> courses = MainController.getUser().getIsp().getCourses();
			String[] courseTitles = new String[courses.size()];
			
			Course course = null;
			
			for(int i = 0;i< courses.size();i++){
				String courseName = courses.get(i).getCourseName();
				courseTitles[i] = courseName;
				if (courseTitle == courseName){
					course = courses.get(i);
				}
			}
		 
				 
		 String prof= course.getProf().getFirstName() + " " + course.getProf().getLastName();
		 String code = course.getCourseCode();
		 String year = course.getAcademicYear();
		 
		 TextView proftxt = (TextView) findViewById(R.id.prof);
		 proftxt.setText("professor" + ": " + prof);
		 
		 TextView codetxt = (TextView) findViewById(R.id.code);
		 codetxt.setText("code van de cursus" + ": " + code);
		 
		 TextView yeartxt = (TextView) findViewById(R.id.year);
		 yeartxt.setText("academiejaar" + ": " + year);
		 
	     

	}
}
