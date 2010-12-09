package cw.kuleuven.be.peno1011.cwb2.model;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MapOverlay extends ItemizedOverlay {
	
	private ArrayList<OverlayItem> Overlay = new ArrayList<OverlayItem>();
	//maak een nieuwe mapoverlay
	public MapOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}

	@Override
	protected OverlayItem createItem(int i) {
		return Overlay.get(i);
	}

	@Override
	public int size() {
		return Overlay.size();
	}
	//voeg nieuwe overlay toe
	public void addOverlay(OverlayItem overlay) {
	    Overlay.add(overlay);
	    populate();
	}
	
}


