package com.basilsystems.app.cloverboard.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.basilsystems.app.cloverboard.util.DemoData;
import com.demo.cloverboard.cloverboardlibrary.data.Appliance;
import com.demo.cloverboard.cloverboardlibrary.data.Appliance.ApplianceType;
import com.demo.cloverboard.cloverboardlibrary.data.Device;
import com.demo.cloverboard.cloverboardlibrary.data.Theme;
import com.basilsystems.app.asdasdasdasdas.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EditApplianceActivity extends AppCompatActivity {


    @Bind(R.id.edit_appliance_toolbar)
    Toolbar toolbar;

    @Bind(R.id.edit_appliance_collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    //Spinner Data
    private String[] spinner_appliance_type_data = {"Light Source Dimmable", "Only Light Source", "Non Light Source Dimmable", "Non Light Source"};
    private String[] spinner_appliance_functionality_data = {"Own", "ThemeActivity", "Appliance"};

    //initialize spinners
    private Spinner applianceTypeSpinner, applianceFunctionSpinner;
    private Button button_save, button_cancel;
    private String appliance_id;
    private EditText appliance_name;
    private String selected_room;;
    private int appliance_position;
     Appliance edited_appliance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appliances);


        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            if (intent.getExtras().containsKey("appliance_id")) {
                appliance_id = intent.getExtras().getString("appliance_id").toString();
            }
            if (intent.getExtras().containsKey("selected_room")) {
                selected_room = intent.getExtras().getString("selected_room").toString();
            }
            if (intent.getExtras().containsKey("appliance_position")) {
                appliance_position = intent.getExtras().getInt("appliance_position");
            }
        }

        edited_appliance = DemoData.getApplianceList(selected_room).get(appliance_position);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);
        getSupportActionBar().setTitle("Edit Appliances");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//Finding Views to the Items

        applianceTypeSpinner = (Spinner) findViewById(R.id.spinner1);
        applianceFunctionSpinner = (Spinner) findViewById(R.id.spinner2);

        button_save = (Button) findViewById(R.id.button_save);
        button_cancel = (Button) findViewById(R.id.button_cancel);


        appliance_name = (EditText) findViewById(R.id.appliance_name);

        appliance_name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    if(edited_appliance.roleAssigned == Appliance.Role.OWN) {
                        edited_appliance.setApplianceName(appliance_name.getText().toString());
                    }else if(edited_appliance.roleAssigned == Appliance.Role.THEME) {
                       //TODO : change the theme name here
                    }else if(edited_appliance.roleAssigned == Appliance.Role.OTHER_APPLIANCE) {
                       //TODO: change the other appliance name here
                    }
                    return true;
                }
                return false;
            }
        });
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditApplianceActivity.this, MainActivity.class));
            }
        });
        setupSpinner();


    }

    private void setupSpinner() {

        //Setup Appliance Type spinner

        ArrayAdapter<String> spinner_type_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinner_appliance_type_data);
        spinner_type_adapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        applianceTypeSpinner.setAdapter(spinner_type_adapter);


        //Setup Appliance Functionality spinner

        ArrayAdapter<String> spinner_functionality_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinner_appliance_functionality_data);
        spinner_functionality_adapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        applianceFunctionSpinner.setAdapter(spinner_functionality_adapter);
        applianceFunctionSpinner.setSelection(0);


        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DemoData.getApplianceList(selected_room).remove(appliance_position);
                DemoData.getApplianceList(selected_room).add(appliance_position, edited_appliance);
                startActivity(new Intent(EditApplianceActivity.this, MainActivity.class));
            }
        });

        if(edited_appliance.roleAssigned == Appliance.Role.OWN){
            applianceFunctionSpinner.setSelection(0);
        }else if(edited_appliance.roleAssigned == Appliance.Role.THEME){
            applianceFunctionSpinner.setSelection(1);
            applianceTypeSpinner.setVisibility(View.INVISIBLE);
        }else if(edited_appliance.roleAssigned == Appliance.Role.OTHER_APPLIANCE){
            applianceFunctionSpinner.setSelection(2);
        }

        if(edited_appliance.applianceType == ApplianceType.LIGHT_SOURCE_DIMMABLE){
             applianceTypeSpinner.setSelection(0);
        } else if(edited_appliance.applianceType == ApplianceType.ONLY_LIGHT_SOURCE){
            applianceTypeSpinner.setSelection(1);
        } else if(edited_appliance.applianceType == ApplianceType.NON_LIGHT_SOURCE_DIMMABLE){
            applianceTypeSpinner.setSelection(2);
        } else if(edited_appliance.applianceType == ApplianceType.NON_LIGHT_SOURCE){
            applianceTypeSpinner.setSelection(3);
        }

        applianceTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

        {
            public void onItemSelected(
                    AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        edited_appliance.applianceType = ApplianceType.LIGHT_SOURCE_DIMMABLE;
                        break;
                    case 1:
                        edited_appliance.applianceType = ApplianceType.ONLY_LIGHT_SOURCE;
                        break;
                    case 2:
                        edited_appliance.applianceType = ApplianceType.NON_LIGHT_SOURCE_DIMMABLE;
                        break;
                    case 3:
                        edited_appliance.applianceType = ApplianceType.NON_LIGHT_SOURCE;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        applianceFunctionSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener()

                {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                edited_appliance.setRoleToOwn();
                                break;
                            case 1:
                                //the role is assigned to a theme
                                AlertDialog levelDialog;
                                applianceTypeSpinner.setVisibility(View.INVISIBLE);
                                // Strings to Show In Dialog with Radio Buttons
//                                final CharSequence[] items = {" Morning ", " Afternoon ", " Evening ", " Night "};
                                final List<String> items = new ArrayList<>();
                                for (Theme theme : DemoData.getThemes("Home")) {
                                    items.add(theme.getThemeName());
                                }
                                // Creating and Building the Dialog
                                AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                                builder.setTitle("Choose ThemeActivity");
                                final int[] selectedItem = new int[1];
                                builder.setSingleChoiceItems(items.toArray(new CharSequence[items.size()]), -1, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int item) {

                                        /* levelDialog.dismiss();*/
                                        selectedItem[0] = item;


                                    }
                                });
                                builder
                                        .setCancelable(false)
                                        .setPositiveButton("CHOOSE",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        edited_appliance.setRoleToTheme(items.get(selectedItem[0]));
                                                    }

                                                })
                                        .setNegativeButton("CANCEL",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.cancel();

                                                    }
                                                });
                                levelDialog = builder.create();
                                levelDialog.show();
                                break;
                            case 2:
                                // Your code when 3rd option seletced
                                AlertDialog levelDialog2;

                                // Strings to Show In Dialog with Radio Buttons
