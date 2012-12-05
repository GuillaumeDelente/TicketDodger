package com.android.ticketdodger;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ParkActivity extends Activity {

	static public final String CAN_PARK = "can_park";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		boolean canPark = intent.getBooleanExtra(CAN_PARK, false);
		setContentView(canPark ? R.layout.activity_park_yes
				: R.layout.activity_park_no);
		Button alarmButton = (Button) findViewById(R.id.setup_alarm_button);
		if (alarmButton != null) {
			alarmButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					TimePickerDialog dialog = new TimePickerDialog(
							ParkActivity.this, null, 2, 12, true);
					dialog.setTitle("Remind me in :");
					dialog.show();
				}
			});
		}
		Button parkingLocationsButton = (Button) findViewById(R.id.show_me_where_to_park_button);
		if (parkingLocationsButton != null) {
			parkingLocationsButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					startActivity(new Intent(ParkActivity.this,
							ParkingLocationsActivity.class));
				}
			});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_park, menu);
		return true;
	}

}
