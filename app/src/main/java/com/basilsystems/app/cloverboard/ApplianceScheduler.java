package com.basilsystems.app.cloverboard;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextClock;
import android.widget.TimePicker;

import com.basilsystems.app.asdasdasdasdas.R;

import java.util.Calendar;

import butterknife.ButterKnife;

public class ApplianceScheduler extends AppCompatActivity {


    private  Toolbar toolbar;
    private int hour;
    private int minute;
    private Boolean selector = false;
    private Boolean selector2 = false;
    private Boolean selector3 = false;
    private Boolean selector4 = false;
    private Boolean selector5 = false;
    private Boolean selector6 = false;
    private Boolean selector7 = false;
    private boolean isLongPressed = false;
    private Button power_button;
    static final int TIME_DIALOG_ID = 999;
    static final int TIME_DIALOG_ID2 = 99;
    private Button style_button_sunday, style_button_tuesday, style_button_wednesday, style_button_thrusday, style_button_friday, style_button_saturday, style_button_monday, style_button_cancel, style_button_schedule;
    private TextClock clockstart,clockend;
    private CheckBox checkbox_everyweek;
    private Button schedule_power_button;
    private   CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance_scheduler);
        toolbar=(Toolbar)findViewById(R.id.schedule_appliance_toolbar);
        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.schedule_appliance_collapsingToolbarLayout);

        style_button_sunday=(Button)findViewById(R.id.sunday_button);
        style_button_monday=(Button)findViewById(R.id.monday_button);
        style_button_tuesday=(Button)findViewById(R.id.tuesday_button);
        style_button_wednesday=(Button)findViewById(R.id.wednesday_button);
        style_button_thrusday=(Button)findViewById(R.id.thrusday_button);
        style_button_friday=(Button)findViewById(R.id.friday_button);
        style_button_saturday=(Button)findViewById(R.id.saturday_button);
        style_button_cancel=(Button)findViewById(R.id.cancel_button);
        style_button_schedule=(Button)findViewById(R.id.schedule_button);







        clockstart=(TextClock)findViewById(R.id.textClock_start);
        clockend=(TextClock)findViewById(R.id.textClock_end);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);
        getSupportActionBar().setTitle("Schedule Appliance");
        getSupportActionBar().setSubtitle("Bedroom Fan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        schedule_power_button=(Button)findViewById(R.id.schedule_power);
        checkbox_everyweek=(CheckBox)findViewById(R.id.schedule_appliance_checbox);
        checkbox_everyweek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    style_button_sunday.setVisibility(View.VISIBLE);//TO SHOW THE BUTTON

                    style_button_monday.setVisibility(View.VISIBLE);//TO SHOW THE BUTTON

                    style_button_tuesday.setVisibility(View.VISIBLE);//TO SHOW THE BUTTON

                    style_button_wednesday.setVisibility(View.VISIBLE);//TO SHOW THE BUTTON

                    style_button_thrusday.setVisibility(View.VISIBLE);//TO SHOW THE BUTTON

                    style_button_friday.setVisibility(View.VISIBLE);//TO SHOW THE BUTTON

                    style_button_saturday.setVisibility(View.VISIBLE);//TO SHOW THE BUTTON
                } else {
                    style_button_sunday.setVisibility(View.GONE);

                    style_button_monday.setVisibility(View.GONE);

                    style_button_tuesday.setVisibility(View.GONE);

                    style_button_wednesday.setVisibility(View.GONE);

                    style_button_thrusday.setVisibility(View.GONE);

                    style_button_friday.setVisibility(View.GONE);

                    style_button_saturday.setVisibility(View.GONE);
                }
            }
        });



        style_button_sunday.setVisibility(View.GONE);
        style_button_sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selector == false) {
                    style_button_sunday.setSelected(true);
                    style_button_sunday.setTextColor(v.getContext().getResources().getColor(R.color.md_white_1000));
                    selector = true;
                } else {
                    style_button_sunday.setSelected(false);
                    style_button_sunday.setTextColor(v.getContext().getResources().getColor(R.color.md_grey_900));
                    selector = false;
                }
            }
        });



       style_button_monday.setVisibility(View.GONE);
        style_button_monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selector2 == false) {
                    style_button_monday.setSelected(true);
                    style_button_monday.setTextColor(v.getContext().getResources().getColor(R.color.md_white_1000));
                    selector2 = true;
                } else {
                    style_button_monday.setSelected(false);
                    style_button_monday.setTextColor(v.getContext().getResources().getColor(R.color.md_grey_900));
                    selector2 = false;
                }
            }
        });


       style_button_tuesday.setVisibility(View.GONE);
        style_button_tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selector3 == false) {
                    style_button_tuesday.setSelected(true);
                    style_button_tuesday.setTextColor(v.getContext().getResources().getColor(R.color.md_white_1000));
                    selector3 = true;
                } else {
                    style_button_tuesday.setSelected(false);
                    style_button_tuesday.setTextColor(v.getContext().getResources().getColor(R.color.md_grey_900));
                    selector3 = false;
                }
            }
        });

       style_button_wednesday.setVisibility(View.GONE);
        style_button_wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selector4 == false) {
                    style_button_wednesday.setSelected(true);
                    style_button_wednesday.setTextColor(v.getContext().getResources().getColor(R.color.md_white_1000));
                    selector4 = true;
                } else {
                    style_button_wednesday.setSelected(false);
                    style_button_wednesday.setTextColor(v.getContext().getResources().getColor(R.color.md_grey_900));
                    selector4 = false;
                }
            }
        });



       style_button_thrusday.setVisibility(View.GONE);
        style_button_thrusday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selector5 == false) {
                    style_button_thrusday.setSelected(true);
                    style_button_thrusday.setTextColor(v.getContext().getResources().getColor(R.color.md_white_1000));
                    selector5 = true;
                } else {
                    style_button_thrusday.setSelected(false);
                    style_button_thrusday.setTextColor(v.getContext().getResources().getColor(R.color.md_grey_900));
                    selector5 = false;
                }
            }
        });


       style_button_friday.setVisibility(View.GONE);
       style_button_friday.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (selector6 == false) {
                   style_button_friday.setSelected(true);
                   style_button_friday.setTextColor(v.getContext().getResources().getColor(R.color.md_white_1000));
                   selector6 = true;
               } else {
                   style_button_friday.setSelected(false);
                   style_button_friday.setTextColor(v.getContext().getResources().getColor(R.color.md_grey_900));
                   selector6 = false;
               }
           }
       });

        style_button_saturday.setVisibility(View.GONE);
         style_button_saturday.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (selector7 == false) {
                     style_button_saturday.setSelected(true);
                     style_button_saturday.setTextColor(v.getContext().getResources().getColor(R.color.md_white_1000));
                     selector7 = true;
                 } else {
                     style_button_saturday.setSelected(false);
                     style_button_saturday.setTextColor(v.getContext().getResources().getColor(R.color.md_grey_900));
                     selector7 = false;
                 }
             }
         });



        //Scheduler Application Power Button
        schedule_power_button.setBackgroundResource(R.drawable.off);
        schedule_power_button.setOnClickListener(new View.OnClickListener() {
            boolean isOddClicked = true;

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!isLongPressed) {
                    if (isOddClicked) {
                        schedule_power_button.setBackgroundResource(R.drawable.on);
                        isOddClicked = false;

                    } else {
                        schedule_power_button.setBackgroundResource(R.drawable.off);
                        isOddClicked = true;
                    }
                } else {





                }
            }
        });


        schedule_power_button.setOnLongClickListener(new View.OnLongClickListener() {
            boolean isLongPressed = true;

            @Override
            public boolean onLongClick(View v) {
                if (isLongPressed) {
                    schedule_power_button.setBackgroundResource(R.drawable.auto_on);
                    isLongPressed = false;

                } else {

                    isLongPressed = true;
                }
                return true;
            }
        });








        clockstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(TIME_DIALOG_ID);

            }
        });


        clockend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(TIME_DIALOG_ID2);

            }
        });







    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_appliance_scheduler, menu);
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

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                // set time picker as current time
                return new TimePickerDialog(this,
                        timePickerListener, hour, minute, false);
            case TIME_DIALOG_ID2:
                // set time picker as current time
                return new TimePickerDialog(this,
                        timePickerListener2, hour, minute, false);
        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {

                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    String am_pm = "";

                    Calendar datetime = Calendar.getInstance();
                    datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    datetime.set(Calendar.MINUTE, minute);

                    if (datetime.get(Calendar.AM_PM) == Calendar.AM)
                        am_pm = "AM";
                    else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
                        am_pm = "PM";

                    String strHrsToShow = (datetime.get(Calendar.HOUR) == 0) ? "12" : datetime.get(Calendar.HOUR) + "";

                    clockstart.setText(strHrsToShow + ":" + datetime.get(Calendar.MINUTE) + " " + am_pm);
                }
            };
    private TimePickerDialog.OnTimeSetListener timePickerListener2 =
            new TimePickerDialog.OnTimeSetListener() {

                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    String am_pm = "";

                    Calendar datetime = Calendar.getInstance();
                    datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    datetime.set(Calendar.MINUTE, minute);

                    if (datetime.get(Calendar.AM_PM) == Calendar.AM)
                        am_pm = "AM";
                    else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
                        am_pm = "PM";

                    String strHrsToShow = (datetime.get(Calendar.HOUR) == 0) ? "12" : datetime.get(Calendar.HOUR) + "";

                    clockend.setText(strHrsToShow + ":" + datetime.get(Calendar.MINUTE) + " " + am_pm);
                }
            };


}
