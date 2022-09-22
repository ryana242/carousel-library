package com.example.carousellibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class SignUpActivity extends AppCompatActivity {
    private Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupBtn = findViewById(R.id.signUpButton);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),HomeClient.class);
                startActivity(intent);
            }
        });
    }

    public void onRadioButtonClicked(View view) {

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioButton);
        int radioButtonId = rg.getCheckedRadioButtonId();
        System.out.println(radioButtonId);
        RadioButton rb = (RadioButton) rg.findViewById(radioButtonId);
        String text = (String) rb.getText();

        EditText NID = (EditText) findViewById(R.id.signUpNID);
        if(text.equals("Fur Mommy")) {
            NID.setVisibility(View.VISIBLE);
        } else {
            NID.setVisibility(View.GONE);
        }
    }
}