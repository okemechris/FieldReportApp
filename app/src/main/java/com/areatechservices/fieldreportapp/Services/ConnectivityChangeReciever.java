package com.areatechservices.fieldreportapp.Services;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityChangeReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // Explicitly specify that which service class will handle the intent.
        ComponentName comp = new ComponentName(context.getPackageName(),
                HandleSurveyOnNetworkChangeService.class.getName());
        intent.putExtra("isNetworkConnected",isConnected(context));
        context.startService((intent.setComponent(comp)));



    }

    public  boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        System.out.println("ddsdss fffff dddd sss");
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }


}
