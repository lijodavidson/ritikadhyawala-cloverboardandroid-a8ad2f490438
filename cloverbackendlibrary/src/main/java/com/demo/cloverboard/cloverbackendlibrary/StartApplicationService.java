package com.demo.cloverboard.cloverbackendlibrary;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ritikadhyawala on 18/01/16.
 */
public class StartApplicationService extends Service {


    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        public StartApplicationService getService() {
            // Return this instance of LocalService so clients can call public methods
            return StartApplicationService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    public void begin() {
        if (SharedPreferenceManager.getInstance(getApplicationContext()).getPreferenceValue(Util.TOKEN, SharedPreferenceManager.STRING) != null) {
// if yes then sign in and open the ListPlaceActivity
            if(Util.isConnectingToInternet(getApplicationContext())){
                new AuthAsyncTask().execute();
            }else{
                proceedToActivity(true);
//                startActivity(new Intent(StartActivity.this, ListPlacesActivity .class));
//                onAuthenticatedListener.onAuthenticated(Boolean.TRUE);
            }

        } else {
            // if not then open the SignInActivity
//            startActivity(new Intent(StartActivity.this, SignInActivity .class));
            proceedToActivity(false);
//            onAuthenticatedListener.onAuthenticated(Boolean.FALSE);
        }
    }

    private void proceedToActivity(boolean value) {
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(Util.START_APP_BROADCAST_INTENT_ACTION);
        broadcastIntent.putExtra(Util.START_APP_BROADCAST_EXTRA, value);
        sendBroadcast(broadcastIntent);
    }

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

                            proceedToActivity(true);
//                            mService.subscribeToTopic(email_id + "/+");
                            // send the getUser topic to the MQTT to get the user data
                            //start the ListPlaceActivity when the user data is rendered
//                            context.startActivity(new Intent(.this, ListPlacesActivity.class));
//                            onAuthenticatedListener.onAuthenticated(Boolean.TRUE);

                        } else {
                            proceedToActivity(false);
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
