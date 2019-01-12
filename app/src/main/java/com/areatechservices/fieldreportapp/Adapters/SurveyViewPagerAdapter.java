package com.areatechservices.fieldreportapp.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;

import com.areatechservices.fieldreportapp.Constant;
import com.areatechservices.fieldreportapp.Domain.ImageDomain;
import com.areatechservices.fieldreportapp.Fragments.DatePickerDialogFragment;
import com.areatechservices.fieldreportapp.Fragments.HomeLandingFragment;
import com.areatechservices.fieldreportapp.MainActivity;
import com.areatechservices.fieldreportapp.Models.Survey;
import com.areatechservices.fieldreportapp.Models.SurveyComent;
import com.areatechservices.fieldreportapp.Models.SurveyImages;
import com.areatechservices.fieldreportapp.R;
import com.areatechservices.fieldreportapp.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by djbabs on 12/14/18.
 */


public class SurveyViewPagerAdapter extends PagerAdapter implements View.OnClickListener {

    private Context mContext;
    private FragmentActivity activity;
    private Survey uSurvey;

    private EditText geo;
    private EditText startDate,surveyCompleted,equipPickupSuplierCivilWorks,personelDptCivilWorks,
            personelArvCivilWorks,startDateCivilWorks,equipOnSiteCivilWorks,allExcavationCompleted,
            fencingCivilCompleted,pylonCivilCompleted,equipPickupSuplierFencingPylon,personelDptFencing,
            personelArvFencing,startDateFencing,equipOnSiteFencing,installPylonComplete,installFencingComplete,
            civilVsatComplete,civilSolarComplete,equipPickupWarehouseSolar,personnelDepSolar,personnelArvSolar
            ,startDateSolar,equipOnSiteSolar,installSolarCompleted,installVsatComplete,installBtsComplete,installWifiComplete,
            personnelDepCommisioning,personnelArvCommisioning,startDateCommisioning,commisioningSolar,CommisioningVsat,commisioningBts
            ,commisioningWifi,personnelDepAcceptance,personnelArvAcceptance,startDateAcceptance,acceptanceFencing,acceptancePylon,acceptanceSolar,
            acceptanceVsat,acceptance3G,acceptanceWifi;

    DatePickerDialogFragment datePicker;
    private Util util;

    public SurveyViewPagerAdapter(Context context, final FragmentActivity activity, final Long survey, final Util util) {


        if(survey != null){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(SurveyComent i : getallImages()){
                        System.out.println("hdhhd==============="+"dddddd "+i.getSurveyId());

                    }
                    uSurvey = util.getSurveyWithImagesAndComments(survey);
                    System.out.println(uSurvey.getSurveyComents().size());

                }
            }).start();
        }

        mContext = context;
        this.activity = activity;
        this.util = util;
        datePicker = new DatePickerDialogFragment();

    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
