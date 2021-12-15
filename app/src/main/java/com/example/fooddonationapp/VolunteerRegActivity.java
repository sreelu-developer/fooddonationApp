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

public class VolunteerRegActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText VolName,VolEmail,VolPassword,VolPhone,VolAdress;
    Button RegisterBtn;
    ImageView VolProfile,VolDoc;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_reg);

        VolName = findViewById(R.id.name);
        VolEmail = findViewById(R.id.email);
        VolPassword = findViewById(R.id.password);
        VolPhone = findViewById(R.id.phone);
        VolAdress =findViewById(R.id.adress);
        RegisterBtn =findViewById(R.id.register);

        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        if(fAuth.getCurrentUser() !=null){

            Intent intent = new Intent(VolunteerRegActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        RegisterBtn.setOnClickListener(new View.OnClickListener ()
        {
            @Override
            public void onClick(View v)
            {
                String email = VolEmail.getText().toString().trim();
                String password= VolPassword.getText().toString().trim();
                String name= VolName.getText().toString().trim();
                String phone= VolPhone.getText().toString().trim();
                String adress= VolAdress.getText().toString().trim();
                String usertype="VOLUNTEER";


                if(TextUtils.isEmpty(email))
                {
                    VolEmail.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password))
                {
                    VolPassword.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6)
                {
                    VolPassword.setError("Password Must be >=6 Characters");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(VolunteerRegActivity.this, "User Created.", Toast.LENGTH_SHORT) .show();
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
                                    Toast.makeText(VolunteerRegActivity.this, "Registered Successfully.", Toast.LENGTH_SHORT) .show();
                                }
                            });
                            //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            Intent intent = new Intent(VolunteerRegActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(VolunteerRegActivity.this, "Error!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });



    }
}