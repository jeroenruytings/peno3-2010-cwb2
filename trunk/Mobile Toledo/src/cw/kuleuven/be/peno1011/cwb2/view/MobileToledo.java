package cw.kuleuven.be.peno1011.cwb2.view;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.LoginController;
import cw.kuleuven.be.peno1011.cwb2.model.User;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class MobileToledo extends Activity {
	
	private boolean remember = false;
    private static final int INSERT_ID = Menu.FIRST;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);		
		setContentView(R.layout.mobiletoledo);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);		
        ((TextView)findViewById(R.id.titlebar)).setText(R.string.mobiletoledo);
                
        String oldUsername = LoginController.getInstance().getUsername(MobileToledo.this);
        String oldPassword = LoginController.getInstance().getPassword(MobileToledo.this);
        
        if(oldPassword != null && oldUsername != null){
        	EditText mUsername = (EditText) findViewById(R.id.musername);
        	EditText mPassword = (EditText) findViewById(R.id.mpassword);
        	mUsername.setText(oldUsername);
        	mPassword.setText(oldPassword);
        }
        
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
       
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, INSERT_ID, 0, R.string.menu_newuser);
        return true;
    }
	
	@Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
            case INSERT_ID:
                createUser();
                return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }
	
	private void createUser() {
        Intent i = new Intent(this, NewUser.class);
        startActivity(i);
    }
	
	
	private void login(String username, String password) {
		User user = null;
		try {
			user = LoginController.getInstance().getUser(username);
		} catch (HttpException e) {
			Context context = getApplicationContext();
			CharSequence text = "Geen internetverbinding!";
    		int duration = Toast.LENGTH_SHORT;
    		Toast toast = Toast.makeText(context, text, duration);
    		toast.show();
		} catch (IOException e) {
			
		}
		
		if (user == null){
			Context context = getApplicationContext();
    		CharSequence text = "Gebruiker niet gevonden!";
    		int duration = Toast.LENGTH_SHORT;
    		Toast toast = Toast.makeText(context, text, duration);
    		toast.show();
		}
		else if(! user.getPassword().equals(password)){
			Context context = getApplicationContext();
    		CharSequence text = "Onjuist wachtwoord bij gebruiker" + user.getUserId();;
    		int duration = Toast.LENGTH_SHORT;
    		Toast toast = Toast.makeText(context, text, duration);
    		toast.show();
		}
		
		else{
			LoginController.getInstance().login(user);
			if(remember)
				remember(username,password);
			Intent i = new Intent(this, MainMenu.class);
	        startActivity(i);
	        finish();
		}
		
	}

	private void remember(String username, String password) {
		LoginController.getInstance().remember(username,password,MobileToledo.this);
	}
}
