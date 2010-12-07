package cw.kuleuven.be.peno1011.cwb2.view;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.HttpException;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.CalendarController;
import cw.kuleuven.be.peno1011.cwb2.model.Building;
import cw.kuleuven.be.peno1011.cwb2.model.Event;
import cw.kuleuven.be.peno1011.cwb2.model.GPSLocation;
import cw.kuleuven.be.peno1011.cwb2.view.widgets.EventAdapter;

public class EventsList extends ListActivity{
	ListView list;
	CalendarController controller = new CalendarController();
	int numberOfDays;
	String span;
	
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  
		  Bundle bundle = getIntent().getExtras();
		  span = (String) bundle.get("span");
		  
		  try {
			showEvents(span);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	}
	public void onResume (){
		super.onResume();
		try {
			showEvents(span);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void makeAdapter(List<Event> eventsList){
		 final List<Event> events = eventsList;
		 list = getListView();
		 EventAdapter adapter = new EventAdapter(this, events);
	     list.setAdapter(adapter);
		 list.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {	
					LayoutInflater inflater=LayoutInflater.from(EventsList.this);
					View eventView=inflater.inflate(R.layout.eventview, null);
					TextView descr = (TextView) eventView.findViewById(R.id.eventdescr);
					Event event = events.get(position);
					setCategory(event,eventView);
			        descr.setText(event.getDescription());
			        
			        TextView loc = (TextView) eventView.findViewById(R.id.eventloc2);
			        loc.setText("MOET NOG AANGEVULD WORDEN!");
//			        TODO final GPSLocation location = event.getPlace();
//			        if(location instanceof Building){
//			        	loc.setText(((Building) location).getName());
//			    	}
//			        else if(location instanceof GPSLocation){
//			        	loc.setText(location.getStreet() + " " + location.getNumber());
//			        }
			        ImageView marker = (ImageView) eventView.findViewById(R.id.locmarker);
			        OnClickListener listener =new OnClickListener(){

						@Override
						public void onClick(View arg0) {
							Intent intent = new Intent(EventsList.this,GetInfo.class);
							//TODO intent.putExtra("", ((Building) location).getName());
							
						}
			        	
			        };
			        loc.setOnClickListener(listener);marker.setOnClickListener(listener);
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
	public void showEvents(String span) throws HttpException, IOException{
		List<Event> events = new ArrayList<Event>();
		  if(span.equals("day")){
			  events = controller.getEvents(1);
			  numberOfDays=1;
		  }
		  else if(span.equals("week")){
			  events = controller.getEvents(7);
			  numberOfDays=7;
		  }
		  else{
			  events = controller.getEvents(30);
			  numberOfDays=30;
		  }
		  makeAdapter(events);
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        	menu.add(0, 1, 0, "Lessen");
        	menu.add(0, 2, 0, "Feestjes");
        	menu.add(0, 3, 0, "Cultuur");
//        	menu.add(1,4,0, "Naar datum");
        return true;
    }
	
	@Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch(item.getItemId()) {
//        case Menu.FIRST:
//        	final String[] options = {"Lessen","Feestjes","Cultuur"};
//        	List<String> selected = new ArrayList<String>();
//        	AlertDialog.Builder ab=new AlertDialog.Builder(EventsList.this);
//    		ab.setTitle("Selecteer weer te geven categorieën");
//    		ab.setMultiChoiceItems(options, new boolean[]{false, true, false},new DialogInterface.OnMultiChoiceClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int pos, boolean isChecked) {
//					// TODO Auto-generated method stub
//					
//				}
//            });
//            ab.setPositiveButton("Ingeven", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int whichButton) {
//                	
//                	
//            }
//            });
//            ab.setNegativeButton("Annuleer", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int whichButton) {
//                }
//            });
//        	return true;
            case 1:
			List<Event> events1 = null;
			try {
				events1 = controller.getCategoryEvents(numberOfDays,"college");
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      		  	makeAdapter(events1);
                return true;
            case 2:
			List<Event> events2 = null;
			try {
				events2 = controller.getCategoryEvents(numberOfDays,"party");
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      		  	makeAdapter(events2);
                return true;
            case 3:
			List<Event> events3 = null;
			try {
				events3 = controller.getCategoryEvents(numberOfDays,"culture");
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      		  	makeAdapter(events3);
                return true;
//            case 4:
//            	showDialog(1);
//        	return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }
	public void setCategory(Event event, View view){
		if(event.getCategorie().equals("college")) {
        	TextView blueCategory = (TextView) view.findViewById(R.id.bcat2);
        	blueCategory.setVisibility(TextView.VISIBLE);
        }
        else if(event.getCategorie().equals("party")) {
        	TextView redCategory = (TextView) view.findViewById(R.id.rcat2);
        	redCategory.setVisibility(TextView.VISIBLE);
        }
        else if(event.getCategorie().equals("culture")) {
        	TextView orangeCategory = (TextView) view.findViewById(R.id.ocat2);
        	orangeCategory.setVisibility(TextView.VISIBLE);
        }
        else{
        	TextView noCategory = (TextView) view.findViewById(R.id.nocat2);
        	noCategory.setVisibility(TextView.VISIBLE);
        }
	}
//	@Override
//	protected Dialog onCreateDialog(int id) {
//		Calendar calendar = Calendar.getInstance();
//		int cyear = calendar.get(Calendar.YEAR);
//		int cmonth = calendar.get(Calendar.MONTH);
//		int cday = calendar.get(Calendar.DAY_OF_MONTH);
//		switch (id) {
//			case 1:
//				return new DatePickerDialog(this,mDateSetListener,cyear, cmonth, cday);
//			}
//	return null;
//	}
//	
//	private DatePickerDialog.OnDateSetListener mDateSetListener =new DatePickerDialog.OnDateSetListener() {
//		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//			String selectedDate = String.valueOf(monthOfYear+1)+" /"+String.valueOf(dayOfMonth)+" /"+String.valueOf(year);
//			Toast.makeText(EventsList.this, "Selected Date is ="+selectedDate, Toast.LENGTH_SHORT).show();
//		}
//	};
	
}
