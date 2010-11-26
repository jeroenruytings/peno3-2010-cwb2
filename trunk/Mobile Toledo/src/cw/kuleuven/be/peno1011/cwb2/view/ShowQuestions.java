package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.List;

import cw.kuleuven.be.peno1011.cwb2.controller.InfoController;
import cw.kuleuven.be.peno1011.cwb2.model.Announcement;
import cw.kuleuven.be.peno1011.cwb2.model.Question;
import cw.kuleuven.be.peno1011.cwb2.R;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ShowQuestions extends ListActivity {
	
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
	
		  //TODO een QuestionController schrijven
		  
//		  QuestionController controller = new QuestionController();
//		  final List<Question> questions = (List<Question>) controller.question //TODO question
//		  String[] displayStrings = makeStrings(questions);
		  
/*		  setListAdapter(new ArrayAdapter<String>(this,
		          android.R.layout.simple_list_item_1, displayStrings));
		  ListView lv = getListView();
		  lv.setTextFilterEnabled(true);
		  lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				      Toast.makeText(getApplicationContext(), questions.get(questons.size()-position-1).getMessage(),
				          Toast.LENGTH_LONG).show();
				    }
		  });
		  
	*/	  
		  
	}
}

