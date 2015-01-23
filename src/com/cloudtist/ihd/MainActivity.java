package com.cloudtist.ihd;

import info.ihd.customlistviewvolley.app.AppController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mobi.vserv.android.ads.AdPosition;
import mobi.vserv.android.ads.VservManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.androidquery.AQuery;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.PushService;
import com.sattboot.gallery.adapter.JsonImageAdapter;
import com.sattboot.gallery.widget.CoverFlow;
import com.cloudtist.ihd.R;
import com.sattboot.horizontallistview.view.HorizontalListView;

import com.google.ads.mediation.vserv.VservAdapterExtras;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.android.gms.ads.*;
import com.google.android.gms.analytics.Tracker;

public class MainActivity extends Activity {
	Context mContext = this;
	private final int SPLASH_DISPLAY_LENGTH = 10000;
	public AQuery aq  = new AQuery(this);
	private ProgressDialog pDialog;
	private EasyTracker easyTracker = null;
	InterstitialAd iAd;
	private CoverFlow coverflow;
	final Context context = this;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	Menu menu;

	private int defaultPosition = 10;

	ArrayList<Movie> movieList1 = new ArrayList<Movie>();
	ArrayList<Movie> movieList2 = new ArrayList<Movie>();
	ArrayList<Movie> movieList3 = new ArrayList<Movie>();
	ArrayList<Movie> movieList4 = new ArrayList<Movie>();
	ArrayList<Movie> movieList5 = new ArrayList<Movie>();
	ArrayList<Movie> movieList6 = new ArrayList<Movie>();
	ArrayList<Movie> movieList7 = new ArrayList<Movie>();
	ArrayList<Movie> movieList8 = new ArrayList<Movie>();
	ArrayList<Movie> movieList9 = new ArrayList<Movie>();

	ArrayList<Movie> coverList = new ArrayList<Movie>();
	ArrayList<ArrayList<Movie>> bigList = new ArrayList<ArrayList<Movie>>();
	@SuppressLint("UseSparseArrays")
	Map<Integer, CustomJsonArrayAdapter> adapterMap = new HashMap<Integer, CustomJsonArrayAdapter>();

	CustomJsonArrayAdapter jsonAdapter1;
	CustomJsonArrayAdapter jsonAdapter2;
	CustomJsonArrayAdapter jsonAdapter3;
	CustomJsonArrayAdapter jsonAdapter4;
	CustomJsonArrayAdapter jsonAdapter5;
	CustomJsonArrayAdapter jsonAdapter6;
	CustomJsonArrayAdapter jsonAdapter7;
	CustomJsonArrayAdapter jsonAdapter8;
	CustomJsonArrayAdapter jsonAdapter9;

	public static final String ID = "ID";
	public static final String TITLE = "TITLE";
	public static final String DETAIL = "DETAIL";
	public static final String VDOURL = "VDOURL";
	public static final String LOCIMG = "LOCIMG";
	public static final String IMGURL = "IMGURL";

	ArrayList<HorizontalListView> listview = new ArrayList<HorizontalListView>();

