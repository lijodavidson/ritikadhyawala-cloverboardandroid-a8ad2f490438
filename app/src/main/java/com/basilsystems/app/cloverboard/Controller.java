package com.basilsystems.app.cloverboard;

import android.content.Context;

/**
 * Created by LIJO on 12/13/2015.
 */
public class Controller {


    final static private String SHARED_PREF_NAME="app_data";
    final static private String SHARED_PREF_KEY_ISLOGED="isFirst";

    public static boolean isFirstTime(Context context){
        return context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .getBoolean(SHARED_PREF_KEY_ISLOGED, true);
    }

    public static void changeStatus(Context context){
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE).
                edit().
                putBoolean(SHARED_PREF_KEY_ISLOGED,false).
                commit();
    }

    public static void clearCache(Context context){
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE).
                edit().clear().	commit();
    }




}
