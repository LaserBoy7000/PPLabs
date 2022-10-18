package com.labs.core.service;
import com.google.inject.ImplementedBy;
import com.labs.core.entity.*;
import com.labs.core.service.impl.Selector;

@ImplementedBy(Selector.class)
public interface ISelector {
    public Object getSelected();
    public Class<?> getSelectedType();
    public Object[] getLastSelection();
    public Tax[] selectTax();
    public Income[] selectIncome();
    public Exemption[] selectExemption();
    public User[] selectUser();
    public boolean selectOfIndex(int i, Class<?> type);
    public void setBounds(String property, Double min, Double max);
    public void setOrder(String property, boolean descending);
    public void setStringTemplate(String property, String text);
    public void setFromPrevious(boolean fromPrevious);
    public String getLastOperationStatus();
}
