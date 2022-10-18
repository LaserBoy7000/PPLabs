package com.labs.core.service;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.labs.core.service.module.ServiceConfigModule;

public class DependenciesInjector {
    private static Injector Injector;

    public static void configureInjector(){
        Injector = Guice.createInjector(new ServiceConfigModule());
    }

    public static Injector getCurrentInjector(){
        return Injector;
    }

    public static Object get(Class<?> type){
        return Injector.getInstance(type);
    }
}
