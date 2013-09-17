package br.com.eher.cademeufretado;

import android.app.Service;
import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;
import android.widget.Toast;

public class LocationService extends Service {

	private static final int MIN_MILLISECONDS_BEFORE_UPDATE_LOCATION = 1000 * 30;
	private static final int MIN_METERS_BEFORE_UPDATE_LOCATION = 100;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	public void onDestroy() {
		Toast.makeText(this, "stopping location service", Toast.LENGTH_SHORT).show();
	}

	public void onCreate() {
		Toast.makeText(this, "starting location service", Toast.LENGTH_SHORT).show();
		startMyLocationListener();
	}

	private void startMyLocationListener() {
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		locationManager
				.requestLocationUpdates(LocationManager.GPS_PROVIDER,
						MIN_MILLISECONDS_BEFORE_UPDATE_LOCATION,
						MIN_METERS_BEFORE_UPDATE_LOCATION,
						new MyLocationListener(this));
	}
}
