package cw.kuleuven.be.peno1011.cwb2.view;

import cw.kuleuven.be.peno1011.cwb2.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MainMenu extends Activity {
	
    protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	    
	    setContentView(R.layout.mainmenu);
	    setTitle(R.string.mainmenu);
	    
	    ImageButton navigationButton = (ImageButton) findViewById(R.id.compassbutton);
	    navigationButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainMenu.this,NavigationMenu.class);
				startActivity(intent);
			}
	    });
	    ImageButton interactionButton = (ImageButton) findViewById(R.id.interactionbutton);
	    interactionButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MainMenu.this,InteractionMenu.class);
				startActivity(intent);
			}
	    });
    }
    
}
