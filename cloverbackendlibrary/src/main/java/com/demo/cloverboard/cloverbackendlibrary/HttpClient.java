package com.demo.cloverboard.cloverbackendlibrary;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpClient {

	public static String getResponseFromHTTPServer(URL url) {
		try {
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

				InputStream in = new BufferedInputStream(urlConnection.getInputStream());
				return Util.readStream(in);
			}
		} catch (IOException e) {
			Log.d("HttpURLConnection Err: ", e.getMessage());
			e.printStackTrace();
		}


//		DefaultHttpClient httpclient = new DefaultHttpClient();
//		HttpResponse response;
//		try {
//			response = httpclient.execute(httpGet);
////			response.wait(100);
//			StatusLine statusLine = response.getStatusLine();
//			if(statusLine.getStatusCode() == HttpStatus.SC_OK){
//				ByteArrayOutputStream out = new ByteArrayOutputStream();
//				response.getEntity().writeTo(out);
//				out.close();
//				return out.toString();
//			}else{
//				//Closes the connection.
//				response.getEntity().getContent().close();
//				throw new IOException(statusLine.getReasonPhrase());
//			}
//		}catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		catch (InterruptedException e) {
//			e.printStackTrace();
//		}


		return null;
	}

}
