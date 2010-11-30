package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
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
	ListView list;
	CalendarController controller;
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  
		  Bundle bundle = getIntent().getExtras();
		  final String span = (String) bundle.get("span");
		  
		  Event e1 = new Event("Stelsels differentiaalvergelijkingen","beschr",null,"college", new Date(),new Date());
		  Event e2 = new Event("DubbelTD","descr",null,"party", new Date(),new Date());
		  Event e3 = new Event("Dynamica","descr",null,"bll", new Date(),new Date());
		  Event e4 = new Event("Dynamica","descr",null,"college", new Date(),new Date());
		  Event e5 = new Event("Dynamica","descr",null,"college", new Date(),new Date());
		  Event e6 = new Event("Dynamica","descr",null,"college", new Date(),new Date());
		  Event e7 = new Event("Dynamica","descr",null,"college", new Date(),new Date());
		  List<Event> events = new ArrayList<Event>();
		  events.add(e1);
		  events.add(e2);events.add(e3);events.add(e4);events.add(e5);events.add(e6);events.add(e7);
		  makeAdapter(events);

		  if(span.equals("day")){
			  
		  }
		  else if(span.equals("week")){
			  
		  }
		  else{
			  
		  }
		  
	}
	public void makeAdapter(List<Event> eventsList){
		final List<Event> events = eventsList;
		 list = getListView();
		 EventAdapter adapter = new EventAdapter(this, events);
	        
	     list.setAdapter(adapter);

//		  setListAdapter(new EventAdapter(this,
//		          events ));
//		  ListView lv = getListView();
//		  lv.setTextFilterEnabled(true);
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
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        	menu.add(0, Menu.FIRST, 0, "Uurrooster");
        	menu.add(0, 2, 0, "Feestjes");
        	menu.add(0, 3, 0, "Cultuur");
        return true;
    }
	
	@Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
            case Menu.FIRST:
            	Event e1 = new Event("Stelsels differentiaalvergelijkingen","beschr",null,"college", new Date(),new Date());
      		  	List<Event> events = new ArrayList<Event>();
      		  	events.add(e1);
//      		  	List<Event> events = controller.getCategoryEvents("college");
      		  	makeAdapter(events);
                return true;
            case 2:
//  		  	List<Event> events = controller.getCategoryEvents("party");
            	Event e2 = new Event("Dynamica","descr",null,"party", new Date(),new Date());
      		  	List<Event> events2 = new ArrayList<Event>();
      		  	events2.add(e2);
      		  	makeAdapter(events2);
                return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }
}
