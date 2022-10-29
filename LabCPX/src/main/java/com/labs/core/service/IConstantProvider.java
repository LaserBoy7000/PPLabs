package com.labs.core.service;

import com.google.inject.ImplementedBy;
import com.labs.core.service.impl.ConstantProvider;

//Service provides constants for calculating formulas
@ImplementedBy(ConstantProvider.class)
public interface IConstantProvider {
    public double getConstant(String title);
    public boolean setConstant(String title, double value);
    public String getLastOperationStatus();
}
