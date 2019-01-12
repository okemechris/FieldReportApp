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
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.areatechservices.fieldreportapp.ApiUrls;
import com.areatechservices.fieldreportapp.Constant;
import com.areatechservices.fieldreportapp.Models.Survey;
import com.areatechservices.fieldreportapp.Models.SurveyComent;
import com.areatechservices.fieldreportapp.Models.SurveyImages;
import com.areatechservices.fieldreportapp.RoomDatabase;
import com.areatechservices.fieldreportapp.SharedPrefManager;
import com.areatechservices.fieldreportapp.SurveyDatabase;
import com.areatechservices.fieldreportapp.VolleyMultipartRequest;
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
    Boolean doingImageUpload = false;
    Boolean doingSurveyUpload = false;
    Boolean doingCommentUpload = false;
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
                    if(!doingSurveyUpload){

                        sendSurveyToServer(survey);
                        doingSurveyUpload = true;
                    }


                   }

                for(SurveyImages i :getImagesNotSent()){

                    if(!doingImageUpload){

                        try {
                            doImageUpload(i);
                            doingImageUpload = true;
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                }

                for(SurveyComent s : getCommentsNotSent()){

                    if(!doingCommentUpload){
                            sendCommentToServer(s);
                            doingCommentUpload = true;
                    }
                }

            }

        }).start();


    }

//    public Survey getSurveyWithImagesByStatus(Long id) {
//        Survey survey = db.daoAccess().findSurveyById(id);
//        return survey;
//    }

    public List<SurveyImages> getImagesNotSent(){

        List<SurveyImages> images = db.daoAccess().getSurveyImagesNotUploaded(Constant.SURVEYNOTUPLOADED);

        return images;

    }

    public List<SurveyComent> getCommentsNotSent(){

        List<SurveyComent> comments = db.daoAccess().findCommentByStatus(1);

        return comments;
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
                              */doingSurveyUpload = false;
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
                        doingSurveyUpload = false;
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
               //System.out.println("token is"+ SharedPrefManager.getInstance(context).getUserToken());
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
        Bitmap b = decodeFile(file);
        //System.out.println("jjdjdjdj ======="+ b.getByteCount());
        b.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        doUpload(image,byteArray);

    }

    private Bitmap decodeFile(File f){
        try {
            //Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f),null,o);

            //The new size we want to scale to
            final int REQUIRED_SIZE=150;

            //Find the correct scale value. It should be the power of 2.
            int scale=1;
            while(o.outWidth/scale/2>=REQUIRED_SIZE && o.outHeight/scale/2>=REQUIRED_SIZE)
                scale*=2;

            //Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize=scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {

        }

        return null;
    }


    private void doUpload(final SurveyImages image, final byte[] bitmap) {
        // loading or check internet connection or something...
        // ... then
        Map<String, String> params = new HashMap<String, String>();
        String auth = "Bearer " + SharedPrefManager.getInstance(context).getUserToken();
        params.put("Authorization", auth);
        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(ApiUrls.URL_UPLOAD,params, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                String resultResponse = new String(response.data);
                try {
                    JSONObject result = new JSONObject(resultResponse);
//                    String status = result.getString("status");
//                    String message = result.getString("message");

                    doingImageUpload = false;
                            image.setUploaded(1);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    db.daoAccess().updateImage(image);

                                }
                            }).start();
//
//                    if (status.equals(Constant.REQUEST_SUCCESS)) {
//                        // tell everybody you have succed upload image and post strings
//                        Log.i("Messsage", message);
//                    } else {
//                        Log.i("Unexpected", message);
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                String errorMessage = "Unknown error";
                if (networkResponse == null) {
                    if (error.getClass().equals(TimeoutError.class)) {
                        errorMessage = "Request timeout";
                    } else if (error.getClass().equals(NoConnectionError.class)) {
                        errorMessage = "Failed to connect server";
                    }
                } else {
                    String result = new String(networkResponse.data);
                    try {
                        JSONObject response = new JSONObject(result);
                        String status = response.getString("status");
                        String message = response.getString("message");

                        Log.e("Error Status", status);
                        Log.e("Error Message", message);

                        if (networkResponse.statusCode == 404) {
                            errorMessage = "Resource not found";
                        } else if (networkResponse.statusCode == 401) {
                            errorMessage = message+" Please login again";
                        } else if (networkResponse.statusCode == 400) {
                            errorMessage = message+ " Check your inputs";
                        } else if (networkResponse.statusCode == 500) {
                            errorMessage = message+" Something is getting wrong";
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.i("Error", errorMessage);
                error.printStackTrace();
            }
        }) {



            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("description", image.getDescription());
                params.put("survey_id", image.getSurveyId().toString());
                params.put("title", "");
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                // file name could found file base or direct access from real path
                // for now just get bitmap data from ImageView
                params.put("image", new DataPart(image.getImage(), bitmap, "image/jpeg"));
               // params.put("cover", new DataPart("file_cover.jpg", AppHelper.getFileDataFromDrawable(getBaseContext(), mCoverImage.getDrawable()), "image/jpeg"));

                return params;
            }
        };

        VolleySingleton.getInstance(context).addToRequestQueue(multipartRequest);
    }


    public void sendCommentToServer(final SurveyComent comment) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiUrls.URL_ADD_COMMENTS,
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
                              */doingCommentUpload = false;
                                comment.setStatus(0);
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        db.daoAccess().updateComment(comment);
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
                        doingCommentUpload = false;
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
                //System.out.println("token is"+ SharedPrefManager.getInstance(context).getUserToken());
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //System.out.println("sssssssss ======"+survey.getId().toString());
                Map<String, String> params = new HashMap<>();
                params.put("survey_id", comment.getSurveyId().toString());
                params.put("risk", comment.getRisk());
                params.put("achievement", comment.getAchievement());
                params.put("activity", comment.getActivity());


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


