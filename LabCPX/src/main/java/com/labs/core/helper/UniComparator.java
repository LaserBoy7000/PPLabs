package com.labs.core.helper;

public class UniComparator {
  
    public boolean le(Object first, Object second){
        if(first == null){
            if(second == null)
                return true;
            return false;
        }

        if(CanBeNumber(first) && CanBeNumber(second))
            return (double)first <= (double)second;

        return first.toString().compareTo(second.toString()) <= 0;
    }

    public boolean ge(Object first, Object second){
        if(first == null){
            if(second == null)
                return true;
            return false;
        }

        if(CanBeNumber(first) && CanBeNumber(second))
            return (double)first >= (double)second;

        return first.toString().compareTo(second.toString()) >= 0;
    }

    private boolean CanBeNumber(Object value){
        try 
        {
            value = (double)value;
            return true;
        } catch(Exception e) {}
        return false;
    }
}
