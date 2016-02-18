package com.basilsystems.app.cloverboard.util;


import com.demo.cloverboard.cloverboardlibrary.data.Appliance;
import com.demo.cloverboard.cloverboardlibrary.data.Device;
import com.demo.cloverboard.cloverboardlibrary.data.Theme;
import com.basilsystems.app.asdasdasdasdas.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ritikadhyawala on 27/10/15.
 */
public class DemoData {

    public static List<Appliance> bedroom_appliances;
    private static List<Appliance> livingroom_appliances;
    private static List<Appliance> kitchen_appliances;



    public static List<Theme> themes;

    public static List<Device> devices;


    public static final String BEDROOM = "Bedroom";

    public static final String LIVING_ROOM = "Living Room";

    public static final String KITCHEN = "Kitchen";


    static {

        devices = new ArrayList<>();
        devices.add(new Device("Bedroom", "Home", R.drawable.bedroom_header));
        devices.add(new Device("Kitchen", "Home", R.drawable.kitchen_header1));
        devices.add(new Device("Living Room", "Home", R.drawable.living_header));

        bedroom_appliances = new ArrayList<>();
        bedroom_appliances.add(new Appliance(BEDROOM, "Light", 1, true, true, true));
        bedroom_appliances.add(new Appliance(BEDROOM, "Fan", 1, true, true, true));
        bedroom_appliances.add(new Appliance(BEDROOM, "CFL", 0, true, false, true));
        bedroom_appliances.add(new Appliance(BEDROOM, "AC", 0, true, false, true));

        livingroom_appliances = new ArrayList<>();
        livingroom_appliances.add(new Appliance(LIVING_ROOM, "Light", 1, true, true, true));
        livingroom_appliances.add(new Appliance(LIVING_ROOM, "Fan", 1, true, true, true));
        livingroom_appliances.add(new Appliance(LIVING_ROOM, "CFL", 0, true, false, true));
        livingroom_appliances.add(new Appliance(LIVING_ROOM, "AC", 0, true, false, true));

        kitchen_appliances = new ArrayList<>();
        kitchen_appliances.add(new Appliance(KITCHEN, "Light", 1, true, true, true));
        kitchen_appliances.add(new Appliance(KITCHEN, "Fan", 1, true, true, true));
        kitchen_appliances.add(new Appliance(KITCHEN, "CFL", 0, true, false, true));
        kitchen_appliances.add(new Appliance(KITCHEN, "AC", 0, true, false, true));

        themes = new ArrayList<>();
        themes.add(new Theme("Morning", "Home", "theme1", R.drawable.theme_morning));
        themes.add(new Theme("Night", "Home", "theme2", R.drawable.theme_morning));
        themes.add(new Theme("Evening", "Home", "theme3", R.drawable.theme_morning));

    }


    public static List<Appliance> getApplianceList(String room) {
        if (room.equals(BEDROOM)) {
            return bedroom_appliances;
        } else if (room.equals(LIVING_ROOM)) {
            return livingroom_appliances;
        } else if (room.equals(KITCHEN)) {
            return kitchen_appliances;
        }
        return null;
    }

    public static List<Device> getDevices(String place) {
        if (place.equals("Home")) {
            return devices;
        }
        return devices;
    }

    public static List<Theme> getThemes(String place) {
        if (place.equals("Home")) {
            return themes;
        }
        return themes;
    }
}

