package cw.kuleuven.be.peno1011.cwb2.view;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.InfoController;
import cw.kuleuven.be.peno1011.cwb2.model.Announcement;

public class TestList extends Activity implements Runnable
{
    private ExpandableAdapter adapter;

    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);	
    	
    	requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);		
		setContentView(R.layout.testlist);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);		
        ((TextView)findViewById(R.id.titlebar)).setText("Aankondigingen per vak");

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
       
        listView.setOnChildClickListener(new OnChildClickListener()
        {
           
            @Override
            public boolean onChildClick(ExpandableListView arg0, View arg1, int arg2, int arg3, long arg4)
            {
                
                return false;
            }
        });
       
        listView.setOnGroupClickListener(new OnGroupClickListener()
        {
           
            @Override
            public boolean onGroupClick(ExpandableListView arg0, View arg1, int arg2, long arg3)
            {
                
                return false;
            }
        });

        adapter = new ExpandableAdapter(this, new ArrayList<String>(),
                new ArrayList<ArrayList<Announcement>>());

        listView.setAdapter(adapter);

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run()
    {
        InfoController controller = new InfoController();
        List<Announcement> announcements = controller.recentAnnouncements(7);
        int i = 0;
        while (i != announcements.size()-1)
        {
            adapter.addItem(announcements.get(i));
            i++;
            
            handler.sendEmptyMessage(1);
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private Handler handler = new Handler()
    {

        @Override
        public void handleMessage(Message msg)
        {
            adapter.notifyDataSetChanged();
            super.handleMessage(msg);
        }

    };
}


