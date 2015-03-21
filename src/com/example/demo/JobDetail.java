package com.example.demo;

import com.demo.common.BaseUtility;
import com.demo.models.Jobs;

import android.support.v7.app.ActionBarActivity;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class JobDetail extends ActionBarActivity {
    private TextView jobs_title,organization,experience,vacancy,location,salary,job_posted_date,
            description,functional_area,role,key_skills,jobs_link,jobs_type,mail_to;
    Jobs job;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_job_detail);
		job=(Jobs) getIntent().getExtras().getSerializable("job");
		
		setupActionBar();
		
		jobs_title=(TextView) findViewById(R.id.detail_job_title);
		organization=(TextView) findViewById(R.id.detail_organization);
		experience=(TextView) findViewById(R.id.detail_experience);
		vacancy=(TextView) findViewById(R.id.detail_vacany);
		location=(TextView) findViewById(R.id.detail_location);
		salary=(TextView) findViewById(R.id.detail_salary);
		job_posted_date=(TextView) findViewById(R.id.detail_job_posted_date);
		description=(TextView) findViewById(R.id.detail_job_description);
		functional_area=(TextView) findViewById(R.id.detail_functional_area);
		role=(TextView) findViewById(R.id.detail_role);
		key_skills=(TextView) findViewById(R.id.detail_key_skills);
		jobs_link=(TextView) findViewById(R.id.detail_jobs_link);
		jobs_type=(TextView) findViewById(R.id.detail_jobs_type);
		mail_to=(TextView) findViewById(R.id.detail_mail_to);
		
		jobs_title.setText(job.getJobs_title());
		organization.setText(job.getOrganisation_title());
		experience.setText(job.getExperience());
		vacancy.setText("Vacancy :"+job.getVacancy());
		location.setText(job.getLocation());
		if(job.getSalary().equalsIgnoreCase("0"))
		  salary.setText("Not Disclosed");
		else
			salary.setText("INR "+job.getSalary()+"P.A.");
		job_posted_date.setText(job.getJob_posting_date());
		description.setText(job.getDescription());
		functional_area.setText(job.getFunctional_area());
		role.setText(job.getJobs_title());
		key_skills.setText(job.getKey_skills());
		jobs_link.setText(job.getJobs_link());
		jobs_type.setText(job.getJobs_type());
		mail_to.setText(job.getMail_to());
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
		getMenuInflater().inflate(R.menu.job_detail, menu);
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
}
