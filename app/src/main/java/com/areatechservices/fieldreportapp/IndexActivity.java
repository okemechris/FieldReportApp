package com.areatechservices.fieldreportapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IndexActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginButton;
    private Button createButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);



        loginButton = findViewById(R.id.signinBtn);
        createButton = findViewById(R.id.registerBtn);


        /**
         * set onclick listeners
         */
        loginButton.setOnClickListener(this);
        createButton.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){

            case R.id.signinBtn:
                intent = new Intent(this, SigninActivity.class);
                startActivity(intent);
                break;

            case R.id.registerBtn:
                intent = new Intent(this, RegistrationActivity.class);
                startActivity(intent);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //if the user is already logged in we will directly start the profile activity
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {

            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }
}
