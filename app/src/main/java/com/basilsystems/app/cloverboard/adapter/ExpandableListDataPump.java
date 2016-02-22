package com.basilsystems.app.cloverboard.adapter;

import android.graphics.drawable.Icon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by LIJO on 2/19/2016.
 */
public class ExpandableListDataPump {

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> themes = new ArrayList<String>();
        themes.add("MORNING");
        themes.add("NIGHT ");
        themes.add("PARTY");







        List<String> ahome = new ArrayList<String>();
        ahome.add("BEDROOM");
        ahome.add("KITCHEN");
        ahome.add("LIVING ROOM");


        List<String> settings = new ArrayList<String>();
        List<String> notification = new ArrayList<String>();


        expandableListDetail.put("cNOTIFICATION", notification);
        expandableListDetail.put("bTHEME", themes);
        expandableListDetail.put("dSETTINGS", settings);
        expandableListDetail.put("aHOME", ahome);

        return expandableListDetail;
    }
}
