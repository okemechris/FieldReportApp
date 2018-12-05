package com.areatechservices.fieldreportapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.areatechservices.fieldreportapp.Constant;
import com.areatechservices.fieldreportapp.MainActivity;
import com.areatechservices.fieldreportapp.Models.Survey;
import com.areatechservices.fieldreportapp.R;

import java.util.List;


public class SurveyListFragment extends Fragment {

   private ListView surveyList ;

    public SurveyAdapter surveyAdapter;


    public List<Survey> surveyArrayList;

    public SurveyListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.survey_list_layout, container, false);

        surveyList = view.findViewById(R.id.surveyList);

        startSurveyThread();




        return view;
    }

    public void startSurveyThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                surveyArrayList = getAllSurvey();
                surveyAdapter = new SurveyAdapter(getActivity().getApplicationContext(), surveyArrayList);
                surveyList.setAdapter(surveyAdapter);
//                uncompletedAdapter.notifyDataSetChanged();



            }
        }) .start();

    }

    public List<Survey> getAllSurvey(){

        return ((MainActivity)getActivity()).getSurveyDatabase().daoAccess().getAllSurvey();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public class SurveyAdapter extends ArrayAdapter<Survey> {
        private final Context context;
        private final List<Survey> surveyList;


        public SurveyAdapter(Context context,
                                        List<Survey> surveyList) {
            super(context, R.layout.survey_list_row, surveyList);
            this.context = context;
            this.surveyList = surveyList;

        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View rowView= inflater.inflate(R.layout.survey_list_row, null, true);

            TextView txtTitle = rowView.findViewById(R.id.title);
            TextView status = rowView.findViewById(R.id.status);
            Button setComplete = rowView.findViewById(R.id.complete);

            txtTitle.setText(surveyList.get(position).getGeo());

            if(surveyList.get(position).getStatus() == Constant.SURVEYCREATED){

                setComplete.setVisibility(View.VISIBLE);
                status.setVisibility(View.GONE);
            }else{

                setComplete.setVisibility(View.GONE);
                status.setVisibility(View.VISIBLE);
            }

            final Long positionId = surveyList.get(position).getId();
            setComplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            Survey survey = ((MainActivity)getActivity()).getSurveyDatabase().daoAccess().findSurveyById(positionId);
                            survey.setStatus(Constant.SURVEYCOMPLETED);
                            ((MainActivity)getActivity()).getSurveyDatabase().daoAccess ().updateSurvey (survey);
                            surveyAdapter.notifyDataSetChanged();
                        }
                    }).start();
                }
            });





            return rowView;
        }
    }
}
