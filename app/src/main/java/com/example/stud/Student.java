package com.example.stud;

public class Student {
    // variables for storing our data
    private String Firstname, MiddleName, LastName, Email, RegNo, YOB;
    // empty constructor
    public Student(){    }
    //constructor for all variables
    public Student(String fname, String mname, String lname, String email, String reg, String yob){

        this.Firstname = fname;
        this.MiddleName = mname;
        this.LastName = lname;
        this.Email = email;
        this.RegNo = reg;
        this.YOB = yob;
    }
    //getter methods for all variables


    public String getFirstname() {
        return Firstname;
    }



    public String getMiddleName() {
        return MiddleName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return Email;
    }

    public String getRegNo() {
        return RegNo;
    }

    public String getYob() {
        return YOB;
    }


    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setRegNo(String regNo) {
        RegNo = regNo;
    }

    public void setYOB(String YOB) {
        this.YOB = YOB;
    }
}
