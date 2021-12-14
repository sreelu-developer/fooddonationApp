package com.example.fooddonationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;



public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
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

        Spinner spinner = (Spinner) findViewById(R.id.userspinner);
        spinner.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.user_array, android.R.layout.simple_spinner_item);
         // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

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

                            DocumentReference reference;
                            reference=fStore.collection("users").document(userID);
                            reference.get()
                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            DocumentSnapshot document = task.getResult();
                                            userType = document.getString("usertype");


                                        }
                                    });

                                 if(userType.equals("NGO"))
                                  {
                                     Toast.makeText(MainActivity.this, "Logged in Successfully.", Toast.LENGTH_SHORT).show();
                                     Intent intent = new Intent(MainActivity.this, NgoHomeActivity.class);
                                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                     startActivity(intent); }


                       }
                        else{
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
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        userType = parent.getItemAtPosition(pos).toString();
        Toast.makeText(parent.getContext(), "Selected: " + userType, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}