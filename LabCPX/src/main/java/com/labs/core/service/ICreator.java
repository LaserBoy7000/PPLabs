package com.labs.core.service;

import com.google.inject.ImplementedBy;
import com.labs.core.service.impl.CreatorService;

@ImplementedBy(CreatorService.class)
public interface ICreator {
    public boolean createTax();
    public boolean createIncome();
    public boolean createExemption();
    public String getLastOperationStatus();
}
