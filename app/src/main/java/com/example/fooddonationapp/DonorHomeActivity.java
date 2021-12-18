package com.example.fooddonationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DonorHomeActivity extends AppCompatActivity {
    CardView ngo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_home);
        ngo = findViewById(R.id.cardNgo);

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