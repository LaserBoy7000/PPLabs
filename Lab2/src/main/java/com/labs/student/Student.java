package com.labs.student;

import java.sql.Date;

public class Student {
    int ID;
    String Surname;
    String Name;
    Date Birthday;
    String Adress;
    int Phone;
    String Faculty;
    int Course;
    int Group;
    
    public Student(int iD, String surname, String name, Date birthday, String adress, int phone, String faculty,
            int course, int group) {
        ID = iD;
        Surname = surname;
        Name = name;
        Birthday = birthday;
        Adress = adress;
        Phone = phone;
        Faculty = faculty;
        Course = course;
        Group = group;
    }
    
    //
    public String getFaculty() {
        return Faculty;
    }
    public void setFaculty(String faculty) {
        Faculty = faculty;
    }
    //
    //
    public Date getBirthday() {
        return Birthday;
    }
    //
    //
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    //
    //
    public String getSurname() {
        return Surname;
    }
    public void setSurname(String surname) {
        Surname = surname;
    }
    //
    //
    public int getID() {
        return ID;
    }
}
