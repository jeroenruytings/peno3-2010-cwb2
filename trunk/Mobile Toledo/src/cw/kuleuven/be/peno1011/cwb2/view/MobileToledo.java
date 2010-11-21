package cw.kuleuven.be.peno1011.cwb2.view;

import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.LoginController;
import cw.kuleuven.be.peno1011.cwb2.controller.MainController;
import cw.kuleuven.be.peno1011.cwb2.model.User;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MobileToledo extends Activity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.mobiletoledo);
        setTitle(R.string.mobiletoledo);
        
        Button loginbutton = (Button) findViewById(R.id.login);

        loginbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
            	EditText mUsername = (EditText) findViewById(R.id.musername);
                EditText mPassword = (EditText) findViewById(R.id.mpassword);
            	String username = mUsername.getText().toString();
            	String password = mPassword.getText().toString();
            	if (username.equals("") || password.equals("")){
            		Context context = getApplicationContext();
            		CharSequence text = "You have to enter your username" +
            				" and your password!";
            		int duration = Toast.LENGTH_SHORT;
            		Toast toast = Toast.makeText(context, text, duration);
            		toast.show();
            	}
            	else
            	   login(username, password) ;
            }

        });
    }

	private void login(String username, String password) {
		User user = LoginController.getUser(username,password);
		if(user != null){
			LoginController.login(user);
			Intent i = new Intent(this, MainMenu.class);
	        startActivity(i);
	        finish();
		}
		else{
			Context context = getApplicationContext();
    		CharSequence text = "Invalid combination username-password!";
    		int duration = Toast.LENGTH_SHORT;
    		Toast toast = Toast.makeText(context, text, duration);
    		toast.show();
		}
	}

	
}
