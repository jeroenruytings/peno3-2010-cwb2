package cw.kuleuven.be.peno1011.cwb2.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CourseInfo extends Activity{
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);
//	     setContentView(R.layout.courseinfo);
	     
        TextView textview = new TextView(this);
        textview.setText("vakinfo hier");
        setContentView(textview);


	}
}
