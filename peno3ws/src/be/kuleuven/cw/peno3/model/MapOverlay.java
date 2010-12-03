//package be.kuleuven.cw.peno3.model;
//
//import java.util.ArrayList;
//
//import android.graphics.drawable.Drawable;
//
//import com.google.android.maps.ItemizedOverlay;
//import com.google.android.maps.OverlayItem;
//
//public class MapOverlay extends ItemizedOverlay {
//	
//	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
//	
//	public MapOverlay(Drawable defaultMarker) {
//		super(boundCenterBottom(defaultMarker));
//	}
//
//	@Override
//	protected OverlayItem createItem(int i) {
//		return mOverlays.get(i);
//	}
//
//	@Override
//	public int size() {
//		return mOverlays.size();
//	}
//	
//	public void addOverlay(OverlayItem overlay) {
//	    mOverlays.add(overlay);
//	    populate();
//	}
//	
//}
//

