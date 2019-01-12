package com.areatechservices.fieldreportapp.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.areatechservices.fieldreportapp.Models.Survey;
import com.areatechservices.fieldreportapp.Models.SurveyComent;
import com.areatechservices.fieldreportapp.Models.SurveyImages;
import com.areatechservices.fieldreportapp.Models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by djbabs on 12/2/18.
 */

@Dao
public interface DaoAccess {

//    @Delete
//    void deleteSurvey (Survey survey);


    //statement comment

    @Insert
    long insertSurveyComment(SurveyComent comment);

    @Update
    void updateComment (SurveyComent comment);

    @Query("SELECT * FROM SurveyComent WHERE status = :status")
    List<SurveyComent>findCommentByStatus(int status);

    @Query("SELECT * FROM SurveyComent ")
    List<SurveyComent> getAllComments();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCommentList(List<SurveyComent> comments);

    @Query("SELECT * FROM SurveyComent WHERE surveyId =:surveyId")
    List<SurveyComent> getSurveyComments(Long surveyId);



    //select statement survey
    @Insert
    long insertOnlySingleSurvey(Survey survey);

    @Insert
    void insertMultipleSurvey(List<Survey> surveyList);

    @Update
    void updateSurvey (Survey survey);

    @Query("SELECT * FROM Survey WHERE id = :id")
    Survey findSurveyById(Long id);

    @Query("SELECT * FROM Survey WHERE status = :status")
    List<Survey> findSurveyByStatus(int status);

    @Query("SELECT * FROM Survey ")
    List<Survey> getAllSurvey();


    @Query("SELECT * FROM Survey WHERE updated = :updated")
    List<Survey> getUpdatedSurvey(int updated);



    // statement SurveyImages
    @Insert
    long insertSurveyImage(SurveyImages surveyImages);

    @Update
    void updateImage (SurveyImages survey);

    @Insert
    void insertMultipleSurveyImage(List<SurveyImages> surveyImages);

    @Query("SELECT * FROM SurveyImages WHERE uploaded = :uploaded")
    List<SurveyImages> getSurveyImagesNotUploaded(int uploaded);

    @Query("SELECT * FROM SurveyImages ")
    List<SurveyImages> getAllimages();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertImageList(List<SurveyImages> images);

    @Query("SELECT * FROM SurveyImages WHERE surveyId =:surveyId")
    List<SurveyImages> getSurveyImages(Long surveyId);


}
