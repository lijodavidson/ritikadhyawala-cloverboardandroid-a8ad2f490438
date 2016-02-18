package com.basilsystems.app.cloverboard.activities;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.basilsystems.app.asdasdasdasdas.R;
import com.basilsystems.app.cloverboard.adapter.EditThemeAdapter;
import com.basilsystems.app.cloverboard.EditThemeModel;

import java.util.ArrayList;
import java.util.List;

public class EditThemeActivity extends AppCompatActivity {
    private boolean isLongPressed = false;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private RecyclerView recyclerView;
    private EditThemeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_theme);

        toolbar = (Toolbar) findViewById(R.id.edit_theme_toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.edit_theme_collapsingToolbarLayout);

        recyclerView = (RecyclerView) findViewById(R.id.edit_theme_recyclerView);
        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);
        getSupportActionBar().setTitle("Edit Theme");
        getSupportActionBar().setSubtitle("Morning");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeViewsAdapter();
        loadData();
    }

    private void initializeViewsAdapter() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new EditThemeAdapter(getApplicationContext());
        recyclerView.setAdapter(adapter);

    }

    private void loadData() {
        List<EditThemeModel> radioList = new ArrayList<>();
        /**
         * initialize the array list
         */
        radioList.add(new EditThemeModel("Bedroom Fan"));
        radioList.add(new EditThemeModel("Bedroom Lights"));
        radioList.add(new EditThemeModel("Hall Ac"));
        radioList.add(new EditThemeModel("Hall Lights"));
        radioList.add(new EditThemeModel("Bedroom2 Lights"));
        radioList.add(new EditThemeModel("Master Bedroom Fan"));
        radioList.add(new EditThemeModel("Kitchen Lights"));
        radioList.add(new EditThemeModel("Terrace Lights"));
        radioList.add(new EditThemeModel("Bedroom3 Lights"));
        radioList.add(new EditThemeModel("Child room Fan"));
        radioList.add(new EditThemeModel("Porch Lights"));

        adapter.setRadioList(radioList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_theme, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.d
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