	HorizontalListView listView1;
	HorizontalListView listView2;
	HorizontalListView listView3;
	HorizontalListView listView4;
	HorizontalListView listView5;
	HorizontalListView listView6;
	HorizontalListView listView7;
	HorizontalListView listView8;
	HorizontalListView listView9;

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		 easyTracker = EasyTracker.getInstance(MainActivity.this);
		
		
		 findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					easyTracker.send(MapBuilder.createEvent(
							"TrackEventTest", "button_pressed",
							"track_event", null).build());
				}
			});
		 
		 
		
		
			
		ParseAnalytics.trackAppOpened(getIntent());
		PushService.subscribe(this, "free_version", MainActivity.class);

		new Thread(new Runnable() {
			@Override
			public void run() {

				ParseInstallation.getCurrentInstallation().saveInBackground();

			}
		}).start();

		pDialog = new ProgressDialog(this);
		// Showing progress dialog before making http request
		pDialog.setMessage("Loading...");
		pDialog.show();

		bigList.add(movieList1);
		bigList.add(movieList2);
		bigList.add(movieList3);
		bigList.add(movieList4);
		bigList.add(movieList5);
		bigList.add(movieList6);
		bigList.add(movieList7);
		bigList.add(movieList8);
		bigList.add(movieList9);

		listView1 = (HorizontalListView) findViewById(R.id.listview1);
		listView2 = (HorizontalListView) findViewById(R.id.listview2);
		listView3 = (HorizontalListView) findViewById(R.id.listview3);
		listView4 = (HorizontalListView) findViewById(R.id.listview4);
		listView5 = (HorizontalListView) findViewById(R.id.listview5);
		//listView6 = (HorizontalListView) findViewById(R.id.listview6);
		//listView7 = (HorizontalListView) findViewById(R.id.listview7);
		listView8 = (HorizontalListView) findViewById(R.id.listview8);
		listView9 = (HorizontalListView) findViewById(R.id.listview9);

		jsonAdapter1 = new CustomJsonArrayAdapter(getBaseContext(),
				bigList.get(0), 0, 0);
		jsonAdapter2 = new CustomJsonArrayAdapter(getBaseContext(),
				bigList.get(1), 1, 1);
		jsonAdapter3 = new CustomJsonArrayAdapter(getBaseContext(),
				bigList.get(2), 2, 2);
		jsonAdapter4 = new CustomJsonArrayAdapter(getBaseContext(),
				bigList.get(3), 3, 3);
		jsonAdapter5 = new CustomJsonArrayAdapter(getBaseContext(),
				bigList.get(4), 4, 4);
		jsonAdapter6 = new CustomJsonArrayAdapter(getBaseContext(),
				bigList.get(5), 5, 5);
		jsonAdapter7 = new CustomJsonArrayAdapter(getBaseContext(),
				bigList.get(6), 6, 6);
		jsonAdapter8 = new CustomJsonArrayAdapter(getBaseContext(),
				bigList.get(7), 7, 7);
		jsonAdapter9 = new CustomJsonArrayAdapter(getBaseContext(),
				bigList.get(8), 8, 8);

		adapterMap.put(0, jsonAdapter1);
		adapterMap.put(1, jsonAdapter2);
		adapterMap.put(2, jsonAdapter3);
		adapterMap.put(3, jsonAdapter4);
		adapterMap.put(4, jsonAdapter5);
		adapterMap.put(5, jsonAdapter6);
		adapterMap.put(6, jsonAdapter7);
		adapterMap.put(7, jsonAdapter8);
		adapterMap.put(8, jsonAdapter9);

		listView1.setAdapter(adapterMap.get(0));
		listView2.setAdapter(adapterMap.get(1));
		listView3.setAdapter(adapterMap.get(2));
		listView4.setAdapter(adapterMap.get(3));
		listView5.setAdapter(adapterMap.get(4));
		//listView6.setAdapter(adapterMap.get(5));
		//listView7.setAdapter(adapterMap.get(6));
		listView8.setAdapter(adapterMap.get(7));
		listView9.setAdapter(adapterMap.get(8));

		listview.add(listView1);
		listview.add(listView2);
		listview.add(listView3);
		listview.add(listView4);
		listview.add(listView5);
		listview.add(listView6);
		listview.add(listView7);
		listview.add(listView8);
		listview.add(listView9);

		Button btn1 = (Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				PopupMenu popup = new PopupMenu(MainActivity.this, v);

				popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());

				popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					public boolean onMenuItemClick(MenuItem item) {
						switch (item.getItemId()) {
						case R.id.action1:
							Intent reg = new Intent(getApplicationContext(),
									Login.class);
							startActivity(reg);

							break;
						case R.id.action2:
							Intent login = new Intent(getApplicationContext(),
									Register.class);
							startActivity(login);

							break;
						}
						return true;
					}
				});
				popup.show();
			}
		});

		Button btn2 = (Button) findViewById(R.id.button2);
		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PopupMenu popup = new PopupMenu(MainActivity.this, v);
				popup.getMenuInflater().inflate(R.menu.moviecard,popup.getMenu());
				popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						switch (item.getItemId()) {
						case R.id.titleLogin:
							Intent login = new Intent(getBaseContext(),MovieCard.class);
							startActivity(login);
							break;
						case R.id.titleRegister:
							Intent reg = new Intent(getBaseContext(),MovieCardRegister.class);
							startActivity(reg);
							break;
						}
						return true;
					}
				});
				popup.show();
			}
		});

		Button btn3 = (Button) findViewById(R.id.button3);
		btn3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PopupMenu popuptitle = new PopupMenu(MainActivity.this, v);

				popuptitle.getMenuInflater().inflate(R.menu.category,
						popuptitle.getMenu());
				popuptitle
						.setOnMenuItemClickListener(new OnMenuItemClickListener() {

							@Override
							public boolean onMenuItemClick(MenuItem item) {
								switch (item.getItemId()) {
								case R.id.title1:
									String update = "http://graph.facebook.com/753706308036834/photos/uploaded?fields=name,picture,images,source,from&limit=100";
									Intent i = new Intent(
											getApplicationContext(),
											GridViewCategory.class);
									i.putExtra("url", update);
									i.putExtra("is", 1);
									startActivity(i);
									break;
								case R.id.title2:
							
									 String Hd = "http://graph.facebook.com/753710374703094/photos/uploaded?fields=name,picture,images,source,from&limit=100";
									Intent j = new Intent(getBaseContext(),
											GridViewCategory.class);
									j.putExtra("url", Hd);
									j.putExtra("is", 2);
									startActivity(j);
									break;
								case R.id.title3:
									String popular = "http://graph.facebook.com/753716378035827/photos/uploaded?fields=name,picture,images,source,from&limit=100";
									
									Intent k = new Intent(getBaseContext(),
											GridViewCategory.class);
									k.putExtra("url", popular);
									k.putExtra("is", 3);
									startActivity(k);
									break;
								case R.id.title4:
									String tv = "http://graph.facebook.com/753765261364272/photos/uploaded?fields=name,picture,images,source,from&limit=100";
									Intent q = new Intent(getBaseContext(),
											GridViewCategory.class);
									q.putExtra("url", tv);
									q.putExtra("is", 4);
									startActivity(q);
									break;
//								case R.id.title5:
//									String sport = "https://graph.facebook.com/v2.1/753766401364158/photos?access_token=391414774312517|f486294a7603127e78833e54f17cbc51";
//									Intent l = new Intent(getBaseContext(),
//											GridViewCategory.class);
//									l.putExtra("url", sport);
//									l.putExtra("is", 5);
//									startActivity(l);
//									break;

								case R.id.title9:
									Intent m = new Intent(getBaseContext(),
											WalkingDead.class);
									startActivity(m);
									break;

								}
								return true;
							}
						});
				popuptitle.show();
			}
		});

		Button btn4 = (Button) findViewById(R.id.button4);
		btn4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = getIntent();
				overridePendingTransition(0, 0);
				intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				finish();
				overridePendingTransition(0, 0);
				startActivity(intent);

			}
		});
		Button btn5 = (Button) findViewById(R.id.button5);
		btn5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String uri = "fb://group/747326605348720";
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
				startActivity(intent);
				
