package com.basilsystems.app.cloverboard.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.basilsystems.app.asdasdasdasdas.R;
import com.basilsystems.app.cloverboard.ApplianceScheduler;

public class ACRemoteActivity extends AppCompatActivity {

    private boolean isLongPressed = false;
    private Button onOffButton,fan,coolong,dry,turbo,fanAC,heat,up,right,auto,bluebutton2;
    private int count=1,flag3=0,flag6=0,flag7=0,t=27;
    private String temp="27";
    private String text = temp;
    private TextView blueButtontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acremote);
        customizedButton2();
        fan();
        turbo();
        tempup();
        tempdown();
        up();
        right();
        cooling();
        dry();
        fanAC();
        heat();
        auto();
        scheduleActivity();
        deleteActivity();
        editActivity();
    }

    private void customizedButton2() {
        onOffButton = (Button) findViewById(R.id.onOffButton);
        onOffButton.setBackgroundResource(R.drawable.off);
        onOffButton.setOnClickListener(new View.OnClickListener() {
            boolean isOddClicked = true;

            @Override
            public void onClick(View v) {
                if (!isLongPressed) {
                    if (isOddClicked) {
                        onOffButton.setBackgroundResource(R.drawable.on);
                        isOddClicked = false;

                    } else {
                        onOffButton.setBackgroundResource(R.drawable.off);
                        isOddClicked = true;
                    }
                }
            }
        });
        onOffButton.setOnLongClickListener(new View.OnLongClickListener() {
            boolean isLongPressed = true;

            @Override
            public boolean onLongClick(View v) {
                if (isLongPressed) {
                    onOffButton.setBackgroundResource(R.drawable.auto_on);
                    isLongPressed = false;

                } else {
                    isLongPressed = true;
                }
                return true;
            }
        });
    }
    private void fan()
    {
        fan = (Button) findViewById(R.id.fan);
        fan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (count == 1) {
                    fan.setBackgroundResource(R.drawable.fanmedium);
                    count++;
                } else if (count == 2) {
                    fan.setBackgroundResource(R.drawable.fanhigh);
                    count++;
                } else if (count == 3) {
                    fan.setBackgroundResource(R.drawable.fanlow);
                    count = 1;
                }
            }
        });
    }
    private void turbo()
    {
        turbo = (Button) findViewById(R.id.turbo);
        turbo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (flag3 == 0) {
                    turbo.setBackgroundResource(R.drawable.turbo_on);
                    flag3++;
                } else if (flag3 == 1) {
                    turbo.setBackgroundResource(R.drawable.turbo_off);
                    flag3--;
                }
            }
        });
    }
    private void cooling()
    {
        coolong = (Button) findViewById(R.id.cooling);
        blueButtontext = (TextView) findViewById(R.id.blueButtonText);
        coolong.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dry.setBackgroundResource(R.drawable.dryoff);
                fanAC.setBackgroundResource(R.drawable.fanoff);
                heat.setBackgroundResource(R.drawable.heatoff);
                auto.setBackgroundResource(R.drawable.auto);
                coolong.setBackgroundResource(R.drawable.coolon);
                blueButtontext.setText("Cooling");
                bluebutton2.setBackgroundResource(R.drawable.blue_button_in);
            }
        });
    }
    private void dry()
    {

        dry = (Button) findViewById(R.id.dry);
        dry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                coolong.setBackgroundResource(R.drawable.cooloff);
                fanAC.setBackgroundResource(R.drawable.fanoff);
                heat.setBackgroundResource(R.drawable.heatoff);
                auto.setBackgroundResource(R.drawable.auto);
                dry.setBackgroundResource(R.drawable.dryon);
                blueButtontext.setText("   Dry");
                bluebutton2.setBackgroundResource(R.drawable.blue_button_in);
            }
        });
    }
    private void fanAC()
    {
        fanAC = (Button) findViewById(R.id.fan_AC);
        fanAC.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dry.setBackgroundResource(R.drawable.dryoff);
                coolong.setBackgroundResource(R.drawable.cooloff);
                heat.setBackgroundResource(R.drawable.heatoff);
                auto.setBackgroundResource(R.drawable.auto);
                fanAC.setBackgroundResource(R.drawable.fanon);
                blueButtontext.setText("   Fan");
                bluebutton2.setBackgroundResource(R.drawable.black_button_in);
            }
        });
    }
    private void heat()
    {
        heat = (Button) findViewById(R.id.heat);
        heat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dry.setBackgroundResource(R.drawable.dryoff);
                coolong.setBackgroundResource(R.drawable.cooloff);
                fanAC.setBackgroundResource(R.drawable.fanoff);
                auto.setBackgroundResource(R.drawable.auto);
                heat.setBackgroundResource(R.drawable.heaton);
                blueButtontext.setText("  Heat");
                bluebutton2.setBackgroundResource(R.drawable.orange_button_in);
            }
        });
    }
    private void auto()
    {
        auto = (Button) findViewById(R.id.auto);
        auto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dry.setBackgroundResource(R.drawable.dryoff);
                coolong.setBackgroundResource(R.drawable.cooloff);
                fanAC.setBackgroundResource(R.drawable.fanoff);
                heat.setBackgroundResource(R.drawable.heatoff);
                auto.setBackgroundResource(R.drawable.autoon);
                blueButtontext.setText("  Auto");
                bluebutton2.setBackgroundResource(R.drawable.blue_button_in);
            }
        });
    }
    private void up()
    {
        up = (Button) findViewById(R.id.up_down);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (flag6 == 0) {
                    up.setBackgroundResource(R.drawable.up_on);
                    flag6++;
                } else if (flag6 == 1) {
                    up.setBackgroundResource(R.drawable.upoff);
                    flag6--;
                }
            }
        });
    }
    private void right()
    {
        right = (Button) findViewById(R.id.right_on);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (flag7 == 0) {
                    right.setBackgroundResource(R.drawable.right_on);
                    flag7++;
                } else if (flag7 == 1) {
                    right.setBackgroundResource(R.drawable.right_off);
                    flag7--;
                }
            }
        });
    }
    private void tempup()
    {
        bluebutton2 = (Button) findViewById(R.id.blue_button2);
        bluebutton2.setText(Html.fromHtml(text + "<sup><small>°</small></sup>" + "<sup><sup><small><small><small>c</small></small></small></sup></sup>"));
        Button tempup = (Button) findViewById(R.id.tempup);
        tempup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t<30)
                {
                    t = Integer.parseInt(temp);
                    t=t+1;
                    temp = String.valueOf(t);
                    text =temp;
                    bluebutton2.setText(Html.fromHtml(text + "<sup><small>°</small></sup>"+"<sup><sup><small><small><small>c</small></small></small></sup></sup>"));
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "This is the maximum limit",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void tempdown()
    {
        Button tempdown = (Button) findViewById(R.id.tempdown);
        tempdown.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(t>16)
                {
                    t = Integer.parseInt(temp);
                    t=t-1;
                    temp = String.valueOf(t);
                    text =temp;
                    bluebutton2.setText(Html.fromHtml(text + "<sup><small>°</small></sup>"+"<sup><sup><small><small><small>c</small></small></small></sup></sup>"));
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "This is the minimum limit",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private  void scheduleActivity()
    {
        Button schedule = (Button) findViewById(R.id.schedule);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), ApplianceScheduler.class);
                startActivity(i);
            }
        });
    }
    private  void deleteActivity()
    {
        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ACRemoteActivity.this);
                alertDialogBuilder.setMessage("Are you sure,You wanted to delete");

                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(ACRemoteActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                    }
                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }
    private  void editActivity()
    {
        Button edit = (Button) findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), EditApplianceActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_acremote, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
