package com.areatechservices.fieldreportapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.areatechservices.fieldreportapp.Models.SurveyComent;
import com.areatechservices.fieldreportapp.R;

import java.util.List;

public class CommentListAdapter extends ArrayAdapter<SurveyComent> {

    private final Context context;
    private final List<SurveyComent> commentList;


    public CommentListAdapter(Context context,
                                  List<SurveyComent> commentList) {
        super(context, R.layout.simple_list_item, commentList);
        this.context = context;
        this.commentList = commentList;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View rowView= inflater.inflate(R.layout.simple_list_item, null, true);
        TextView txtTitle = rowView.findViewById(R.id.title);

        txtTitle.setText("comment");








        return rowView;
    }
}

