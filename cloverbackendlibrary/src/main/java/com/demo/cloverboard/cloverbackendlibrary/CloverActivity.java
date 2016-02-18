package com.demo.cloverboard.cloverbackendlibrary;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ritikadhyawala on 12/02/16.
 */
public abstract class CloverActivity extends AppCompatActivity {

    protected abstract void onBroadcastReceive(Intent intent);

    protected abstract void onMqttServiceConnected();

    protected abstract void onInt();

    protected abstract void onActivityStart();
    protected  abstract void onActivityStop();
    private CloverReceiver receiver;

    protected MqttService mqttService;
     boolean isMqttServiceBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.registerReceiver(this.mConnReceiver,
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        onInt();
    }

    private void registerReceiver(String receiverFilter) {
        IntentFilter filter = new IntentFilter(receiverFilter);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(receiver, filter);
    }

    private BroadcastReceiver mConnReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean noConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
            String reason = intent.getStringExtra(ConnectivityManager.EXTRA_REASON);
            boolean isFailover = intent.getBooleanExtra(ConnectivityManager.EXTRA_IS_FAILOVER, false);

            NetworkInfo currentNetworkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            NetworkInfo otherNetworkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_OTHER_NETWORK_INFO);

            if (currentNetworkInfo.isConnected()) {
                Toast.makeText(getApplicationContext(), "Internet Connected", Toast.LENGTH_LONG).show();
                //start the MqttService again
                new AuthAsyncTask().execute();
                startService(new Intent(CloverActivity.this, MqttService.class));
            } else {
                Toast.makeText(getApplicationContext(), "Internet Not Connected", Toast.LENGTH_LONG).show();
                //stop the Mqtt Service
                stopService(new Intent(CloverActivity.this, MqttService.class));
            }
        }
    };

    private class CloverReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            onBroadcastReceive(intent);

        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        onActivityStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (isMqttServiceBound) {
            unbindService(mConnection);
            isMqttServiceBound = false;
        }
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
        onActivityStop();
    }

    /**
     * Defines callbacks for service binding, passed to bindService()
     */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get
            // LocalService instance
            mqttService = ((MqttService.LocalBinder<MqttService>) service).getService();
//            mqttService.rebroadcastReceivedMessages();
            isMqttServiceBound = true;
            onMqttServiceConnected();

        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            isMqttServiceBound = false;
        }
    };

    private class AuthAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {

            String responseFromHTTPServer = null;
            try {

                String uri = Uri.parse("http://52.11.122.248")
                        .buildUpon()
                        .appendQueryParameter("token", (String) SharedPreferenceManager.getInstance(getApplicationContext()).getPreferenceValue(Util.TOKEN, SharedPreferenceManager.STRING))
                        .build().toString();
                String url = uri;
                URL server_url = new URL(url);


                responseFromHTTPServer = HttpClient.getResponseFromHTTPServer(server_url);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return responseFromHTTPServer;

        }

        @Override
        protected void onPostExecute(String responseFromHTTPServer) {
            super.onPostExecute(responseFromHTTPServer);
            {
                JSONObject httpResponse = null;
                try {
                    httpResponse = new JSONObject(responseFromHTTPServer);
                    if (httpResponse.has("authenticated")) {
                        if (httpResponse.getBoolean("authenticated")) {
//                            Toast.makeText(getApplicationContext(), "Successfully authenticated", Toast.LENGTH_SHORT).show();
                            if (httpResponse.has(Util.TOKEN)) {
                                //save the token to the shared preferences
                                SharedPreferenceManager.getInstance(getApplicationContext()).setPreference(Util.TOKEN, httpResponse.getString(Util.TOKEN), SharedPreferenceManager.STRING);
                            }
                            //start the MQTTService
                            startService(new Intent(getApplicationContext(), MqttService.class));

//                            mService.subscribeToTopic(email_id + "/+");
                            // send the getUser topic to the MQTT to get the user data
                            //start the ListPlaceActivity when the user data is rendered
//                            context.startActivity(new Intent(.this, ListPlacesActivity.class));
//                            onAuthenticatedListener.onAuthenticated(Boolean.TRUE);

                        } else {
//                            onAuthenticatedListener.onAuthenticated(Boolean.FALSE);
//                            Toast.makeText(getApplicationContext(), "Not authenticated", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
