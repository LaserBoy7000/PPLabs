package com.labs.core.entity;
import java.util.Date;

public class Income {
    private int ID;
    private Date Date;
    private String Title;
    private double Value;
    private double ValueTaxed;
    private Exemption Exemption;

    public Income(){}
    public Income(java.util.Date date, String title, double value, double valueTaxed, Exemption exemption) {
        Date = date;
        Title = title;
        Value = value;
        ValueTaxed = valueTaxed;
        Exemption = exemption;
    }

    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }

    public Date getDate() {
        return Date;
    }
    public void setDate(Date date) {
        Date = date;
    }

    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }
    
    public double getValue() {
        return Value;
    }
    public void setValue(double value) {
        Value = value;
    }

    public double getValueTaxed() {
        return ValueTaxed;
    }
    public void setValueTaxed(double valueTaxed) {
        ValueTaxed = valueTaxed;
    }

    public Exemption getExemption() {
        return Exemption;
    }
    public void setExemption(Exemption exemption) {
        Exemption = exemption;
    }
}
