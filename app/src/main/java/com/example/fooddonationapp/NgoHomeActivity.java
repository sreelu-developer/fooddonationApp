package com.example.fooddonationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NgoHomeActivity extends AppCompatActivity {
    CardView CreateReq,ViewReq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_home);
        CreateReq = findViewById(R.id.createReq);
        ViewReq = findViewById(R.id.ViewReq);

        CreateReq.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), FoodRequestActivity.class);
                startActivity(intent);
            }
        });
        ViewReq.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), ViewFoodRequest.class);
                startActivity(intent);
            }
        });
    }
}