//        ModelObject modelObject = ModelObject.values()[position];
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = null;


        switch (position){

            case 0:
                layout = (ViewGroup) inflater.inflate(R.layout.survey_phase_1, collection, false);
                geo = layout.findViewById(R.id.geo);
                startDate = layout.findViewById(R.id.startDate);
                surveyCompleted  = layout.findViewById(R.id.surveyCompleted);

                break;

            case 1:

                layout = (ViewGroup) inflater.inflate(R.layout.survey_phase_2, collection, false);

                equipPickupSuplierCivilWorks = layout.findViewById(R.id.equipPickupSuplierCivilWorks);
                personelDptCivilWorks = layout.findViewById(R.id.personelDptCivilWorks);
                personelArvCivilWorks = layout.findViewById(R.id.personelArvCivilWorks);
                startDateCivilWorks = layout.findViewById(R.id.startDateCivilWorks);
                break;

            case 2:

                layout = (ViewGroup) inflater.inflate(R.layout.survey_phase_3, collection, false);
                equipOnSiteCivilWorks = layout.findViewById(R.id.equipOnSiteCivilWorks);
                allExcavationCompleted = layout.findViewById(R.id.allExcavationCompleted);
                fencingCivilCompleted = layout.findViewById(R.id.fencingCivilCompleted);
                pylonCivilCompleted = layout.findViewById(R.id.pylonCivilCompleted);

                break;

            case 3:
                layout = (ViewGroup) inflater.inflate(R.layout.survey_phase_4, collection, false);
                equipPickupSuplierFencingPylon = layout.findViewById(R.id.equipPickupSuplierFencingPylon);
                personelDptFencing = layout.findViewById(R.id.personelDptFencing);
                personelArvFencing = layout.findViewById(R.id.personelArvFencing);
                startDateFencing = layout.findViewById(R.id.startDateFencing);

                break;

            case 4:

                layout = (ViewGroup) inflater.inflate(R.layout.survey_phase_5, collection, false);
                equipOnSiteFencing = layout.findViewById(R.id.equipOnSiteFencing);
                installPylonComplete = layout.findViewById(R.id.installPylonComplete);
                installFencingComplete = layout.findViewById(R.id.installFencingComplete);
                civilVsatComplete = layout.findViewById(R.id.civilVsatComplete);
                civilSolarComplete = layout.findViewById(R.id.civilSolarComplete);
                break;

            case 5:

                layout = (ViewGroup) inflater.inflate(R.layout.survey_phase_6, collection, false);

                equipPickupWarehouseSolar = layout.findViewById(R.id.equipPickupWarehouseSolar);
                personnelDepSolar = layout.findViewById(R.id.personnelDepSolar);
                personnelArvSolar = layout.findViewById(R.id.personnelArvSolar);
                startDateSolar = layout.findViewById(R.id.startDateSolar);

                break;
            case 6:
                layout = (ViewGroup) inflater.inflate(R.layout.survey_phase_7, collection, false);
                equipOnSiteSolar = layout.findViewById(R.id.equipOnSiteSolar);
                installSolarCompleted = layout.findViewById(R.id.installSolarCompleted);
                installVsatComplete = layout.findViewById(R.id.installVsatComplete);
                installBtsComplete = layout.findViewById(R.id.installBtsComplete);
                installWifiComplete = layout.findViewById(R.id.installWifiComplete);
                break;

            case 7:

                layout = (ViewGroup) inflater.inflate(R.layout.survey_phase_8, collection, false);
                personnelDepCommisioning = layout.findViewById(R.id.personnelDepCommisioning);
                personnelArvCommisioning = layout.findViewById(R.id.personnelArvCommisioning);
                startDateCommisioning = layout.findViewById(R.id.startDateCommisioning);
                commisioningSolar = layout.findViewById(R.id.commisioningSolar);
                CommisioningVsat = layout.findViewById(R.id.CommisioningVsat);
                commisioningBts = layout.findViewById(R.id.commisioningBts);
                commisioningWifi = layout.findViewById(R.id.commisioningWifi);
                break;

            case 8:

                layout = (ViewGroup) inflater.inflate(R.layout.survey_phase_9, collection, false);
                personnelDepAcceptance  = layout.findViewById(R.id.personnelDepAcceptance);
                personnelArvAcceptance = layout.findViewById(R.id.personnelArvAcceptance);
                startDateAcceptance = layout.findViewById(R.id.startDateAcceptance);
                acceptanceFencing = layout.findViewById(R.id.acceptanceFencing);
                acceptancePylon = layout.findViewById(R.id.acceptancePylon);
                acceptanceSolar = layout.findViewById(R.id.acceptanceSolar);
                acceptanceVsat = layout.findViewById(R.id.acceptanceVsat);
                acceptance3G = layout.findViewById(R.id.acceptance3G);
                acceptanceWifi = layout.findViewById(R.id.acceptanceWifi);




        }

        setListeners();

        if(uSurvey != null){
            preloadForms();
        }

        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {

        return 9;
    }

    void setListeners(){

        if(startDate !=null)
            startDate.setOnClickListener(this);
        if(surveyCompleted !=null)
            surveyCompleted.setOnClickListener(this);
        if(equipPickupSuplierCivilWorks !=null)

            equipPickupSuplierCivilWorks.setOnClickListener(this);
        if(personelDptCivilWorks !=null)

            personelDptCivilWorks.setOnClickListener(this);
        if(personelArvCivilWorks !=null)

            personelArvCivilWorks.setOnClickListener(this);
        if(startDateCivilWorks !=null)

            startDateCivilWorks.setOnClickListener(this);
        if(equipOnSiteCivilWorks !=null)

            equipOnSiteCivilWorks.setOnClickListener(this);
        if(allExcavationCompleted !=null)

            allExcavationCompleted.setOnClickListener(this);
        if(fencingCivilCompleted !=null)

            fencingCivilCompleted.setOnClickListener(this);
        if(pylonCivilCompleted !=null)

            pylonCivilCompleted.setOnClickListener(this);
        if(equipPickupSuplierFencingPylon !=null)

            equipPickupSuplierFencingPylon.setOnClickListener(this);
        if(personelDptFencing !=null)

            personelDptFencing.setOnClickListener(this);
        if(personelArvFencing !=null)

            personelArvFencing.setOnClickListener(this);
        if(startDateFencing !=null)

            startDateFencing.setOnClickListener(this);
        if(equipOnSiteFencing !=null)

            equipOnSiteFencing.setOnClickListener(this);
        if(installPylonComplete !=null)

            installPylonComplete.setOnClickListener(this);
        if(installFencingComplete !=null)

            installFencingComplete.setOnClickListener(this);
        if(civilVsatComplete !=null)

            civilVsatComplete.setOnClickListener(this);
        if(civilSolarComplete !=null)

            civilSolarComplete.setOnClickListener(this);
        if(equipPickupWarehouseSolar !=null)

            equipPickupWarehouseSolar.setOnClickListener(this);
        if(personnelDepSolar !=null)

            personnelDepSolar.setOnClickListener(this);
        if(personnelArvSolar !=null)

            personnelArvSolar.setOnClickListener(this);
        if(startDateSolar !=null)

            startDateSolar.setOnClickListener(this);
        if(equipOnSiteSolar !=null)

            equipOnSiteSolar.setOnClickListener(this);
        if(installSolarCompleted !=null)

            installSolarCompleted.setOnClickListener(this);
        if(installVsatComplete !=null)

            installVsatComplete.setOnClickListener(this);
        if(installBtsComplete !=null)

            installBtsComplete.setOnClickListener(this);
        if(installWifiComplete !=null)

            installWifiComplete.setOnClickListener(this);
        if(personnelDepCommisioning !=null)

            personnelDepCommisioning.setOnClickListener(this);
        if(personnelArvCommisioning !=null)

            personnelArvCommisioning.setOnClickListener(this);
        if(startDateCommisioning !=null)

            startDateCommisioning.setOnClickListener(this);
        if(commisioningSolar !=null)

            commisioningSolar.setOnClickListener(this);
        if(CommisioningVsat !=null)

            CommisioningVsat.setOnClickListener(this);
        if(commisioningBts !=null)

            commisioningBts.setOnClickListener(this);
        if(commisioningWifi !=null)

            commisioningWifi.setOnClickListener(this);
        if(personnelDepAcceptance !=null)

            personnelDepAcceptance.setOnClickListener(this);
        if(personnelArvAcceptance !=null)

            personnelArvAcceptance.setOnClickListener(this);
        if(startDateAcceptance !=null)

            startDateAcceptance.setOnClickListener(this);
        if(acceptanceFencing !=null)

            acceptanceFencing.setOnClickListener(this);
        if(acceptanceSolar !=null)

            acceptanceSolar.setOnClickListener(this);
        if(acceptanceVsat !=null)

            acceptanceVsat.setOnClickListener(this);
        if(acceptancePylon !=null)

            acceptancePylon.setOnClickListener(this);
        if(acceptance3G !=null)

            acceptance3G.setOnClickListener(this);
        if(acceptanceWifi !=null)

            acceptanceWifi.setOnClickListener(this);

    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()) {

            case R.id.startDate:
                datePicker.setEditText(startDate);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.surveyCompleted:
                datePicker.setEditText(surveyCompleted);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.equipPickupSuplierCivilWorks:
                datePicker.setEditText(equipPickupSuplierCivilWorks);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.personelDptCivilWorks:
                datePicker.setEditText(personelDptCivilWorks);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.personelArvCivilWorks:
                datePicker.setEditText(personelArvCivilWorks);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.startDateCivilWorks:
                datePicker.setEditText(startDateCivilWorks);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.equipOnSiteCivilWorks:
                datePicker.setEditText(equipOnSiteCivilWorks);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.allExcavationCompleted:
                datePicker.setEditText(allExcavationCompleted);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.fencingCivilCompleted:
                datePicker.setEditText(fencingCivilCompleted);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.pylonCivilCompleted:
                datePicker.setEditText(pylonCivilCompleted);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.equipPickupSuplierFencingPylon:
                datePicker.setEditText(equipPickupSuplierFencingPylon);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.personelDptFencing:
                datePicker.setEditText(personelDptFencing);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.personelArvFencing:
                datePicker.setEditText(personelArvFencing);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.startDateFencing:
                datePicker.setEditText(startDateFencing);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.equipOnSiteFencing:
                datePicker.setEditText(equipOnSiteFencing);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;

            case R.id.installPylonComplete:
                datePicker.setEditText(installPylonComplete);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;

            case R.id.installFencingComplete:
                datePicker.setEditText(installFencingComplete);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;

            case R.id.civilVsatComplete:
                datePicker.setEditText(civilVsatComplete);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;

            case R.id.civilSolarComplete:
                datePicker.setEditText(civilSolarComplete);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;

            case R.id.equipPickupWarehouseSolar:
                datePicker.setEditText(equipPickupWarehouseSolar);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;

            case R.id.personnelDepSolar:
                datePicker.setEditText(personnelDepSolar);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.personnelArvSolar:
                datePicker.setEditText(personnelArvSolar);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;

            case R.id.startDateSolar:
                datePicker.setEditText(startDateSolar);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;

            case R.id.equipOnSiteSolar:
                datePicker.setEditText(equipOnSiteSolar);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.installSolarCompleted:
                datePicker.setEditText(installSolarCompleted);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;

            case R.id.installVsatComplete:
                datePicker.setEditText(installVsatComplete);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;

            case R.id.installBtsComplete:
                datePicker.setEditText(installBtsComplete);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.installWifiComplete:
                datePicker.setEditText(installWifiComplete);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;

            case R.id.personnelDepCommisioning:
                datePicker.setEditText(personnelDepCommisioning);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;

            case R.id.personnelArvCommisioning:
                datePicker.setEditText(startDate);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.startDateCommisioning:
                datePicker.setEditText(startDateCommisioning);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.commisioningSolar:
                datePicker.setEditText(commisioningSolar);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.CommisioningVsat:
                datePicker.setEditText(CommisioningVsat);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.commisioningBts:
                datePicker.setEditText(commisioningBts);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.commisioningWifi:
                datePicker.setEditText(commisioningWifi);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.personnelDepAcceptance:
                datePicker.setEditText(personnelDepAcceptance);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.personnelArvAcceptance:
                datePicker.setEditText(personnelArvAcceptance);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.startDateAcceptance:
                datePicker.setEditText(startDateAcceptance);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.acceptanceFencing:
                datePicker.setEditText(acceptanceFencing);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.acceptancePylon:
                datePicker.setEditText(acceptancePylon);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.acceptanceSolar:
                datePicker.setEditText(acceptanceSolar);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.acceptanceVsat:
                datePicker.setEditText(acceptanceVsat);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.acceptance3G:
                datePicker.setEditText(acceptance3G);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
            case R.id.acceptanceWifi:
                datePicker.setEditText(acceptanceWifi);
                datePicker.show(activity.getFragmentManager(), "tag");
                break;
        }
    }

    public void dosave(final ArrayList<SurveyImages> images, final ArrayList<SurveyComent>  comments){

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(mContext, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(mContext);
        }
        builder.setTitle("Create New Survey")
                .setMessage("Are you sure you want to create this survey?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        try {

                            util.saveImagesToFolder(images);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if(uSurvey == null){
                            doAdd(images,comments);

                        }else{
                            doUpdate(images,comments);
                        }

                        HomeLandingFragment home = new HomeLandingFragment();
                        activity.getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentContainer, home,"homefragment")
                                .addToBackStack(null)
                                .commit();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    public void insertSurveyWithImagesAndComments(Survey survey) {
        List<SurveyImages> images = survey.getSurveyImages();
        List<SurveyComent> comments = survey.getSurveyComents();
        long id = ((MainActivity)activity).getSurveyDatabase().daoAccess ().insertOnlySingleSurvey (survey);
        System.out.println("IMAGES ==="+images.size());
        for (int i = 0; i < images.size(); i++) {

            System.out.println("survey id ==="+id);
            images.get(i).setSurveyId(id);
        }
        for (int i = 0; i < comments.size(); i++) {

            System.out.println("survey id ==="+id);
            comments.get(i).setSurveyId(id);
        }

        ((MainActivity)activity).getSurveyDatabase().daoAccess ().insertImageList (images);
        ((MainActivity)activity).getSurveyDatabase().daoAccess ().insertCommentList (comments);


    }



    public void updateSurveyWithImageAndComment(Survey survey) {
        List<SurveyImages> images = survey.getSurveyImages();
        List<SurveyComent> comments = survey.getSurveyComents();

        System.out.println("comments ==="+comments.size());
        for (int i = 0; i < images.size(); i++) {

            images.get(i).setSurveyId(survey.getId());
        }

        for (int i = 0; i < comments.size(); i++) {

            comments.get(i).setSurveyId(survey.getId());
        }

        ((MainActivity)activity).getSurveyDatabase().daoAccess ().insertImageList (images);
        ((MainActivity)activity).getSurveyDatabase().daoAccess ().insertCommentList (comments);

        ((MainActivity)activity).getSurveyDatabase().daoAccess ().updateSurvey (survey);

    }



    public List<SurveyComent> getallImages() {

        List<SurveyComent> images = ((MainActivity)activity).getSurveyDatabase().daoAccess ().getAllComments();

        return images;
    }


    void doAdd(final ArrayList<SurveyImages> images, final ArrayList<SurveyComent>  comments){


        new Thread(new Runnable() {
            @Override
            public void run() {
                Survey survey =new Survey();
                if(geo !=null)
                survey.setGeo(geo.getText().toString());
                survey.setStatus(Constant.SURVEYCREATED);
                survey.setSurveyImages(images);
                survey.setSurveyComents(comments);
                if(startDate !=null)

                    survey.setStartDate(startDate.getText().toString());
                if(surveyCompleted !=null)

                    survey.setSurveyCompleted(surveyCompleted.getText().toString());
                if(equipPickupSuplierCivilWorks !=null)
                    survey.setEquipPickupSuplierCivilWorks(equipPickupSuplierCivilWorks.getText().toString());
                if(personelDptCivilWorks !=null)

                    survey.setPersonelDptCivilWorks(personelDptCivilWorks.getText().toString());
                if(personelArvCivilWorks !=null)

                    survey.setPersonelArvCivilWorks(personelArvCivilWorks.getText().toString());
                if(startDateCivilWorks !=null)

                    survey.setStartDateCivilWorks(startDateCivilWorks.getText().toString());
                if(equipOnSiteCivilWorks !=null)

                    survey.setEquipOnSiteCivilWorks(equipOnSiteCivilWorks.getText().toString());
                if(allExcavationCompleted !=null)

                    survey.setAllExcavationCompleted(allExcavationCompleted.getText().toString());
                if(fencingCivilCompleted !=null)

                    survey.setFencingCivilCompleted(fencingCivilCompleted.getText().toString());
                if(pylonCivilCompleted !=null)

                    survey.setPylonCivilCompleted(pylonCivilCompleted.getText().toString());
                if(equipPickupSuplierFencingPylon !=null)

                    survey.setEquipPickupSuplierFencingPylon(equipPickupSuplierFencingPylon.getText().toString());
                if(personelDptFencing !=null)
                    survey.setPersonelDptFencing(personelDptFencing.getText().toString());
                if(personelArvFencing !=null)

                    survey.setPersonelArvFencing(personelArvFencing.getText().toString());
                if(startDateFencing !=null)

                    survey.setStartDateFencing(startDateFencing.getText().toString());
                if(equipOnSiteFencing !=null)

                    survey.setEquipOnSiteFencing(equipOnSiteFencing.getText().toString());
                if(installPylonComplete !=null)

                    survey.setInstallPylonComplete(installPylonComplete.getText().toString());
                if(installFencingComplete !=null)

                    survey.setInstallFencingComplete(installFencingComplete.getText().toString());
                if(civilVsatComplete !=null)

                    survey.setCivilVsatComplete(civilVsatComplete.getText().toString());
                if(civilSolarComplete !=null)

                    survey.setCivilSolarComplete(civilSolarComplete.getText().toString());
                if(equipPickupWarehouseSolar !=null)

                    survey.setEquipPickupWarehouseSolar(equipPickupWarehouseSolar.getText().toString());
                if(personnelDepSolar !=null)

                    survey.setPersonnelDepSolar(personnelDepSolar.getText().toString());
                if(personnelArvSolar !=null)

                    survey.setPersonnelArvSolar(personnelArvSolar.getText().toString());
                if(startDateSolar !=null)

                    survey.setStartDateSolar(startDateSolar.getText().toString());
                if(equipOnSiteSolar !=null)

                    survey.setEquipOnSiteSolar(equipOnSiteSolar.getText().toString());
                if(installSolarCompleted !=null)

                    survey.setInstallSolarCompleted(installSolarCompleted.getText().toString());
                if(installVsatComplete !=null)

                    survey.setInstallVsatComplete(installVsatComplete.getText().toString());
                if(installBtsComplete !=null)

                    survey.setInstallBtsComplete(installBtsComplete.getText().toString());
                if(installWifiComplete !=null)

                    survey.setInstallWifiComplete(installWifiComplete.getText().toString());
                if(personnelDepCommisioning !=null)

                    survey.setPersonnelDepCommisioning(personnelDepCommisioning.getText().toString());
                if(personnelArvCommisioning !=null)

                    survey.setPersonnelArvCommisioning(personnelArvCommisioning.getText().toString());
                if(startDateCommisioning !=null)

                    survey.setStartDateCommisioning(startDateCommisioning.getText().toString());
                if(commisioningSolar !=null)

                    survey.setCommisioningSolar(commisioningSolar.getText().toString());
                if(CommisioningVsat !=null)

                    survey.setCommisioningVsat(CommisioningVsat.getText().toString());
                if(commisioningBts !=null)

                    survey.setCommisioningBts(commisioningBts.getText().toString());
                if(commisioningWifi !=null)

                    survey.setCommisioningWifi(commisioningWifi.getText().toString());
                if(personnelDepAcceptance !=null)

                    survey.setPersonnelDepAcceptance(personnelDepAcceptance.getText().toString());
                if(personnelArvAcceptance !=null)

                    survey.setPersonnelArvAcceptance(personnelArvAcceptance.getText().toString());
                if(startDateAcceptance !=null)

                    survey.setStartDateAcceptance(startDateAcceptance.getText().toString());
                if(acceptanceFencing !=null)

                    survey.setAcceptanceFencing(acceptanceFencing.getText().toString());
                if(acceptancePylon !=null)

                    survey.setAcceptancePylon(acceptancePylon.getText().toString());
                if(acceptanceSolar !=null)

                    survey.setAcceptanceSolar(acceptanceSolar.getText().toString());
                if(acceptanceVsat !=null)

                    survey.setAcceptanceVsat(acceptanceVsat.getText().toString());
                if(acceptance3G !=null)

                    survey.setAcceptance3G(acceptance3G.getText().toString());
                if(acceptanceWifi !=null)

                    survey.setAcceptanceWifi(acceptanceWifi.getText().toString());

                survey.setUpdated(1);

                if(survey.isEmpty()){
                    return;
                }

                insertSurveyWithImagesAndComments(survey);
                //((MainActivity)activity).getSurveyDatabase().daoAccess ().insertOnlySingleSurvey (survey);

            }
        }) .start();



    }

    void doUpdate(final ArrayList<SurveyImages> images, final ArrayList<SurveyComent>  comments){


        new Thread(new Runnable() {
            @Override
            public void run() {
                if(geo !=null)
                    uSurvey.setGeo(geo.getText().toString());
                uSurvey.setStatus(Constant.SURVEYCREATED);
                uSurvey.setSurveyImages(images);
                uSurvey.setSurveyComents(comments);
                if(startDate !=null)

                    uSurvey.setStartDate(startDate.getText().toString());
                if(surveyCompleted !=null)

                    uSurvey.setSurveyCompleted(surveyCompleted.getText().toString());
                if(equipPickupSuplierCivilWorks !=null)
                    uSurvey.setEquipPickupSuplierCivilWorks(equipPickupSuplierCivilWorks.getText().toString());
                if(personelDptCivilWorks !=null)

                    uSurvey.setPersonelDptCivilWorks(personelDptCivilWorks.getText().toString());
                if(personelArvCivilWorks !=null)

                    uSurvey.setPersonelArvCivilWorks(personelArvCivilWorks.getText().toString());
                if(startDateCivilWorks !=null)

                    uSurvey.setStartDateCivilWorks(startDateCivilWorks.getText().toString());
                if(equipOnSiteCivilWorks !=null)

                    uSurvey.setEquipOnSiteCivilWorks(equipOnSiteCivilWorks.getText().toString());
                if(allExcavationCompleted !=null)

                    uSurvey.setAllExcavationCompleted(allExcavationCompleted.getText().toString());
                if(fencingCivilCompleted !=null)

                    uSurvey.setFencingCivilCompleted(fencingCivilCompleted.getText().toString());
                if(pylonCivilCompleted !=null)

                    uSurvey.setPylonCivilCompleted(pylonCivilCompleted.getText().toString());
                if(equipPickupSuplierFencingPylon !=null)

                    uSurvey.setEquipPickupSuplierFencingPylon(equipPickupSuplierFencingPylon.getText().toString());
                if(personelDptFencing !=null)
                    uSurvey.setPersonelDptFencing(personelDptFencing.getText().toString());
                if(personelArvFencing !=null)

                    uSurvey.setPersonelArvFencing(personelArvFencing.getText().toString());
                if(startDateFencing !=null)

                    uSurvey.setStartDateFencing(startDateFencing.getText().toString());
                if(equipOnSiteFencing !=null)

                    uSurvey.setEquipOnSiteFencing(equipOnSiteFencing.getText().toString());
                if(installPylonComplete !=null)

                    uSurvey.setInstallPylonComplete(installPylonComplete.getText().toString());
                if(installFencingComplete !=null)

                    uSurvey.setInstallFencingComplete(installFencingComplete.getText().toString());
                if(civilVsatComplete !=null)

                    uSurvey.setCivilVsatComplete(civilVsatComplete.getText().toString());
                if(civilSolarComplete !=null)

                    uSurvey.setCivilSolarComplete(civilSolarComplete.getText().toString());
                if(equipPickupWarehouseSolar !=null)

                    uSurvey.setEquipPickupWarehouseSolar(equipPickupWarehouseSolar.getText().toString());
                if(personnelDepSolar !=null)

                    uSurvey.setPersonnelDepSolar(personnelDepSolar.getText().toString());
                if(personnelArvSolar !=null)

                    uSurvey.setPersonnelArvSolar(personnelArvSolar.getText().toString());
                if(startDateSolar !=null)

                    uSurvey.setStartDateSolar(startDateSolar.getText().toString());
                if(equipOnSiteSolar !=null)

                    uSurvey.setEquipOnSiteSolar(equipOnSiteSolar.getText().toString());
                if(installSolarCompleted !=null)

                    uSurvey.setInstallSolarCompleted(installSolarCompleted.getText().toString());
                if(installVsatComplete !=null)

                    uSurvey.setInstallVsatComplete(installVsatComplete.getText().toString());
                if(installBtsComplete !=null)

                    uSurvey.setInstallBtsComplete(installBtsComplete.getText().toString());
                if(installWifiComplete !=null)

                    uSurvey.setInstallWifiComplete(installWifiComplete.getText().toString());
                if(personnelDepCommisioning !=null)

                    uSurvey.setPersonnelDepCommisioning(personnelDepCommisioning.getText().toString());
                if(personnelArvCommisioning !=null)

                    uSurvey.setPersonnelArvCommisioning(personnelArvCommisioning.getText().toString());
                if(startDateCommisioning !=null)

                    uSurvey.setStartDateCommisioning(startDateCommisioning.getText().toString());
                if(commisioningSolar !=null)

                    uSurvey.setCommisioningSolar(commisioningSolar.getText().toString());
                if(CommisioningVsat !=null)

                    uSurvey.setCommisioningVsat(CommisioningVsat.getText().toString());
                if(commisioningBts !=null)

                    uSurvey.setCommisioningBts(commisioningBts.getText().toString());
                if(commisioningWifi !=null)

                    uSurvey.setCommisioningWifi(commisioningWifi.getText().toString());
                if(personnelDepAcceptance !=null)

                    uSurvey.setPersonnelDepAcceptance(personnelDepAcceptance.getText().toString());
                if(personnelArvAcceptance !=null)

                    uSurvey.setPersonnelArvAcceptance(personnelArvAcceptance.getText().toString());
                if(startDateAcceptance !=null)

                    uSurvey.setStartDateAcceptance(startDateAcceptance.getText().toString());
                if(acceptanceFencing !=null)

                    uSurvey.setAcceptanceFencing(acceptanceFencing.getText().toString());
                if(acceptancePylon !=null)

                    uSurvey.setAcceptancePylon(acceptancePylon.getText().toString());
                if(acceptanceSolar !=null)

                    uSurvey.setAcceptanceSolar(acceptanceSolar.getText().toString());
                if(acceptanceVsat !=null)

                    uSurvey.setAcceptanceVsat(acceptanceVsat.getText().toString());
                if(acceptance3G !=null)

                    uSurvey.setAcceptance3G(acceptance3G.getText().toString());
                if(acceptanceWifi !=null)

                    uSurvey.setAcceptanceWifi(acceptanceWifi.getText().toString());

                uSurvey.setUpdated(1);


                updateSurveyWithImageAndComment(uSurvey);

            }
        }) .start();



    }

    public void preloadForms(){


        if(geo !=null)
            geo.setText(uSurvey.getGeo());
        if(startDate !=null)

            startDate.setText(uSurvey.getStartDate());
        if(surveyCompleted !=null)

            surveyCompleted.setText(uSurvey.getSurveyCompleted());
        if(equipPickupSuplierCivilWorks !=null)
            equipPickupSuplierCivilWorks.setText(uSurvey.getEquipPickupSuplierCivilWorks());

        if(personelDptCivilWorks !=null)

            personelDptCivilWorks.setText(uSurvey.getPersonelDptCivilWorks());
        if(personelArvCivilWorks !=null)

            personelArvCivilWorks.setText(uSurvey.getPersonelArvCivilWorks());
        if(startDateCivilWorks !=null)

            startDateCivilWorks.setText(uSurvey.getStartDateCivilWorks());
        if(equipOnSiteCivilWorks !=null)

            equipOnSiteCivilWorks.setText(uSurvey.getEquipOnSiteCivilWorks());
        if(allExcavationCompleted !=null)

            allExcavationCompleted.setText(uSurvey.getAllExcavationCompleted());
        if(fencingCivilCompleted !=null)

            fencingCivilCompleted.setText(uSurvey.getFencingCivilCompleted());
        if(pylonCivilCompleted !=null)

            pylonCivilCompleted.setText(uSurvey.getPylonCivilCompleted());
        if(equipPickupSuplierFencingPylon !=null)

            equipPickupSuplierFencingPylon.setText(uSurvey.getEquipPickupSuplierFencingPylon());
        if(personelDptFencing !=null)
            personelDptFencing.setText(uSurvey.getPersonelDptFencing());
        if(personelArvFencing !=null)

            personelArvFencing.setText(uSurvey.getPersonelArvFencing());
        if(startDateFencing !=null)

            startDateFencing.setText(uSurvey.getStartDateFencing());
        if(equipOnSiteFencing !=null)

            equipOnSiteFencing.setText(uSurvey.getEquipOnSiteFencing());
        if(installPylonComplete !=null)

            installPylonComplete.setText(uSurvey.getInstallPylonComplete());
        if(installFencingComplete !=null)

            installFencingComplete.setText(uSurvey.getInstallFencingComplete());
        if(civilVsatComplete !=null)

            civilVsatComplete.setText(uSurvey.getCivilVsatComplete());
        if(civilSolarComplete !=null)

            civilSolarComplete.setText(uSurvey.getCivilSolarComplete());
        if(equipPickupWarehouseSolar !=null)

            equipPickupWarehouseSolar.setText(uSurvey.getEquipPickupWarehouseSolar());
        if(personnelDepSolar !=null)

            personnelDepSolar.setText(uSurvey.getPersonnelDepSolar());
        if(personnelArvSolar !=null)

            personnelArvSolar.setText(uSurvey.getPersonnelArvSolar());
        if(startDateSolar !=null)

            startDateSolar.setText(uSurvey.getEquipPickupSuplierCivilWorks());
        if(equipOnSiteSolar !=null)

            equipOnSiteSolar.setText(uSurvey.getStartDateSolar());
        if(installSolarCompleted !=null)

            installSolarCompleted.setText(uSurvey.getInstallSolarCompleted());
        if(installVsatComplete !=null)

            installVsatComplete.setText(uSurvey.getInstallVsatComplete());
        if(installBtsComplete !=null)

            installBtsComplete.setText(uSurvey.getInstallBtsComplete());
        if(installWifiComplete !=null)

            installWifiComplete.setText(uSurvey.getInstallWifiComplete());
        if(personnelDepCommisioning !=null)

            personnelDepCommisioning.setText(uSurvey.getPersonnelDepCommisioning());
        if(personnelArvCommisioning !=null)

            personnelArvCommisioning.setText(uSurvey.getPersonnelArvCommisioning());
        if(startDateCommisioning !=null)

            startDateCommisioning.setText(uSurvey.getStartDateCommisioning());

        if(commisioningSolar !=null)

            commisioningSolar.setText(uSurvey.getCommisioningSolar());
        if(CommisioningVsat !=null)

            CommisioningVsat.setText(uSurvey.getCommisioningVsat());
        if(commisioningBts !=null)

            commisioningBts.setText(uSurvey.getCommisioningBts());
        if(commisioningWifi !=null)

            commisioningWifi.setText(uSurvey.getCommisioningWifi());
        if(personnelDepAcceptance !=null)

            personnelDepAcceptance.setText(uSurvey.getPersonnelDepAcceptance());
        if(personnelArvAcceptance !=null)

            personnelArvAcceptance.setText(uSurvey.getPersonnelArvAcceptance());
        if(startDateAcceptance !=null)

            startDateAcceptance.setText(uSurvey.getStartDateAcceptance());
        if(acceptanceFencing !=null)

            acceptanceFencing.setText(uSurvey.getAcceptanceFencing());
        if(acceptancePylon !=null)

            acceptancePylon.setText(uSurvey.getAcceptancePylon());
        if(acceptanceSolar !=null)

            acceptanceSolar.setText(uSurvey.getAcceptanceSolar());
        if(acceptanceVsat !=null)

            acceptanceVsat.setText(uSurvey.getAcceptanceVsat());
        if(acceptance3G !=null)

            acceptance3G.setText(uSurvey.getAcceptance3G());
        if(acceptanceWifi !=null)

            acceptanceWifi.setText(uSurvey.getAcceptanceWifi());
    }

}



