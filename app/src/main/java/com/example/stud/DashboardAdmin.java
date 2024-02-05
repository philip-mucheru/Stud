package com.example.stud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardAdmin extends AppCompatActivity {

    Button regStudent, logout, regCourse;
    TextView textView;
    FirebaseAuth fAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);

        regStudent = findViewById(R.id.regStudent);
        regCourse = findViewById(R.id.regCourse);
        fAuth = FirebaseAuth.getInstance();
        textView = findViewById(R.id.user_details);
        user = fAuth.getCurrentUser();
        // set on click listner
        regStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdmin.this, RegStudent.class);
                startActivity(intent);
            }
        });
        logout = findViewById(R.id.logout);

        user = fAuth.getCurrentUser();
        if(user==null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            textView.setText(user.getEmail());
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        // on click listner for register course button
        regCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardAdmin.this, RegCourse.class);
                startActivity(intent);
            }
        });
    }
}