//				Uri uri = Uri.parse("market://details?id=com.sattboot.horizontallistview&hl=th" + mContext.getPackageName());
//				Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
//				 
//				 startActivity(myAppLinkToMarket);
			}
		});

		
		Button btn6 = (Button) findViewById(R.id.button6);
		btn6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String videoUrl = "IHD ดูหนังฟรีบน Android www.cloudtist.com/apk";

				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("text/plain");
				i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
				i.putExtra(Intent.EXTRA_TEXT, videoUrl);
				startActivity(Intent.createChooser(i, "Share URL"));
				
			}
		});
		
		
		
		///
		
			String url = "http://graph.facebook.com/753706308036834/photos/uploaded?fields=name,picture,images,source,from&limit=100";
			String url1="http://graph.facebook.com/753706308036834/photos/uploaded?fields=name,picture,images,source,from&limit=100";
			String url2 = "http://graph.facebook.com/753710374703094/photos/uploaded?fields=name,picture,images,source,from&limit=100";
			String url3 = "http://graph.facebook.com/753716378035827/photos/uploaded?fields=name,picture,images,source,from&limit=100";
			String url4 = "http://graph.facebook.com/753765261364272/photos/uploaded?fields=name,picture,images,source,from&limit=100";
			String url5 = "http://graph.facebook.com/753766401364158/photos/uploaded?fields=name,picture,images,source,from&limit=100";
		///

		//String url = "https://graph.facebook.com/v2.1/753706308036834/photos?access_token=391414774312517|f486294a7603127e78833e54f17cbc51";
		//String url1="https://graph.facebook.com/v2.1/753706308036834/photos?access_token=391414774312517%7Cf486294a7603127e78833e54f17cbc51&pretty=1&limit=25&after=NzUzNzA2NjAxMzcwMTM4";
		//String url11 = "https://graph.facebook.com/v2.1/753706308036834/photos?access_token=391414774312517%7Cf486294a7603127e78833e54f17cbc51&pretty=1&limit=25&after=NzUzNzA3NDY0NzAzMzg1";
		//String url2 = "https://graph.facebook.com/v2.1/753710374703094/photos?access_token=391414774312517|f486294a7603127e78833e54f17cbc51";
		//String url22 = "https://graph.facebook.com/v2.1/753710374703094/photos?access_token=391414774312517%7Cf486294a7603127e78833e54f17cbc51&pretty=1&limit=25&after=NzUzNzEwNjM4MDM2NDAx";
		//String url3 = "https://graph.facebook.com/v2.1/753716378035827/photos?access_token=391414774312517|f486294a7603127e78833e54f17cbc51";
		//String url4 = "https://graph.facebook.com/v2.1/753765261364272/photos?access_token=391414774312517|f486294a7603127e78833e54f17cbc51";
		//String url5 = "https://graph.facebook.com/v2.1/753766401364158/photos?access_token=391414774312517|f486294a7603127e78833e54f17cbc51";

		// ฮอร์โมน Season 2
		String url7 = "https://graph.facebook.com/v2.1/753769818030483/photos?access_token=391414774312517|f486294a7603127e78833e54f17cbc51";
		// The Walking Dead
		String url8 = "http://graph.facebook.com/753767524697379/photos/uploaded?fields=name,picture,images,source,from&limit=100";
		String urlthai = "https://gdata.youtube.com/feeds/api/videos?author=movieclipsTRAILERS&v=2&alt=jsonc";
		String urlmpicture = "https://gdata.youtube.com/feeds/api/videos?author=UCXFQG9CseWEUiRSS0soKFYA&v=2&alt=jsonc";
		Coverflow(url2);

		getJson(url, 0);
		getJson11(url1,0);
		//getJson111(url11,0);
		getJson(url2, 1);
		//getJson22(url22,1);
		getJson(url3, 2);
		getJson(url8, 3);
		getJson(url4, 4);
		//getJson(url5, 5);
		//getJson(url7, 6);

		Moviethai2(urlthai, 8);

		Moviethai(urlmpicture, 7);

		// Google Admob
		Location location = new Location("AdMobProvider");
		location.setLatitude(13.543296);
		location.setLatitude(100.924562);

		AdRequest.Builder adBuilder = new AdRequest.Builder();
		adBuilder.setLocation(location);

		AdRequest adRequest1 = adBuilder.build();
		AdView adView = (AdView) findViewById(R.id.adView);
		adView.loadAd(adRequest1);

		InterstitialAd iAd = new InterstitialAd(this);

		// Set AdUnidId (AdMob)
		iAd.setAdUnitId("ca-app-pub-1640846146810179/2018607042");

		// Create Vserv Adapter & Set AdPosition
		VservAdapterExtras networkExtras = new VservAdapterExtras().setShowAt(AdPosition.IN);

		// Create AdRequest & Add Vserv Adapter
		AdRequest adRequest = new AdRequest.Builder().addNetworkExtras(networkExtras).build();

		// Handle event
		iAd.setAdListener(new VservAdListener());

		// Load Ad
		iAd.loadAd(adRequest);

		networkExtras.setShowAt(AdPosition.START);

		AppController.getInstance().getRequestQueue().start();

		HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();
		
	}


	
	@Override
	protected void onResume(){
		super.onResume();
		
		Bundle bundle = getIntent().getExtras();
		if(bundle != null){
		String notification = bundle.getString("com.parse.Data");	
		if(notification != null){
			Log.d("Check:", notification);
			Intent i = new Intent(getApplicationContext(), MainActivity.class);
			startActivity(i);
			finish();
	
			
		 }
		}
		
	}
	
	public void mPrint(String str) {
		Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();
	}

	public void getJson(String url, final int index) {
		final String TAG = "CHECKTAG";

		listview.get(index).setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long arg3) {

				final Dialog dialog = new Dialog(context);

				dialog.setContentView(R.layout.detail);
				dialog.setTitle(bigList.get(index).get(position).getTitle());

				Button btn1 = (Button) dialog.findViewById(R.id.button1);
				btn1.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						String videoUrl = bigList.get(index).get(position).getVdoUrl();
						String imageUrl = bigList.get(index).get(position).getImgUrl();
						String title = bigList.get(index).get(position).getTitle();
						// Log.e("heyheyhey", index + " " + position + " > "
						// + videoUrl);

						if (index == 0) {
							Intent i2 = new Intent(getBaseContext(),HelloInterruptVideoStream.class);

							i2.putExtra("VdoUrl", videoUrl);
							i2.putExtra("ImageUrl", imageUrl);
							i2.putExtra("title", title);
							startActivity(i2);

						}

						if (index == 1) {
							Intent i2 = new Intent(getBaseContext(),HelloInterruptVideoStream.class);

							i2.putExtra("VdoUrl", videoUrl);
							i2.putExtra("ImageUrl", imageUrl);
							i2.putExtra("title", title);
							startActivity(i2);

						}
						if (index == 2) {
							Intent i2 = new Intent(getBaseContext(),HelloInterruptVideoStream.class);
							i2.putExtra("VdoUrl", videoUrl);
							i2.putExtra("ImageUrl", imageUrl);
							i2.putExtra("title", title);
							startActivity(i2);

						}
						if (index == 3) {
							Intent i2 = new Intent(getBaseContext(),HelloInterruptVideoStream.class);
							i2.putExtra("VdoUrl", videoUrl);
							i2.putExtra("ImageUrl", imageUrl);
							i2.putExtra("title", title);
							startActivity(i2);

						}
						if (index == 4) {
							Intent i2 = new Intent(getBaseContext(),HelloInterruptVideoStream.class);
							i2.putExtra("VdoUrl", videoUrl);
							i2.putExtra("ImageUrl", imageUrl);
							i2.putExtra("title", title);
							startActivity(i2);

						}
						if (index == 5) {
							Intent i2 = new Intent(getBaseContext(),HelloInterruptVideoStream.class);
							i2.putExtra("VdoUrl", videoUrl);
							i2.putExtra("ImageUrl", imageUrl);
							i2.putExtra("title", title);
							startActivity(i2);

						}
						if (index == 6) {
							Intent i2 = new Intent(getBaseContext(),HelloInterruptVideoStream.class);
							i2.putExtra("VdoUrl", videoUrl);
							i2.putExtra("ImageUrl", imageUrl);
							i2.putExtra("title", title);
							startActivity(i2);
						}
						
					}
				});

				Button btn3 = (Button) dialog.findViewById(R.id.btnShare);
				btn3.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						String videoUrl = bigList.get(index).get(position)
								.getVdoUrl();

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
				TextView titlemovie = (TextView) dialog.findViewById(R.id.text_title);
				titlemovie.setText(bigList.get(index).get(position).getTitle());

				NetworkImageView image = (NetworkImageView) dialog
						.findViewById(R.id.image);
				image.setImageUrl(bigList.get(index).get(position).getImgUrl(),
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
		
		CustomRequest movieReq = new CustomRequest(url,new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
						hidePDialog();
						Log.e("asdf", response.toString());
						try {

							JSONArray a = response.getJSONArray("data");

							for (int i = 0; i < a.length(); i++) {
								JSONObject obj = a.getJSONObject(i);
								Log.d(TAG, obj.toString());

								String imgurl = obj.getString("source");
								String name_full = obj.getString("name");

								String string = name_full;
								String[] parts = string.split("http");
								String part2 = parts[1];

								// String[] parts2 = string.split("(");
								// String part21 = parts2[0];

								String movieUrl = "http" + part2;

								Movie movie2 = new Movie();
								movie2.setId("");
								movie2.setTitle(parts[0]);
								movie2.setDetail(name_full);
								movie2.setVdoUrl(movieUrl);
								movie2.setImgUrl(imgurl);

								bigList.get(index).add(movie2);

							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						adapterMap.get(index).notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hidePDialog();

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(movieReq);
	}
	
	public void getJson111(String url, final int index) {
		final String TAG = "CHECKTAG";

		CustomRequest movieReq = new CustomRequest(url,new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
						hidePDialog();
						Log.e("asdf", response.toString());
						try {

							JSONArray a = response.getJSONArray("data");

							for (int i = 0; i < a.length(); i++) {
								JSONObject obj = a.getJSONObject(i);
								Log.d(TAG, obj.toString());

								String imgurl = obj.getString("source");
								String name_full = obj.getString("name");

								String string = name_full;
								String[] parts = string.split("http");
								String part2 = parts[1];

								// String[] parts2 = string.split("(");
								// String part21 = parts2[0];

								String movieUrl = "http" + part2;

								Movie movie2 = new Movie();
								movie2.setId("");
								movie2.setTitle(parts[0]);
								movie2.setDetail(name_full);
								movie2.setVdoUrl(movieUrl);
								movie2.setImgUrl(imgurl);

								bigList.get(index).add(movie2);

							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						adapterMap.get(index).notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hidePDialog();

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(movieReq);
	}
	public void getJson11(String url, final int index) {
		final String TAG = "CHECKTAG";

		CustomRequest movieReq = new CustomRequest(url,new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
						hidePDialog();
						Log.e("asdf", response.toString());
						try {

							JSONArray a = response.getJSONArray("data");

							for (int i = 0; i < a.length(); i++) {
								JSONObject obj = a.getJSONObject(i);
								Log.d(TAG, obj.toString());

								String imgurl = obj.getString("source");
								String name_full = obj.getString("name");

								String string = name_full;
								String[] parts = string.split("http");
								String part2 = parts[1];

								// String[] parts2 = string.split("(");
								// String part21 = parts2[0];

								String movieUrl = "http" + part2;

								Movie movie2 = new Movie();
								movie2.setId("");
								movie2.setTitle(parts[0]);
								movie2.setDetail(name_full);
								movie2.setVdoUrl(movieUrl);
								movie2.setImgUrl(imgurl);

								bigList.get(index).add(movie2);

							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						adapterMap.get(index).notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hidePDialog();

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(movieReq);
	}
	
	public void getJson22(String url, final int index) {
		final String TAG = "CHECKTAG";

		CustomRequest movieReq = new CustomRequest(url,new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
						hidePDialog();
						Log.e("asdf", response.toString());
						try {

							JSONArray a = response.getJSONArray("data");

							for (int i = 0; i < a.length(); i++) {
								JSONObject obj = a.getJSONObject(i);
								Log.d(TAG, obj.toString());

								String imgurl = obj.getString("source");
								String name_full = obj.getString("name");

								String string = name_full;
								String[] parts = string.split("http");
								String part2 = parts[1];

								// String[] parts2 = string.split("(");
								// String part21 = parts2[0];

								String movieUrl = "http" + part2;

								Movie movie2 = new Movie();
								movie2.setId("");
								movie2.setTitle(parts[0]);
								movie2.setDetail(name_full);
								movie2.setVdoUrl(movieUrl);
								movie2.setImgUrl(imgurl);

								bigList.get(index).add(movie2);

							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						adapterMap.get(index).notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hidePDialog();

					}
				});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(movieReq);
	}

	public void Coverflow(String url) {

		final String TAG = "CHECKTAGCOVERFLOW";
		coverflow = (CoverFlow) findViewById(R.id.of_image_show);
		coverflow.setSpacing(-150);
		coverflow.setSelection(defaultPosition, true);
		coverflow.setAnimationDuration(1000);

		final JsonImageAdapter adaptercover = new JsonImageAdapter(context,
				coverList);

		AdRequest.Builder adBuilder = new AdRequest.Builder();
		AdRequest adRequest = adBuilder.build();
		iAd = new InterstitialAd(this);
		iAd.setAdUnitId("ca-app-pub-1640846146810179/2018607042");
		iAd.loadAd(adRequest);
		iAd.setAdListener(new AdListener() {
			public void onAdClosed() {
				nextActivity();
			}
		});

		adaptercover.createReflectedImages();
		coverflow.setAdapter(adaptercover);
		coverflow.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				coverList.get(position);
				if (iAd.isLoaded())
					iAd.show();
				else
					nextActivity();
			}
		});

		CustomRequest movieReqcover = new CustomRequest(url,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						 Log.d(TAG, response.toString());
						hidePDialog();

						// Log.e("asdf", response.toString());
						try {
							JSONArray a = response.getJSONArray("data");

							for (int i = 0; i < a.length(); i++) {
								JSONObject obj = a.getJSONObject(i);

								JSONArray images = obj.getJSONArray("images");
								String imgurl = images.getJSONObject(0)
										.getString("source");
								String name_full = obj.getString("name");

								String string = name_full;
								String[] parts = string.split("http");
								String part2 = parts[1];

								// String[] parts2 = string.split("(");
								// String part21 = parts2[0];

								String movieUrl = "http" + part2;

								Movie movie2 = new Movie();
								movie2.setId("");
								movie2.setTitle(name_full);
								movie2.setDetail(name_full);
								movie2.setVdoUrl(movieUrl);
								movie2.setImgUrl(imgurl);

								coverList.add(movie2);

							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						adaptercover.notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hidePDialog();

					}
				});
		AppController.getInstance().addToRequestQueue(movieReqcover);
	}

	public void nextActivity() {
		Intent intent = new Intent(getApplicationContext(), GridView0.class);
		startActivity(intent);
	}

	public void nextActivity1() {
		Intent intent = new Intent(getApplicationContext(), NextActivity.class);
		startActivity(intent);
	}

	public void Moviethai2(String url, final int index) {

		final String TAG = "CHECKTAGCOVERFLOW";

		listview.get(index).setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long arg3) {

				final Dialog dialog = new Dialog(context);

				dialog.setContentView(R.layout.detail);
				dialog.setTitle(bigList.get(index).get(position).getTitle());

				Button btn1 = (Button) dialog.findViewById(R.id.button1);
				btn1.setOnClickListener(new OnClickListener() {

					@SuppressLint("ShowToast")
					@Override
					public void onClick(View v) {

						String videoUrl = bigList.get(index).get(position)
								.getVdoUrl();

						// Log.e("heyheyhey", index + " " + position + " > "
						// + videoUrl);
						if (index == 8) {
							Intent i2 = new Intent(getBaseContext(),
									YouTubePlayerActivity.class);
							i2.putExtra("video_id", videoUrl);
							startActivity(i2);

						}

					}
				});

				Button btn3 = (Button) dialog.findViewById(R.id.btnShare);
				btn3.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						String videoUrl = bigList.get(index).get(position)
								.getVdoUrl();

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
				titlemovie.setText(bigList.get(index).get(position).getTitle());

				NetworkImageView image = (NetworkImageView) dialog
						.findViewById(R.id.image);
				image.setImageUrl(bigList.get(index).get(position).getImgUrl(),
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

		CustomRequest movieReqcover = new CustomRequest(url,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						// Log.d(TAG, response.toString());
						hidePDialog();

						// Log.e("asdf", response.toString());
						try {
							JSONObject a = response.getJSONObject("data");

							JSONArray arr = a.getJSONArray("items");
							for (int i = 0; i < arr.length(); i++) {

								Log.d(TAG, arr.toString());
								JSONObject obj = arr.getJSONObject(i);
								String name = obj.getString("title");

								// Name title
								JSONObject image = obj
										.optJSONObject("thumbnail");
								String title = image.optString("hqDefault");
								Log.d(TAG, title);

								// Url Vdo Youtube
								// JSONObject vdo = obj.optJSONObject("player");
								// String movieUrl = vdo.optString("mobile");

								String movieUrl = obj.getString("id");
								Log.d(TAG, movieUrl);

								Movie movie2 = new Movie();
								// movie2.setId("");
								movie2.setTitle(name);
								movie2.setDetail(name);
								movie2.setVdoUrl(movieUrl);
								movie2.setImgUrl(title);

								bigList.get(index).add(movie2);

							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						adapterMap.get(index).notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hidePDialog();

					}
				});
		AppController.getInstance().addToRequestQueue(movieReqcover);
	}

	public void Moviethai(String url, final int index) {

		final String TAG = "CHECKTAGCOVERFLOW";

		listview.get(index).setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long arg3) {

				final Dialog dialog = new Dialog(context);

				dialog.setContentView(R.layout.detail);
				dialog.setTitle(bigList.get(index).get(position).getTitle());

				Button btn1 = (Button) dialog.findViewById(R.id.button1);
				btn1.setOnClickListener(new OnClickListener() {

					@SuppressLint("ShowToast")
					@Override
					public void onClick(View v) {

						String videoUrl = bigList.get(index).get(position)
								.getVdoUrl();

						// Log.e("heyheyhey", index + " " + position + " > "
						// + videoUrl);
						if (index == 7) {
							Intent i2 = new Intent(getBaseContext(),
									YouTubePlayerActivity.class);
							i2.putExtra("video_id", videoUrl);
							startActivity(i2);

						}

					}
				});

				Button btn3 = (Button) dialog.findViewById(R.id.btnShare);
				btn3.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						String videoUrl = bigList.get(index).get(position)
								.getVdoUrl();

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
				titlemovie.setText(bigList.get(index).get(position).getTitle());

				NetworkImageView image = (NetworkImageView) dialog
						.findViewById(R.id.image);
				image.setImageUrl(bigList.get(index).get(position).getImgUrl(),
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

		CustomRequest movieReqcover = new CustomRequest(url,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						// Log.d(TAG, response.toString());
						hidePDialog();

						// Log.e("asdf", response.toString());
						try {
							JSONObject a = response.getJSONObject("data");

							JSONArray arr = a.getJSONArray("items");
							for (int i = 0; i < arr.length(); i++) {

								Log.d(TAG, arr.toString());
								JSONObject obj = arr.getJSONObject(i);
								String name = obj.getString("title");

								// Name title
								JSONObject image = obj
										.optJSONObject("thumbnail");
								String title = image.optString("hqDefault");
								Log.d(TAG, title);

								// Url Vdo Youtube
								// JSONObject vdo = obj.optJSONObject("player");
								// String movieUrl = vdo.optString("mobile");

								String movieUrl = obj.getString("id");
								Log.d(TAG, movieUrl);

								Movie movie2 = new Movie();
								// movie2.setId("");
								movie2.setTitle(name);
								movie2.setDetail(name);
								movie2.setVdoUrl(movieUrl);
								movie2.setImgUrl(title);

								bigList.get(index).add(movie2);

							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						adapterMap.get(index).notifyDataSetChanged();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						hidePDialog();

					}
				});
		AppController.getInstance().addToRequestQueue(movieReqcover);
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

	// Inner class for handle AdMob & Vserv
	class VservAdListener extends AdListener {
		@Override
		public void onAdClosed() {
			// TODO Auto-generated method stub
			super.onAdClosed();
		}

		@Override
		public void onAdFailedToLoad(int errorCode) {
			// TODO Auto-generated method stub
			super.onAdFailedToLoad(errorCode);
		}

		@Override
		public void onAdLeftApplication() {
			// TODO Auto-generated method stub
			super.onAdLeftApplication();
		}

		@Override
		public void onAdLoaded() {
			// TODO Auto-generated method stub
			super.onAdLoaded();
		}

		@Override
		public void onAdOpened() {
			// TODO Auto-generated method stub
			super.onAdOpened();
		}
	}

	// Override method onActivityResult of Activity for release resources
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == VservManager.REQUEST_CODE) {
			if (data != null) {
				if (data.hasExtra("showAt")
						&& data.getStringExtra("showAt")
								.equalsIgnoreCase("mid"))
					VservManager.getInstance(this).release(this);
			} else {
				VservManager.getInstance(this).release(this);
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	public enum TrackerName {
		APP_TRACKER, // Tracker used only in this app.
		GLOBAL_TRACKER, // Tracker used by all the apps from a company. eg:
						// roll-up tracking.
		ECOMMERCE_TRACKER, // Tracker used by all ecommerce transactions from a
							// company.
	}
	
	 @Override
	    protected void onStart() {
	    	super.onStart();
	    	
	    	EasyTracker.getInstance(this).activityStart(this);
	    }
	 
	 @Override
	    protected void onStop() {
	    	super.onStop();
	    	
	    	EasyTracker.getInstance(this).activityStop(this);
	    }

}
