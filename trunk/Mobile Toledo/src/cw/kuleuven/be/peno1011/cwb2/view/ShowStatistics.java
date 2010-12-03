package cw.kuleuven.be.peno1011.cwb2.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import cw.kuleuven.be.peno1011.cwb2.R;

public class ShowStatistics extends Activity {

	
	//public class ShowStatistics extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
	         super.onCreate(savedInstanceState);

	         setContentView(R.layout.statistics);

	         final ProgressBar stata = (ProgressBar) findViewById(R.id.sa);
	         stata.setProgress(50);
	         
	         final ProgressBar statb = (ProgressBar) findViewById(R.id.sb);
	         statb.setProgress(50);
	         
	         final ProgressBar statc = (ProgressBar) findViewById(R.id.sc);
	         statc.setProgress(50);
	         
	         final ProgressBar statd = (ProgressBar) findViewById(R.id.sd);
	         statc.setProgress(50);
	         
	         final ProgressBar state = (ProgressBar) findViewById(R.id.se);
	         statc.setProgress(50);
	        
	         

/*	         // Start lengthy operation in a background thread
	         new Thread(new Runnable() {
	             public void run() {
	                 while (mProgressStatus < 100) {
	                     mProgressStatus = 'Ons getal' ;

	                     // Update the progress bar
	                     mHandler.post(new Runnable() {
	                         public void run() {
	                             mProgress.setProgress(mProgressStatus);
	                         }
	                     });
	                 }
	             }
	         }).start();*/
	     }
	 }
	 

