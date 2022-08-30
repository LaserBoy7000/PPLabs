package com.labs.student;

import java.sql.Date;

public class Student {
    int ID;
    String Surname;
    String Name;
    String Patronymic;
    Date Birthday;
    String Adress;
    String Phone;
    String Faculty;
    int Course;
    int Group;
    
    public Student(int iD, String surname, String name, String patronymic, Date birthday, String adress, String phone, String faculty,
            int course, int group) {
        ID = iD;
        Surname = surname;
        Patronymic = patronymic;
        Name = name;
        Birthday = birthday;
        Adress = adress;
        Phone = phone;
        Faculty = faculty;
        Course = course;
        Group = group;
    }
    

    public int getGroup() {
        return Group;
    }
    public void setGroup(int group) {
        Group = group;
    }
    //
    //
    public int getCourse() {
        return Course;
    }
    public void setCourse(int course) {
        Course = course;
    }
    //
    //
    public String getAdress() {
        return Adress;
    }
    public void setAdress(String adress) {
        Adress = adress;
    }
    //
    //
    public String getPhone() {
        return Phone;
    }
    public void setPhone(String phone) {
        Phone = phone;
    }
    //
    //
    public String getPatronymic() {
        return Patronymic;
    }
    public void setPatronymic(String patronymic) {
        Patronymic = patronymic;
    }
    //
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
