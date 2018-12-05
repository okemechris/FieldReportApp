package com.areatechservices.fieldreportapp.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.areatechservices.fieldreportapp.Models.Survey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by djbabs on 12/2/18.
 */

@Dao
public interface DaoAccess {

    @Insert
    void insertOnlySingleSurvey(Survey survey);
    @Insert
    void insertMultipleSurvey(List<Survey> surveyList);
    @Query("SELECT * FROM Survey WHERE id = :id")
    Survey findSurveyById(Long id);
    @Update
    void updateSurvey (Survey survey);
    @Delete
    void deleteSurvey (Survey survey);

    @Query("SELECT * FROM Survey WHERE status = :status")
    List<Survey> findSurveyByStatus(int status);

    @Query("SELECT * FROM Survey ")
    List<Survey> getAllSurvey();


}