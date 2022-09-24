package com.example.carousellibrary;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupBtn = findViewById(R.id.signUpButton);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRadioButtonClicked(view);
//                Intent intent = new Intent(view.getContext(),HomeClient.class);
//                startActivity(intent);
                //create validity method outside oncra
                //CHECK IF ALL FIELDS ARE FILLED
                String email = ((EditText)findViewById(R.id.signUpEmail)).getText().toString();
                String name = ((EditText)findViewById(R.id.signUpName)).getText().toString();
                String DOB = ((EditText)findViewById(R.id.signUpDob)).getText().toString();
                String contactNumber = ((EditText)findViewById(R.id.signUpPhone)).getText().toString();
                String password = ((EditText)findViewById(R.id.signUpPw)).getText().toString();
               System.out.println(NID);
//                if (email.isEmpty()) {
//                    ((EditText)findViewById(R.id.signUpEmail)).setError("Email cant be empty");
//                    return;
//                }
                //check if furmommy hoy NID jate empty na thake
                // cnofirm pass = pass
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
                        } catch (Exception e){
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
        });
    }

    public void onRadioButtonClicked(View view) {

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioButton);
        int radioButtonId = rg.getCheckedRadioButtonId();
        System.out.println(radioButtonId);
        RadioButton rb = (RadioButton) rg.findViewById(radioButtonId);
        adminType = (String) rb.getText();

        EditText NIDEt = (EditText) findViewById(R.id.signUpNID);
        if(adminType.equals("Fur Mommy")) {
            NIDEt.setVisibility(View.VISIBLE);
            NID = ((EditText)findViewById(R.id.signUpNID)).getText().toString();
        } else {
            NIDEt.setVisibility(View.GONE);
            NID = null;
        }
    }
}