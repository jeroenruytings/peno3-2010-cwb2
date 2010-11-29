package cw.kuleuven.be.peno1011.cwb2.view;

import cw.kuleuven.be.peno1011.cwb2.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CourseInfo extends Activity{
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.courseinfo);
	     
	     Bundle bundle = getIntent().getExtras();
		 final String courseTitle = (String) bundle.get("courseTitle");

	}
}
