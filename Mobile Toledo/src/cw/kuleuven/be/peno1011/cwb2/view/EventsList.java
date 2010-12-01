package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.CalendarController;
import cw.kuleuven.be.peno1011.cwb2.model.Event;

public class EventsList extends ListActivity{
	public static EventsList self;
	ListView list;
	CalendarController controller = new CalendarController();
	int numberOfDays;
	
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  self=this;
		  
		  Bundle bundle = getIntent().getExtras();
		  final String span = (String) bundle.get("span");
		  
		  showEvents(span);
		  
	}
	public void makeAdapter(List<Event> eventsList){
		final List<Event> events = eventsList;
		 list = getListView();
		 EventAdapter adapter = new EventAdapter(this, events);
	     list.setAdapter(adapter);

		  list.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {						
					AlertDialog.Builder ab=new AlertDialog.Builder(EventsList.this);
					ab.setTitle(events.get(position).getTitle());
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
			  Toast.makeText(getApplicationContext(), "maand",
		          Toast.LENGTH_LONG).show();
		  }
		  makeAdapter(events);
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        	menu.add(0, Menu.FIRST, 0, "Lessen");
        	menu.add(0, 2, 0, "Feestjes");
        	menu.add(0, 3, 0, "Cultuur");
        return true;
    }
	
	@Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
            case Menu.FIRST:
      		  	List<Event> events1 = controller.getCategoryEvents(numberOfDays,"college");
      		  	makeAdapter(events1);
                return true;
            case 2:
  		  		List<Event> events2 = controller.getCategoryEvents(numberOfDays,"party");
      		  	makeAdapter(events2);
                return true;
            case 3:
  		  		List<Event> events3 = controller.getCategoryEvents(numberOfDays,"culture");
      		  	makeAdapter(events3);
                return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }
}
