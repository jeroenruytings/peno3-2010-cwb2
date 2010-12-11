package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import cw.kuleuven.be.peno1011.cwb2.controller.InfoController;
import cw.kuleuven.be.peno1011.cwb2.model.Announcement;
import cw.kuleuven.be.peno1011.cwb2.model.Course;
import cw.kuleuven.be.peno1011.cwb2.view.widgets.ExpandableAdapter;

public class CourseAnnouncements extends ListActivity {
    private ExpandableAdapter adapter;
    private InfoController controller = InfoController.getInstance();

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);     

            Bundle bundle = getIntent().getExtras();
            Course course = (Course) bundle.get("course");

            final List<Announcement> courseAnnouncements = (List<Announcement>) controller.courseAnnouncements(course);
            String[] displayStrings = controller.makeStrings(courseAnnouncements);
        	ListView lv = getListView();
        	setListAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, displayStrings));
            lv.setTextFilterEnabled(true);
            lv.setOnItemClickListener(new OnItemClickListener() {
                          @Override
                          public void onItemClick(AdapterView<?> parent, View view,int position, long id) {                                               
                        	  showOneAnnouncement(courseAnnouncements.get(courseAnnouncements.size()-position-1));    
                          }
            });
                     
    }
    private void showOneAnnouncement(Announcement announcement){
                AlertDialog.Builder ab=new AlertDialog.Builder(CourseAnnouncements.this);
                ab.setTitle(announcement.getTitle());
                ab.setMessage(announcement.getMessage());
        ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                
                
        }
        });
        ab.show();
        }
}
