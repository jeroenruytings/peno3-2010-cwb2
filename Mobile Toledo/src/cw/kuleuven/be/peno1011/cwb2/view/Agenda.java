package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.model.Lecture;

public class Agenda extends TabActivity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		
		Bundle bundle = getIntent().getExtras();
		try{
			final String day = (String) bundle.get("day");
		}
		catch(NullPointerException ne){
			
		}
//		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);		
		setContentView(R.layout.agenda);
		TextView title = (TextView) findViewById(R.id.titlebar);
		title.setText("Agenda voor (datum)" );
//		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);		
//        ((TextView)findViewById(R.id.titlebar)).setText("Agenda");
		
	    TabHost tabHost = getTabHost();  
	    TabHost.TabSpec spec; 

	    Intent intent1 = new Intent().setClass(this, EventsList.class);
	    intent1.putExtra("span", "day");
	    spec = tabHost.newTabSpec("day").setIndicator("Vandaag")
	                  .setContent(intent1);
	    tabHost.addTab(spec);
	
	    Intent intent2 = new Intent().setClass(this, EventsList.class);
	    intent2.putExtra("span", "week");
	    spec = tabHost.newTabSpec("week").setIndicator("Deze week")
	                  .setContent(intent2);
	    tabHost.addTab(spec);
	
	    Intent intent3 = new Intent().setClass(this, EventsList.class);
	    intent3.putExtra("span", "month");
	    spec = tabHost.newTabSpec("month").setIndicator("Deze maand")
	                  .setContent(intent3);
	    tabHost.addTab(spec);
	
	    tabHost.setCurrentTab(0);
	    
	    ImageView tab = (ImageView) findViewById(R.id.floatingtab);
	    tab.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(1);
			}
	    		
	    });
	}
	@Override
	protected Dialog onCreateDialog(int id) {
		Calendar calendar = Calendar.getInstance();
		int cyear = calendar.get(Calendar.YEAR);
		int cmonth = calendar.get(Calendar.MONTH);
		int cday = calendar.get(Calendar.DAY_OF_MONTH);
		switch (id) {
			case 1:
				return new DatePickerDialog(this,mDateSetListener,cyear, cmonth, cday);
			}
	return null;
	}
	
	private DatePickerDialog.OnDateSetListener mDateSetListener =new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			String selectedDate = String.valueOf(dayOfMonth)+" /"+String.valueOf(monthOfYear+1)+" /"+String.valueOf(year);
			Toast.makeText(Agenda.this, selectedDate, Toast.LENGTH_SHORT).show();
		}
	};
}
