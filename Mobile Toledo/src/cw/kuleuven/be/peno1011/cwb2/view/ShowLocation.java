package cw.kuleuven.be.peno1011.cwb2.view;



import cw.kuleuven.be.peno1011.cwb2.R;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowLocation extends Activity{
	
	private TextView latitudeField;
	private TextView longitudeField;
	
	
public void onCreate(Bundle savedInstanceState)
{
	super.onCreate(savedInstanceState);
	setContentView(R.layout.navigationmenu);
	latitudeField = (TextView) findViewById(1);
	longitudeField = (TextView) findViewById(1);

}

public void showLocation(View view)
{
	switch (view.getId()) {
	case R.id.ownlocation:
				LocationManager locationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
				Location location = locationmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		
				if(location != null)
				{
					int lat = (int) (location.getLatitude());
					int lng = (int) (location.getLongitude());
					latitudeField.setText(String.valueOf(lat));
					longitudeField.setText(String.valueOf(lng));
				}
				else
				{
					longitudeField.setText("Sorry, u werd niet gevonden");
					latitudeField.setText("Waar zit gij ergens?");
				}
		break;
		}
	}
}

