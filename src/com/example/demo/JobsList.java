package com.example.demo;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.demo.common.Adapter;
import com.demo.common.AppPreferences;
import com.demo.common.BaseUtility;
import com.demo.models.Jobs;

import com.webservices.JsonParser;

import android.support.v7.app.ActionBarActivity;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

public class JobsList extends ActionBarActivity implements OnItemClickListener{
    ListView jobs_list;
    ArrayList<Jobs> list;
    Jobs jobs,jobs1;
    LoadJobsListAsync mAsync;
    String URL=null;
    String location_value;
    String jobs_types_value;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jobs_list);
		setupActionBar();
		
		jobs_list=(ListView) findViewById(R.id.jobs_list);
		list=new ArrayList<Jobs>();
        jobs_list.setOnItemClickListener(this);
//   call for job list	
        URL="http://54.169.91.175:8080/dhs/gsapi/jobs/"+AppPreferences.getAppPreference(getApplicationContext(), AppPreferences.SHARED_PREF_TOKEN_ID, "")+"/abcd-sns";
        Log.d("URL", URL);
		mAsync=new LoadJobsListAsync();
		mAsync.execute(URL);
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
		getMenuInflater().inflate(R.menu.jobs_list, menu);
		return true;
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
//		if(id==R.id.create_jobs)
//		{
//			Intent intent=new Intent(JobsList.this, CreateJobs.class);
//			startActivity(intent);
//		}
	if(id==R.id.jobs_filter)
	{
		LayoutInflater layoutInflater 
	     = (LayoutInflater)getBaseContext()
	      .getSystemService(LAYOUT_INFLATER_SERVICE);  
	    View popupView = layoutInflater.inflate(R.layout.jobs_filter_popup, null); 
	    
	             final PopupWindow popupWindow = new PopupWindow(
	               popupView, 
	               900,  
	                     LayoutParams.WRAP_CONTENT);  
	             final String[] location_items=getResources().getStringArray(R.array.jobs_location_ar);
	             final String[] jobs_types_items=getResources().getStringArray(R.array.jobs_types_ar);
	             
	             Spinner location_spinner=(Spinner) popupView.findViewById(R.id.jobs_location_filter);
	             location_spinner.setAdapter(new ArrayAdapter<String>(JobsList.this, android.R.layout.simple_list_item_1, location_items));
	             location_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						// TODO Auto-generated method stub
						location_value=location_items[position];
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				});
	             Spinner jobs_type_spinner=(Spinner) popupView.findViewById(R.id.jobs_types_filter);
	             jobs_type_spinner.setAdapter(new ArrayAdapter<String>(JobsList.this, android.R.layout.simple_list_item_1, jobs_types_items));
	             jobs_type_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						// TODO Auto-generated method stub
						jobs_types_value=jobs_types_items[position];
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				});
	             
	             Button btnDismiss = (Button)popupView.findViewById(R.id.jobs_filter_cancel);
	             btnDismiss.setOnClickListener(new Button.OnClickListener(){

	     @Override
	     public void onClick(View v) {
	      // TODO Auto-generated method stub
	      popupWindow.dismiss();
	     }});
	         Button btnApply=(Button) popupView.findViewById(R.id.jobs_filter_apply);
	         btnApply.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					popupWindow.dismiss();
					list.clear();
					URL="http://54.169.91.175:8080/dhs/gsapi/jobs/"+AppPreferences.getAppPreference(JobsList.this, AppPreferences.SHARED_PREF_TOKEN_ID, "")+"/abcd-sns?location="+location_value+"&jobType="+jobs_types_value;
					mAsync=new LoadJobsListAsync();
					mAsync.execute(URL);
					
				}
			});
	       //     popupWindow.showAsDropDown(popupView, 100, 250);
	              popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
	         
	   
	}

		return super.onOptionsItemSelected(item);
	}
	
	public class LoadJobsListAsync extends AsyncTask<String, Void, ArrayList<String>>
	{
		JsonParser parser=new JsonParser();
        JSONArray jsonArray=null;
        JSONObject jsonObject=null;
        Jobs jobs=null;
        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
        	// TODO Auto-generated method stub
        	super.onPreExecute();
        	pd=new ProgressDialog(JobsList.this);
        	pd.setMessage("Loading Jobs...");
        	pd.setCancelable(false);
        	pd.show();
        }
		@Override
		protected ArrayList<String> doInBackground(String... urls) {
			// TODO Auto-generated method stub
			
			return parser.fetchDataList(urls[0]);
		}
		@Override
		protected void onPostExecute(ArrayList<String> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
			if(result!=null)
			{
				if(result.size()!=0)
				{
					if(Integer.parseInt(result.get(1))==200 && result.get(0)!=null)
					{
				try {
				      Log.d("product json", result.get(0));
					jsonArray=new JSONArray(result.get(0));
					for(int i=0;i<jsonArray.length();i++)
					{  
						jobs=new Jobs();
						jobs.setJobs_title(jsonArray.getJSONObject(i).getString("title"));
						jobs.setOrganisation_title(jsonArray.getJSONObject(i).getString("organization"));
						jobs.setExperience(jsonArray.getJSONObject(i).getString("experience"));
						jobs.setLocation(jsonArray.getJSONObject(i).getString("location"));
						jobs.setJob_posting_date(jsonArray.getJSONObject(i).getString("created"));
						jobs.setDescription(jsonArray.getJSONObject(i).getString("description"));
                        jobs.setKey_skills(jsonArray.getJSONObject(i).getString("desiredSkills"));
                        jobs.setJobs_type(jsonArray.getJSONObject(i).getString("jobType"));
                        jobs.setJobs_link(jsonArray.getJSONObject(i).getString("jobLinks"));
                        jobs.setMail_to(jsonArray.getJSONObject(i).getJSONObject("contact").getString("email"));
                        jobs.setVacancy(jsonArray.getJSONObject(i).getString("noOfPosition"));
                        jobs.setFunctional_area(jsonArray.getJSONObject(i).getString("functionalArea"));
                        jobs.setSalary(jsonArray.getJSONObject(i).getString("salary"));
						list.add(jobs);
					}
						jobs_list.setAdapter(new Adapter(JobsList.this,list));
					
				} catch (Exception e) {
					// TODO: handle exception
					Toast.makeText(getApplicationContext(), "There is problem loading in jobs list !!!", Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
					}
				else
					Toast.makeText(JobsList.this, "No Jobs Available!!!", Toast.LENGTH_LONG).show();
					}
				
				
			}
			else
				Toast.makeText(getApplicationContext(), getResources().getString(R.string.default_service_error), Toast.LENGTH_LONG).show();
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
			pd.dismiss();
		}
		}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(JobsList.this,JobDetail.class);
		intent.putExtra("job", list.get(position));
		startActivity(intent);
	}
	
	
	}

