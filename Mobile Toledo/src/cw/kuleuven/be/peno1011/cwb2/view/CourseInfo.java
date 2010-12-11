package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.MainController;
import cw.kuleuven.be.peno1011.cwb2.model.Course;

public class CourseInfo extends Activity{
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.courseinfo);
	     
		     Bundle bundle = getIntent().getExtras();
			 Course course = (Course) bundle.get("course");
			 
			 Toast.makeText(getApplicationContext(), course.getAcademicYear(),
	                    Toast.LENGTH_LONG).show();
			 
			 String prof= "Geen prof gespecifieerd";
			 String code = "onbekend";
			 String year = "onbekend";
			 try{
				 prof= course.getProf().getFirstName() + " " + course.getProf().getLastName();
				 code = course.getCourseCode();
			 }
			 catch(NullPointerException ne){
		    	 Toast.makeText(getApplicationContext(), "Waarschuwing: Een aantal vakgegevens ontbreken.",
				          Toast.LENGTH_LONG).show();
		     }
			 try{
				 year = course.getAcademicYear();
			 }
			 catch(NullPointerException ne){
		    	 Toast.makeText(getApplicationContext(), "Waarschuwing: Een aantal vakgegevens ontbreken.",
				          Toast.LENGTH_LONG).show();
		     }
			 
			 TextView proftxt = (TextView) findViewById(R.id.prof);
			 proftxt.setText("Professor" + ": " + prof);
			 
			 TextView codetxt = (TextView) findViewById(R.id.code);
			 codetxt.setText("Cursuscode" + ": " + code);
			 
			 TextView yeartxt = (TextView) findViewById(R.id.year);
			 yeartxt.setText("Academiejaar" + ": " + year);

	}
}
