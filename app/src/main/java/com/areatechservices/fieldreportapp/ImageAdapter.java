package com.areatechservices.fieldreportapp;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.areatechservices.fieldreportapp.Domain.ImageDomain;
import com.areatechservices.fieldreportapp.Models.SurveyImages;

import java.util.ArrayList;

/**
 * Created by djbabs on 12/15/18.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<SurveyImages> mImages;

    public ImageAdapter(Context c, ArrayList<SurveyImages> images) {
        mContext = c;
        mImages = images;
    }

    public int getCount() {
        return mImages.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {

            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(5, 5, 5, 5);
        } else {

            imageView = (ImageView) convertView;
        }

        imageView.setImageURI(Uri.parse(mImages.get(position).getUri()));
        return imageView;
    }

}