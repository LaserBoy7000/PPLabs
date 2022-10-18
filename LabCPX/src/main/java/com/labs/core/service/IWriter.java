package com.labs.core.service;

public interface IWriter {
    public boolean update(String param, Object value);
    public boolean remove(boolean allSelection);
    public String getLastOperationStatus();
}
