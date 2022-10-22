package com.example.carousellibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public class HomeClient extends AppCompatActivity {
    private Button petFeedBtn;
    private Button inboxBtn;
    private Button findBtn;
    private Button logoutBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_client);

        SharedPreferences settings = getApplicationContext().getSharedPreferences("localStorage", 0);
//        if (!settings.contains("userUNID")){
//            Intent myIntent = new Intent(HomeClient.this, LoginActivity.class);
//            startActivity(myIntent);
//        }

        ImageCarousel carousel = findViewById(R.id.carousel);

        // Register lifecycle. For activity this will be lifecycle/getLifecycle() and for fragments
        // it will be viewLifecycleOwner/getViewLifecycleOwner().
        carousel.registerLifecycle(getLifecycle());

        List<CarouselItem> list = new ArrayList<>();

        list.add(
                new CarouselItem(
                        R.drawable.carousel_1
                )
        );

        list.add(
                new CarouselItem(
                        R.drawable.carousel_2
                )
        );

        list.add(
                new CarouselItem(
                        R.drawable.carousel_3
                )
        );

        list.add(
                new CarouselItem(
                        R.drawable.carousel_5
                )
        );

        list.add(
                new CarouselItem(
                        R.drawable.carousel_6
                )
        );

        carousel.setAutoPlay(true);
        carousel.setAutoPlayDelay(3000);

        carousel.setData(list);

//        logoutBtn = (Button) findViewById(R.id.logoutBtn);
//        logoutBtn.setOnClickListener(new View.OnClickListener() {

//            @Override
//            public void onClick(View view) {
//                SharedPreferences settings = getApplicationContext().getSharedPreferences("localStorage", 0);
//                SharedPreferences.Editor editor = settings.edit();
//                System.out.println(settings.contains("userUNID"));
//                editor.remove("userUNID");
//                editor.apply();
//                Intent myIntent = new Intent(HomeClient.this, LoginActivity.class);
//                startActivity(myIntent);
//                finish();
//            }
//        });
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
