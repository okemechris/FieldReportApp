package com.areatechservices.fieldreportapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.areatechservices.fieldreportapp.Dao.DaoAccess;
import com.areatechservices.fieldreportapp.Models.Survey;

/**
 * Created by djbabs on 12/2/18.
 */
@Database(entities = {Survey.class},version = 1,exportSchema = false)
public abstract class SurveyDatabase extends RoomDatabase{

 public abstract DaoAccess daoAccess();

}
