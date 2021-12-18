package com.example.fooddonationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VolunteerHomeActivity extends AppCompatActivity {
    CardView donor,ngo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_home);
        donor = findViewById(R.id.cardDonor);
        ngo = findViewById(R.id.cardNgo);


        donor.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Donor Requests
                Intent intent = new Intent(getApplicationContext(), DonorRequestActivity.class);
                startActivity(intent);
            }
        });

        ngo.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ngo Requests
                Intent intent = new Intent(getApplicationContext(), NgoRequestActivity.class);
                startActivity(intent);
            }
        });

    }
}