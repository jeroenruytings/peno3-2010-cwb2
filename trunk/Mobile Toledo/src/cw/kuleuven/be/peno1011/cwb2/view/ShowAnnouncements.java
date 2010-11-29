package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.InfoController;
import cw.kuleuven.be.peno1011.cwb2.model.Announcement;
import cw.kuleuven.be.peno1011.cwb2.model.Course;
import cw.kuleuven.be.peno1011.cwb2.model.MultipleChoice;

public class ShowAnnouncements extends ListActivity {
	
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  
		  try{
			  Bundle bundle = getIntent().getExtras();
			  final String courseTitle = (String) bundle.get("courseTitle");
			  
			  //TODO/knoppen om ipv recente announcements alle announcements of announcements per vak te bezien
			  
			  InfoController controller = new InfoController();
			  Course course = controller.findCourse(courseTitle);
			  final List<Announcement> courseAnnouncements = (List<Announcement>) controller.courseAnnouncements(course); //laatste 7 dagen
			  String[] displayStrings = controller.makeStrings(courseAnnouncements);
			  
			  setListAdapter(new ArrayAdapter<String>(this,
			          android.R.layout.simple_list_item_1, displayStrings));
			  ListView lv = getListView();
			  lv.setTextFilterEnabled(true);
			  lv.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,int position, long id) {						
					showOneAnnouncement(courseAnnouncements.get(courseAnnouncements.size()-position-1));	
					}
			  });
		  }
		  catch(NullPointerException ne){
			  //TODO/knoppen om ipv recente announcements alle announcements of announcements per vak te bezien
			  
			  InfoController controller = new InfoController();
			  final List<Announcement> recentAnnouncements = (List<Announcement>) controller.recentAnnouncements(7); //laatste 7 dagen
			  String[] displayStrings = controller.makeStrings(recentAnnouncements);
			  
			  setListAdapter(new ArrayAdapter<String>(this,
			          android.R.layout.simple_list_item_1, displayStrings));
			  ListView lv = getListView();
			  lv.setTextFilterEnabled(true);
			  lv.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					showOneAnnouncement(recentAnnouncements.get(recentAnnouncements.size()-position-1));	
					}
			  });
		  }
		  //TODO/knoppen om ipv recente announcements alle announcements of announcements per vak te bezien

	}
	private void showOneAnnouncement(Announcement announcement){
		AlertDialog.Builder ab=new AlertDialog.Builder(ShowAnnouncements.this);
		ab.setTitle(announcement.getTitle());
		ab.setMessage(announcement.getMessage());
        ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            	
            	
        }
        });
        ab.show();
	}
}