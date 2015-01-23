package com.cloudtist.ihd;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.cloudtist.ihd.R;

import android.os.Bundle;
import android.util.Log;
import android.app.Activity;

public class NextActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_next);
		AdRequest.Builder adBuilder = new AdRequest.Builder();
		AdRequest adRequest = adBuilder.build();
		InterstitialAd intAd = new InterstitialAd(this);
		intAd.setAdUnitId("ca-app-pub-1640846146810179/3434855447");
		intAd.loadAd(adRequest);

		intAd.show();
	}
}
