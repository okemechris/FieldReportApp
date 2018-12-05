package com.areatechservices.fieldreportapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.areatechservices.fieldreportapp.Constant;
import com.areatechservices.fieldreportapp.MainActivity;
import com.areatechservices.fieldreportapp.Models.Survey;
import com.areatechservices.fieldreportapp.R;

import java.util.List;


public class HomeLandingFragment extends Fragment {


    private ListView uncompletedSurveyList;
    private ListView completedSurveyList;
    public UncompletedSurveyAdapter uncompletedAdapter;
    public CompletedSurveyAdapter completedAdapter;


    public List<Survey> uncompletedSurveyArrayList;
    public List<Survey> completedSurveyArrayList;

    public HomeLandingFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.homelandinglist_layout, container, false);

        uncompletedSurveyList = view.findViewById(R.id.uncompletedSurveyList);
        completedSurveyList = view.findViewById(R.id.completedSurveyList);

        startUncompletedThread();

        uncompletedSurveyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle b = new Bundle();
                Survey s = (Survey)adapterView.getItemAtPosition(i);
                b.putLong("surveyId",s.getId());

                EditSurveyFragment f = new EditSurveyFragment();
                f.setArguments(b);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, f,"findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;

    }


    public void startUncompletedThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                uncompletedSurveyArrayList = getUncompletedSurvey();
                uncompletedAdapter = new UncompletedSurveyAdapter(getActivity().getApplicationContext(),uncompletedSurveyArrayList);
                uncompletedSurveyList.setAdapter(uncompletedAdapter);
//                uncompletedAdapter.notifyDataSetChanged();

                completedSurveyArrayList = getCompletedSurvey();
                completedAdapter = new CompletedSurveyAdapter(getActivity().getApplicationContext(),completedSurveyArrayList);
                completedSurveyList.setAdapter(completedAdapter);
//                uncompletedAdapter.notifyDataSetChanged();


            }
        }) .start();

    }

    public List<Survey> getUncompletedSurvey(){

       return ((MainActivity)getActivity()).getSurveyDatabase().daoAccess().findSurveyByStatus(Constant.SURVEYCREATED);
    }

    public List<Survey> getCompletedSurvey(){

        return ((MainActivity)getActivity()).getSurveyDatabase().daoAccess().findSurveyByStatus(Constant.SURVEYCOMPLETED);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }




    public class UncompletedSurveyAdapter extends ArrayAdapter<Survey> {
        private final Context context;
        private final List<Survey> surveyList;


        public UncompletedSurveyAdapter(Context context,
                                        List<Survey> surveyList) {
            super(context, R.layout.simple_list_item, surveyList);
            this.context = context;
            this.surveyList = surveyList;

        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View rowView= inflater.inflate(R.layout.simple_list_item, null, true);
            TextView txtTitle = rowView.findViewById(R.id.title);

            txtTitle.setText(surveyList.get(position).getGeo());








            return rowView;
        }
    }


    public class CompletedSurveyAdapter extends ArrayAdapter<Survey> {
        private final Context context;
        private final List<Survey> surveyList;


        public CompletedSurveyAdapter(Context context,
                                        List<Survey> surveyList) {
            super(context, R.layout.simple_list_item, surveyList);
            this.context = context;
            this.surveyList = surveyList;

        }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View rowView= inflater.inflate(R.layout.simple_list_item, null, true);
            TextView txtTitle = rowView.findViewById(R.id.title);

            txtTitle.setText(surveyList.get(position).getGeo());








            return rowView;
        }
    }


    }

