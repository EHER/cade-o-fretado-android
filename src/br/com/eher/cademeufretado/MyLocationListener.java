package br.com.eher.cademeufretado;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class MyLocationListener implements LocationListener {

	private static final String LOCATION_ENDPOINT_URL = "";
	private Context context;

	public MyLocationListener(Context context) {
		this.context = context;
	}

	@Override
	public void onLocationChanged(Location location) {
		Toast.makeText(
				context,
				"New Location: " + location.getLatitude() + " "
						+ location.getLongitude(), Toast.LENGTH_SHORT).show();
		postLocation(location);
	}

	@Override
	public void onProviderDisabled(String arg0) {
		Toast.makeText(context, "stopping GPS", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onProviderEnabled(String arg0) {
		Toast.makeText(context, "starting GPS", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		Toast.makeText(context, "WTF?!", Toast.LENGTH_SHORT).show();
	}

	private void postLocation(Location location) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(LOCATION_ENDPOINT_URL);

		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("location[lat]", Double
				.toString(location.getLatitude())));
		pairs.add(new BasicNameValuePair("location[lng]", Double
				.toString(location.getLongitude())));

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(pairs));
			httpclient.execute(httpPost);
		} catch (ClientProtocolException e) {
			Toast.makeText(context,
					"ClientProtocolException: " + e.getMessage(),
					Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		} catch (IOException e) {
			Toast.makeText(context, "IOException: " + e.getMessage(),
					Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
	}
}
