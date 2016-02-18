package com.demo.cloverboard.cloverbackendlibrary;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;

import com.demo.cloverboard.cloverboardlibrary.data.Appliance;
import com.demo.cloverboard.cloverboardlibrary.data.Device;
import com.demo.cloverboard.cloverboardlibrary.data.Theme;
import com.demo.cloverboard.cloverboardlibrary.data.ModeAppliance;
import com.demo.cloverboard.cloverboardlibrary.data.ModeDevice;
import com.demo.cloverboard.cloverboardlibrary.data.Place;
import com.demo.cloverboard.cloverboardlibrary.data.Schedular;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ritikadhyawala on 16/01/16.
 */
public class SignInAuthenticationService extends Service{


    MqttService mService;
    boolean mBound = false;

    private StartActivityReceiver receiver;
    private ProgressDialog progressDialog;
    /**
     * Defines callbacks for service binding, passed to bindService()
     */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get
            // LocalService instance
//            Toast.makeText(getApplicationContext()
//, "in binding", Toast.LENGTH_SHORT).show();
            mService = ((MqttService.LocalBinder<MqttService>) service).getService();
//            mService.rebroadcastReceivedMessages();
            mBound = true;


//            if (SharedPreferenceManager.getInstance(getApplicationContext()
//).getPreferenceValue(Util.CONFIGURED, SharedPreferenceManager.BOOLEAN) == null || !(Boolean) SharedPreferenceManager.getInstance(getApplicationContext()
//).getPreferenceValue(Util.CONFIGURED, SharedPreferenceManager.BOOLEAN)) {

            // send the getUser topic to the MQTT to get the user data
//                Toast.makeText(getApplicationContext()
//, "in binding 1", Toast.LENGTH_SHORT).show();
            String email_id = (String) SharedPreferenceManager.getInstance(getApplicationContext()).getPreferenceValue(Util.EMAIL_ID, SharedPreferenceManager.STRING);

            if (mService != null) {
                mService.subscribeToTopic(email_id + "/+");
                mService.publishMessage("fromApp/" + email_id, "getUser".getBytes());
                new RegisterBackground().execute();
            }
            // do all the subscriptions

//            }
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();

