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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {
	
	AQuery aq;
	AQuery aq2;
    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        aq = new AQuery(this);
     
        // Permission StrictMode
        final EditText txtUsername = (EditText)findViewById(R.id.txtUsername); 
        final EditText txtPassword = (EditText)findViewById(R.id.txtPassword); 
        final EditText txtName = (EditText)findViewById(R.id.txtName); 
        final EditText txtEmail = (EditText)findViewById(R.id.txtEmail); 
        final EditText txtTel = (EditText)findViewById(R.id.txtTel); 
        
        // btnSave
        final Button btnSave = (Button) findViewById(R.id.btnsave);
        // Perform action on click
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	String tag = "Check";
            	String username = txtUsername.getText().toString();
				String password = txtPassword.getText().toString();
				String name = txtName.getText().toString();
				String email = txtEmail.getText().toString();
				String tel = txtTel.getText().toString();
				
				Log.e(tag, username);
				Log.e(tag, password);
				aqRegister(username, password, name, email, tel);
           
            	
            }	
        });
        
    }
    
	public String getHttpPost(String url,List<NameValuePair> params) {
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
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
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
	
	public void aqRegister(String username, String password,String name,String email,String tel) {

		//String url = "http://192.168.1.34/androidapi/register.php";
		String url = "http://192.168.43.19/androidapi/register.php";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sUsername", username);
		params.put("sPassword", password);
		params.put("sName", name);
		params.put("sEmail", email);
		params.put("sTel", tel);

		aq.ajax(url, params, JSONObject.class, this, "registerCb");

	}	
	public void registerCb(String url, JSONObject jo, AjaxStatus status)
			throws JSONException {
		if (jo != null) {

			Log.e("myjson", jo.toString());
			if(jo.getString("StatusID").equals("1")){
				Toast.makeText(getBaseContext(), "Status OK", Toast.LENGTH_SHORT).show();
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