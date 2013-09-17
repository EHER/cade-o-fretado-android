package br.com.eher.cademeufretado;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_start_location_service:
			startLocationService();
			return true;
		case R.id.action_stop_location_service:
			stopLocationService();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startLocationService();
	}

	private void startLocationService() {
		startService(new Intent(this, LocationService.class));
	}

	private void stopLocationService() {
		stopService(new Intent(this, LocationService.class));
	}
}
