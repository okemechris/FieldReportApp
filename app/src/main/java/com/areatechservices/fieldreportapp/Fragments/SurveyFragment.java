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
import android.widget.ListView;

import com.areatechservices.fieldreportapp.Adapters.CommentListAdapter;
import com.areatechservices.fieldreportapp.Constant;
import com.areatechservices.fieldreportapp.Adapters.ImageAdapter;
import com.areatechservices.fieldreportapp.Models.SurveyComent;
import com.areatechservices.fieldreportapp.Models.SurveyImages;
import com.areatechservices.fieldreportapp.R;
import com.areatechservices.fieldreportapp.Adapters.SurveyViewPagerAdapter;
import com.areatechservices.fieldreportapp.Util;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


public class SurveyFragment extends Fragment implements View.OnClickListener {

    ViewPager viewPager;
    FloatingActionButton save;
    ImageAdapter imageAdapter;
    CommentListAdapter commentListAdapter;
    ArrayList<SurveyImages> imageArrayList;
    ArrayList<SurveyComent> commentArrayList;
    Util util;

    private int GALLERY = 1, CAMERA = 2;


    public SurveyFragment() {
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
        final SurveyViewPagerAdapter adapter = new SurveyViewPagerAdapter(this.getActivity(),this.getActivity(),null,util);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(8);

        imageArrayList = new ArrayList<>();
        commentArrayList = new ArrayList<>();

        imageAdapter = new ImageAdapter(getContext(),imageArrayList);
        commentListAdapter = new CommentListAdapter(getContext(),commentArrayList);

        ListView commentList = view.findViewById(R.id.commentList);
        commentList.setAdapter(commentListAdapter);

        GridView grid = view.findViewById(R.id.thumbnailGrid);
        grid.setAdapter(imageAdapter);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.dosave(imageArrayList,commentArrayList);

            }
        });

        ImageButton upload = view.findViewById(R.id.addImage);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showPictureDialog();
            }
        });

        Button commentButton = view.findViewById(R.id.addComment);
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showAddCommentPopup();
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


    }




//    private void takePhotoFromCamera() {
//        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, CAMERA);
//    }


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


    //pop up picture and properties
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
                SurveyImages i = new SurveyImages();
                i.setDescription(imageDes.getText().toString());
                i.setUri(uri.toString());
                i.setUploaded(Constant.SURVEYNOTUPLOADED);
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


    //pop up picture and properties
    public void showAddCommentPopup(){


        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this.getActivity());
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_comment_popup_layout, null);
        dialogBuilder.setView(dialogView);

        final EditText risk = dialogView.findViewById(R.id.risk);
        final EditText achievement = dialogView.findViewById(R.id.achievement);
        final EditText activity = dialogView.findViewById(R.id.activity);
        Button addButton = dialogView.findViewById(R.id.addComment);


        final AlertDialog alertDialog = dialogBuilder.create();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SurveyComent comment = new SurveyComent();
                comment.setRisk(risk.getText().toString());
                comment.setAchievement(achievement.getText().toString());
                comment.setActivity(activity.getText().toString());
                comment.setStatus(1);
                commentArrayList.add(comment);
                commentListAdapter.notifyDataSetChanged();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
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
}
