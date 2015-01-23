package com.cloudtist.ihd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.cloudtist.ihd.R;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

	AQuery aq;
	AQuery aq2;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.login);
		aq = new AQuery(this);

		// txtUsername & txtPassword
		final EditText txtUser = (EditText) findViewById(R.id.editText1);
		final EditText txtPass = (EditText) findViewById(R.id.editText2);

		// btnLogin
		final Button btnLogin = (Button) findViewById(R.id.button1);
		// Perform action on click
		btnLogin.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String tag = "Check";
				String user = txtUser.getText().toString();
				String pass = txtPass.getText().toString();
				Log.e(tag, user);
				Log.e(tag, pass);
				aqLogin(user, pass);

			}
		});

	}

	public String getHttpPost(String url, List<NameValuePair> params) {
		StringBuilder str = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = client.execute(httpPost);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) { // Status OK
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					str.append(line);
				}
			} else {
				Log.e("Log", "Failed to download result..");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str.toString();
	}

	public void aqLogin(String username, String password) {

		//String url = "http://192.168.1.35/androidapi/login.php";
		String url = "http://192.168.43.19/androidapi/login.php";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("strUser", username);
		params.put("strPass", password);

		aq.ajax(url, params, JSONObject.class, this, "loginCb");

	}

	public void loginCb(String url, JSONObject jo, AjaxStatus status)
			throws JSONException {
		if (jo != null) {

			Log.e("myjson", jo.toString());
			if(jo.getString("StatusID").equals("1")){

				Intent i = new Intent(getBaseContext(),MainActivity.class);
				startActivity(i);
				finish();
			}else{
				Toast.makeText(getBaseContext(), "Status Faile", Toast.LENGTH_SHORT).show();
			}
			/*
			 * DataUser.VM_USER_FB_ID = JsonUtility.getString(jo, "id");
			 * DataUser.VM_USER_FB_TOKEN = handle.getToken();
			 * DataUser.VM_USER_FB = JsonUtility.getString(jo, "username")
			 * .replace(".", ""); DataUser.VM_USER_FB_EMAIL =
			 * DataUser.VM_USER_FB + "@facebook.com"; DataUser.VM_USER_FB_FNAME
			 * = JsonUtility.getString(jo, "first_name");
			 * DataUser.VM_USER_FB_LNAME = JsonUtility.getString(jo,
			 * "last_name"); DataUser.VM_IS_AUTHED = true;
			 */
			// fbSignup();

		}
	}

}
