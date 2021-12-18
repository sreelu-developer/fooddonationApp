package com.example.fooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DonorDetailsActivity extends AppCompatActivity {
    Button status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_details);
        status = findViewById(R.id.DonorStatus);

        status.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //View Status
                Intent intent = new Intent(getApplicationContext(), ViewStatusActivity.class);
                startActivity(intent);

            }
        });
    }
}