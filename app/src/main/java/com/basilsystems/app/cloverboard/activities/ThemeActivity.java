package com.basilsystems.app.cloverboard.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.basilsystems.app.cloverboard.util.DemoData;
import com.basilsystems.app.cloverboard.adapter.DialogImagesAdapter;
import com.basilsystems.app.cloverboard.adapter.ThemesAdapter;
import com.demo.cloverboard.cloverboardlibrary.data.Theme;
import com.basilsystems.app.asdasdasdasdas.R;

import java.util.ArrayList;
import java.util.List;

public class ThemeActivity extends AppCompatActivity {
    private GridView gridViewObj;
    private List<Theme> themeArray = new ArrayList<>();
    private ArrayList<Integer> dialogArray = new ArrayList<>();
    private ThemesAdapter themesAdapter;
    private DialogImagesAdapter dialogImagesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);

        //set Main grid view item
        themeArray = DemoData.getThemes("Home");

        //set Dialog grid view item
        dialogArray.add(R.drawable.theme_night);
        dialogArray.add(R.drawable.theme_party);
        dialogArray.add(R.drawable.theme_morning);
        dialogArray.add(R.drawable.theme_evening);
        dialogArray.add(R.drawable.theme_noon);
        dialogArray.add(R.drawable.theme_party);
        //render ThemesAdapter
        gridViewObj = (GridView) findViewById(R.id.gridTheme);
        themesAdapter = new ThemesAdapter(this, themeArray);
        gridViewObj.setAdapter(themesAdapter);

        //set DialogAdapter object
        dialogImagesAdapter = new DialogImagesAdapter(this, dialogArray);

        ImageButton addButt = (ImageButton) findViewById(R.id.addButton);
        addButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(ThemeActivity.this, R.anim.image_click));
                // get dialog_items.xml view
                LayoutInflater li = LayoutInflater.from(ThemeActivity.this);
                View promptsView = li.inflate(R.layout.dialog_items, null);

                // set dialog_items.xml to alertdialog builder
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ThemeActivity.this);
                alertDialogBuilder.setView(promptsView);

                // set items of dialog_items
                final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);
                gridViewObj = (GridView) promptsView.findViewById(R.id.gridView_Dialog);
                gridViewObj.setAdapter(dialogImagesAdapter);

                // set dialog message and actions
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        String input = userInput.getText().toString();
                                        if(input.matches(""))
                                        {
                                            Toast.makeText(getApplicationContext(), "please enter the theme name", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        themeArray.add(new Theme(input, "Home", "theme" + themeArray.size() + 1,  R.drawable.theme_morning));
                                        gridViewObj = (GridView) findViewById(R.id.gridTheme);
                                        gridViewObj.setAdapter(themesAdapter);
                                        Toast.makeText(getApplicationContext(), "Ok button click activity", Toast.LENGTH_SHORT).show();

                                    }

                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_theme, menu);
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
