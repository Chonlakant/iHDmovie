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
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class USseries extends Activity {
	
	String USalbums = "http://graph.facebook.com/556970007748218/albums";
	private ProgressDialog pDialog;
	CustomJsonGridViewAdapter jsonAdapter;
	GridView gridView;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	ArrayList<Movie> movieList = new ArrayList<Movie>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview_movie);
		
		getJson(USalbums);
		
	}
	public void getJson(String url) {
		final Context context = this;
		final String TAG = "CHECKTAG";

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
								Log.d("TestEStsrst::", obj.toString());

							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						//jsonAdapter.notifyDataSetChanged();
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
