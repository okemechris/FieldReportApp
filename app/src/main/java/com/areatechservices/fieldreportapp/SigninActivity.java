package com.areatechservices.fieldreportapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SigninActivity extends AppCompatActivity {

    private ProgressDialog dialog;

    private Button signInButton;

    private EditText editTextEmail, editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);

        signInButton = findViewById(R.id.sign_in_button);
        dialog = new ProgressDialog(this);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                userLogin();

//                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                startActivity(intent);
            }
        });
    }


    private void userLogin() {
        //first getting the values
        final String email = editTextEmail.getText().toString();
        final String password = editTextPassword.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please enter your email");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        dialog.show();
        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiUrls.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dialog.dismiss();
                        System.out.println("error on response");
                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
                                JSONObject userJson = obj.getJSONObject("user");

                                //creating a new user object
                                User user = new User(
                                        userJson.getInt("id"),
                                        userJson.getString("name"),
                                        userJson.getString("email")
                                );

                                //storing the user in shared preferences
                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                                //starting the profile activity
                                finish();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        System.out.println("error on response");
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                System.out.println("auth error==========");
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }


        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
