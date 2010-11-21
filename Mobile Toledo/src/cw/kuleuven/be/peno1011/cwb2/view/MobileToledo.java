package cw.kuleuven.be.peno1011.cwb2.view;

import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.LoginController;
import cw.kuleuven.be.peno1011.cwb2.database.local.LoginDbAdaptor;
import cw.kuleuven.be.peno1011.cwb2.model.User;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class MobileToledo extends Activity {
	
	private boolean remember = false;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.mobiletoledo);
        setTitle(R.string.mobiletoledo);
                
//        String oldUsername = LoginController.getInstance().getUsername(MobileToledo.this);
//        String oldPassword = LoginController.getInstance().getPassword(MobileToledo.this);
//        
//        if(oldPassword != null && oldUsername != null){
//        	EditText mUsername = (EditText) findViewById(R.id.musername);
//        	EditText mPassword = (EditText) findViewById(R.id.mpassword);
//        	mUsername.setText(oldUsername);
//        	mPassword.setText(oldPassword);
//        }
        
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
        
        CheckBox rememberMe = (CheckBox) findViewById(R.id.rememberme);        
        rememberMe.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					remember = true;
				}
				else{
					remember = false;
				}
			}
		});
	}
        
	private void login(String username, String password) {
		User user = LoginController.getInstance().getUser(username,password);
		if(user != null){
			LoginController.getInstance().login(user);
			if(remember)
				remember(username,password);
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

	private void remember(String username, String password) {
		LoginController.getInstance().remember(username,password,MobileToledo.this);
	}
}
