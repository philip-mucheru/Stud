package com.example.stud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    Button signupbtn;
    EditText adminFname, adminLname, adminEmail, adminPassword;
    boolean valid = true;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupbtn = findViewById(R.id.signupbtn);
        adminFname = findViewById(R.id.adminFname);
        adminLname = findViewById(R.id.adminLname);
        adminEmail = findViewById(R.id.adminemail);
        adminPassword = findViewById(R.id.adminpass);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        // on click lisnter
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkField(adminFname);
                checkField(adminLname);
                checkField(adminEmail);
                checkField(adminPassword);

                if(valid){
                    fAuth.createUserWithEmailAndPassword(adminEmail.getText().toString(), adminPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser admin = fAuth.getCurrentUser();
                            Toast.makeText(Signup.this, "Account created", Toast.LENGTH_SHORT).show();
                            DocumentReference df = fStore.collection("Admin").document(admin.getUid());
                            Map<String, Object> userInfo = new HashMap<>();
                            userInfo.put("Firstname", adminFname.getText().toString());
                            userInfo.put("Lastname", adminLname.getText().toString());
                            userInfo.put("Email", adminEmail.getText().toString());
                            userInfo.put("Password", adminPassword.getText().toString());
                            // specify if user is admin
                            userInfo.put("isAdmin", "1");
                            df.set(userInfo);

                            startActivity(new Intent(getApplicationContext(), DashboardAdmin.class));

                            finish();
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Signup.this, "Failed to create account", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

        });



    }
    public boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }

        return valid;
    }
}