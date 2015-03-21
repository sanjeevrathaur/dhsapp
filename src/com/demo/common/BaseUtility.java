package com.demo.common;

import com.example.demo.R;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;

public class BaseUtility {

	 public static int pxFromDp(Context contect,float dp)
	    {
	        return (int) (dp * contect.getResources().getDisplayMetrics().density);
	    }
	    
		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		public static void setupActionBar(Activity context) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				// Show the Up button in the action bar.
		//		context.getActionBar().setDisplayHomeAsUpEnabled(true);
				context.getActionBar().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.bg));
			}
			else{
				TextView title = (TextView)context.findViewById(android.R.id.title);
				
				if (title != null) {
				  ViewParent parent = title.getParent();
				  if (parent != null && (parent instanceof View)) {
				    View parentView = (View)parent;
				    parentView.setBackgroundResource(R.drawable.bg);
				    android.view.ViewGroup.LayoutParams params=parentView.getLayoutParams();
				    params.height=android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
				    parentView.setLayoutParams(params);
				  }
				  title.setGravity(3);
				  title.setTextColor(context.getResources().getColor(R.color.title_color));
				  title.setShadowLayer(0, 0, 0, R.color.title_color);
				  title.setSingleLine(false);
				  title.setMaxLines(2);
				  title.setTypeface(Typeface.SERIF);
				  title.setPadding(BaseUtility.pxFromDp(context, 8), BaseUtility.pxFromDp(context, 3), BaseUtility.pxFromDp(context, 8), BaseUtility.pxFromDp(context, 3));
				}
				
			}
		}
	}

