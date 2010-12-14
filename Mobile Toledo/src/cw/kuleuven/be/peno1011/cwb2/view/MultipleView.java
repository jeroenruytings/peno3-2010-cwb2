package cw.kuleuven.be.peno1011.cwb2.view;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cw.kuleuven.be.peno1011.cwb2.R;
import cw.kuleuven.be.peno1011.cwb2.controller.InfoController;
import cw.kuleuven.be.peno1011.cwb2.controller.MainController;
import cw.kuleuven.be.peno1011.cwb2.model.Answer;
import cw.kuleuven.be.peno1011.cwb2.model.Lecture;
import cw.kuleuven.be.peno1011.cwb2.model.MultipleChoice;
import cw.kuleuven.be.peno1011.cwb2.model.User;

public class MultipleView extends Activity{    
       
        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
               
                try{
                     Bundle bundle = getIntent().getExtras();
                     int lectureId = (Integer) bundle.get("lecture");
                     String courseCode = (String) bundle.get("courseCode");
                     Lecture lecture = InfoController.getInstance().findLectureById(lectureId,courseCode);
                     User user = MainController.getInstance().getUser();
                                if(user.getRank()==1){
                                        poseMultiple(lecture);
                                }
                                else{
                                        answerMultiple(lecture);
                                }  
                }
                catch(NullPointerException ne){
                		Toast.makeText(MultipleView.this, "Geen les van dit vak gevonden die momenteel bezig is." , Toast.LENGTH_LONG).show();
                        finish();
                }
        }
       
        //TODO: saven naar database
        private boolean saveMultiple(){
                boolean isSaved = false;
                return isSaved;
        }
       
        //VRAAG BEANTWOORDEN
        private void answerMultiple(final Lecture lecture){
            requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);              
            setContentView(R.layout.multiplechoice);
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);    
            ((TextView)findViewById(R.id.titlebar)).setText("Meerkeuzevraag beantwoorden");
               
            MultipleChoice multiple = lecture.getMultipleChoice();
            final ArrayList<Answer> answers = multiple.getAnswers();
            ArrayList<String> answerStrings = new ArrayList<String>();
            for(int i=0;i<answers.size();i++){
            	answerStrings.add(answers.get(i).getAnswer());
            }
            String[] answerArray = (String[]) answerStrings.toArray();
