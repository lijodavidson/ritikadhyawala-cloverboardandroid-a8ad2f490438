package com.basilsystems.app.cloverboard;

/**
 * Created by LIJO on 1/21/2016.
 */
public class Radio {

    String radioName;

    int radioArt;


    /*String radioDial;*/


    /**
     *
     * @param radioName
     * @param radioArt
     */
    public Radio(String radioName ,int radioArt) {
        this.radioName = radioName;
        this.radioArt = radioArt;

       /* this.radioDial = radioDial;*/
    }

    public String getRadioName() {

        return radioName;
    }


    public void setRadioName(String radioName) {
        this.radioName = radioName;
    }

    public int getRadioArt() {
        return radioArt;
    }


    public void setRadioArt(int radioArt) {
        this.radioArt = radioArt;
    }

    /*public String getRadioDial() {
        return radioDial;
    }

    public void setRadioDial(String radioDial) {
        this.radioDial = radioDial;
    }*/

}

