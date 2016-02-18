//package com.basilsystems.app.cloverboard;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.basilsystems.app.asdasdasdasdas.R;
//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;
//
//import org.eclipse.paho.android.service.MqttAndroidClient;
//import org.eclipse.paho.client.mqttv3.IMqttActionListener;
//import org.eclipse.paho.client.mqttv3.IMqttToken;
//import org.eclipse.paho.client.mqttv3.MqttClient;
//import org.eclipse.paho.client.mqttv3.MqttException;
//
//public class Signup extends AppCompatActivity {
//
//
//    private static final String TAG ="MQTTActivity" ;
//    private EditText signup_email;
//    private EditText signup_password;
//    private EditText signup_name;
//    MqttAndroidClient client;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Firebase.setAndroidContext(this);
//        setContentView(R.layout.activity_signup);
//        signup_email=(EditText)findViewById(R.id.input_email);
//        signup_password=(EditText)findViewById(R.id.input_password);
//        signup_name=(EditText)findViewById(R.id.input_name);
//        Button signup_button = (Button) findViewById(R.id.btn_signup);
//        TextView already_login = (TextView) findViewById(R.id.link_login);
//
//
//        String clientId = MqttClient.generateClientId();
//        client = new MqttAndroidClient(this.getApplicationContext(), "tcp://10.50.8.24:9244", clientId);
//
//        try {
//            IMqttToken token = client.connect();
//            token.setActionCallback(new IMqttActionListener() {
//                @Override
//                public void onSuccess(IMqttToken asyncActionToken) {
//                    // We are connected
//                    Log.d(TAG, "onSuccess");
//                    Toast.makeText(getApplicationContext(), "Connection Successful", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                    // Something went wrong e.g. connection timeout or firewall problems
//                    Log.d(TAG, "onFailure");
//
//                    Toast.makeText(getApplicationContext(), "Connection Unsuccessful", Toast.LENGTH_SHORT).show();
//
//
//
//
//                }
//
//
//            });
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
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
//        signup_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });
//
//        already_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent z = new Intent(getApplicationContext(), login.class);
//                startActivity(z);
//                finish();
//
//            }
//        });
//
//
//
//
//
//    }
//
//
//
//
//
//
//
//    public boolean validate() {
//        boolean valid = true;
//
//        String name = signup_name.getText().toString();
//        String email = signup_email.getText().toString();
//        String password = signup_password.getText().toString();
//
//        if (name.isEmpty() || name.length() < 3) {
//
//            signup_name.setError("at least 3 characters");
//            valid = false;
//        } else {
//            signup_name.setError(null);
//        }
//
//        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            signup_email.setError("enter a valid email address");
//
//            valid = false;
//        } else {
//            signup_email.setError(null);
//        }
//
//        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
//            signup_password.setError("between 4 and 10 alphanumeric characters");
//            valid = false;
//        } else {
//            signup_password.setError(null);
//        }
//
//        return valid;
//    }
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
//}
