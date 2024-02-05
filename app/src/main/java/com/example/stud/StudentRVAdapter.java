package com.example.stud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentRVAdapter extends RecyclerView.Adapter<StudentRVAdapter.ViewHolderh> {
    // creating variables for our ArrayList and context
    private ArrayList<Student> coursesArrayList;
    private Context context;

    // creating constructor for our adapter class
    public StudentRVAdapter(ArrayList<Student> coursesArrayList, Context context) {
        this.coursesArrayList = coursesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentRVAdapter.ViewHolderh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new ViewHolderh(LayoutInflater.from(context).inflate(R.layout.student_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentRVAdapter.ViewHolderh holder, int position) {
        // setting data to our text views from our modal class.
        Student student = coursesArrayList.get(position);

        holder.fnameTV.setText(student.getFirstname());
        holder.mnameTV.setText(student.getMiddleName());
        holder.lnameTV.setText(student.getLastName());
        holder.emailTV.setText(student.getEmail());
        holder.regTV.setText(student.getRegNo());
        holder.yobTV.setText(student.getYob());


    }

    @Override
    public int getItemCount() {
        // returning the size of our array list.
        return coursesArrayList.size();
    }

    class ViewHolderh extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        private final TextView fnameTV;
        private final TextView mnameTV;
        private final TextView lnameTV;
        private final TextView emailTV;
        private final TextView regTV;
        private final TextView yobTV;


        public ViewHolderh(@NonNull View itemView) {
            super(itemView);
            // initializing our text views.
            fnameTV = itemView.findViewById(R.id.idTVFName);
            mnameTV = itemView.findViewById(R.id.idTVMName);
            lnameTV = itemView.findViewById(R.id.idTVLName);
            emailTV = itemView.findViewById(R.id.idTVEmail);
            regTV = itemView.findViewById(R.id.idTVRegNo);
            yobTV = itemView.findViewById(R.id.idTVYob);

        }
    }
}
