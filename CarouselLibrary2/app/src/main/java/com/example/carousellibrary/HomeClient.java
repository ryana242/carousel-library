package com.example.carousellibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeClient extends AppCompatActivity {
    private Button petFeedBtn;
    private Button inboxBtn;
    private Button findBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_client);
//
//        inboxBtn = findViewById(R.id.button);
//        inboxBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(),Inbox.class);
//                startActivity(intent);
//            }
//        });

//        petFeedBtn = findViewById(R.id.button);
//        petFeedBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(),HomeClient.class);
//                startActivity(intent);
//            }
//        });
    }
}