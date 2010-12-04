package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.InfoController;
import cw.kuleuven.be.peno1011.cwb2.model.Announcement;
import cw.kuleuven.be.peno1011.cwb2.view.widgets.ExpandableAdapter;

public class ShowAnnouncements extends Activity {
    private ExpandableAdapter adapter;
    private InfoController controller = new InfoController();

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);     
        
        try{
            Bundle bundle = getIntent().getExtras();
            final String courseTitle = (String) bundle.get("courseTitle");
            requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);            
            setContentView(R.layout.showannouncements);
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);         
            ((TextView)findViewById(R.id.titlebar)).setText("Aankondigingen");
    
            ExpandableListView listView = (ExpandableListView) findViewById(R.id.list);
            
            adapter = new ExpandableAdapter(this, new ArrayList<String>(),
                    new ArrayList<ArrayList<Announcement>>());
    
            listView.setAdapter(adapter);
            final List<Announcement> announcements = controller.recentAnnouncements(7);
            int i = 0;
            while (i != announcements.size()-1)
            {
                adapter.addItem(announcements.get(i));
                i++;
                
            }
            
            listView.setOnChildClickListener(new OnChildClickListener()
            {
                    @Override
                public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id)
                {
                            String title = (String) adapter.getChild(groupPosition, childPosition);
                            Announcement announcement = controller.findAnnouncement(announcements,title);
                    showOneAnnouncement(announcement);
                    return false;
                }
            });
                
        }
        catch(NullPointerException ne){
        	setContentView(R.layout.showannouncements);  
            final List<Announcement> announcements = controller.recentAnnouncements(7);//TODO allannouncements

            ExpandableListView listView = (ExpandableListView) findViewById(R.id.list);
            
            listView.setOnChildClickListener(new OnChildClickListener()
            {
                    @Override
                public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id)
                {
//                            String title = (String) adapter.getChild(groupPosition, childPosition);
                            Announcement announcement = (Announcement) adapter.getChild(groupPosition, childPosition);
                    showOneAnnouncement(announcement);
                    return false;
                }
            });
            adapter = new ExpandableAdapter(this, new ArrayList<String>(),
                    new ArrayList<ArrayList<Announcement>>());
           listView.setAdapter(adapter);
            int i = 0;
            while (i != announcements.size()-1)
            {
                adapter.addItem(announcements.get(i));
                i++;
            }
        }
        
        
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
