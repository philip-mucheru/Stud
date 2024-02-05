package com.example.stud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    Button login, createAccount;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    EditText username, apassword;
    FirebaseFirestore fStore;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = fAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), DashboardAdmin.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);
        username = findViewById(R.id.username);
        apassword = findViewById(R.id.password);
        createAccount = findViewById(R.id.createAccountBtn);
        // set on click listener
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = String.valueOf(username.getText());
                password = String.valueOf(apassword.getText());


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login.this, "ENTER EMAIL", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "ENTER PASSWORD", Toast.LENGTH_SHORT).show();
                }
                fAuth.signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(Login.this, "LOG IN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                                checkUserAdmin(authResult.getUser().getUid());
                                checkUserStudent(authResult.getUser().getUid());
                            }

                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login.this, "LOG IN FAILED", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });



        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });
    }
    private void checkUserAdmin(String uid){
        DocumentReference df = fStore.collection("Admin").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "onSuccess:" + documentSnapshot.getData());
                if(documentSnapshot.getString("isAdmin") != null ){
                    //user is admin
                    startActivity(new Intent(getApplicationContext(), DashboardAdmin.class));
                    finish();
                }
            }
        });
    }
    private void checkUserStudent(String uid){
        DocumentReference df = fStore.collection("Student").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "onSuccess:" + documentSnapshot.getData());
                if(documentSnapshot.getString("isStudent") != null ){
                    //user is admin
                    startActivity(new Intent(getApplicationContext(), DashboardStudent.class));
                    finish();
                }
            }
        });
    }
}