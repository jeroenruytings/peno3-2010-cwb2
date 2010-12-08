package cw.kuleuven.be.peno1011.cwb2.view;

import cw.kuleuven.be.peno1011.cwb2.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CourseDocuments extends Activity{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coursedocs);

        TextView textview =(TextView) findViewById(R.id.docsView);
        textview.setText("Geen documenten gevonden");
        setContentView(textview);
        
		Bundle bundle = getIntent().getExtras();
    }

}
