package com.example.fooddonationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserlistActivity extends AppCompatActivity {
    CardView donor,volunteer,ngo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        donor = findViewById(R.id.cardDonor);
        volunteer = findViewById(R.id.cardVolunteer);
        ngo = findViewById(R.id.cardNgo);


        donor.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), DonorRegActivity.class);
                startActivity(intent);
            }
        });
        volunteer.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), VolunteerRegActivity.class);
                startActivity(intent);
            }
        });
        ngo.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), NgoRegActivity.class);
                startActivity(intent);
            }
        });

    }
}