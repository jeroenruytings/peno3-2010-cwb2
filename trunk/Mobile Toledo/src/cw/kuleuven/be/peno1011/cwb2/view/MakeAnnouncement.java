package cw.kuleuven.be.peno1011.cwb2.view;

import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.database.AnnouncementDAO;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MakeAnnouncement extends Activity{
	protected void OnCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.makeannouncement);
	    setTitle(R.string.makeannouncement);
	    
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
                AnnouncementDAO dao = AnnouncementDAO.getInstance();
               	dao.insert(mTitle.getText().toString(),mMessage.getText().toString());
            }

        });
	}
	
}
