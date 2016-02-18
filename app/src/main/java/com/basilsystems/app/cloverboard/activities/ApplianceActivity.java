package com.basilsystems.app.cloverboard.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.basilsystems.app.asdasdasdasdas.R;
import com.basilsystems.app.cloverboard.Controller;
import com.basilsystems.app.cloverboard.util.DemoData;
import com.basilsystems.app.cloverboard.util.OnApplianceStatusChangeListener;
import com.basilsystems.app.cloverboard.util.OnSwipeTouchListener;
import com.demo.cloverboard.cloverbackendlibrary.CloverActivity;
import com.demo.cloverboard.cloverbackendlibrary.DatabaseHandler;
import com.demo.cloverboard.cloverbackendlibrary.MqttService;
import com.demo.cloverboard.cloverboardlibrary.data.Appliance;
import com.demo.cloverboard.cloverboardlibrary.data.Device;
import com.ibm.mqtt.Mqtt;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ApplianceActivity extends CloverActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;
    private TabLayout tabLayout;
    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView imageView;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private MenuItem menuItem;
    private String temp = "27";
    private String text = temp;
    private int t = 27;
    private NavigationView navigationView;
    long current_time;
    private boolean isLongPressed = false;
    Button acTempButton;
    private Button button;


    @Override
    protected void onBroadcastReceive(Intent intent) {
        String message = (String) intent.getExtras().get("message");
        try {
            JSONObject applianceStatus = new JSONObject(message);
            JSONObject messageForUpdate = new JSONObject();
            Appliance appliance = DemoData.bedroom_appliances.get(applianceStatus.getInt("s"));

            if (applianceStatus.has("o")) {

                appliance.setIsOn(applianceStatus.getInt("o") > 0 ? true : false);

            } else if (applianceStatus.has("a")) {
                appliance.setIsAuto(
                        applianceStatus.getInt("a") > 0 ? true : false);
            } else {
                appliance.setStatus(
                        applianceStatus.getInt("l") * 6);

            }
            DemoData.bedroom_appliances.remove(applianceStatus.getInt("s"));
            DemoData.bedroom_appliances.add(applianceStatus.getInt("s"), appliance);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ((ApplianceFragment) (adapter.getItem(0))).getApplianceAdapter().setData(DemoData.getApplianceList(DemoData.BEDROOM));
        ((ApplianceFragment) (adapter.getItem(0))).getApplianceAdapter().notifyDataSetChanged();


    }

    @Override
    protected void onMqttServiceConnected() {
//TODO: subscribe the topics for the user
        mqttService.subscribeToTopic("email_id1/+");
    }

    @Override
    protected void onInt() {

        setContentView(R.layout.activity_main);
        if (!MqttService.isServiceRunning) {
            startService(new Intent(this, MqttService.class));
        }

        imageView = (ImageView) findViewById(R.id.header);
        imageView.setImageResource(R.drawable.bedroom_header);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(
                R.id.collapse_toolbar);

        collapsingToolbar.setTitleEnabled(false);

        acTempButton = (Button) findViewById(R.id.blue_button);
        button = (Button) findViewById(R.id.main_button20);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("HOME");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        onClickACTempButton();
        onClickACTempUpButton();
        onCLickACTempDownButton();
        customizedButton();

        imageView.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {

            @Override
            public void onSwipeRight() {

                super.onSwipeRight();

                Log.d("Swipe", "Right");
                int currentItem = viewPager.getCurrentItem();
                if (currentItem > 0) {


                    viewPager.setCurrentItem(currentItem - 1);
                }
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Log.d("Swipe", "Left");
                int currentItem = viewPager.getCurrentItem();
                if (currentItem < (viewPager.getChildCount() - 1)) {

                    viewPager.setCurrentItem(currentItem + 1);
                }
            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.d("Position : ", String.valueOf(position));
                current_time = System.currentTimeMillis();
            }

            @Override
            public void onPageSelected(final int position) {
                Log.d("Position1 : ", String.valueOf(position));

                Toast.makeText(getApplicationContext(), String.valueOf(System.currentTimeMillis() - current_time), Toast.LENGTH_SHORT).show();

                switch (position) {


                    case 0:
                        navigationView.setCheckedItem(R.id.item_navigation_bedroom);
                        break;


                    case 1:
                        navigationView.setCheckedItem(R.id.item_navigation_drawer_starred);
                        break;

                    case 2:
                        navigationView.setCheckedItem(R.id.item_navigation_living_room);
                        break;


                }

                navigationView.setCheckedItem(position);

                Animation image_animation_fade_out = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_out);
                final Animation image_animation_fade_in = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);


                image_animation_fade_out.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {


                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
//                        toolbarImage.setVisibility(View.INVISIBLE);
                        switch (position) {

                            case 0:
                                imageView.setImageResource(R.drawable.bedroom_header);
                                break;
                            case 1:
                                imageView.setImageResource(R.drawable.kitchen_header1);
                                break;

                            case 2:
                                imageView.setImageResource(R.drawable.living_header);
                                break;

                            case 3:
                                imageView.setImageResource(R.drawable.bed2);
                                break;

                            case 4:
                                imageView.setImageResource(R.drawable.top);
                                break;
                        }

                        imageView.setAnimation(image_animation_fade_in);
                        imageView.startAnimation(image_animation_fade_in);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                imageView.setAnimation(image_animation_fade_out);
                imageView.startAnimation(image_animation_fade_out);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }

        setupNavigationDrawerContent(navigationView);
        //displays first in nav view

    }

    @Override
    protected void onActivityStart() {

    }

    @Override
    protected void onActivityStop() {

    }


    private void customizedButton() {
        button.setBackgroundResource(R.drawable.off);
        button.setOnClickListener(new View.OnClickListener() {
            boolean isOddClicked = true;

            public void onClick(View v) {


                if (!isLongPressed) {
                    if (isOddClicked) {
                        button.setBackgroundResource(R.drawable.on);
                        isOddClicked = false;

                    } else {
                        button.setBackgroundResource(R.drawable.off);
                        isOddClicked = true;
                    }
                }
            }
        });
        button.setOnLongClickListener(new View.OnLongClickListener() {
            boolean isLongPressed = true;

            @Override
            public boolean onLongClick(View v) {
                if (isLongPressed) {
                    button.setBackgroundResource(R.drawable.auto_on);
                    isLongPressed = false;

                } else {
                    isLongPressed = true;
                }
                return true;
            }
        });
    }


    /**
     * AC Button Intent.
     */
    private void onCLickACTempDownButton() {
        Button tempdown = (Button) findViewById(R.id.dec_temp);
        tempdown.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (t > 16) {
                    t = Integer.parseInt(temp);
                    t = t - 1;
                    temp = String.valueOf(t);
                    text = temp;
                    acTempButton.setText(Html.fromHtml(text + "<sup><small>°</small></sup>" + "<sup><sup><small><small><small>c</small></small></small></sup></sup>"));
                } else {
                    Toast.makeText(getApplicationContext(), "This is the minimum limit",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void onClickACTempUpButton() {

        acTempButton.setText(Html.fromHtml(text + "<sup><small>°</small></sup>" + "<sup><sup><small><small><small>c</small></small></small></sup></sup>"));
        Button tempup = (Button) findViewById(R.id.inc_temp);
        tempup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t < 30) {
                    t = Integer.parseInt(temp);
                    t = t + 1;
                    temp = String.valueOf(t);
                    text = temp;
                    acTempButton.setText(Html.fromHtml(text + "<sup><small>°</small></sup>" + "<sup><sup><small><small><small>c</small></small></small></sup></sup>"));
                } else {
                    Toast.makeText(getApplicationContext(), "This is the maximum limit",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void onClickACTempButton() {


        acTempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), ACRemoteActivity.class);
                startActivity(i);
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        List<Device> devices = DatabaseHandler.getInstance(getApplicationContext()).getAllDevices("Home");
        if (devices.size() > 0) {

            for (Device device : devices) {
                adapter.addFrag(new ApplianceFragment(new OnApplianceStatusChangeListener() {
                    @Override
                    public void onApplianceStatusChange(JSONObject status) {
                        mqttService.publishMessage("controlAppliance/hgjgj", status
                                .toString().getBytes());
                    }
                }, device.getName()), device.getName());

            }
            ;

        }
        viewPager.setAdapter(adapter);


    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();


        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView) {

        Menu navigationMenus = navigationView.getMenu();
        MenuItem homeMenu = navigationMenus.add("Home");
        for (Device device : DemoData.getDevices("Home")) {
            navigationMenus.addSubMenu(device.getName());
        }
        navigationMenus.add("Themes");
        navigationMenus.add("Settings");
        navigationMenus.add("Notifications");

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_bedroom:
                                menuItem.setChecked(true);

                                viewPager.setCurrentItem(0);

                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_starred:
                                menuItem.setChecked(true);

                                viewPager.setCurrentItem(1);

                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;


                            case R.id.item_navigation_living_room:
                                menuItem.setChecked(true);

                                viewPager.setCurrentItem(2);

                                drawerLayout.closeDrawer(GravityCompat.START);

                                return true;


                            case R.id.item_navigation_drawer_logout:
                                menuItem.setChecked(true);
                              /*  ref.unauth();*/
                                Controller.clearCache(getApplicationContext());
//                                Intent z = new Intent(getApplicationContext(), login.class);
//                                startActivity(z);
                                finish();


                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;



                        /*    case R.id.item_navigation_drawer_settings:
                                menuItem.setChecked(true);

                                Toast.makeText(MainActivity.this, "Launching " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                                startActivity(intent);
                                return true;*/

                            case R.id.item_navigation_addnew:
                                menuItem.setChecked(true);

                                LayoutInflater inflater = LayoutInflater.from(ApplianceActivity.this);
                                View subView = inflater.inflate(R.layout.dilog_item, null);
                                final EditText subEditText = (EditText) subView.findViewById(R.id.editdilog);


                                AlertDialog.Builder builder = new AlertDialog.Builder(ApplianceActivity.this, R.style.AppCompatAlertDialogStyle);
                                builder.setTitle("Add Room");

                                builder.setMessage("Press the Configuration Button on the CloverBoard ");
                                builder.setPositiveButton("ADD", null);
                                builder.setNegativeButton("CANCEL", null);
                                builder.setView(subView);
                                builder.show();


                            case R.id.item_navigation_drawer_settings:
                                menuItem.setChecked(true);
                                Toast.makeText(ApplianceActivity.this, menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;


                            case R.id.item_navigation_drawer_theme:
                                menuItem.setChecked(true);
                                Intent the = new Intent(getApplicationContext(), ThemeActivity.class);
                                startActivity(the);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;


                        }
                        return true;
                    }
                });
    }

}


