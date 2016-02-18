//package com.basilsystems.app.cloverboard;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.support.design.widget.CoordinatorLayout;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.basilsystems.app.asdasdasdasdas.R;
//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;
//
//public class PasswordReset extends AppCompatActivity {
//    private CoordinatorLayout coordinatorLayout;
//private Button reset_mail;
//    private EditText reset_email;
//    private static final String TAG = "PasswordResetActivity";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Firebase.setAndroidContext(this);
//
//        setContentView(R.layout.activity_password_reset);
//        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
//                .coordinatorLayout);
//
//        reset_mail=(Button)findViewById(R.id.btn_reset);
//        reset_email=(EditText)findViewById(R.id.input_email);
//        reset_mail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                rsetpass();
//            }
//        });
//    }
//
//    private void rsetpass() {
//
//
//
//        Log.d(TAG, "Signup");
//
//        if (!validate()) {
//            Toast.makeText(getApplicationContext(), "Input proper Credentials ", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        reset_mail.setEnabled(false);
//
//        final ProgressDialog progressDialog = new ProgressDialog(PasswordReset.this,
//                R.style.AppCompatAlertDialogStyle);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Creating Account...");
//        progressDialog.show();
//
//
//
//
//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onSignupSuccess or onSignupFailed
//                        // depending on success
//                        onmailsent();
//                        // onSignupFailed();
//                        progressDialog.dismiss();
//                    }
//                }, 4000);
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    }
//
//    private boolean validate() {
//
//        boolean valid = true;
//
//        String remail=reset_email.getText().toString().trim();
//
//        if (remail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(remail).matches()) {
//            reset_email.setError("enter a valid email address");
//
//            valid = false;
//        } else {
//            reset_email.setError(null);
//        }
//
//
//
//
//        return valid;
//    }
//
//    private void onmailsent() {
//
//
//        reset_mail.setEnabled(true);
//
//
//
//        String remail=reset_email.getText().toString().trim();
//        final Firebase ref = new Firebase("https://cloverboard.firebaseio.com");
//        ref.resetPassword(remail, new Firebase.ResultHandler() {
//
//
//
//
//            @Override
//            public void onSuccess() {
//
//
//
//
//
//
//
//
//
//
//
//
//
//                Snackbar snackbar = Snackbar
//                        .make(coordinatorLayout, "Mail Sent", Snackbar.LENGTH_LONG)
//                        .setAction("OK", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Intent z = new Intent(getApplicationContext(), login.class);
//                                startActivity(z);
//                                finish();
//                            }
//                        });
//
//                snackbar.show();
//
//
//
//            }
//            @Override
//            public void onError(FirebaseError error) {
//
//
//                switch (error.getCode()) {
//
//                    case FirebaseError.USER_DOES_NOT_EXIST:
//
//
//                        Snackbar snackbar = Snackbar
//                                .make(coordinatorLayout, "USER DOES NOT EXIST", Snackbar.LENGTH_LONG);
//
//                        snackbar.show();
//
//
//                        break;
//
//                    case FirebaseError.NETWORK_ERROR:
//
//                        Snackbar snackbar2 = Snackbar
//                                .make(coordinatorLayout, "NETWORK ERROR", Snackbar.LENGTH_LONG);
//
//                        snackbar2.show();
//
//                        break;
//
//                    case FirebaseError.UNKNOWN_ERROR:
//
//
//                        Snackbar snackbar3 = Snackbar
//                                .make(coordinatorLayout, "UNKNOWN ERROR", Snackbar.LENGTH_LONG);
//
//                        snackbar3.show();
//
//
//                break;
//
//                    default:
//
//                        Snackbar snackbar4 = Snackbar
//                                .make(coordinatorLayout, "SOMETHING WENT WRONG", Snackbar.LENGTH_LONG);
//
//                        snackbar4.show();
//
//
//                        break;
//
//
//                }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//            }
//        });
//
//
//
//
//
//
//
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_password_reset, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//}
