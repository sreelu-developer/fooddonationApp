package com.example.fooddonationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DonorRequestActivity extends AppCompatActivity {
    Button status,contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_request);
        status = findViewById(R.id.statusbtn);
        contact = findViewById(R.id.contactbtn);

        status.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Update Status
                Intent intent = new Intent(getApplicationContext(), UpdateStatusActivity.class);
                startActivity(intent);

            }
        });

        contact.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Update Status
                Intent intent = new Intent(getApplicationContext(), DonorContactActivity.class);
                startActivity(intent);

            }
        });

    }

}