    private Context activityContext = null;

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        public SignInAuthenticationService getService(Context context) {
            // Return this instance of LocalService so clients can call public methods
            activityContext = context;
            return SignInAuthenticationService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void authenticateUser(String username, String password) {

        new AuthAsyncTask().execute(username, password);
//        progressDialog = new ProgressDialog(activityContext);
//            progressDialog.show();
    }



    public void setDatabase(String data) {
        try {
            DatabaseHandler db_handler = DatabaseHandler
                    .getInstance(getApplicationContext());
            db_handler.onUpgrade(db_handler.getDatabase(), 0,
                    0);
//            JSONArray place_array = new JSONArray(data);

            // JSONArray jsonArray = new JSONArray(data);
            //
            // JSONArray place_array = new JSONArray(
            // jsonArray.get(0).toString());
            JSONObject user = new JSONObject(data);
             JSONArray place_array = user.getJSONArray("places");

            for (int i = 0; i < place_array.length(); i++) {
                JSONObject place_object = place_array.getJSONObject(i);
                Place place;
                if(place_object.has("name")){
                    place = new Place(place_object.getString("name"));
                }
                else
                    place = new Place(place_object.getString("_id"));
                place.setID(place_object.getString("_id"));
                db_handler.setPlaces(place);
                JSONArray device_array = place_object.getJSONArray("devices");
                for (int j = 0; j < device_array.length(); j++) {
                    JSONObject device_object = device_array.getJSONObject(j);
                    Device device = new Device(device_object.getString("name"),
                            place.getID(), device_object.getInt("icon"));
                    device.setDevice_id(device_object.getString("_id"));
                    db_handler.setDevices(device);
                    JSONArray appliance_array = device_object
                            .getJSONArray("appliances");
                    if (device_object.has("appliances")) {
                        for (int k = 0; k < appliance_array.length(); k++) {
                            JSONObject appliance_object = appliance_array
                                    .getJSONObject(k);
                            Appliance appliance = new Appliance(
                                    device.getDevice_id(),
                                    appliance_object.getString("name"),
                                    appliance_object.getInt("status"),
                                    appliance_object.getBoolean("isSlider"),
                                    appliance_object.getBoolean("isAuto"),
                                    appliance_object.getBoolean("isOn"));
                            appliance.setAppliance_id(appliance_object
                                    .getString("_id"));
                            db_handler.addApplianceToDevice(appliance);
                            if (appliance_object.has("schedule")) {
                                if (!appliance_object.get("schedule").equals(null)) {
                                    JSONObject schedule_object = appliance_object
                                            .getJSONObject("schedule");

                                    Schedular schedular = new Schedular(appliance.getID(),
                                            schedule_object.getBoolean("only_today"),
                                            schedule_object.getString("start_time"),
                                            schedule_object.getString("end_time"),
                                            schedule_object.getBoolean("is_repeat"),
                                            schedule_object.getInt("weekly"),
                                            schedule_object.getBoolean("is_auto"),
                                            schedule_object.getInt("status"));
                                    db_handler.setScheduling(schedular);
                                }
                            }
                        }
                    }
                }
            }


//            mService.publishMessage("fromApp/" + SharedPreferenceManager.getInstance(getApplicationContext()
//).getPreferenceValue(Util.EMAIL_ID, SharedPreferenceManager.STRING), "getUserPlacesNames".getBytes());
            mService.publishMessage("fromApp/" + SharedPreferenceManager.getInstance(getApplicationContext()).getPreferenceValue(Util.EMAIL_ID, SharedPreferenceManager.STRING), "getModes".getBytes());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setModesToDatabase(String data) {
        try {
            DatabaseHandler db_handler = DatabaseHandler
                    .getInstance(getApplicationContext());
//             JSONArray jsonArray = new JSONArray(data);

            // JSONArray mode_array = (JSONArray) new JSONArray(((new JSONArray(
            // jsonArray.toString())).get(0)).toString());

            JSONArray mode_array = new JSONArray(data);
            // JSONArray place_array = user.getJSONArray("place_id");

            for (int i = 0; i < mode_array.length(); i++) {
                JSONObject place_object = mode_array.getJSONObject(i);
                if (place_object.has("place_id")) {
                    String place_id = place_object.getString("place_id");
                    if (place_object.has("modes")) {
                        JSONArray modes = place_object.getJSONArray("modes");
                        if (modes.length() > 0) {
                            for (int j = 0; j < modes.length(); j++) {
                                JSONObject mode = modes.getJSONObject(j);
                                if (mode.has("name") && mode.has("_id")) {
                                    db_handler.addMode(new Theme(mode.getString("name"),
                                            place_id,
                                            mode.getString("_id")));
                                    if (mode.has("devices")) {
                                        JSONArray device_array = mode.getJSONArray("devices");
                                        for (int k = 0; k < device_array.length(); k++) {
                                            JSONObject device_object = device_array.getJSONObject(k);
                                            db_handler.setModeDevices(new ModeDevice(device_object
                                                    .getString("device_id"), mode
                                                    .getString("_id")));
                                            JSONArray appliance_array = device_object
                                                    .getJSONArray("appliances");
                                            for (int l = 0; l < appliance_array.length(); l++) {
                                                JSONObject appliance_object = appliance_array
                                                        .getJSONObject(l);
                                                db_handler.setModeAppliances(new ModeAppliance(
                                                        appliance_object.getString("_id"),
                                                        device_object.getString("device_id"),
                                                        appliance_object.getBoolean("isAuto"),
                                                        appliance_object.getBoolean("isSlider"),
                                                        appliance_object.getInt("status"),
                                                        appliance_object.getBoolean("isOn")));
                                            }
                                        }

                                    }


                                }
                            }
                        }
                    }
                }
            }

            SharedPreferenceManager.getInstance(getApplicationContext())
                    .setPreference(Util.CONFIGURED, true,
                            SharedPreferenceManager.BOOLEAN);

//            startActivity(new Intent(activityContext,
//                    M.class));
            //send broadcast
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(Util.SIGN_IN_BROADCAST_INTENT_ACTION);
            broadcastIntent.putExtra(Util.SIGN_IN_BROADCAST_EXTRA, true);
            sendBroadcast(broadcastIntent);
//            onAuthenticatedListener.onAuthenticated(Boolean.TRUE);
//            progressDialog.dismiss();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setNamesToDatabase(String data) {
        try {
            DatabaseHandler db_handler = DatabaseHandler
                    .getInstance(getApplicationContext());
            // JSONArray jsonArray = new JSONArray(data);
            //
            // JSONArray place_array = (JSONArray) new JSONArray(((new
            // JSONArray(
            // jsonArray.toString())).get(0)).toString());

            // JSONArray place_array = user.getJSONArray("place_id");

            JSONArray place_array = new JSONArray(data);
            for (int i = 0; i < place_array.length(); i++) {
                JSONObject place_object = place_array.getJSONObject(i);
                String place_name = place_object.getString("place_name");
                db_handler.updatePlaceName(place_name,
                        place_object.getString("place_id"));
                JSONArray device_array = place_object
                        .getJSONArray("device_names");
                for (int j = 0; j < device_array.length(); j++) {
                    JSONObject device_object = device_array.getJSONObject(j);
                    db_handler.updateDeviceName(
                            device_object.getString("device_name"),
                            device_object.getString("device_id"));
                    JSONArray appliance_array = device_object
                            .getJSONArray("appliance_names")  ;
                    for (int k = 0; k < appliance_array.length(); k++) {
                        JSONObject appliance_object = appliance_array
                                .getJSONObject(k);
                        db_handler.updateApplianceName(
                                appliance_object.getString("appliance_name"),
                                appliance_object.getString("appliance_id"));
                    }
                }
            }
            mService.publishMessage("fromApp/" + SharedPreferenceManager.getInstance(getApplicationContext()).getPreferenceValue(Util.EMAIL_ID, SharedPreferenceManager.STRING), "getModes".getBytes());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class AuthAsyncTask extends AsyncTask<String, Void, String> {

        private String username;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String responseFromHTTPServer) {
            super.onPostExecute(responseFromHTTPServer);
//            progressDialog.dismiss();

            if (responseFromHTTPServer != null) {
                JSONObject httpResponse = null;
                try {
                    httpResponse = new JSONObject(responseFromHTTPServer);


                    if (httpResponse.has("authenticated")) {

                        if (httpResponse.has(Util.TOKEN)) {
                            //save the token to the shared preferences

                            SharedPreferenceManager.getInstance(getApplicationContext()).setPreference(Util.EMAIL_ID, username, SharedPreferenceManager.STRING);

                            SharedPreferenceManager.getInstance(getApplicationContext()).setPreference(Util.TOKEN, httpResponse.getString(Util.TOKEN), SharedPreferenceManager.STRING);

                        } else {
//                            startActivity(new Intent(SignInActivity.this, ListPlacesActivity.class));
                            Intent broadcastIntent = new Intent();
                            broadcastIntent.setAction(Util.SIGN_IN_BROADCAST_INTENT_ACTION);
                            broadcastIntent.putExtra(Util.SIGN_IN_BROADCAST_EXTRA, true);
                            sendBroadcast(broadcastIntent);
//                            onAuthenticatedListener.onAuthenticated(Boolean.TRUE);
                        }
                        //start the receiver

                        IntentFilter filter = new IntentFilter();
                        filter.addCategory(Intent.CATEGORY_DEFAULT);
                        filter.addAction("getUserData");
                        filter.addAction(Util.MQTT_STATUS_INTENT);
                        receiver = new StartActivityReceiver();
                        getApplicationContext().registerReceiver(receiver, filter);
                        //start the MQTTService
                        getApplicationContext().startService(new Intent(activityContext
                                , MqttService.class));
                    }else{
                        //send broadcast
//                        onAuthenticatedListener.onAuthenticated(Boolean.FALSE);
                        Intent broadcastIntent = new Intent();
                        broadcastIntent.setAction(Util.SIGN_IN_BROADCAST_INTENT_ACTION);
                        broadcastIntent.putExtra(Util.SIGN_IN_BROADCAST_EXTRA, false);
                        sendBroadcast(broadcastIntent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... credentials) {

            String responseFromHTTPServer = null;
            username  = credentials[0];
            try {

                /*ec2-52-11-122-248.us-west-2.compute.amazonaws.com*/
                String uri = Uri.parse("http://52.11.122.248")
                        .buildUpon()
                        .appendQueryParameter("email_id", credentials[0])
                        .appendQueryParameter("password", credentials[1])
                        .build().toString();
                String url = uri ;
                URL server_url = new URL(url);


                responseFromHTTPServer = HttpClient.getResponseFromHTTPServer(server_url);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return responseFromHTTPServer;

        }
    }

    private class StartActivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Util.MQTT_STATUS_INTENT)) {
                if (intent.getExtras().containsKey(Util.MQTT_STATUS_MSG)) {
                    if (intent.getExtras().get(Util.MQTT_STATUS_MSG).equals("Started")) {
                        Intent bind_intent = new Intent(getApplicationContext(), MqttService.class);
                        getApplicationContext().bindService(bind_intent, mConnection, getApplicationContext()
                                .BIND_AUTO_CREATE);
                    }
                }
            } else if (intent.getAction().equals("getUserData")){
                if (intent.getExtras().containsKey("getUser")) {
                    String userData = intent.getExtras().getString("getUser");
                    setDatabase(userData);
                } else if (intent.getExtras().containsKey("getModes")) {
                    String userData = intent.getExtras().getString("getModes");
                    setModesToDatabase(userData);
                }
                if (intent.getExtras().containsKey("getUserPlacesNames")) {
                    String userData = intent.getExtras().getString(
                            "getUserPlacesNames");
                    setNamesToDatabase(userData);
                }
            }
        }
    }

    class RegisterBackground extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... arg0) {
            GoogleCloudMessaging gcm = GoogleCloudMessaging
                    .getInstance(activityContext);
            String regid;
            try {
                regid = gcm.register(Util.SENDER_ID);

                JSONObject gcm_object = new JSONObject();
                gcm_object.put("gcm_regid", regid);
                gcm_object.put("type", "gcm_reg");
                mService.publishMessage("fromApp/" + SharedPreferenceManager.getInstance(getApplicationContext()).getPreferenceValue(Util.EMAIL_ID,
                        SharedPreferenceManager.STRING), regid.getBytes());

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

    }

}

