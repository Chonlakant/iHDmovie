package com.cloudtist.ihd;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

public class Application extends android.app.Application {

	public Application() {
	}
	@Override
	public void onCreate() {
		super.onCreate();

		// Initialize the Parse SDK.
		Parse.initialize(this, "xG3juV3Lk1ZK6IfPvRwiSvahx75YvCW7aTPTt4B9",
				"wpDw9mDkTu0JfYC3YEHB8qloZJAMOBR2IH2EpmsX");

		// Specify an Activity to handle all pushes by default.
		PushService.setDefaultPushCallback(this, MainActivity.class);
	}
}