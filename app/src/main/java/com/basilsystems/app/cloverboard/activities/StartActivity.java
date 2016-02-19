package com.basilsystems.app.cloverboard.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import com.demo.cloverboard.cloverbackendlibrary.StartApplicationService;
import com.demo.cloverboard.cloverbackendlibrary.Util;
import com.basilsystems.app.asdasdasdasdas.R;

/**
 * Created by ritikadhyawala on 23/01/16.
 */
public class StartActivity extends Activity{

    StartApplicationService startAppService;
    boolean mBound = false;

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, StartApplicationService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        IntentFilter filter = new IntentFilter();
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        filter.addAction(Util.START_APP_BROADCAST_INTENT_ACTION);
        StartActivityReceiver receiver = new StartActivityReceiver();
        getApplicationContext().registerReceiver(receiver, filter);

    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            StartApplicationService.LocalBinder binder = (StartApplicationService.LocalBinder) service;
            startAppService = binder.getService();
            mBound = true;
            startAppService.begin();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
        stopService(new Intent(StartActivity.this, StartApplicationService.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        startService(new Intent(StartActivity.this, StartApplicationService.class));

    }

    private class StartActivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Util.START_APP_BROADCAST_INTENT_ACTION)) {
                if (intent.getExtras().containsKey(Util.START_APP_BROADCAST_EXTRA)) {
                    if (intent.getExtras().get(Util.START_APP_BROADCAST_EXTRA).equals(true)) {

                        startActivity(new Intent(StartActivity.this, MainActivity.class));
                    }else{
//                        Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(StartActivity.this, LoginActivity.class));
                    }
                }
            }
        }
    }
}
