package cw.kuleuven.be.peno1011.cwb2.view;

import cw.kuleuven.be.peno1011.cwb2.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewUser extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.newuser);
		
		Button createbutton = (Button) findViewById(R.id.createuser);

        createbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
            	EditText mUsername = (EditText) findViewById(R.id.musername);
            	EditText mPassword = (EditText) findViewById(R.id.mpassword);
            	EditText mConfirmPassword = (EditText) findViewById(R.id.mconfirmpassword);
            	
            	String username = mUsername.getText().toString();
            	String password = mPassword.getText().toString();
            	String confirmpassword = mConfirmPassword.getText().toString();
            	
            	if (username.equals("") || password.equals("")|| confirmpassword.equals("")){
            		Context context = getApplicationContext();
            		CharSequence text = "You have to enter your username" +
            				" and your password and confirm it!";
            		int duration = Toast.LENGTH_SHORT;
            		Toast toast = Toast.makeText(context, text, duration);
            		toast.show();
            	}
            	else if(! password.equals(confirmpassword)){
            		Context context = getApplicationContext();
            		CharSequence text = "Your confirmation does not match your password!";
            		int duration = Toast.LENGTH_SHORT;
            		Toast toast = Toast.makeText(context, text, duration);
            		toast.show();
            	}
            	else{
            		//VIA DAO IN INTERNETDB STEKEN
            		finish();
            	}
            }
            
        });
		
	}
}
