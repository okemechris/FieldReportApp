package com.areatechservices.fieldreportapp.Services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.areatechservices.fieldreportapp.ApiUrls;
import com.areatechservices.fieldreportapp.Constant;
import com.areatechservices.fieldreportapp.Models.Survey;
import com.areatechservices.fieldreportapp.Models.User;
import com.areatechservices.fieldreportapp.RoomDatabase;
import com.areatechservices.fieldreportapp.SharedPrefManager;
import com.areatechservices.fieldreportapp.SurveyDatabase;
import com.areatechservices.fieldreportapp.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class HandleSurveyOnNetworkChangeService extends IntentService {

    public HandleSurveyOnNetworkChangeService() {
        super("HandleSurveyOnNetworkChangeService");
    }

    public  User loggedinUser;
    SurveyDatabase db;
    @Override
    protected void onHandleIntent(Intent intent) {

        Bundle extras = intent.getExtras();
        boolean isNetworkConnected = extras.getBoolean("isNetworkConnected");
        db = new RoomDatabase(getApplicationContext()).getSurveyDatabase();
        final User u = SharedPrefManager.getInstance(getApplicationContext()).getUser(getApplicationContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                loggedinUser = db.daoAccess().getUserByEmail(u.getEmail());
            }}).start();

        sendUpdatedSurveyToServer(isNetworkConnected,getApplicationContext());
        startUserToServer(isNetworkConnected,getApplicationContext());
        sendNewSurveyToServer(isNetworkConnected,getApplicationContext());
        // your code
    }


    public void sendUpdatedSurveyToServer(boolean connected, final Context context){

        if(connected){

            new Thread(new Runnable() {
                @Override
                public void run() {

                    List<Survey> surveyList = db.daoAccess ().getUpdatedSurvey (2);
                    for(Survey survey : surveyList){

                        sendSurveyToServer(survey);
                        survey.setUpdated(0);
                        db.daoAccess ().updateSurvey(survey);
                    }

                }

            }).start();





        }


    }

    public void sendNewSurveyToServer(boolean connected, final Context context){

        if(connected){

            new Thread(new Runnable() {
                @Override
                public void run() {

                    List<Survey> surveyList = db.daoAccess ().getUpdatedSurvey (1);
                    for(Survey survey : surveyList){

                        sendNewSurveyToServerd(survey);
                        survey.setUpdated(0);
                        db.daoAccess ().updateSurvey(survey);
                    }

                }

            }).start();





        }


    }


    public void startUserToServer(boolean connected, final Context context){

        if(connected){

            new Thread(new Runnable() {
                @Override
                public void run() {

                    List<User> userList = db.daoAccess ().getUnregisteredUser (0);
                    for(User user : userList){

                        sendUserToServer(user);

                        user.setStatus(1);
                       db.daoAccess ().updateUser(user);
                    }

                }

            }).start();





        }


    }


    public void sendNewSurveyToServerd(final Survey survey){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiUrls.URL_ADD_NEW_SURVEY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("error on response");
                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                              /*
                              /*if no error do something
                              */


                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("error on response");
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //params
                Map<String, String> params = new HashMap<>();
                params.put("report_id", survey.getId().toString());
                params.put("geo", survey.getGeo());
                params.put("sc", survey.getSurveyCompleted());
                params.put("sd", survey.getStartDate());
                params.put("pac", survey.getPersonelArvCivilWorks());
                params.put("pdc", survey.getPersonelDptCivilWorks());
                params.put("epsc", survey.getEquipPickupSuplierCivilWorks());
                params.put("startec", survey.getStartDateCivilWorks());
                params.put("eosc", survey.getEquipOnSiteCivilWorks());
                params.put("fcwc", survey.getFencingCivilCompleted());
                params.put("pcwc", survey.getPylonCivilCompleted());
                params.put("paf", survey.getPersonelArvFencing());
                params.put("pdf", survey.getPersonelDptFencing());
                params.put("epsf", survey.getEquipPickupSuplierFencingPylon());
                params.put("startf", survey.getStartDateFencing());
                params.put("eosf", survey.getEquipOnSiteFencing());
                params.put("ipc", survey.getInstallPylonComplete());
                params.put("ifc", survey.getInstallFencingComplete());
                params.put("cwvc", survey.getCivilVsatComplete());
                params.put("cwsbc", survey.getCivilSolarComplete());
                params.put("pdi", survey.getPersonnelDepSolar());
                params.put("epws", survey.getEquipPickupWarehouseSolar());
                params.put("pais", survey.getPersonnelArvSolar());
                params.put("starti", survey.getStartDateSolar());
                params.put("eoss", survey.getEquipOnSiteSolar());
                params.put("isc", survey.getInstallSolarCompleted());
                params.put("ivc", survey.getInstallVsatComplete());
                params.put("ibc", survey.getInstallBtsComplete());
                params.put("iwc", survey.getInstallWifiComplete());
                params.put("pdcom", survey.getPersonnelDepCommisioning());
                params.put("pacom", survey.getPersonnelArvCommisioning());
                params.put("startcom", survey.getStartDateCommisioning());
                params.put("coms", survey.getCommisioningSolar());
                params.put("comv", survey.getCommisioningVsat());
                params.put("comb", survey.getCommisioningBts());
                params.put("comw", survey.getCommisioningWifi());
                params.put("pda", survey.getPersonnelDepAcceptance());
                params.put("paa", survey.getPersonnelArvAcceptance());
                params.put("starta", survey.getStartDateAcceptance());
                params.put("af", survey.getAcceptanceFencing());
                params.put("ap", survey.getAcceptancePylon());
                params.put("as", survey.getAcceptanceSolar());
                params.put("av", survey.getAcceptanceVsat());
                params.put("a3g", survey.getAcceptance3G());
                params.put("awifi", survey.getAcceptanceWifi());


                return params;
            }


        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    public void sendSurveyToServer(final Survey survey){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiUrls.URL_UPDATE_SURVEY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("error on response");
                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                              /*
                              /*if no error do something
                              */


                              } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("error on response");
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //params
                Map<String, String> params = new HashMap<>();
                params.put("report_id", survey.getId().toString());
                params.put("geo", survey.getGeo());
                params.put("sc", survey.getSurveyCompleted());
                params.put("sd", survey.getStartDate());
                params.put("pac", survey.getPersonelArvCivilWorks());
                params.put("pdc", survey.getPersonelDptCivilWorks());
                params.put("epsc", survey.getEquipPickupSuplierCivilWorks());
                params.put("startec", survey.getStartDateCivilWorks());
                params.put("eosc", survey.getEquipOnSiteCivilWorks());
                params.put("fcwc", survey.getFencingCivilCompleted());
                params.put("pcwc", survey.getPylonCivilCompleted());
                params.put("paf", survey.getPersonelArvFencing());
                params.put("pdf", survey.getPersonelDptFencing());
                params.put("epsf", survey.getEquipPickupSuplierFencingPylon());
                params.put("startf", survey.getStartDateFencing());
                params.put("eosf", survey.getEquipOnSiteFencing());
                params.put("ipc", survey.getInstallPylonComplete());
                params.put("ifc", survey.getInstallFencingComplete());
                params.put("cwvc", survey.getCivilVsatComplete());
                params.put("cwsbc", survey.getCivilSolarComplete());
                params.put("pdi", survey.getPersonnelDepSolar());
                params.put("epws", survey.getEquipPickupWarehouseSolar());
                params.put("pais", survey.getPersonnelArvSolar());
                params.put("starti", survey.getStartDateSolar());
                params.put("eoss", survey.getEquipOnSiteSolar());
                params.put("isc", survey.getInstallSolarCompleted());
                params.put("ivc", survey.getInstallVsatComplete());
                params.put("ibc", survey.getInstallBtsComplete());
                params.put("iwc", survey.getInstallWifiComplete());
                params.put("pdcom", survey.getPersonnelDepCommisioning());
                params.put("pacom", survey.getPersonnelArvCommisioning());
                params.put("startcom", survey.getStartDateCommisioning());
                params.put("coms", survey.getCommisioningSolar());
                params.put("comv", survey.getCommisioningVsat());
                params.put("comb", survey.getCommisioningBts());
                params.put("comw", survey.getCommisioningWifi());
                params.put("pda", survey.getPersonnelDepAcceptance());
                params.put("paa", survey.getPersonnelArvAcceptance());
                params.put("starta", survey.getStartDateAcceptance());
                params.put("af", survey.getAcceptanceFencing());
                params.put("ap", survey.getAcceptancePylon());
                params.put("as", survey.getAcceptanceSolar());
                params.put("av", survey.getAcceptanceVsat());
                params.put("a3g", survey.getAcceptance3G());
                params.put("awifi", survey.getAcceptanceWifi());
                params.put("location", survey.getGeo());
                params.put("user_id", loggedinUser.getId().toString());


                return params;
            }


        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }



    public void sendUserToServer(final User user){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiUrls.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("error on response");
                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                              /*
                              /*if no error do something
                              */


                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("error on response");
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //params
                Map<String, String> params = new HashMap<>();
                params.put("email", user.getEmail());
                params.put("password", user.getPassword());
                params.put("name", user.getName());
                params.put("c_password", user.getPassword());
                return params;
            }


        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    }