//                                final CharSequence[] items2 = {" Kitchen Lights ", " Hall Fan ", " Hall Lights ", " Bedroom "};
                                final List<String> items2 = new ArrayList<String>();
                                for (Device device : DemoData.getDevices("Home")) {
                                    for (Appliance appliance : DemoData.getApplianceList(device.getName())) {
                                        items2.add(device.getName() + " " + appliance.getApplianceName());
                                    }
                                }

                                // Creating and Building the Dialog
                                AlertDialog.Builder builder2 = new AlertDialog.Builder(view.getRootView().getContext());
                                builder2.setTitle("Choose Appliance");
                                final int[] selectedPosition = new int[1];
                                builder2.setSingleChoiceItems(items2.toArray(new CharSequence[items2.size()]), -1, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int item2) {
                                        selectedPosition[0] = item2;

                                    }
                                });
                                builder2
                                        .setCancelable(false)
                                        .setPositiveButton("CHOOSE",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        edited_appliance.setRoleToOtherAppliance(items2.get(selectedPosition[0]));
                                                    }

                                                })
                                        .setNegativeButton("CANCEL",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.cancel();
                                                    }
                                                });
                                levelDialog2 = builder2.create();
                                levelDialog2.show();
                                break;

                        }

                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        showToast("Spinner2: unselected");
                    }
                });
    }

    void showToast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_appliances, menu);
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
