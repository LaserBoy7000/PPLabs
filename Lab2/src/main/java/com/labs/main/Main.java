package com.labs.main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.labs.student.*;
/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        List<Student> data = GenStudents(50);
        System.out.println("\n-----All students-----\n\n");
        for(int i = 0; i < data.size(); i++)
            System.out.println(data.get(i).ToString()+"\n");

        System.out.println("\n\n-----Of faculty 'B'-----\n\n");
        List<Student> selection = SelectByFaculty(data, "B");
        for(int i = 0; i < selection.size(); i++)
            System.out.println(selection.get(i).ToString()+"\n");
        
        System.out.println("\n\n-----Born after 2002-----\n\n");
        selection = SelectBornAfter(data, 2002);
        for(int i = 0; i < selection.size(); i++)
            System.out.println(selection.get(i).ToString()+"\n");
        
        System.out.println("\n\n-----Of Group 302-----\n\n");
        selection = SelectByGroup(data, 2, 3);
        for(int i = 0; i < selection.size(); i++)
            System.out.println(selection.get(i).ToString()+"\n");
    }

    static List<Student> GenStudents(int n){
        GregorianCalendar c = new GregorianCalendar();
        c.set(2000, 1, 1);
        Date o = c.getTime();
        c.set(2005, 1, 1);
        Date t =  c.getTime();
        StudentGenerator gn = new StudentGenerator(1, 4, o, t, Arrays.asList("A", "B", "C"), 1, 3);
        return gn.GetN(n, 0);
    }

    static public List<Student> SelectByFaculty(List<Student> list, String faculty){
        List<Student> res = new ArrayList<>();
        for (Student std : list) {
            if(std.getFaculty() == faculty)
                res.add(std);
        }
        return res;
    }

    static public List<Student> SelectBornAfter(List<Student> list, int y){
        List<Student> res = new ArrayList<>();
        Calendar c = GregorianCalendar.getInstance();
        c.set(y,1,1);
        for (Student std : list)
            if(std.getBirthday().compareTo(c.getTime()) > 0)
                res.add(std);
        return res;
    }

    static public List<Student> SelectByGroup(List<Student> list, int group, int course) {
        List<Student> res = new ArrayList<>();
        for (Student std : list) {
            if(std.getGroup() == group && std.getCourse() == course)
                res.add(std);
        }
        return res;
    }
}
