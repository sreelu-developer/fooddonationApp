package com.example.fooddonationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;



public class MainActivity extends AppCompatActivity  {
    TextView mRegisterBtn;
    EditText email;
    EditText password;
    Button login;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userType;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRegisterBtn = findViewById(R.id.register);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);


        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString().trim();
                String pword= password.getText().toString().trim();

                if(TextUtils.isEmpty(mail))
                {
                    email.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(pword))
                {
                    password.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6)
                {
                    password.setError("Password Must be >=6 Characters");
                    return;
                }

                //authenticate the user
                fAuth.signInWithEmailAndPassword(mail,pword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if(task.isSuccessful())
                        {


                            userID = fAuth.getCurrentUser().getUid();
                            Log.d("value of x is ", String.valueOf(userID));

                            DocumentReference reference;
                            reference=fStore.collection("users").document(userID);
                            reference.get()
                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            DocumentSnapshot document = task.getResult();
                                             userType = document.getString("usertype");
                                            Log.d("value of utype is ", String.valueOf(userType));


                                        }
                                    });

                                 if("NGO".equals(userType))
                                  {
                                     Toast.makeText(MainActivity.this, "Logged in Successfully.", Toast.LENGTH_SHORT).show();
                                     Intent intent = new Intent(MainActivity.this, NgoHomeActivity.class);
                                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                     startActivity(intent); }
                            else if("DONOR".equals(userType))
                            {
                                Toast.makeText(MainActivity.this, "Logged in Successfully.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, UserlistActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }

                            else if("VOLUNTEER".equals(userType))
                            {
                                Toast.makeText(MainActivity.this, "Logged in Successfully.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, UserlistActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent); }
                            else
                                {
                                     Toast.makeText(MainActivity.this, "Error!.", Toast.LENGTH_SHORT).show();
                                }




                        }
                        else
                            {
                            Toast.makeText(MainActivity.this, "Error! " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });




        mRegisterBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), UserlistActivity.class);
                startActivity(intent);
            }
        });


    }

}