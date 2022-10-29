package com.labs.core.service;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.labs.core.service.module.ServiceConfigModule;
//Services access point, should be used within commands!
public class DependenciesInjector {
    private Injector Injector;

    public DependenciesInjector() {
        Injector = Guice.createInjector(new ServiceConfigModule());
    }

    public Injector getInjector(){
        return Injector;
    }

    public <T> T get(Class<T> type){
        return Injector.getInstance(type);
    }
}
