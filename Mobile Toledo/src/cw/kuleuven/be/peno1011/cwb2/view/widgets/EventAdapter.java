package cw.kuleuven.be.peno1011.cwb2.view.widgets;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.model.Building;
import cw.kuleuven.be.peno1011.cwb2.model.Event;
import cw.kuleuven.be.peno1011.cwb2.model.GPSLocation;

public class EventAdapter extends BaseAdapter implements OnClickListener {
	    private Context context;
	    private List<Event> events;

	    public EventAdapter(Context context, List<Event> events) {
	        this.context = context;
	        this.events = events;
	    }

	    public View getView(int position, View convertView, ViewGroup viewGroup) {
	        if (convertView == null) {
	            LayoutInflater inflater = (LayoutInflater) context
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = inflater.inflate(R.layout.listevent, null);
	        }
	        Event event = events.get(position);
	     
	        TextView title = (TextView) convertView.findViewById(R.id.eventtitle);
	        title.setText(event.getTitle());
	        
	        TextView loc = (TextView) convertView.findViewById(R.id.eventloc);
	        GPSLocation location = event.getPlace();
	        if(location instanceof Building){
	        	loc.setText(((Building) location).getName());
	    	}
	        else if(location instanceof GPSLocation){
	        	loc.setText(location.getStreet() + " " + location.getNumber());
	        }
	        TextView date = (TextView) convertView.findViewById(R.id.eventdate);
	        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy hh:mm");
	        SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm");
	        Date startDate = event.getStartDate();Date stopDate = event.getStopDate();
	        
	        date.setText(sdf1.format(startDate) + " - " + sdf2.format(stopDate));
	        

	        if(event.getCategory().equals("college")) {
	        	TextView blueCategory = (TextView) convertView.findViewById(R.id.bcat);
	        	blueCategory.setVisibility(TextView.VISIBLE);
	        }
	        else if(event.getCategory().equals("party")) {
	        	TextView redCategory = (TextView) convertView.findViewById(R.id.rcat);
	        	redCategory.setVisibility(TextView.VISIBLE);
	        }
	        else if(event.getCategory().equals("culture")) {
	        	TextView orangeCategory = (TextView) convertView.findViewById(R.id.ocat);
	        	orangeCategory.setVisibility(TextView.VISIBLE);
	        }
	        else{
	        	TextView noCategory = (TextView) convertView.findViewById(R.id.nocat);
	        	noCategory.setVisibility(TextView.VISIBLE);
	        }

	        return convertView;
	    }
	    
	    public int getCount() {
	        return events.size();
	    }

	    public Object getItem(int position) {
	        return events.get(position);
	    }

	    public long getItemId(int position) {
	        return position;
	    }

	    @Override
	    public void onClick(View view) {
	        Event event = (Event) view.getTag();
	        notifyDataSetChanged();

	    }




}
