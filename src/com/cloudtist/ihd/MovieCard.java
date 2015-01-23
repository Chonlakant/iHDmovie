package com.cloudtist.ihd;

import com.cloudtist.ihd.R;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MovieCard extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.moviecard_login);

		Button btn2 = (Button) findViewById(R.id.button2);
		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),
						MovieCardRegister.class);
				startActivity(i);
			}
		});
	}

}
