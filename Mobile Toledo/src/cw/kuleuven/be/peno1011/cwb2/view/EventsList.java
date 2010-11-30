package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.model.Event;

public class EventsList extends ListActivity{
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  
		  Bundle bundle = getIntent().getExtras();
		  final String span = (String) bundle.get("span");
		  
		  Event e1 = new Event("test","test",null,"titel", new Date(),new Date());
		  Event e2 = new Event("titel","descr",null,"categorie", new Date(),new Date());
		  List<Event> events = new ArrayList<Event>();
		  events.add(e1);
		  events.add(e2);
		  makeAdapter(events);
		  
		  if(span.equals("day")){
			  
		  }
		  else if(span.equals("week")){
			  
		  }
		  else{
			  
		  }
		  
	}
	public void makeAdapter(List<Event> events){
		 ListView list = getListView();
		 EventAdapter adapter = new EventAdapter(this, events);
	        
	     list.setAdapter(adapter);

//		  setListAdapter(new EventAdapter(this,
//		          events ));
//		  ListView lv = getListView();
//		  lv.setTextFilterEnabled(true);
		  list.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {						
					//	
				}
		  });
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, Menu.FIRST, 0, R.string.menu_newuser);
        return true;
    }
	
	@Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
            case Menu.FIRST:
                //
                return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }
}
