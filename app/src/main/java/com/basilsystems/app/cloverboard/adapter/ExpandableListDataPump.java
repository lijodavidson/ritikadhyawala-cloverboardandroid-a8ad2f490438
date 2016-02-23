package com.basilsystems.app.cloverboard.adapter;

import android.graphics.drawable.Icon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by LIJO on 2/19/2016.
 */
public class ExpandableListDataPump {

    public static LinkedHashMap<String, List<String>> getData() {

        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();





        List<String> home = new ArrayList<String>();
        home.add("BEDROOM");
        home.add("KITCHEN");
        home.add("LIVING ROOM");
        expandableListDetail.put("HOME", home);



        List<String> theme = new ArrayList<String>();

        expandableListDetail.put("THEME", theme);

        List<String> notification = new ArrayList<String>();
        expandableListDetail.put("NOTIFICATION", notification);

        List<String> settings = new ArrayList<String>();
        expandableListDetail.put("SETTINGS", settings);

        return expandableListDetail;
    }
}
