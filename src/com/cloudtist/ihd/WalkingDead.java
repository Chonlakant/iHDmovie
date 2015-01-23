package com.cloudtist.ihd;

import info.ihd.customlistviewvolley.app.AppController;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.cloudtist.ihd.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class WalkingDead extends Activity {
	private ProgressDialog pDialog;
	CustomJsonGridViewAdapter jsonAdapter;

	String url = "http://graph.facebook.com/595518320560053/photos/uploaded?fields=name,picture,images,source,from&limit=100";
	String url1 = "http://graph.facebook.com/595508983894320/photos/uploaded?fields=name,picture,images,source,from&limit=100";
	String url2 = "http://graph.facebook.com/595488460563039/photos/uploaded?fields=name,picture,images,source,from&limit=100";
	String url3 = "http://graph.facebook.com/595117637266788/photos/uploaded?fields=name,picture,images,source,from&limit=100";
	
	GridView gridView;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	ArrayList<Movie> movieList = new ArrayList<Movie>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview_movie);
		getJson(url);
		getJson1(url1);
		getJson2(url2);
		getJson3(url3);
		
	}

	public void getJson(String url) {
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
						
						//Toast.makeText(getApplicationContext(), ""+videoUrl, Toast.LENGTH_SHORT).show();
						
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
				
				dialog.show();
			}

		});

		CustomRequest movieReq = new CustomRequest(url,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
						hidePDialog();

						Log.e("asdf", response.toString());
						try {
							JSONArray a = response.getJSONArray("data");

							for (int i = 0; i < a.length(); i++) {
								JSONObject obj = a.getJSONObject(i);

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
								movieList.add(movie2);
								Log.e(TAG, imgurl);
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						jsonAdapter.notifyDataSetChanged();
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
	
	public void getJson2(String url) {
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
						
						//Toast.makeText(getApplicationContext(), ""+videoUrl, Toast.LENGTH_SHORT).show();
						
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
				
				dialog.show();
			}

		});

		CustomRequest movieReq = new CustomRequest(url,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
						hidePDialog();

						Log.e("asdf", response.toString());
						try {
							JSONArray a = response.getJSONArray("data");

							for (int i = 0; i < a.length(); i++) {
								JSONObject obj = a.getJSONObject(i);

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
								movieList.add(movie2);
								Log.e(TAG, imgurl);
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						jsonAdapter.notifyDataSetChanged();
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
	
	public void getJson3(String url) {
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
						
						//Toast.makeText(getApplicationContext(), ""+videoUrl, Toast.LENGTH_SHORT).show();
						
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
				
				dialog.show();
			}

		});

		CustomRequest movieReq = new CustomRequest(url,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
						hidePDialog();

						Log.e("asdf", response.toString());
						try {
							JSONArray a = response.getJSONArray("data");

							for (int i = 0; i < a.length(); i++) {
								JSONObject obj = a.getJSONObject(i);

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
								movieList.add(movie2);
								Log.e(TAG, imgurl);
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						jsonAdapter.notifyDataSetChanged();
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
	
	public void getJson1(String url) {
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
						
						//Toast.makeText(getApplicationContext(), ""+videoUrl, Toast.LENGTH_SHORT).show();
						
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
				
				dialog.show();
			}

		});

		CustomRequest movieReq = new CustomRequest(url,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
						hidePDialog();

						Log.e("asdf", response.toString());
						try {
							JSONArray a = response.getJSONArray("data");

							for (int i = 0; i < a.length(); i++) {
								JSONObject obj = a.getJSONObject(i);

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
								movieList.add(movie2);
								Log.e(TAG, imgurl);
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						jsonAdapter.notifyDataSetChanged();
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
