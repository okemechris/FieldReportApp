package com.areatechservices.fieldreportapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.areatechservices.fieldreportapp.Domain.ImageDomain;
import com.areatechservices.fieldreportapp.Models.Survey;
import com.areatechservices.fieldreportapp.Models.SurveyComent;
import com.areatechservices.fieldreportapp.Models.SurveyImages;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by djbabs on 12/15/18.
 */

public class Util {

    Activity activity;

    public Util(Activity activity){

        this.activity = activity;
    }

    public void saveImagesToFolder(ArrayList<SurveyImages> list) throws IOException {
        for(SurveyImages image : list) {
            Bitmap finalBitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), Uri.parse(image.getUri()));
            String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();


            File myDir = new File(root + "/fieldreport");
            if (!myDir.exists()) {
                myDir.mkdirs();
            }

            File file = new File(myDir, image.getImage());
            if (file.exists())
                file.delete();
            try {
                FileOutputStream out = new FileOutputStream(file);

                finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);

                out.flush();

                out.close();

            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
    }



    public Survey getSurveyWithImagesAndComments(Long id) {
        Survey survey = ((MainActivity)activity).getSurveyDatabase().daoAccess ().findSurveyById(id);
        List<SurveyImages> images = ((MainActivity)activity).getSurveyDatabase().daoAccess ().getSurveyImages(survey.getId());
        List<SurveyComent> comments = ((MainActivity)activity).getSurveyDatabase().daoAccess ().getSurveyComments(survey.getId());

        if(survey != null){
            survey.setSurveyImages(images);
            survey.setSurveyComents(comments);
        }

        return survey;
    }
}
