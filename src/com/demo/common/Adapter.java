package com.demo.common;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.models.Jobs;
import com.example.demo.R;

public class Adapter extends BaseAdapter{
	ViewHolder holder;
	private final Activity context;
	private final ArrayList<Jobs> jobs_list;
	 private static LayoutInflater inflater=null;
//	 public ImageLoader imageLoader;
//	 VendorCategory categoryListRow;
	public Adapter(Activity context,ArrayList<Jobs> list)
	{
         this.context=context;
         this.jobs_list=list;
         inflater = (LayoutInflater)context.
			        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return jobs_list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	 /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{
          
    	TextView job_title;
		TextView organisation_title;
		TextView experience;
		TextView location;
    }
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		View vi = convertView;
		if(convertView==null)
		{
			vi=inflater.inflate(R.layout.single_row, null);
			
			 holder = new ViewHolder();
	            holder.job_title=(TextView)vi.findViewById(R.id.title);
	            		
	    		holder.organisation_title=(TextView) vi.findViewById(R.id.organisation_title);
	    		holder.experience=(TextView) vi.findViewById(R.id.experience);
	    		holder.location=(TextView) vi.findViewById(R.id.location);
	            
	           /************  Set holder with LayoutInflater ************/
	            vi.setTag( holder );
	        
		}
		else	
			holder=(ViewHolder)vi.getTag();
			
			holder.job_title.setText(jobs_list.get(position).getJobs_title());
			holder.organisation_title.setText(jobs_list.get(position).getOrganisation_title());
			holder.experience.setText(jobs_list.get(position).getExperience());
			holder.location.setText(jobs_list.get(position).getLocation());
			
			
		
		return vi;
	}

}
