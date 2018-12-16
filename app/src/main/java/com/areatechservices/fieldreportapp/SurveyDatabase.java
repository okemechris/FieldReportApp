package com.areatechservices.fieldreportapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.areatechservices.fieldreportapp.Dao.DaoAccess;
import com.areatechservices.fieldreportapp.Models.Survey;
import com.areatechservices.fieldreportapp.Models.SurveyImages;
import com.areatechservices.fieldreportapp.Models.User;

/**
 * Created by djbabs on 12/2/18.
 */
@Database(entities = {Survey.class,User.class,SurveyImages.class},version = 8,exportSchema = false)
public abstract class SurveyDatabase extends RoomDatabase{

 public abstract DaoAccess daoAccess();

}
