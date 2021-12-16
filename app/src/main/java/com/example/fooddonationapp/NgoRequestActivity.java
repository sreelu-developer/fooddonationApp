package com.example.fooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NgoRequestActivity extends AppCompatActivity {
    Button contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_request);
        contact = findViewById(R.id.NgoContact);

        contact.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ngo Contact
                Intent intent = new Intent(getApplicationContext(), NgoContactActivity.class);
                startActivity(intent);

            }
        });

    }
}