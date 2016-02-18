package com.demo.cloverboard.cloverbackendlibrary;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Util {
	
//	public static final String URL = "http://cloverboard-servercode.jit.su/";
//	public static final String URL = "https://cloverboard-c9-ritikadhyawala.c9.io/";
//	public static final String URL = "10.50.15.119";
//	public static final String URL = "192.168.1.3";
	public static final String URL = "ec2-52-11-122-248.us-west-2.compute.amazonaws.com";
	
//	public static final String URL = "http://52.11.122.248";
	public static final String CONNECTION = "connection";
	public static final String EMAIL_ID = "email_id";
	public static final String CONFIGURED = "configured";
	// constants used to tell the Activity UI the connection status
    public static final String MQTT_STATUS_INTENT = "com.dalelane.mqtt.STATUS";
	public static final String MQTT_STATUS_MSG = "com.dalelane.mqtt.STATUS_MSG";
	public static final String SIGN_IN_BROADCAST_EXTRA = "SignInBroadcastExtra";
	public static final String SIGN_IN_BROADCAST_INTENT_ACTION = "SignInBroadcastIntentAction";

	public static final String START_APP_BROADCAST_EXTRA = "StartAppBroadcastExtra";
	public static final String START_APP_BROADCAST_INTENT_ACTION = "StartAppBroadcastIntentAction";

	public static void showToast(Context context, String text){
		if(context != null && text != null && !text.isEmpty()){
			Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
		}
		
	}

	public static final String TOKEN = "token";
	public static final String RESP_BROADCAST_ACTION = "com.basilsystems.broadcast";
	public static final String APPLIANCE_BROADCAST_ACTION = "com.basilsystems.broadcast.appliance";
	public static final String LOGIN_ACTIVITY = "LoginActivity";
	public static final String FROM_ACTIVITY = "from_activity";
	public static final String SENDER_ID = "263514921368";
	public static final String GCM_REGID = "gcm_regid";
	public static final String UPDATE_DATA = "updateData";
	public static final String ADDPLACE = "addPlace";
	
	public static String PLACE = "place";
	public static String DEVICE = "device";
	
	public static String SELECTED_PLACE = "selected_place";
	public static String SELECTED_DEVICE = "selected_device";
	public static String SELECTED_APPLIANCE = "selected_appliance";
	
	public static String SELECTED_MODE = "selected_mode1";
	public static String SELECTED_MODE_DEVICE = "selected_mode_device";
	public static final String GET_USER = "getUser";
	public static final String EVENT2 = "event";
	public static final String MESSAGE = "message";
	public static final String DB_UPDATE = "db_update";
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	public static final String CHANGE_APPLIANCE_STATUS = "change_appliance_status";
	
	public static String getSelectedStrings(String selection, Context context) {
		return (String)SharedPreferenceManager.getInstance(context).getPreferenceValue(selection, SharedPreferenceManager.STRING);
	}

	public static final String GET_USER_PLACES_NAMES = "getUserPlacesNames";
	public static final String GET_MODES = "getModes";
	public static final String UPDATE_NAME = "update_name";
	
	public static final int LIVING_ROOM = 0;
	public static final int BEDROOM = 1;
	public static final int KITCHEN = 2;
	public static final int STUDY_ROOM = 3;
	public static final int ALL_APPLIANCES = 4;
	public static final int ADD_DEVICE = 5;
	public static final String LIST_PLACES_ACTIVITY = "ListPlacesActivity";
	public static final String ACCESS_INTERNET_CONTROL = "AccessInternetControl";
	public static final String REMOTE_NAME = "remoteName";
	public static final String REMOTE_NAMES = "remote_names";
	public static final String GOT_RAW_CODE = "GotRawCode";

	public static final String IR_REMOTE = "irRemote";

	public static final String COM_BASILSYSTEMS_BROADCAST_REMOTE = "com.basilsystems.broadcast.remote";

	public static final String DB_VERSION = "DbVersion";

	public static String readStream(InputStream is) {
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			int i = is.read();
			while(i != -1) {
				bo.write(i);
				i = is.read();
			}
			return bo.toString();
		} catch (IOException e) {
			return "";
		}
	}

	public static boolean isConnectingToInternet(Context context){
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null)
		{
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED)
					{
						return true;
					}

		}
		return false;
	}
}
