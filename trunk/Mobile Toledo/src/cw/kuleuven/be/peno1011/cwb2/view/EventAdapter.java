package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.model.Event;

public class EventAdapter extends BaseAdapter implements OnClickListener {
	    private Context context;
	    private List<Event> events;

	    public EventAdapter(Context context, List<Event> events) {
	        this.context = context;
	        this.events = events;
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

	    public View getView(int position, View convertView, ViewGroup viewGroup) {
	        Event event = events.get(position);
	        if (convertView == null) {
	            LayoutInflater inflater = (LayoutInflater) context
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView = inflater.inflate(R.layout.listevent, null);
	        }
	        TextView title = (TextView) convertView.findViewById(R.id.eventtitle);
	        title.setText(event.getTitle());

	        TextView date = (TextView) convertView.findViewById(R.id.eventdate);
	        date.setText(event.getStartDate().toGMTString() + event.getStartDate().getHours() + ":" + event.getStartDate().getMinutes());

	        return convertView;
	    }

	    @Override
	    public void onClick(View view) {
	        Event event = (Event) view.getTag();
	        // listPhonebook.remove(view.getId());
	        notifyDataSetChanged();

	    }




}
