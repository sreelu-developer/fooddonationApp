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
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NgoRegActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText ngoName,ngoEmail,ngoPassword,ngoPhone,ngoAdress;
    Button RegisterBtn;
    ImageView ngoProfile,ngoDoc;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_reg);

        ngoName = findViewById(R.id.name);
        ngoEmail = findViewById(R.id.email);
        ngoPassword = findViewById(R.id.password);
        ngoPhone = findViewById(R.id.phone);
        ngoAdress =findViewById(R.id.adress);
        RegisterBtn =findViewById(R.id.register);

        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser() !=null){

            Intent intent = new Intent(NgoRegActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        RegisterBtn.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v)
            {
                String email = ngoEmail.getText().toString().trim();
                String password= ngoPassword.getText().toString().trim();
                String name= ngoName.getText().toString().trim();
                String phone= ngoPhone.getText().toString().trim();
                String adress= ngoAdress.getText().toString().trim();
                String usertype="NGO";


                if(TextUtils.isEmpty(email))
                {
                    ngoEmail.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password))
                {
                    ngoPassword.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6)
                {
                    ngoPassword.setError("Password Must be >=6 Characters");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(NgoRegActivity.this, "User Created.", Toast.LENGTH_SHORT) .show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("name",name);
                            user.put("email",email);
                            user.put("phone",phone);
                            user.put("adress",adress);
                            user.put("password",password);
                            user.put("usertype",usertype);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG,"onSuccess: user Profile is created for "+ userID);
                                    Toast.makeText(NgoRegActivity.this, "Registered Successfully.", Toast.LENGTH_SHORT) .show();
                                }
                            });
                            //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            Intent intent = new Intent(NgoRegActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(NgoRegActivity.this, "Error!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}