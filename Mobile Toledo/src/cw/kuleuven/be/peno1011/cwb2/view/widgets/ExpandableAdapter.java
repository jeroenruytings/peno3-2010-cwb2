package cw.kuleuven.be.peno1011.cwb2.view.widgets;

import java.util.ArrayList;

import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.model.Announcement;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<String> groups;
    private ArrayList<ArrayList<Announcement>> children;

    public ExpandableAdapter(Context context, ArrayList<String> groups,
            ArrayList<ArrayList<Announcement>> children) {
        this.context = context;
        this.groups = groups;
        this.children = children;
    }


    public void addItem(Announcement announcement) {
        if (!groups.contains(announcement.getCourse().getCourseName())) {
            groups.add(announcement.getCourse().getCourseName());
        }
        int index = groups.indexOf(announcement.getCourse().getCourseName());
        if (children.size() < index + 1) {
            children.add(new ArrayList<Announcement>());
        }
        children.get(index).add(announcement);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return children.get(groupPosition).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
   
    // Return a child view. You can load your custom layout here.
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
            View convertView, ViewGroup parent) {
        Announcement announcement = (Announcement) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.tvChild);
        tv.setText("   " + announcement.getTitle());
        return convertView;
    }
    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
    }
    @Override
    public int getGroupCount() {
        return groups.size();
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        return children.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
            ViewGroup parent) {
        String group = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.group, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.tvGroup);
        tv.setText(group);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }
    
    @Override
    public boolean hasStableIds() {
        return true;
    }

}


