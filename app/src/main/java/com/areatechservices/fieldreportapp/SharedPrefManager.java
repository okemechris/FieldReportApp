package com.areatechservices.fieldreportapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.areatechservices.fieldreportapp.Domain.UserDomain;
import com.areatechservices.fieldreportapp.Models.User;

/**
 * Created by chris on 9/5/2017.
 */

//here for this class we are using a singleton pattern

public class SharedPrefManager {

    //the constants
    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_NAME = "keyname";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_ID = "keyid";
    private static final String USER_TOKEN = "userToken";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(String token) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(USER_TOKEN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //editor.putString(KEY_NAME, user.getName());
        editor.putString(USER_TOKEN, token);
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(USER_TOKEN, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_TOKEN, null) != null;
    }

    //this method will give the logged in user
//    public User getUser(Context context) {
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return new RoomDatabase(context).getSurveyDatabase().daoAccess().getUserByEmail(sharedPreferences.getString(KEY_EMAIL, ""));
//
//    }

    public String getUserToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(USER_TOKEN, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_TOKEN, "");
    }



    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, IndexActivity.class));
    }
}