package cw.kuleuven.be.peno1011.cwb2.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.CalendarController;
import cw.kuleuven.be.peno1011.cwb2.model.Building;
import cw.kuleuven.be.peno1011.cwb2.model.Event;
import cw.kuleuven.be.peno1011.cwb2.model.GPSLocation;
import cw.kuleuven.be.peno1011.cwb2.view.widgets.EventAdapter;

public class EventsList extends ListActivity{
	public static EventsList self;
	ListView list;
	CalendarController controller = new CalendarController();
	int numberOfDays;
	
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  self=this;
		  
		  Bundle bundle = getIntent().getExtras();
		  String span = (String) bundle.get("span");
		  
		  showEvents(span);
		  
	}
	
	public void makeAdapter(List<Event> eventsList){
		final List<Event> events = eventsList;
		 list = getListView();
		 EventAdapter adapter = new EventAdapter(this, events);
	     list.setAdapter(adapter);
	     final Context context = this;
		  list.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {	
					LayoutInflater inflater=LayoutInflater.from(context);
					View eventView=inflater.inflate(R.layout.eventview, null);
					TextView descr = (TextView) eventView.findViewById(R.id.eventdescr);
					Event event = events.get(position);
					setCategory(event,eventView);
			        descr.setText(event.getDescription());
			        
			        TextView loc = (TextView) eventView.findViewById(R.id.eventloc2);
			        GPSLocation location = event.getPlace();
			        if(location instanceof Building){
			        	loc.setText(((Building) location).getName());
			    	}
			        else if(location instanceof GPSLocation){
			        	loc.setText(location.getStreet() + " " + location.getNumber());
			        }
			        TextView date = (TextView) eventView.findViewById(R.id.eventdate2);
			        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy hh:mm");
			        SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm");
			        Date startDate = event.getStartDate();Date stopDate = event.getStopDate();
			        date.setText(sdf1.format(startDate) + " - " + sdf2.format(stopDate));
					
					AlertDialog.Builder ab=new AlertDialog.Builder(EventsList.this);
					ab.setTitle(events.get(position).getTitle());
					ab.setView(eventView);
			        ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int whichButton) {
			        }
			        });
			        ab.show();
				}
		  });
	}
	public void showEvents(String span){
		List<Event> events = new ArrayList<Event>();
		  if(span.equals("day")){
			  events = controller.getEvents(1);
			  numberOfDays=1;
//			  Toast.makeText(getApplicationContext(), "dag",
//			          Toast.LENGTH_LONG).show();
		  }
		  else if(span.equals("week")){
			  events = controller.getEvents(7);
			  numberOfDays=7;
//			  Toast.makeText(getApplicationContext(), "week",
//			          Toast.LENGTH_LONG).show();
		  }
		  else{
			  events = controller.getEvents(30);
			  numberOfDays=30;
//			  Toast.makeText(getApplicationContext(), "maand",
//		          Toast.LENGTH_LONG).show();
		  }
		  makeAdapter(events);
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        
        	menu.add(0, Menu.FIRST, 0, "Categorieën weergeven");
//        	menu.add(0, 2, 0, "Feestjes");
//        	menu.add(0, 3, 0, "Cultuur");
        	menu.add(1,2,0, "Ga naar datum");
        return true;
    }
	
	@Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
        case Menu.FIRST:
        		//maak keuzelijst
        	return true;
        case 2:
        		//maak calendarpicker
        	return true;
//            case Menu.FIRST:
//      		  	List<Event> events1 = controller.getCategoryEvents(numberOfDays,"college");
//      		  	makeAdapter(events1);
//                return true;
//            case 2:
//  		  		List<Event> events2 = controller.getCategoryEvents(numberOfDays,"party");
//      		  	makeAdapter(events2);
//                return true;
//            case 3:
//  		  		List<Event> events3 = controller.getCategoryEvents(numberOfDays,"culture");
//      		  	makeAdapter(events3);
//                return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }
	public void setCategory(Event event, View view){
		if(event.getCategory().equals("college")) {
        	TextView blueCategory = (TextView) view.findViewById(R.id.bcat2);
        	blueCategory.setVisibility(TextView.VISIBLE);
        }
        else if(event.getCategory().equals("party")) {
        	TextView redCategory = (TextView) view.findViewById(R.id.rcat2);
        	redCategory.setVisibility(TextView.VISIBLE);
        }
        else if(event.getCategory().equals("culture")) {
        	TextView orangeCategory = (TextView) view.findViewById(R.id.ocat2);
        	orangeCategory.setVisibility(TextView.VISIBLE);
        }
        else{
        	TextView noCategory = (TextView) view.findViewById(R.id.nocat2);
        	noCategory.setVisibility(TextView.VISIBLE);
        }
	}
}
