package com.cloudtist.ihd;

import com.cloudtist.ihd.R;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class JoinNow extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.join_now);

		Button btn1 = (Button) findViewById(R.id.button1);
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), MovieCard.class);
				startActivity(i);
			}
		});

		Button btn2 = (Button) findViewById(R.id.button2);
		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), Login.class);
				startActivity(i);

			}
		});

		Button btn3 = (Button) findViewById(R.id.button3);
		btn3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), Register.class);
				startActivity(i);
			}
		});
	}

}
