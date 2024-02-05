package com.example.stud;

public class AddCourse {
    // variables for storing our data
    private String unit_code, unit_name;
    // empty constructor
    public AddCourse(){    }
    //constructor for all variables
    public AddCourse(String unit_code, String unit_name){
        this.unit_code = unit_code;
        this.unit_name = unit_name;
    }
    //getter methods for all variables
    public String getUnit_code(){
        return  unit_code;
    }
    public String getUnit_name(){
        return unit_name;
    }
    // setter methods for all variables
    public  void  setUnit_code(String unit_code){
        this.unit_code = unit_code;
    }
    public void setUnit_name(String unit_name){
        this.unit_name = unit_name;
    }

}
