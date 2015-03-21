package com.demo.common;

import android.content.Context;
import android.content.SharedPreferences;

public abstract class AppPreferences {

		public final static String SHARED_PREF_NAME = "SPORTY";
		public final static String SHARED_PREF_TOKEN_ID="Token_id";	
		
		public static void setAppPreference(Context context, String key, String value) {
			SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_WORLD_WRITEABLE);
			SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
			sharedPreferencesEditor.putString(key, value);
			sharedPreferencesEditor.commit();
		}

		public static void setAppPreference(Context context, String key, boolean value) {
			SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_WORLD_WRITEABLE);
			SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
			sharedPreferencesEditor.putBoolean(key, value);
			sharedPreferencesEditor.commit();
		}
		
		public static void setAppPreference(Context context, String key, int value) {
			SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_WORLD_WRITEABLE);
			SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
			sharedPreferencesEditor.putInt(key, value);
			sharedPreferencesEditor.commit();
		}

		public static String getAppPreference(Context context, String key, String defaultValue) {
			SharedPreferences sp = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_WORLD_WRITEABLE);
			return sp.getString(key, defaultValue);
		}
		
		public static int getAppPreference(Context context, String key, int defaultValue) {
			SharedPreferences sp = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_WORLD_WRITEABLE);
			return sp.getInt(key, defaultValue);
		}
		
		public static boolean getAppPreference(Context context, String key, boolean defaultValue) {
			SharedPreferences sp = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_WORLD_WRITEABLE);
			return sp.getBoolean(key, defaultValue);
		}

	}// End of the class