//            final String[] options = multiple.getAnswers();
           
	        AlertDialog.Builder ab=new AlertDialog.Builder(MultipleView.this);
	                ab.setTitle(multiple.getQuestion());
	                ab.setSingleChoiceItems(answerArray, 0,new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	            }
	        });
	        //op ingeven drukken
	        ab.setPositiveButton("Ingeven", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	                Toast msg = Toast.makeText(MultipleView.this, Integer.toString(whichButton) , Toast.LENGTH_LONG);
	                msg.show();
	                MultipleChoice multiple = lecture.getMultipleChoice();
	                ArrayList<Answer> newAnswers = answers;
	                newAnswers.get(whichButton).setTotal(answers.get(whichButton).getTotal()+1);
	                multiple.setAnswers(answers);
	                if(saveMultiple()){
	                        TextView feedback = (TextView) findViewById(R.id.multiplefeedback);
	                        feedback.setText("Meerkeuzevraag beantwoordt");
	                }
	                else{
	                        TextView feedback = (TextView) findViewById(R.id.multiplefeedback);
	                        feedback.setText("Fout bij verzenden meerkeuzevraag");
	                }
	               
	        }
	        });
	        //op annuleer drukken
	        ab.setNegativeButton("Annuleer", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	                TextView feedback = (TextView) findViewById(R.id.multiplefeedback);
	                feedback.setText("Meerkeuzevraag niet beantwoordt");
	            }
	        });
	        ab.show();
        }
       
        //VRAAG STELLEN
        private void poseMultiple(final Lecture lecture){
            requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);              
            setContentView(R.layout.posemultiplechoice);
            getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);    
            ((TextView)findViewById(R.id.titlebar)).setText("Meerkeuzevraag stellen");
               
            final EditText mansw1 = (EditText) findViewById(R.id.mansw1);
            final EditText mansw2 = (EditText) findViewById(R.id.mansw2);
            final EditText mansw3 = (EditText) findViewById(R.id.mansw3);
            final EditText mansw4 = (EditText) findViewById(R.id.mansw4);
           
            mansw1.setOnKeyListener(new View.OnKeyListener() {
                        @Override
                        public boolean onKey(View v, int keyCode, KeyEvent event) {
                            TextView answ2 = (TextView) findViewById(R.id.answ2);
                            EditText mansw2 = (EditText) findViewById(R.id.mansw2);
                                answ2.setVisibility(View.VISIBLE);
                                mansw2.setVisibility(View.VISIBLE);
                                return false;
                        }
                });
            mansw2.setOnKeyListener(new View.OnKeyListener() {
                        @Override
                        public boolean onKey(View v, int keyCode, KeyEvent event) {
                            TextView answ3 = (TextView) findViewById(R.id.answ3);
                            EditText mansw3 = (EditText) findViewById(R.id.mansw3);
                                answ3.setVisibility(View.VISIBLE);
                                mansw3.setVisibility(View.VISIBLE);
                                return false;
                        }
                });
            mansw3.setOnKeyListener(new View.OnKeyListener() {
                        @Override
                        public boolean onKey(View v, int keyCode, KeyEvent event) {
                            TextView answ4 = (TextView) findViewById(R.id.answ4);
                            EditText mansw4 = (EditText) findViewById(R.id.mansw4);
                                answ4.setVisibility(View.VISIBLE);
                                mansw4.setVisibility(View.VISIBLE);
                                return false;
                        }
                });
            mansw4.setOnKeyListener(new View.OnKeyListener() {
                        @Override
                        public boolean onKey(View v, int keyCode, KeyEvent event) {
                            TextView answ5 = (TextView) findViewById(R.id.answ5);
                            EditText mansw5 = (EditText) findViewById(R.id.mansw5);
                                answ5.setVisibility(View.VISIBLE);
                                mansw5.setVisibility(View.VISIBLE);
                                return false;
                        }
                });
           
            Button resetbutton = (Button) findViewById(R.id.reset1);

        resetbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                //TODO/reset implementatie
            }

        });
        Button submitbutton = (Button) findViewById(R.id.submit1);

        submitbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                // METHODE IS VERKEERD, EEN MULTIPLECHOICE KAN ONEINDIG VEEL MOGELIJKE ANTWOORDEN HEBBEN
                EditText multiQuestion = (EditText) findViewById(R.id.submit1);
                String answ1 = mansw1.getText().toString();
                String answ2 = mansw2.getText().toString();
                String answ3 = mansw3.getText().toString();
                String answ4 = mansw4.getText().toString();
                EditText mansw5 = (EditText) findViewById(R.id.mansw1);
                String answ5 = mansw5.getText().toString();
                String question = multiQuestion.getText().toString();
                if(!question.equals("") || !answ1.equals("") || !answ2.equals("")){
                        ArrayList<Answer> answers = new ArrayList<Answer>();
                        answers.add(new Answer(0,0,answ1,new ArrayList<User>()));answers.add(new Answer(0,0,answ2,new ArrayList<User>()));
                        if(!answ3.equals("")){
                                answers.add(new Answer(0,0,answ3,new ArrayList<User>()));
                        }
                        if(!answ4.equals("")){
                                answers.add(new Answer(0,0,answ4,new ArrayList<User>()));
                        }
                        if(!answ5.equals("")){
                                answers.add(new Answer(0,0,answ5,new ArrayList<User>()));
                        }
//                        answers.add(answ3);answers.add(answ4);answers.add(answ5);
                        MultipleChoice multiple = new MultipleChoice(question, answers, 1);
                        //TODO/ in database steken
                }
                else{
                                      Toast.makeText(getApplicationContext(), "Gelieve een vraag en minstens 2 mogelijke antwoorden in te geven!",
                                  Toast.LENGTH_LONG).show();
                }
               
            }

        });
        }

}