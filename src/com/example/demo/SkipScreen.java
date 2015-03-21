package com.example.demo;

import com.demo.common.BaseUtility;

import android.support.v7.app.ActionBarActivity;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SkipScreen extends ActionBarActivity implements OnClickListener{
    private Button organisation,indivisual;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_skip_screen);
		setupActionBar();
		
		organisation=(Button) findViewById(R.id.for_organization_btn);
		indivisual=(Button) findViewById(R.id.for_individual_btn);
		organisation.setOnClickListener(this);
		indivisual.setOnClickListener(this);
	}

	 private boolean appInstalledOrNot(String uri) {
	        PackageManager pm = getPackageManager();
	        boolean app_installed = false;
	        try {
	            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
	            app_installed = true;
	        }
	        catch (PackageManager.NameNotFoundException e) {
	            app_installed = false;
	        }
	        return app_installed ;
	    }
	 
	 /**
		 * Set up the {@link android.app.ActionBar}, if the API is available.
		 */
		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		private void setupActionBar() {
			BaseUtility.setupActionBar(this);
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.skip_screen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent LaunchIntent = new Intent(SkipScreen.this, HomeScreen.class);
        startActivity(LaunchIntent);
	}
}
