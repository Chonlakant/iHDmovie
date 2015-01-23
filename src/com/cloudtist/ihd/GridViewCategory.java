package com.cloudtist.ihd;

import info.ihd.customlistviewvolley.app.AppController;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.cloudtist.ihd.R;

public class GridViewCategory extends Activity {
	private ProgressDialog pDialog;
	CustomJsonGridViewAdapter jsonAdapter;
	GridView gridView;
	ArrayList<Movie> movieList = new ArrayList<Movie>();
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	public AQuery aq;
	String url;
	String is;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview_movie);
		aq = new AQuery(this);

		is = getIntent().getStringExtra("is");
		url = getIntent().getStringExtra("url");

		aq.ajax(url, JSONObject.class, this, "getJson");


	}

	public void getJson(String url, JSONObject jo, AjaxStatus status)
			throws JSONException {
		final Context context = this;
		final String TAG = "CHECKTAG";
		gridView = (GridView) findViewById(R.id.gridView1);
		jsonAdapter = new CustomJsonGridViewAdapter(getBaseContext(), movieList);
		gridView.setAdapter(jsonAdapter);

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {

				final Dialog dialog = new Dialog(context);

				dialog.setContentView(R.layout.detail);
				dialog.setTitle(movieList.get(position).getTitle());

				Button btn1 = (Button) dialog.findViewById(R.id.button1);
				btn1.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						Intent i2 = new Intent(getBaseContext(),
								HelloInterruptVideoStream.class);
						//
						String videoUrl = movieList.get(position).getVdoUrl();
						String imageUrl = movieList.get(position).getImgUrl();
						String title = movieList.get(position).getTitle();
						// Log.e("heyheyhey", index + " " + position + " > "
						// + videoUrl);
						i2.putExtra("VdoUrl", videoUrl);
						i2.putExtra("ImageUrl", imageUrl);
						i2.putExtra("title", title);

						startActivity(i2);
					}
				});

				Button btn3 = (Button) dialog.findViewById(R.id.btnShare);
				btn3.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						String videoUrl = movieList.get(position).getVdoUrl();

						Intent i = new Intent(Intent.ACTION_SEND);
						i.setType("text/plain");
						i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
						i.putExtra(Intent.EXTRA_TEXT, videoUrl);

						// Toast.makeText(getApplicationContext(), ""+videoUrl,
						// Toast.LENGTH_SHORT).show();

						startActivity(Intent.createChooser(i, "Share URL"));

					}
				});

				Button btn2 = (Button) dialog.findViewById(R.id.button2);
				btn2.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						dialog.dismiss();
					}
				});

				TextView titlemovie = (TextView) dialog
						.findViewById(R.id.text_title);
				titlemovie.setText(movieList.get(position).getTitle());

				NetworkImageView image = (NetworkImageView) dialog
						.findViewById(R.id.image);
				image.setImageUrl(movieList.get(position).getImgUrl(),
						imageLoader);
				
				// Google Admob
				Location location = new Location("AdMobProvider");
				location.setLatitude(13.543296);
				location.setLatitude(100.924562);

				AdRequest.Builder adBuilder = new AdRequest.Builder();
				adBuilder.setLocation(location);

				AdRequest adRequest = adBuilder.build();
				AdView adView = (AdView) dialog.findViewById(R.id.adView);
				adView.loadAd(adRequest);

				dialog.show();
			}

		});

		AQUtility.debug("jo", jo);
		if (jo != null) {
			JSONArray ja = jo.getJSONArray("data");
			for (int i = 0; i < ja.length(); i++) {

				JSONObject obj = ja.getJSONObject(i);

				String imgurl = obj.getString("source");
				String name_full = obj.getString("name");

				String string = name_full;
				String[] parts = string.split("http");
				String part2 = parts[1];

				// String[] parts2 = string.split("(");
				// String part21 = parts2[0];

				String movieUrl = "http" + part2;

				Movie movie2 = new Movie();
				// movie2.setId(i);
				movie2.setTitle(parts[0]);
				movie2.setDetail(name_full);
				movie2.setVdoUrl(movieUrl);
				movie2.setImgUrl(imgurl);

				movieList.add(movie2);
				Log.e(TAG, imgurl);
			}
			jsonAdapter.notifyDataSetChanged();
			AQUtility.debug("done");

		} else {
			AQUtility.debug("error!");
		}
	}
	

	@Override
	public void onDestroy() {
		super.onDestroy();
		hidePDialog();
	}

	private void hidePDialog() {
		if (pDialog != null) {
			pDialog.dismiss();
			pDialog = null;
		}
	}
}
