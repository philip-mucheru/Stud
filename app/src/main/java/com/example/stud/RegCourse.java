package com.example.stud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;



public class RegCourse extends AppCompatActivity {

    EditText unitName, unitCode;
    Button regCourseBtn, viewCourseBtn;
    FirebaseFirestore fStote;
    String unit_name, unit_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_course);

        //intialize
        unitName = findViewById(R.id.unitName);
        unitCode = findViewById(R.id.unitCode);
        regCourseBtn = findViewById(R.id.regCourseBtn);
        viewCourseBtn = findViewById(R.id.viewCourseBtn);
        fStote = FirebaseFirestore.getInstance();

        // set click lisnter
        regCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check whether valid

                //getting data from edit text fields
                unit_code = unitCode.getText().toString();
                unit_name = unitName.getText().toString();
                // validating the text fields if empty or not
                if(TextUtils.isEmpty(unit_code)){
                    unitCode.setError("Please enter course code");
                } else if(TextUtils.isEmpty(unit_name)){
                    unitName.setError("Please enter course name");
                } else {
                    // If both fields are non-empty, add the course
                    addCourse(unit_code, unit_name);
                }

                // add intent

            }
        });
        // click listner for view course
        viewCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ViewCourse.class));
                finish();
            }
        });

    }
    private  void addCourse(String unit_code, String unit_name){
        // creating a collection reference for our Firebase Firestore database.
        CollectionReference dbCourses = fStote.collection("Course");
        //adding our data to our data object class
        AddCourse addCourses = new AddCourse(unit_code,unit_name);
        // below method is use to add data to Firebase Firestore.
        dbCourses.add(addCourses).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // after the data addition is successful
                // we are displaying a success toast message.
                Toast.makeText(RegCourse.this, "Your Course has been added successfully", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(RegCourse.this, "Failed to add course \n" + e, Toast.LENGTH_SHORT).show();

            }
        });
    }
}