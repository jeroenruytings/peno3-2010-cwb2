package cw.kuleuven.be.peno1011.cwb2.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CourseDocuments extends Activity{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("documenten hier");
        setContentView(textview);
        
		Bundle bundle = getIntent().getExtras();
	    //final String courseTitle = (String) bundle.get("courseTitle");
    }

}
