package com.example.fooddonationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;


public class FoodRequestActivity extends AppCompatActivity implements View.OnClickListener {
    EditText selectTime, Food, Packet, selectDate;
    DatePicker picker;
    Button SubmitBtn;
    DatePickerDialog datePickerDialog;
    private int mHour;
    private int mMinute;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_request);
        selectTime = findViewById(R.id.Ftime);
        selectDate = findViewById(R.id.Fdate);


        selectTime.setOnClickListener(this);
        SubmitBtn = findViewById(R.id.submitbtn);
        Food = findViewById(R.id.fooditem);
        Packet = findViewById(R.id.Food_Packets);
        // getting our instance
        // from Firebase Firestore.
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        Log.d("value of x is ", String.valueOf(userID));

        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FoodItem = Food.getText().toString().trim();
                String packet = Packet.getText().toString().trim();
                String TimeLimit = selectTime.getText().toString().trim();
                String Datelimit = selectDate.getText().toString().trim();
                String Uid = userID;

                String status = "new";
                if (TextUtils.isEmpty(FoodItem)) {
                    Food.setError("Food item is Required.");
                    return;
                }

                if (TextUtils.isEmpty(packet)) {
                    Packet.setError("No. of packets is Required.");
                    return;
                }

                if (TextUtils.isEmpty(TimeLimit)) {
                    selectTime.setError("Time limit is required");
                    return;
                }
                // calling method to add data to Firebase Firestore.
                addDataToFirestore(FoodItem, packet, TimeLimit,Datelimit, Uid);


            }

            private void addDataToFirestore(String fooditem, String nopackets, String timeduration,String datelimit, String ngoid) {

                // creating a collection reference
                // for our Firebase Firetore database.
                CollectionReference dbfood = fStore.collection("Food-Details");

                // adding our data to our courses object class.
                FoodDetails fd = new FoodDetails(fooditem, nopackets, timeduration,datelimit, ngoid);

                // below method is use to add data to Firebase Firestore.
                dbfood.add(fd).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // after the data addition is successful
                        // we are displaying a success toast message.
                        Toast.makeText(FoodRequestActivity.this, "Your Request has been Sent", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // this method is called when the data addition process is failed.
                        // displaying a toast message when data addition is failed.
                        Toast.makeText(FoodRequestActivity.this, "Fail to Sent Request \n" + e, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // perform click event on edit text
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(FoodRequestActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                selectDate.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }



        public void onClick (View v){
            if (v == selectTime) {
                //get time
                final Calendar calendar = Calendar.getInstance();

                //launch picker dialog
                final TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //am pm mode
                        String AM_PM;
                        if (hourOfDay >= 0 && hourOfDay < 12) {
                            AM_PM = " AM";
                        } else {
                            AM_PM = " PM";
                        }
                        selectTime.setText(hourOfDay + ":" + minute + "" + AM_PM);
                    }
                }, mHour, mMinute, false);

                timePickerDialog.show();
            }
        }
    }
