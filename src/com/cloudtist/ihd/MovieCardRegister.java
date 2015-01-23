package com.cloudtist.ihd;

import com.cloudtist.ihd.R;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class MovieCardRegister extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.moviecard_register);
	}

}
