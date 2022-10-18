package com.labs.core.service;

import com.google.inject.ImplementedBy;
import com.labs.core.service.impl.ExpressionEvaluator;

@ImplementedBy(ExpressionEvaluator.class)
public interface IExpressionEvaluator {
    public Boolean calcBool(String expression);
    public double calcVal(String expression);
    public String getLastOperationStatus();
}
