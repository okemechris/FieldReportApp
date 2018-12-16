package com.areatechservices.fieldreportapp.Fragments;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.areatechservices.fieldreportapp.Domain.ImageDomain;
import com.areatechservices.fieldreportapp.ImageAdapter;
import com.areatechservices.fieldreportapp.MainActivity;
import com.areatechservices.fieldreportapp.Models.Survey;
import com.areatechservices.fieldreportapp.Models.SurveyImages;
import com.areatechservices.fieldreportapp.R;
import com.areatechservices.fieldreportapp.SurveyViewPagerAdapter;
import com.areatechservices.fieldreportapp.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class EditSurveyFragment extends Fragment implements View.OnClickListener {



        ArrayList<SurveyImages> imageArrayList;
        ImageAdapter imageAdapter;
        ViewPager viewPager;
        FloatingActionButton save;

        public Long surveyId;
        Bundle bundle;
        Survey survey;
        SurveyViewPagerAdapter adapter;
    private int GALLERY = 1, CAMERA = 2;
    Util util;

        public EditSurveyFragment() {
            // Required empty public constructor
        }


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            util=new Util(getActivity());



        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.create_survey_layout, container, false);
            save = view.findViewById(R.id.save);
            viewPager = view.findViewById(R.id.viewpager);
            bundle = this.getArguments();
            surveyId = bundle.getLong("surveyId");
            adapter = new SurveyViewPagerAdapter(getActivity(),getActivity(),surveyId);
            viewPager.setAdapter(adapter);
            viewPager.setOffscreenPageLimit(8);


            TextView heading = view.findViewById(R.id.heading);
            heading.setText("Update Report");
            imageArrayList = new ArrayList<>();
            imageAdapter = new ImageAdapter(getContext(),imageArrayList);


            GridView grid = view.findViewById(R.id.thumbnailGrid);
            grid.setAdapter(imageAdapter);



            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.dosave(imageArrayList);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                util.saveImagesToFolder(imageArrayList);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            });

            ImageButton upload = view.findViewById(R.id.addImage);
            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPictureDialog();
                }
            });


            return view;

        }

//    public Survey getSurveyWithImagesByStatus(Long id) {
//        Survey survey = ((MainActivity)getActivity()).getSurveyDatabase().daoAccess().findSurveyById(id);
//        List<SurveyImages> images = ((MainActivity)getActivity()).getSurveyDatabase().daoAccess().getSurveyImages(id);
//        survey.setSurveyImages(images);
//        return survey;
//    }




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


        }

        @Override
        public void onDestroy() {
            super.onDestroy();

        }


    public void showPopup(final Uri uri){


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this.getActivity());
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.picture_properties, null);
        dialogBuilder.setView(dialogView);

        final EditText imageDes = (EditText) dialogView.findViewById(R.id.imageDes);
        Button doSave = (Button) dialogView.findViewById(R.id.saveImage);
        ImageView image = dialogView.findViewById(R.id.imageView);


        image.setImageURI(uri);



        final AlertDialog alertDialog = dialogBuilder.create();

        doSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SurveyImages i =new SurveyImages();
                i.setDescription(imageDes.getText().toString());
                i.setUri(uri.toString());
                String name = getRealPathFromURI(uri);
                i.setImage(name);
                imageArrayList.add(i);
                imageAdapter.notifyDataSetChanged();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                //break;
//                            case 1:
//                                takePhotoFromCamera();
//                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }


    private String getRealPathFromURI(Uri contentUri) {

        String[] proj = { MediaStore.Video.Media.DATA };
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String name = cursor.getString(column_index);
        String[] nameArray = name.split("/");
        return nameArray[nameArray.length-1];
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {


            case 1:
                if(resultCode == RESULT_OK){

                    Uri selectedImage = imageReturnedIntent.getData();
                    showPopup(selectedImage);
                }

//                break;
//            case 2:
//                if(resultCode == RESULT_OK){
//                    System.out.println("djdjdjdjdj ---------------");
//                    Uri selectedImage = imageReturnedIntent.getData();
//                    ImageDomain i =new ImageDomain();
//                    i.setDescription("");
//                    i.setImageUri(selectedImage);
//
//                    imageArrayList.add(i);
//                }
//                break;
        }

    }

}
