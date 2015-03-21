package com.example.demo;

import java.util.ArrayList;

import com.demo.common.AppPreferences;
import com.demo.common.BaseUtility;
import com.webservices.JsonParser;

import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends ActionBarActivity implements OnClickListener{
    private EditText email,password;
    private Button login,skip;
    LoginTaskAsync loginAsync;
    String email_value,password_value;
    private static final String URL="http://54.169.91.175:8080/dhs/gsapi/users";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_screen);
		setupActionBar();
		
		email=(EditText) findViewById(R.id.email);
		password=(EditText) findViewById(R.id.password);
		login=(Button) findViewById(R.id.login_btn);
		skip=(Button) findViewById(R.id.skip_btn);
	    
		login.setOnClickListener(this);
		skip.setOnClickListener(this);
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		BaseUtility.setupActionBar(this);
	}

	public void attemptLogin()
	{
		if(loginAsync!=null)
		{
			return;
		}
		
		email.setError(null);
		password.setError(null);
		
		email_value=email.getText().toString();
		password_value=password.getText().toString();
		
		boolean cancel=false;
		View focusView=null;
		
		if(TextUtils.isEmpty(email_value))
		{
			email.setError(getResources().getString(R.string.error_field_required));
			focusView=email;
			cancel=true;
		}
		else if(!email_value.contains("@"))
		{
			email.setError(getResources().getString(R.string.incorrect_email_id));
			focusView=email;
			cancel=true;
		}
		
		if(TextUtils.isEmpty(password_value))
		{
			password.setError(getResources().getString(R.string.error_field_required));
			focusView=password;
			cancel=true;
		}
		
		if(cancel)
		{
			focusView.requestFocus();
		}
		else
		{
			loginAsync=new LoginTaskAsync();
			loginAsync.execute(URL);
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login_screen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==login)
		{
			attemptLogin();
//			Intent goToMarket = new Intent(Intent.ACTION_VIEW)
//		    .setData(Uri.parse("market://details?id=com.whatsapp"));
//		startActivity(goToMarket);
		
//		Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
//		startActivity(launchIntent);
		}
		else if(v==skip)
		{
			Intent intent=new Intent(LoginScreen.this, SkipScreen.class);
			startActivity(intent);
		}
	}
	
	public class LoginTaskAsync extends AsyncTask<String, Void, ArrayList<String>>
	{
		ProgressDialog pd;
       @Override
    protected void onPreExecute() {
    	// TODO Auto-generated method stub
    	super.onPreExecute();
    	pd=new ProgressDialog(LoginScreen.this);
    	pd.setMessage("progress...");
    	pd.setCancelable(false);
    	pd.show();
    }
		@Override
		protected ArrayList<String> doInBackground(String... urls) {
			// TODO Auto-generated method stub
			JsonParser jsonParser=new JsonParser();
			return jsonParser.loginPostData(email_value, password_value, urls[0]);
		}
		@Override
		protected void onPostExecute(ArrayList<String> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			loginAsync=null;
			pd.dismiss();
			if(result!=null)
			{
			if(result.size()!=0)
			{
				Log.d("result", result.get(0));
		      if(Integer.parseInt(result.get(1))==200 && result.get(0)!=null){
		    	  Log.d("tokenID", result.get(0));
		//    	    AppPreferences.setAppPreference(getApplicationContext(), AppPreferences.SHARED_PREF_VENDOR_NAME, UserEmailID);
					AppPreferences.setAppPreference(getApplicationContext(), AppPreferences.SHARED_PREF_TOKEN_ID, result.get(0));				
					Intent intent=new Intent(LoginScreen.this, HomeScreen.class);
					startActivity(intent);
		//			finish();
		      }
		      else
		    	  Toast.makeText(LoginScreen.this, result.get(0), Toast.LENGTH_LONG).show(); 
			}
			}
			else{
				Toast.makeText(LoginScreen.this, getString(R.string.default_service_error), Toast.LENGTH_LONG).show();
			
			
			}
		
			
		}
		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
			loginAsync=null;
			pd.dismiss();
		}
	}
}
