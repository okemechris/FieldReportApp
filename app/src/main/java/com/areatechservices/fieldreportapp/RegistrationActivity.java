package com.areatechservices.fieldreportapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.areatechservices.fieldreportapp.Models.User;

public class RegistrationActivity extends AppCompatActivity {

    EditText editTextName, editTextEmail,editTextCPassword, editTextPassword;
    Button register;
    private ProgressDialog dialog;
    RoomDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        db = new RoomDatabase(getApplicationContext());
        editTextName = findViewById(R.id.name);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextCPassword = findViewById(R.id.cPassword);

        register = findViewById(R.id.registerBtn);

        dialog = new ProgressDialog(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerUser();
            }
        });



    }


    private void registerUser() {
        final String name = editTextName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String cPassword = editTextCPassword.getText().toString().trim();


        //first we will do the validations

        if (TextUtils.isEmpty(name)) {
            editTextName.setError("Please enter username");
            editTextName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please enter your email");
            editTextEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Enter a password");
            editTextPassword.requestFocus();
            return;
        }

        if (!password.equals(cPassword)) {
            editTextCPassword.setError("password must match");
            editTextCPassword.requestFocus();
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                User user = new User();
                user.setEmail(email);
                user.setName(name);
                user.setPassword(password);
                user.setStatus(0);

                db.getSurveyDatabase().daoAccess().insertUser(user);
                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//
            }}).start();
        //        dialog.show();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiUrls.URL_REGISTER,
//                new Response.Listener<String>() {
//
//                    @Override
//                    public void onResponse(String response) {
//                        //progressBar.setVisibility(View.GONE);
//                        dialog.dismiss();
//
//                        try {
//                            //converting response to json object
//                            JSONObject obj = new JSONObject(response);
//
//                            //if no error in response
//                            if (!obj.getBoolean("error")) {
//                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//
//                                //getting the user from the response
//                                JSONObject userJson = obj.getJSONObject("user");
//
//                                //creating a new user object
//                                User user = new User(
//                                        userJson.getInt("id"),
//                                        userJson.getString("name"),
//                                        userJson.getString("email")
//                                );
//
//                                //storing the user in shared preferences
//                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
//
//                                //starting the profile activity
//                                finish();
//                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                            } else {
//                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("name", name);
//                params.put("email", email);
//                params.put("password", password);
//                params.put("cpassword", cPassword);
//                return params;
//            }
//        };
//
//        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }
}
