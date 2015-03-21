package com.demo.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;

public class DeviceInfoManager {

	public static boolean isNetworkAvailable(Context context) {

		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		// if no network is available networkInfo will be null, otherwise check
		// if we are connected
		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		return false;
	}

	public static int getApplicationVersionCode(Context context) {
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			return 1;
		}
	}

	public static String getApplicationVersionName(Context context) {
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			return "1.0";
		}
	}

	public static String getSDKVersion() {
		return Build.VERSION.SDK;
	}// End Method

	public static String getReleaseVersion() {
		return Build.VERSION.RELEASE;
	}// End Method

	public static String getBrandInfo() {
		return Build.DEVICE;
	}// End Method

	public static String getHostInfo() {
		return Build.HOST;
	}// End Method

	public static String getDeviceID() {
		return Build.ID;
	}// End Method

	public static String getProductInfo() {
		return Build.PRODUCT;
	}// End Method

	public static String getModelInfo() {
		return Build.MODEL;
	}// End Method

	public static String getCarriar(Context context) {
		TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return manager.getNetworkOperatorName();
	}
	
	
	public static String getDeviceName() {
		  String manufacturer = Build.MANUFACTURER;
		  String model = Build.MODEL;
		  if (model.startsWith(manufacturer)) {
		    return capitalize(model);
		  } else {
		    return capitalize(manufacturer) + " " + model;
		  }
		}


		private static String capitalize(String s) {
		  if (s == null || s.length() == 0) {
		    return "";
		  }
		  char first = s.charAt(0);
		  if (Character.isUpperCase(first)) {
		    return s;
		  } else {
		    return Character.toUpperCase(first) + s.substring(1);
		  }
		}
		
		 public static String getSecurePassword(String passwordToHash, String salt)
		    {
		        String generatedPassword = null;
		        try {
		            // Create MessageDigest instance for MD5
		            MessageDigest md = MessageDigest.getInstance("MD5");
		            //Add password bytes to digest
		            md.update(salt.getBytes());
		            //Get the hash's bytes
		            byte[] bytes = md.digest(passwordToHash.getBytes());
		            //This bytes[] has bytes in decimal format;
		            //Convert it to hexadecimal format
		            StringBuilder sb = new StringBuilder();
		            for(int i=0; i< bytes.length ;i++)
		            {
		                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		            }
		            //Get complete hashed password in hex format
		            generatedPassword = sb.toString();
		        }
		        catch (NoSuchAlgorithmException e) {
		            e.printStackTrace();
		        }
		        return generatedPassword;
		    }
		     
		    //Add salt
		    public static String getSalt() throws NoSuchAlgorithmException, NoSuchProviderException
		    {
		        //Always use a SecureRandom generator
		        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
		        //Create array for salt
		        byte[] salt = new byte[16];
		        //Get a random salt
		        sr.nextBytes(salt);
		        //return salt
		        return salt.toString();
		    }
			   
			  public static String getMD5(String password)
			  {

				    MessageDigest md;
					try {
						md = MessageDigest.getInstance("MD5");
						md.update(password.getBytes());

					    byte byteData[] = md.digest();

					    StringBuffer sb = new StringBuffer();
					    for (int i = 0; i < byteData.length; i++)
					        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

					    System.out.println("Digest(in hex format):: " + sb.toString());
					    return sb.toString();
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
				    
			  }
}// End of the class
