package com.example.carousellibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class SignUpActivity extends AppCompatActivity {
    private Button signupBtn;
    private String adminType = null;
    private String NID = null;
    private RadioGroup rg = null;
    private RadioButton rb = null;

    private String email, name, DOB, contactNumber, password, confirmPassword;
    private int radioButtonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupBtn = findViewById(R.id.signUpButton);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRadioButtonClicked(view);

                email = ((EditText) findViewById(R.id.signUpEmail)).getText().toString();
                name = ((EditText) findViewById(R.id.signUpName)).getText().toString();
                DOB = ((EditText) findViewById(R.id.signUpDob)).getText().toString();
                contactNumber = ((EditText) findViewById(R.id.signUpPhone)).getText().toString();
                password = ((EditText) findViewById(R.id.signUpPw)).getText().toString();
                confirmPassword = ((EditText) findViewById(R.id.signUpConfirmPw)).getText().toString();

                if (isSignUpValid()) {
                    JSONObject jsonBody = new JSONObject();
                    try {
                        jsonBody.put("email", email);
                        jsonBody.put("password", password);
                        jsonBody.put("name", name);
                        jsonBody.put("dateOfBirth", DOB);
                        jsonBody.put("contactNumber", contactNumber);
                        jsonBody.put("actorType", adminType);
                        jsonBody.put("NID", NID);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    System.out.println(adminType);

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                            SplashActivity.baseURL + "/auth/register", jsonBody, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println("AISE");

                            try {
                                if (response.getString("error").toString().isEmpty()) {
                                    Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                                    Intent myIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                                    startActivity(myIntent);
                                } else {
                                    Toast.makeText(SignUpActivity.this, response.getString("error").toString(), Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(SignUpActivity.this, "DHUKBENA", Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        }
                    });
                    jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                            5000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    MySingleton.getInstance(SignUpActivity.this).addToRequestQueue(jsonObjectRequest);
                }

            }
        });
    }

    @SuppressLint("ResourceType")
    public boolean isSignUpValid() {
        if (email.isEmpty()) {
            ((EditText) findViewById(R.id.signUpEmail)).setError("Email cannot be empty");
            return false;
        }
        if (name.isEmpty()) {
            ((EditText) findViewById(R.id.signUpName)).setError("Name cannot be empty");
            return false;
        }
        if (DOB.isEmpty()) {
            ((EditText) findViewById(R.id.signUpDob)).setError("Date of birth cannot be empty");
            return false;
        }
        if (contactNumber.isEmpty()) {
            ((EditText) findViewById(R.id.signUpPhone)).setError("Contact Number cannot be empty");
            return false;
        }
        if (password.isEmpty()) {
            ((EditText) findViewById(R.id.signUpPw)).setError("Password cannot be empty");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            ((EditText) findViewById(R.id.signUpConfirmPw)).setError("Password does not match");
            return false;
        }

        if (rb != null) {
            if(adminType.equals("FurMommy")) {
                if (NID.isEmpty()) {
                    ((EditText) findViewById(R.id.signUpNID)).setError("NID cannot be empty");
                    return false;
                }
            }
        } else {
            RadioButton lastRadiobtn = (RadioButton) findViewById(R.id.radioFurMommy);
            if(rg.getCheckedRadioButtonId() <= 0) {
                lastRadiobtn.setError("Select an option");
                return false;
            }
        }
        return true;
    }

    public void onRadioButtonClicked(View view) {

        rg = (RadioGroup) findViewById(R.id.radioButton);
        radioButtonId = rg.getCheckedRadioButtonId();
        System.out.println(radioButtonId);
        rb = (RadioButton) rg.findViewById(radioButtonId);
        if (rb != null) {
            adminType = (String) rb.getText();
            EditText NIDEt = (EditText) findViewById(R.id.signUpNID);
            if (adminType.equals("Fur Mommy")) {
                NIDEt.setVisibility(View.VISIBLE);
                NID = ((EditText) findViewById(R.id.signUpNID)).getText().toString();
            } else {
                NIDEt.setVisibility(View.GONE);
                NID = null;
            }
        }
    }
}