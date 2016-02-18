package com.basilsystems.app.cloverboard;

/**
 * Created by LIJO on 1/28/2016.
 */
public class EditThemeModel {

    String radioName;

    public EditThemeModel(String radioName /*,int radioArt*/) {
        this.radioName = radioName;
    }

    public String getRadioName() {

        return radioName;
    }

    public void setRadioName(String radioName) {
        this.radioName = radioName;
    }


}
