package com.webservices;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.util.Log;

public class JsonParser {

	  private  HttpClient httpclient = new DefaultHttpClient();
	   InputStream in;
	   ArrayList<String> result=null;
	   
	   public ArrayList<String> loginPostData(String email,String password,String url)
		{
			ArrayList<String> result=new ArrayList<String>();
			try {
				Log.d("email", email);
				Log.d("pwd", password);
				JSONObject CredJson=new JSONObject();
				CredJson.put("email", email);
				CredJson.put("passwd", getMD5(password));
				CredJson.put("deviceid", "abcd" ); //
	//  make POST request to the given URL
	        HttpPost httpPost = new HttpPost(url);
	        StringEntity se=new StringEntity(CredJson.toString());
	 //       se.setContentType("application/json;charset=UTF-8");
	 //       se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
	        httpPost.setEntity(se);
	        HttpParams httpParameters=new BasicHttpParams();
	        
	    	int timeoutConnection = 10000;
	    	HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
	    	
	    	// Set the default socket timeout (SO_TIMEOUT)
	    	// in milliseconds which is the timeout for waiting for data.
	    	
	    	int timeoutSocket = 60000;
	    	HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

	  //      Set some headers to inform server about the type of the content   
	        httpPost.setHeader("Accept", "application/json");
	        httpPost.setHeader("Content-type", "application/json");
	        
	        
	 //     Execute POST request to the given URL
	        HttpResponse httpResponse = httpclient.execute(httpPost);
	        HttpEntity entity=httpResponse.getEntity();
	        
	        Log.d("status code", ""+httpResponse.getStatusLine().getStatusCode());
	        in=entity.getContent();
	        
	    	if(httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
	    		result.add(convertInputStreamToString(in));
	    		result.add(""+httpResponse.getStatusLine().getStatusCode());
	    		return result;
	    		}
	    	
	        
	        result.add(convertInputStreamToString(in));         
	        result.add(""+httpResponse.getStatusLine().getStatusCode());
	        return result;
	        
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
				
			} 
		}
		
	   public ArrayList<String> fetchDataList(String url)
		{
			try 
			{
			result=new ArrayList<String>();
				 	
			HttpGet httpGet=new HttpGet(url);
			HttpResponse response=httpclient.execute(httpGet);
			HttpEntity entity=response.getEntity();
			in=entity.getContent();
			
			 if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
		    		result.add(convertInputStreamToString(in));
		    		result.add(""+response.getStatusLine().getStatusCode());
		    		return result;
		    		}	    	
		       
		        result.add(convertInputStreamToString(in));         
		        result.add(""+response.getStatusLine().getStatusCode());
		        return result;
		} catch (Exception e) {
			// TODO: handle exception

			return null;
		}
		}
	   
		 private static String convertInputStreamToString(InputStream inputStream) throws IOException
		 {
	         StringBuilder out = new StringBuilder();	            
	         Reader in = new InputStreamReader(inputStream, "UTF-8");	

	         try {
		            int l;
		            char[] buffer = new char[1024];
		            while ((l = in.read(buffer)) != -1) {
		                 out.append(buffer, 0, l);
		            }
		        } 
	         	catch(OutOfMemoryError ex )
	         	{
	         		//w ill see
	         		return "";
	         	}
	         	finally {
		        	inputStream.close();
		        	in.close();
		        }
			 return out.toString();
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

}
