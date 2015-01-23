package com.cloudtist.ihd;

import info.ihd.customlistviewvolley.app.AppController;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.cloudtist.ihd.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

@SuppressLint("InflateParams")
public class HelloInterruptVideoStream extends Activity {

	ImageLoader imageLoader = AppController.getInstance().getImageLoader();
	private VideoView video_view_;
	final Context context = this;
	Dialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.main_video_player);
		video_view_ = (VideoView) findViewById(R.id.surface_view);

		String image = getIntent().getExtras().getString("ImageUrl");
		String title = getIntent().getExtras().getString("title");

		dialog = new Dialog(context);

		dialog.setContentView(R.layout.dialogadmob);
		dialog.setTitle(title);

		NetworkImageView imageview = (NetworkImageView) dialog.findViewById(R.id.imageView2);
		imageview.setImageUrl(image, imageLoader);

		// Google Admob
		Location location = new Location("AdMobProvider");
		location.setLatitude(13.543296);
		location.setLatitude(100.924562);

		AdRequest.Builder adBuilder = new AdRequest.Builder();
		adBuilder.setLocation(location);

		AdRequest adRequest = adBuilder.build();
		AdView adView = (AdView) dialog.findViewById(R.id.adView1);
		adView.loadAd(adRequest);
		dialog.show();
		
		video_view_.setOnErrorListener(new OnErrorListener() {
			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				Toast.makeText(getApplicationContext(), "ดูไม่ได้ครับ", Toast.LENGTH_LONG).show();

				Intent intent = new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intent);
				finish();
				return false;
			}
		});


		PlayVideo();
	}

	public void PlayVideo() {
		
		try {
			String url = getIntent().getExtras().getString("VdoUrl");
			getWindow().clearFlags(
					WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
			video_view_.setVideoURI(Uri.parse(url));
			// video_view_.setVideoPath(path);
			video_view_.setMediaController(new MediaController(this));

			video_view_.setOnPreparedListener(new OnPreparedListener() {
				public void onPrepared(MediaPlayer mp) {

					dialog.dismiss();
					video_view_.start();
				}
			});

		} catch (Exception e) {

			finish();
		}

	}

	@Override
	protected void onDestroy() {
		try {
			video_view_.stopPlayback();
		} catch (Exception e) {
			Log.d("MideoPlayer", "Error OnDestroy");
		}
		super.onDestroy();
	}
}
