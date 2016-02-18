package com.basilsystems.app.cloverboard.activities;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.cloverboard.cloverbackendlibrary.SignInAuthenticationService;
import com.demo.cloverboard.cloverbackendlibrary.Util;
import com.basilsystems.app.asdasdasdasdas.R;


public class LoginActivity extends AppCompatActivity {


    SignInAuthenticationService signInService;
    boolean mBound = false;

    private static final String TAG = "LoginActivity";

    private EditText login_email;
    private EditText login_password;
    private Button login_button;
    private TextView forgot;

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, SignInAuthenticationService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        IntentFilter filter = new IntentFilter();
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        filter.addAction(Util.SIGN_IN_BROADCAST_INTENT_ACTION);
        LoginActivityReceiver receiver = new LoginActivityReceiver();
        getApplicationContext().registerReceiver(receiver, filter);

    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            SignInAuthenticationService.LocalBinder binder = (SignInAuthenticationService.LocalBinder) service;
            signInService = binder.getService(LoginActivity.this);
            mBound = true;
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
            stopService(new Intent(LoginActivity.this, SignInAuthenticationService.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        login_email=(EditText)findViewById(R.id.input_email);
        login_button=(Button)findViewById(R.id.btn_login);
        login_password=(EditText)findViewById(R.id.input_password);
        forgot=(TextView)findViewById(R.id.forgot_link);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login_start();
                signInService.authenticateUser(login_email.getText().toString(), login_password.getText().toString());
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                //Intent intent=new Intent();
                //   intent.setClass(this,Signup.class);
                startActivity(intent);
                finish();




            }
        });

        TextView textview1=(TextView) findViewById(R.id.link_signup);
        textview1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), Signup.class);
//
//                //Intent intent=new Intent();
//                //   intent.setClass(this,Signup.class);
//                startActivity(intent);
                finish();

            }

        });

    }

    private void login_start() {

        Log.d(TAG, "Login");
        if (!validate()) {
            Toast.makeText(getApplicationContext(), "Input proper Credentials ", Toast.LENGTH_SHORT).show();
            return;
        }
        login_button.setEnabled(false);


        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppCompatAlertDialogStyle);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating User...");
        progressDialog.show();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onLoginSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 2000);

    }



    private void onLoginSuccess() {

        login_button.setEnabled(true);

    }

    private boolean validate() {
        boolean valid = true;

        final String email=login_email.getText().toString().trim();
        final String password=login_password.getText().toString().trim();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            login_email.setError("enter a valid email address");

            valid = false;
        } else {
            login_email.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            login_password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            login_password.setError(null);
        }

//        SignInAuthenticationService.getInstance(LoginActivity.this).authenticateUser(login_email.getText().toString(), login_password.getText().toString(), new OnAuthenticatedListener() {
//            @Override
//            public void onAuthenticated(Boolean isAuthenticated) {
//
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            }
//        });

        return valid;
    }

    private class LoginActivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Util.SIGN_IN_BROADCAST_INTENT_ACTION)) {
                if (intent.getExtras().containsKey(Util.SIGN_IN_BROADCAST_EXTRA)) {
                    if (intent.getExtras().get(Util.SIGN_IN_BROADCAST_EXTRA).equals(true)) {

                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}
