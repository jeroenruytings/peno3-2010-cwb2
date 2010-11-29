package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.Date;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import cw.kuleuven.be.peno1011.cwb2.controller.MainController;
import cw.kuleuven.be.peno1011.cwb2.model.Course;
import cw.kuleuven.be.peno1011.cwb2.model.Lecture;

public class SelectCourse extends ListActivity{	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        
		try{
			  String[] courseTitles = getCourseTitles();
			  setListAdapter(new ArrayAdapter<String>(this,
			          android.R.layout.simple_list_item_1, courseTitles));
			  ListView lv = getListView();
			  lv.setTextFilterEnabled(true);
			  lv.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				        Bundle bundle = getIntent().getExtras();
				        Object nextView = null;
				        if(!bundle.equals(null))
				        {
					        nextView = bundle.get("nextview");
				        }

						if(!nextView.equals(null)){
		                	Course course = MainController.getUser().getIsp().getCourses().get(position);

		                	Intent intent = new Intent(SelectCourse.this,(Class<?>) nextView);
							if(nextView.equals(CourseDocuments.class)){
			                	intent.putExtra("course",MainController.getUser().getIsp().getCourses().get(position).getCourseName()); 
							}
							else{
								try{
				                	Lecture lecture = findLecture(course);
				                    intent.putExtra("lecture",lecture); 
				                }
				                catch(Exception e){
				                	Context context = getApplicationContext();
				            		CharSequence text = "Geen les van dit vak gevonden die momenteel bezig is.";
				            		Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
				            		toast.show();
				                }
							}
							startActivity(intent);
		            	}
					}
			  });
		  }
		  catch(NullPointerException n){
			  Toast.makeText(getApplicationContext(), "Fout bij het opvragen van de vakken.",
			          Toast.LENGTH_LONG).show();
		  }
	}
	public String[] getCourseTitles(){
		List<Course> courses = MainController.getUser().getIsp().getCourses();
		String[] courseTitles = new String[courses.size()];
		for(int i = 0;i< courses.size();i++){
			String course = courses.get(i).getCourseName();
			courseTitles[i] = course;
		}
		return courseTitles;
	}
	private Lecture findLecture(Course course){
        Date currentDate = new Date();
        List<Lecture> lectures = course.getLectures();
        Lecture lecture = null;
        //TODO/indien meerdere lectures(zoals ANA A-groep en B-groep tegelijk -> keuze geven
        for(int i=0;i<lectures.size();i++){
        	boolean isInBetween = currentDate.compareTo(lectures.get(i).getStartDate())>0 && currentDate.compareTo(lectures.get(i).getStopDate())<0;
        	if(isInBetween){
        		lecture = lectures.get(i);
        	}
        }
        return lecture;
	}

	
}
