package com.areatechservices.fieldreportapp;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by djbabs on 12/5/18.
 */

public class RoomDatabase {



    private SurveyDatabase surveyDatabase;
    private static  final String DATABASE_NAME = "survey_db";

    public RoomDatabase(Context context){

        surveyDatabase = Room.databaseBuilder(context,
                SurveyDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration()
                .build();
    }


    public SurveyDatabase getSurveyDatabase() {
        return surveyDatabase;
    }

    public void setSurveyDatabase(SurveyDatabase surveyDatabase) {
        this.surveyDatabase = surveyDatabase;
    }
}
