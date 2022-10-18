package com.labs.core.entity;

public class Exemption {
    private int ID;
    private String Formula;
    private String Title;

    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }

    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }
    
    public String getFormula() {
        return Formula;
    }
    public void setFormula(String formula) {
        Formula = formula;
    }

    boolean isApplyable(Tax tax, Income income){
        return false;
    }

    boolean isApplyable(User user){
        return false;
    }

    double calculate(Income income){
        return 0.0;
    }
}
