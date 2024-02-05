package com.example.stud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegStudent extends AppCompatActivity {

    EditText fName, mName, lName, sEmail, regNo, yob;
    Button regBtn;
    boolean valid = true;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_student);

        fName = findViewById(R.id.stufname);
        mName = findViewById(R.id.stumname);
        lName = findViewById(R.id.stulname);
        sEmail = findViewById(R.id.stuEmail);
        regNo = findViewById(R.id.stuRegNo);
        yob = findViewById(R.id.stuYOB);
        regBtn = findViewById(R.id.regStubtn);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkField(fName);
                checkField(mName);
                checkField(lName);
                checkField(sEmail);
                checkField(regNo);
                checkField(yob);


                if (valid) {
                    fAuth.createUserWithEmailAndPassword(sEmail.getText().toString(), regNo.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser admin = fAuth.getCurrentUser();
                            Toast.makeText(RegStudent.this, "Account created", Toast.LENGTH_SHORT).show();
                            DocumentReference df = fStore.collection("Student").document(admin.getUid());
                            Map<String, Object> userInfo = new HashMap<>();
                            userInfo.put("Firstname", fName.getText().toString());
                            userInfo.put("MiddleName", mName.getText().toString());
                            userInfo.put("LastName", lName.getText().toString());
                            userInfo.put("Email", sEmail.getText().toString());
                            userInfo.put("RegNo", regNo.getText().toString());
                            userInfo.put("YOB", yob.getText().toString());
                            // specify if user is student
                            userInfo.put("isStudent", "1");
                            df.set(userInfo);

                            startActivity(new Intent(getApplicationContext(), DashboardAdmin.class));

                            finish();
                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegStudent.this, "Failed to create account", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }



    public boolean checkField(EditText textField) {
        if (textField.getText().toString().isEmpty()) {
            textField.setError("Error");
            valid = false;
        } else {
            valid = true;
        }

        return valid;
    }
}