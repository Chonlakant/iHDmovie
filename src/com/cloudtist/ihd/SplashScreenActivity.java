// Copyright 2014 Google Inc. All Rights Reserved.

package com.cloudtist.ihd;

import io.fabric.sdk.android.Fabric;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Displays a splash screen image while and loads an interstitial before
 * starting the application.
 */
public class SplashScreenActivity extends Activity {
	// How long in milliseconds to wait for the interstitial to load.
	private static final int WAIT_TIME = 5000;
	
	public String VERSION;
	// Your interstitial ad unit ID.
	private static final String AD_UNIT_ID = "ca-app-pub-1640846146810179/2018607042";
	AQuery aq;
	private InterstitialAd interstitial;
	private Timer waitTimer;
	
	private boolean interstitialCanceled = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		
		PackageInfo pInfo;
		try {
			pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			VERSION = pInfo.versionName;
			Toast.makeText(getApplicationContext(), VERSION, Toast.LENGTH_LONG).show();
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Fabric.with(this, new Crashlytics());

		setContentView(R.layout.splash_screen);
		aq = new AQuery(this);
		interstitial = new InterstitialAd(this);
		interstitial.setAdUnitId(AD_UNIT_ID);
		interstitial.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				// If the interstitial was canceled due to a timeout or an app
				// being sent to the background,
				// don't show the interstitial.
				if (!interstitialCanceled) {
					waitTimer.cancel();
					interstitial.show();
				}
			}

			@Override
			public void onAdFailedToLoad(int errorCode) {
				// The interstitial failed to load. Start the application.
				startMainActivity();
			}
		});
		interstitial.loadAd(new AdRequest.Builder().build());

		waitTimer = new Timer();
		waitTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				interstitialCanceled = true;
				SplashScreenActivity.this.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// The interstitial didn't load in a reasonable amount
						// of time. Stop waiting for the
						// interstitial, and start the application.
						startMainActivity();
					}
				});
			}
		}, WAIT_TIME);
		
		//checkUpdate();
	}

	@Override
	public void onPause() {
		// Flip the interstitialCanceled flag so that when the user comes back
		// they aren't stuck inside
		// the splash screen activity.
		waitTimer.cancel();
		interstitialCanceled = true;
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		if (interstitial.isLoaded()) {
			// The interstitial finished loading while the app was in the
			// background. It's up to you what
			// the behavior should be when they return. In this example, we show
			// the interstitial since
			// it's ready.
			interstitial.show();
		} else if (interstitialCanceled) {
			// There are two ways the user could get here:
			//
			// 1. After dismissing the interstitial
			// 2. Pressing home and returning after the interstitial finished
			// loading.
			//
			// In either case, it's awkward to leave them in the splash screen
			// activity, so just start the
			// application.
			
			//startMainActivity();
			checkUpdate();
		}
	
	}

	/**
	 * Starts the application's {@link MainActivity}.
	 */
	private void startMainActivity() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	 
	// begin the installation by opening the resulting file 
	protected void onPostExecute(String path) {
	    Intent i = new Intent();
	    i.setAction(Intent.ACTION_VIEW);
	    i.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive" );
	    Log.d("Lofting", "About to install new .apk");
	    this.getApplicationContext().startActivity(i);
	} 
	public void checkUpdate() {
		 
		ProgressDialog dialog = new ProgressDialog(this);
		dialog.setIndeterminate(true);
		dialog.setCancelable(false);
		dialog.setInverseBackgroundForced(false);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setTitle("ตรวจสอบเวอร์ชั่น...");
 
		aq.progress(dialog).ajax("http://www.cloudtist.com/apk/update.php",
				JSONObject.class, this, "updateCb");
	}
	UpdateService atualizaApp;
	public void updateCb(String url, JSONObject jo, AjaxStatus status)
			throws JSONException {
		if (jo != null) {
 
			Log.e("updatetag", jo.optString("update"));
 
			if (jo.optString("update").equals("1")
					&& !jo.optString("version").equals(VERSION)) {
				Toast.makeText(this, "กำลังดาวน์โหลดอัพเดท",
						Toast.LENGTH_SHORT).show();
 
				// declare the dialog as a member field of your activity
				ProgressDialog mProgressDialog;

				
				// instantiate it within the onCreate method
				mProgressDialog = new ProgressDialog(SplashScreenActivity.this);
				mProgressDialog.setMessage("Downloading update");
				mProgressDialog.setIndeterminate(true);
				mProgressDialog
						.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				mProgressDialog.setCancelable(false);
 
				// execute this when the downloader must be fired
 
				mProgressDialog
						.setOnCancelListener(new DialogInterface.OnCancelListener() {
							@Override
							public void onCancel(DialogInterface dialog) {
								atualizaApp.cancel(true);
							}
						});

				
				atualizaApp = new UpdateService();
				atualizaApp
						.setContext(getApplicationContext(), mProgressDialog);
				atualizaApp.execute(jo.optString("downloadURL"));
			} else {
				Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
				startMainActivity();
			}
		}
	}
}
