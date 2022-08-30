package com.labs.student;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentGenerator {
    int GroupLow;
    int GroupHigh; 
    Date BirthayLow; 
    Date BirthdayHigh;
    List<String> Faculties;
    int CourseLow;
    int CourseHigh;
    Random rd;

    public StudentGenerator(int groupLow, int groupHigh, Date date, Date date2, List<String> faculties, int courseLow, int courseHigh){
        GroupLow = groupLow;
        GroupHigh = groupHigh;
        BirthayLow = date;
        BirthdayHigh = date2; 
        Faculties = faculties;
        CourseLow = courseLow;
        CourseHigh = courseHigh; 
        rd = new Random(System.currentTimeMillis());
    }

    public List<Student> GetN(int n, int lowID){
        ArrayList ls = new ArrayList<Student>();
        for(int i = 0; i < n; i++){
            
            String[] strs = new String[4];

            for(int r = 0; r < 4; r++){
                strs[r] = RandString(5, 12);
            }

            int cr = rd.nextInt(CourseLow, CourseHigh+1);
            int g = rd.nextInt(GroupLow, GroupHigh+1);

            Student obj = new Student(++lowID, strs[0], strs[1], strs[2], RandDate(), strs[3], RandPhone("+380"), RandFaculty(), cr, g);
            ls.add(obj);
        }
        return ls;
    }

    String RandString(int lghL, int lghH){
        String ret = new String();
        for(int j = 0; j < rd.nextInt(10,16); j++){
            char sym = (char)rd.nextInt('A', 'Z');
            ret += sym;
        }
        return ret;
    }

    String RandPhone(String code){
        String ph = new String();
        ph += code;
        for(int r = 0; r < 9; r++){
            int nm = rd.nextInt(0, 10);
            ph += (char)(nm+'0');
        }
        return ph;
    }

    Date RandDate(){
        long rdat = rd.nextLong(BirthayLow.getTime(), BirthdayHigh.getTime());
        return new Date(rdat);
    }

    String RandFaculty(){
        return Faculties.get(rd.nextInt(Faculties.size()));
    } 
}