package com.android.ticketdodger;

import java.util.List;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

public class MainActivity extends MapActivity {

	private boolean canPark = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MapView mapView = (MapView) findViewById(R.id.mapview);
		//mapView.setBuiltInZoomControls(true);
		MapController mapController = mapView.getController();
		mapController.setCenter(new GeoPoint((int) (34.073297 * 1E+6),
				(int) (-118.398202 * 1E+6)));
		mapController.setZoom(18);
		MyLocationOverlay locationOverlay = new MyLocationOverlay(this, mapView);
		locationOverlay.enableMyLocation();
		Location location = new Location("LAProvider");
		location.setLatitude(34.073297);
		location.setLongitude(-118.398202);
		location.setAccuracy(2);
		List<Overlay> overlays = mapView.getOverlays();
		overlays.add(locationOverlay);
		mapView.postInvalidate();
		locationOverlay.onLocationChanged(location);
		Button canIParkButton = (Button) findViewById(R.id.can_i_park_here_button);
		// canIParkButton.setVisibility(View.GONE);
		canIParkButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						ParkActivity.class);

				intent.putExtra(ParkActivity.CAN_PARK, canPark);
				canPark = false;
				// intent.putExtra(ParkActivity.CAN_PARK, false);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
