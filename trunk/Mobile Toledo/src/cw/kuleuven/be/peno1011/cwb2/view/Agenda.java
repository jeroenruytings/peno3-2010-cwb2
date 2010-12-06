package cw.kuleuven.be.peno1011.cwb2.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import cw.kuleuven.be.peno1011.cwb2.R;

public class Agenda extends TabActivity{
	private TextView title;
	private boolean isMainAgenda = false;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		
		Bundle bundle = getIntent().getExtras();
		Date date = new Date();
		try{
			date = (Date) bundle.get("date");
		}
		catch(NullPointerException ne){
			
		}

//		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);		
		setContentView(R.layout.agenda);
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");	
		title = (TextView) findViewById(R.id.titlebar);
		title.setText("Agenda voor " + sdf1.format(date));
//		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);		
//        ((TextView)findViewById(R.id.titlebar)).setText("Agenda");
		
	    TabHost tabHost = getTabHost();  
	    TabHost.TabSpec spec; 

	    Intent intent1 = new Intent().setClass(this, EventsList.class);
	    intent1.putExtra("span", "day");
	    intent1.putExtra("date", date);
	    spec = tabHost.newTabSpec("day").setIndicator("Deze dag")
	                  .setContent(intent1);
	    tabHost.addTab(spec);
	
	    Intent intent2 = new Intent().setClass(this, EventsList.class);
	    intent2.putExtra("span", "week");
	    intent2.putExtra("date", date);
	    spec = tabHost.newTabSpec("week").setIndicator("Deze week")
	                  .setContent(intent2);
	    tabHost.addTab(spec);
	    
	    Date current = new Date();
	    if(date.getDate()==(current.getDate())&& date.getMonth()==current.getMonth() && date.getYear()==current.getYear()){
	    	Intent intent3 = new Intent().setClass(this, EventsList.class);
		    intent3.putExtra("span", "month");
		    intent3.putExtra("date", date);
		    spec = tabHost.newTabSpec("month").setIndicator("Deze maand")
		                  .setContent(intent3);
		    tabHost.addTab(spec);
		    isMainAgenda = true;
	    }
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
			Calendar cal = Calendar.getInstance();
			cal.set(year, monthOfYear, dayOfMonth,0,0);
			Date date = cal.getTime();
			Intent i = new Intent(Agenda.this, Agenda.class);
			i.putExtra("date", date);
	        startActivity(i);
	        if(!isMainAgenda){
	        	finish();
	        }
		}
	};
}
