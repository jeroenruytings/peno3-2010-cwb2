package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import cw.kuleuven.be.peno1011.cwb2.controller.InfoController;
import cw.kuleuven.be.peno1011.cwb2.model.Announcement;

public class ShowAnnouncements extends ListActivity {
	
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  InfoController controller = new InfoController();
		  final List<Announcement> recentAnnouncements = (List<Announcement>) controller.recentAnnouncements(7); //laatste 7 dagen
		  String[] displayStrings = makeStrings(recentAnnouncements);
		  
		  setListAdapter(new ArrayAdapter<String>(this,
		          android.R.layout.simple_list_item_1, displayStrings));
		  ListView lv = getListView();
		  lv.setTextFilterEnabled(true);
		  lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				      Toast.makeText(getApplicationContext(), recentAnnouncements.get(recentAnnouncements.size()-position-1).getMessage(),
				          Toast.LENGTH_LONG).show();
				    }
		  });
	}
	
	public String[] makeStrings(List<Announcement> announcements){
		String[] displayStrings = new String[announcements.size()];
		for(int i = 0;i< announcements.size();i++){
			String displayString = announcements.get(i).getCourse().getCourseName() + ": " + announcements.get(i).getTitle();
			displayStrings[announcements.size()-i-1] = displayString;
		}
		return displayStrings;
	}
}