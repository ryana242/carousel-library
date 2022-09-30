package com.example.carousellibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private Button loginBtn;
    private TextView signUp;
    private TextView forgotPass;

    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences settings = getApplicationContext().getSharedPreferences("localStorage", 0);
        if (settings.contains("userUNID")) {
            Intent myIntent = new Intent(LoginActivity.this, HomeClient.class);
            startActivity(myIntent);
        }
        loginBtn = findViewById(R.id.loginButton);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = ((EditText) findViewById(R.id.loginEmail)).getText().toString();
                password = ((EditText) findViewById(R.id.loginPassword)).getText().toString();

                if (email.isEmpty()) {
                    ((EditText) findViewById(R.id.loginEmail)).setError("Email cannot be empty");
                    return;
                }
                if (password.isEmpty()) {
                    ((EditText) findViewById(R.id.loginPassword)).setError("Password cannot be empty");
                    return;
                }

                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("email", email);
                    jsonBody.put("password", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                        SplashActivity.baseURL + "/auth/login", jsonBody, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Aise");

                        try {
                            System.out.println(response.getString("error").toString());
                            if (response.getString("error").toString().isEmpty()) {
                                System.out.println(response.getJSONObject("data").getString("userUNID"));
                                Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                SharedPreferences settings = getApplicationContext().getSharedPreferences("localStorage", 0);
                                SharedPreferences.Editor editor = settings.edit();
                                editor.putString("userUNID", response.getJSONObject("data").getString("userUNID"));
                                editor.apply();
                                Intent myIntent = new Intent(LoginActivity.this, HomeClient.class);
                                startActivity(myIntent);
                            } else {
                                Toast.makeText(LoginActivity.this, response.getString("error").toString(), Toast.LENGTH_SHORT).show();
                            }
//                            System.out.println(response.getString("data"));
//                            System.out.println(response.getString("error"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(LoginActivity.this, "DHUKBE", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "DHUKBENA", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });
                jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                        5000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                MySingleton.getInstance(LoginActivity.this).addToRequestQueue(jsonObjectRequest);
//                Intent intent = new Intent(view.getContext(),HomeClient.class);
//                startActivity(intent);
            }
        });

        signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });

        forgotPass = findViewById(R.id.forgetPass);
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),ForgotPassword.class);
                startActivity(intent);
            }
        });
    }
}