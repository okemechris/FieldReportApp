package com.areatechservices.fieldreportapp.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Base64;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.areatechservices.fieldreportapp.ApiUrls;
import com.areatechservices.fieldreportapp.Models.Survey;
import com.areatechservices.fieldreportapp.Models.SurveyImages;
import com.areatechservices.fieldreportapp.RoomDatabase;
import com.areatechservices.fieldreportapp.SharedPrefManager;
import com.areatechservices.fieldreportapp.SurveyDatabase;
import com.areatechservices.fieldreportapp.VolleyMultipart;
import com.areatechservices.fieldreportapp.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectivityChangeReciever extends BroadcastReceiver {

    Context context;
    SurveyDatabase db;

    @Override
    public void onReceive(Context context, Intent intent) {

        // Explicitly specify that which service class will handle the intent.
        this.context = context;
        db = new RoomDatabase(context).getSurveyDatabase();


        if (isConnected(context)) {
            sendSurveyToServer();

        }


    }

    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }


    public void sendSurveyToServer() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                List<Survey> surveyList = db.daoAccess().getUpdatedSurvey(1);
                for (Survey survey : surveyList) {

                    Survey s = getSurveyWithImagesByStatus(survey.getId());
                    ;
                    sendSurveyToServer(s);
                    for (SurveyImages i : s.getSurveyImages()) {

                        if(i.getUploaded() == 0){
                            try {
                                doImageUpload(i);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }

                    }


                }

            }

        }).start();


    }

    public Survey getSurveyWithImagesByStatus(Long id) {
        Survey survey = db.daoAccess().findSurveyById(id);
        List<SurveyImages> images = db.daoAccess().getSurveyImages(id);
        survey.setSurveyImages(images);
        return survey;
    }


    public void sendSurveyToServer(final Survey survey) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiUrls.URL_ADD_NEW_SURVEY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("response is " + response);
                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.has("error")) {
                              /*
                              /*if no error do something
                              */
                                survey.setUpdated(0);
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        db.daoAccess().updateSurvey(survey);
                                    }
                                }).start();

                            } else {
                                //Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
//                        error.printStackTrace();
                        //System.out.println("error new on response" + error.getMessage());
                        //Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                params.put("Accept", "application/json");
                String auth = "Bearer " + SharedPrefManager.getInstance(context).getUserToken();
                params.put("Authorization", auth);
                //params.put("Token", SharedPrefManager.getInstance(getApplicationContext()).getUserToken());
//                System.out.println("token is"+ SharedPrefManager.getInstance(getApplicationContext()).getUserToken());
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //System.out.println("sssssssss ======"+survey.getId().toString());
                Map<String, String> params = new HashMap<>();
                params.put("location", survey.getGeo());
                params.put("user_id", "");
                params.put("survey_id", survey.getId().toString());
                params.put("sd", survey.getStartDate());
                params.put("sc", survey.getSurveyCompleted());
                params.put("epsc", survey.getEquipPickupSuplierCivilWorks());
                params.put("pdc", survey.getPersonelDptCivilWorks());
                params.put("pdis", survey.getPersonnelDepSolar());
                params.put("pac", survey.getPersonelArvCivilWorks());
                params.put("startec", survey.getStartDateCivilWorks());
                params.put("eosc", survey.getEquipOnSiteCivilWorks());
                params.put("aec", survey.getAllExcavationCompleted());
                params.put("fcwc", survey.getFencingCivilCompleted());
                params.put("pcwc", survey.getPylonCivilCompleted());
                params.put("epsf", survey.getEquipPickupSuplierFencingPylon());
                params.put("pdf", survey.getPersonelDptFencing());
                params.put("paf", survey.getPersonelArvFencing());
                params.put("startf", survey.getStartDateFencing());
                params.put("eoss", survey.getEquipOnSiteSolar());
                params.put("ipc", survey.getInstallPylonComplete());
                params.put("eosf", survey.getEquipOnSiteFencing());
                params.put("ifc", survey.getInstallFencingComplete());
                params.put("cwsbc", survey.getCivilSolarComplete());
                params.put("epws", survey.getEquipPickupWarehouseSolar());
                params.put("pais", survey.getPersonnelArvSolar());
                params.put("starti", survey.getStartDateSolar());
                params.put("isc", survey.getInstallSolarCompleted());
                params.put("ibc", survey.getInstallBtsComplete());
                params.put("ivc", survey.getInstallVsatComplete());
                params.put("iwc", survey.getInstallWifiComplete());
                params.put("pcom", survey.getPersonnelDepCommisioning());
                params.put("pacom", survey.getPersonnelArvCommisioning());
                params.put("startcom", survey.getStartDateCommisioning());
                params.put("coms", survey.getCommisioningSolar());
                params.put("starta", survey.getStartDateAcceptance());
                params.put("af", survey.getAcceptanceFencing());
                params.put("ap", survey.getAcceptancePylon());
                params.put("as", survey.getAcceptanceSolar());
                params.put("av", survey.getAcceptanceVsat());
                params.put("a3g", survey.getAcceptance3G());
                params.put("awifi", survey.getAcceptanceWifi());
                params.put("cwvc", survey.getCivilVsatComplete());
                params.put("cv", survey.getCommisioningVsat());
                params.put("cb", survey.getCommisioningBts());
                params.put("cw", survey.getCommisioningWifi());
                params.put("pda", survey.getPersonnelDepAcceptance());
                params.put("paa", survey.getPersonnelArvAcceptance());


                return params;
            }


        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }


    void doImageUpload(SurveyImages image) throws FileNotFoundException {

        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();

        File myDir = new File(root + "/fieldreport");
        File file = new File(myDir, image.getImage());


        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap b = BitmapFactory.decodeStream(new FileInputStream(file));
        //System.out.println("jjdjdjdj ======="+ b.getByteCount());
        b.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        doUpload(image,byteArray);

    }


public void doUpload(final SurveyImages image, final byte[] bitmap) {
    final String imageString = Base64.encodeToString(bitmap, Base64.DEFAULT);

    StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiUrls.URL_UPLOAD,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //System.out.println("response is " + response);
                    try {
                        //converting response to json object
                        JSONObject obj = new JSONObject(response);

                        //if no error in response

                        image.setUploaded(1);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                db.daoAccess().updateImage(image);

                            }
                        }).start();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
//                        error.printStackTrace();
                    //Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {

        //This is for Headers If You Needed
        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> params = new HashMap<String, String>();
            params.put("Content-Type", "multipart/form-data");
            params.put("Accept", "application/json");
            String auth = "Bearer " + SharedPrefManager.getInstance(context).getUserToken();
            params.put("Authorization", auth);
            //params.put("Token", SharedPrefManager.getInstance(getApplicationContext()).getUserToken());
//                System.out.println("token is"+ SharedPrefManager.getInstance(getApplicationContext()).getUserToken());
            return params;
        }

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            //System.out.println("sssssssss ======"+survey.getId().toString());
            Map<String, String> params = new HashMap<>();
            params.put("image", imageString);
            params.put("description", image.getDescription());
            params.put("survey_id", image.getSurveyId().toString());
            params.put("title", "");
            return params;
        }


    };
    stringRequest.setRetryPolicy(new DefaultRetryPolicy(
            10000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

}


}


