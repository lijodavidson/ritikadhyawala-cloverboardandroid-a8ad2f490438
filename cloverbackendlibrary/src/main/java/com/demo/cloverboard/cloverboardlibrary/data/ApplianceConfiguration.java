package com.demo.cloverboard.cloverboardlibrary.data;

/**
 * Created by ritikadhyawala on 10/08/15.
 */
public class ApplianceConfiguration {

    private String name;

    public appliance_type getApplianceType() {
        return applianceType;
    }

    public String getName() {
        return name;
    }

    private appliance_type applianceType;
    public static enum appliance_type{
        DIMMABLE_LIGHT,
        NON_DIMMABLE_LIGHT,
        OTHERS
    } ;

    public ApplianceConfiguration(String name, appliance_type applianceType){
        this.name = name;
        this.applianceType = applianceType;
    }
}
