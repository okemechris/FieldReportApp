package com.areatechservices.fieldreportapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.areatechservices.fieldreportapp.Constant;
import com.areatechservices.fieldreportapp.MainActivity;
import com.areatechservices.fieldreportapp.Models.Survey;
import com.areatechservices.fieldreportapp.R;


public class SurveyFragment extends Fragment implements View.OnClickListener {

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

    private Button saveSurveyButton ;

    public Long surveyId;


    public SurveyFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         datePicker = new DatePickerDialogFragment();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.equipment_list, container, false);

        geo = view.findViewById(R.id.geo);
        saveSurveyButton = view.findViewById(R.id.save);
        startDate = view.findViewById(R.id.startDate);
        surveyCompleted  = view.findViewById(R.id.surveyCompleted);
        equipPickupSuplierCivilWorks = view.findViewById(R.id.equipPickupSuplierCivilWorks);
        personelDptCivilWorks = view.findViewById(R.id.personelDptCivilWorks);
        personelArvCivilWorks = view.findViewById(R.id.personelArvCivilWorks);
        startDateCivilWorks = view.findViewById(R.id.startDateCivilWorks);
        equipOnSiteCivilWorks = view.findViewById(R.id.equipOnSiteCivilWorks);
        allExcavationCompleted = view.findViewById(R.id.allExcavationCompleted);
        fencingCivilCompleted = view.findViewById(R.id.fencingCivilCompleted);
        pylonCivilCompleted = view.findViewById(R.id.pylonCivilCompleted);
        equipPickupSuplierFencingPylon = view.findViewById(R.id.equipPickupSuplierFencingPylon);
        personelDptFencing = view.findViewById(R.id.personelDptFencing);
        personelArvFencing = view.findViewById(R.id.personelArvFencing);
        startDateFencing = view.findViewById(R.id.startDateFencing);
        equipOnSiteFencing = view.findViewById(R.id.equipOnSiteFencing);
        installPylonComplete = view.findViewById(R.id.installPylonComplete);
        installFencingComplete = view.findViewById(R.id.installFencingComplete);
        civilVsatComplete = view.findViewById(R.id.civilVsatComplete);
        civilSolarComplete = view.findViewById(R.id.civilSolarComplete);
        equipPickupWarehouseSolar = view.findViewById(R.id.equipPickupWarehouseSolar);
        personnelDepSolar = view.findViewById(R.id.personnelDepSolar);
        personnelArvSolar = view.findViewById(R.id.personnelArvSolar);
        startDateSolar = view.findViewById(R.id.startDateSolar);
        equipOnSiteSolar = view.findViewById(R.id.equipOnSiteSolar);
        installSolarCompleted = view.findViewById(R.id.installSolarCompleted);
        installVsatComplete = view.findViewById(R.id.installVsatComplete);
        installBtsComplete = view.findViewById(R.id.installBtsComplete);
        installWifiComplete = view.findViewById(R.id.installWifiComplete);
        personnelDepCommisioning = view.findViewById(R.id.personnelDepCommisioning);
        personnelArvCommisioning = view.findViewById(R.id.personnelArvCommisioning);
        startDateCommisioning = view.findViewById(R.id.startDateCommisioning);
        commisioningSolar = view.findViewById(R.id.commisioningSolar);
        CommisioningVsat = view.findViewById(R.id.CommisioningVsat);
        commisioningBts = view.findViewById(R.id.commisioningBts);
        commisioningWifi = view.findViewById(R.id.commisioningWifi);
        personnelDepAcceptance  = view.findViewById(R.id.personnelDepAcceptance);
        personnelArvAcceptance = view.findViewById(R.id.personnelArvAcceptance);
        startDateAcceptance = view.findViewById(R.id.startDateAcceptance);
        acceptanceFencing = view.findViewById(R.id.acceptanceFencing);
        acceptancePylon = view.findViewById(R.id.acceptancePylon);
        acceptanceSolar = view.findViewById(R.id.acceptanceSolar);
        acceptanceVsat = view.findViewById(R.id.acceptanceVsat);
        acceptance3G = view.findViewById(R.id.acceptance3G);
        acceptanceWifi = view.findViewById(R.id.acceptanceWifi);




        startDate.setOnClickListener(this);
        surveyCompleted.setOnClickListener(this);
        equipPickupSuplierCivilWorks.setOnClickListener(this);
        personelDptCivilWorks.setOnClickListener(this);
        personelArvCivilWorks.setOnClickListener(this);
        startDateCivilWorks.setOnClickListener(this);
        equipOnSiteCivilWorks.setOnClickListener(this);
        allExcavationCompleted.setOnClickListener(this);
        fencingCivilCompleted.setOnClickListener(this);
        pylonCivilCompleted.setOnClickListener(this);
        equipPickupSuplierFencingPylon.setOnClickListener(this);
        personelDptFencing.setOnClickListener(this);
        personelArvFencing.setOnClickListener(this);
        startDateFencing.setOnClickListener(this);
        equipOnSiteFencing.setOnClickListener(this);
        installPylonComplete.setOnClickListener(this);
        installFencingComplete.setOnClickListener(this);
        civilVsatComplete.setOnClickListener(this);
        civilSolarComplete.setOnClickListener(this);
        equipPickupWarehouseSolar.setOnClickListener(this);
        personnelDepSolar.setOnClickListener(this);
        personnelArvSolar.setOnClickListener(this);
        startDateSolar.setOnClickListener(this);
        equipOnSiteSolar.setOnClickListener(this);
        installSolarCompleted.setOnClickListener(this);
        installVsatComplete.setOnClickListener(this);
        installBtsComplete.setOnClickListener(this);
        installWifiComplete.setOnClickListener(this);
        personnelDepCommisioning.setOnClickListener(this);
        personnelArvCommisioning.setOnClickListener(this);
        startDateCommisioning.setOnClickListener(this);
        commisioningSolar.setOnClickListener(this);
        CommisioningVsat.setOnClickListener(this);
        commisioningBts.setOnClickListener(this);
        commisioningWifi.setOnClickListener(this);
        personnelDepAcceptance.setOnClickListener(this);
        personnelArvAcceptance.setOnClickListener(this);
        startDateAcceptance.setOnClickListener(this);
        acceptanceFencing.setOnClickListener(this);
        acceptanceSolar.setOnClickListener(this);
        acceptanceVsat.setOnClickListener(this);
        acceptancePylon.setOnClickListener(this);
        acceptance3G.setOnClickListener(this);
        acceptanceWifi.setOnClickListener(this);




        saveSurveyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(surveyId != null){

                    //do update
                }else{

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Survey survey =new Survey();
                            survey.setGeo(geo.getText().toString());
                            survey.setStatus(Constant.SURVEYCREATED);
                            survey.setStartDate(startDate.getText().toString());
                            survey.setSurveyCompleted(surveyCompleted.getText().toString());
                            survey.setEquipPickupSuplierCivilWorks(equipPickupSuplierCivilWorks.getText().toString());
                            survey.setPersonelDptCivilWorks(personelDptCivilWorks.getText().toString());
                            survey.setPersonelArvCivilWorks(personelArvCivilWorks.getText().toString());
                            survey.setStartDateCivilWorks(startDateCivilWorks.getText().toString());
                            survey.setEquipOnSiteCivilWorks(equipOnSiteCivilWorks.getText().toString());
                            survey.setAllExcavationCompleted(allExcavationCompleted.getText().toString());
                            survey.setFencingCivilCompleted(fencingCivilCompleted.getText().toString());
                            survey.setPylonCivilCompleted(pylonCivilCompleted.getText().toString());
                            survey.setEquipPickupSuplierFencingPylon(equipPickupSuplierFencingPylon.getText().toString());
                            survey.setPersonelDptFencing(personelDptFencing.getText().toString());
                            survey.setPersonelArvFencing(personelArvFencing.getText().toString());
                            survey.setStartDateFencing(startDateFencing.getText().toString());
                            survey.setEquipOnSiteFencing(equipOnSiteFencing.getText().toString());
                            survey.setInstallPylonComplete(installPylonComplete.getText().toString());
                            survey.setInstallFencingComplete(installFencingComplete.getText().toString());
                            survey.setCivilVsatComplete(civilVsatComplete.getText().toString());
                            survey.setCivilSolarComplete(civilSolarComplete.getText().toString());
                            survey.setEquipPickupWarehouseSolar(equipPickupWarehouseSolar.getText().toString());
                            survey.setPersonnelDepSolar(personnelDepSolar.getText().toString());
                            survey.setPersonnelArvSolar(personnelArvSolar.getText().toString());
                            survey.setStartDateSolar(startDateSolar.getText().toString());
                            survey.setEquipOnSiteSolar(equipOnSiteSolar.getText().toString());
                            survey.setInstallSolarCompleted(installSolarCompleted.getText().toString());
                            survey.setInstallVsatComplete(installVsatComplete.getText().toString());
                            survey.setInstallBtsComplete(installBtsComplete.getText().toString());
                            survey.setInstallWifiComplete(installWifiComplete.getText().toString());
                            survey.setPersonnelDepCommisioning(personnelDepCommisioning.getText().toString());
                            survey.setPersonnelArvCommisioning(personnelArvCommisioning.getText().toString());
                            survey.setStartDateCommisioning(startDateCommisioning.getText().toString());
                            survey.setCommisioningSolar(commisioningSolar.getText().toString());
                            survey.setCommisioningVsat(CommisioningVsat.getText().toString());
                            survey.setCommisioningBts(commisioningBts.getText().toString());
                            survey.setCommisioningWifi(commisioningWifi.getText().toString());
                            survey.setPersonnelDepAcceptance(personnelDepAcceptance.getText().toString());
                            survey.setPersonnelArvAcceptance(personnelArvAcceptance.getText().toString());
                            survey.setStartDateAcceptance(startDateAcceptance.getText().toString());
                            survey.setAcceptanceFencing(acceptanceFencing.getText().toString());
                            survey.setGeo(geo.getText().toString());
                            survey.setAcceptancePylon(acceptancePylon.getText().toString());
                            survey.setAcceptanceSolar(acceptanceSolar.getText().toString());
                            survey.setAcceptanceVsat(acceptanceVsat.getText().toString());
                            survey.setAcceptance3G(acceptance3G.getText().toString());
                            survey.setAcceptanceWifi(acceptanceWifi.getText().toString());


                            ((MainActivity)getActivity()).getSurveyDatabase().daoAccess ().insertOnlySingleSurvey (survey);
                        }
                    }) .start();





                }







            }
        });




        return view;

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onClick(View view) {


        switch(view.getId()){

            case R.id.startDate:
                datePicker.setEditText(startDate);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.surveyCompleted:
                datePicker.setEditText(surveyCompleted);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.equipPickupSuplierCivilWorks:
                datePicker.setEditText(equipPickupSuplierCivilWorks);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.personelDptCivilWorks:
                datePicker.setEditText(personelDptCivilWorks);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.personelArvCivilWorks:
                datePicker.setEditText(personelArvCivilWorks);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.startDateCivilWorks:
                datePicker.setEditText(startDateCivilWorks);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.equipOnSiteCivilWorks:
                datePicker.setEditText(equipOnSiteCivilWorks);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.allExcavationCompleted:
                datePicker.setEditText(allExcavationCompleted);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.fencingCivilCompleted:
                datePicker.setEditText(fencingCivilCompleted);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.pylonCivilCompleted:
                datePicker.setEditText(pylonCivilCompleted);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.equipPickupSuplierFencingPylon:
                datePicker.setEditText(equipPickupSuplierFencingPylon);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.personelDptFencing:
                datePicker.setEditText(personelDptFencing);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.personelArvFencing:
                datePicker.setEditText(personelArvFencing);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.startDateFencing:
                datePicker.setEditText(startDateFencing);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.equipOnSiteFencing:
                datePicker.setEditText(equipOnSiteFencing);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;

            case R.id.installPylonComplete:
                datePicker.setEditText(installPylonComplete);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;

            case R.id.installFencingComplete:
                datePicker.setEditText(installFencingComplete);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;

            case R.id.civilVsatComplete:
                datePicker.setEditText(civilVsatComplete);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;

            case R.id.civilSolarComplete:
                datePicker.setEditText(civilSolarComplete);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;

            case R.id.equipPickupWarehouseSolar:
                datePicker.setEditText(equipPickupWarehouseSolar);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;

            case R.id.personnelDepSolar:
                datePicker.setEditText(personnelDepSolar);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.personnelArvSolar:
                datePicker.setEditText(personnelArvSolar);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;

            case R.id.startDateSolar:
                datePicker.setEditText(startDateSolar);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;

            case R.id.equipOnSiteSolar:
                datePicker.setEditText(equipOnSiteSolar);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.installSolarCompleted:
                datePicker.setEditText(installSolarCompleted);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;

            case R.id.installVsatComplete:
                datePicker.setEditText(installVsatComplete);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;

            case R.id.installBtsComplete:
                datePicker.setEditText(installBtsComplete);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.installWifiComplete:
                datePicker.setEditText(installWifiComplete);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;

            case R.id.personnelDepCommisioning:
                datePicker.setEditText(personnelDepCommisioning);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;

            case R.id.personnelArvCommisioning:
                datePicker.setEditText(startDate);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.startDateCommisioning:
                datePicker.setEditText(startDateCommisioning);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.commisioningSolar:
                datePicker.setEditText(commisioningSolar);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.CommisioningVsat:
                datePicker.setEditText(CommisioningVsat);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.commisioningBts:
                datePicker.setEditText(commisioningBts);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.commisioningWifi:
                datePicker.setEditText(commisioningWifi);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.personnelDepAcceptance:
                datePicker.setEditText(personnelDepAcceptance);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.personnelArvAcceptance:
                datePicker.setEditText(personnelArvAcceptance);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.startDateAcceptance:
                datePicker.setEditText(startDateAcceptance);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.acceptanceFencing:
                datePicker.setEditText(acceptanceFencing);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.acceptancePylon:
                datePicker.setEditText(acceptancePylon);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.acceptanceSolar:
                datePicker.setEditText(acceptanceSolar);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.acceptanceVsat:
                datePicker.setEditText(acceptanceVsat);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.acceptance3G:
                datePicker.setEditText(acceptance3G);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;
            case R.id.acceptanceWifi:
                datePicker.setEditText(acceptanceWifi);
                datePicker.show(SurveyFragment.this.getActivity().getFragmentManager(), "tag");
                break;


        }
    }
}
