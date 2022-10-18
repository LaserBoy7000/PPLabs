package com.labs;
import com.labs.UI.impl.СonsoleUI.ConsoleUI;
import com.labs.core.service.DependenciesInjector;

public class Main 
{
    public static void main(String[] args)
    {
       DependenciesInjector.configureInjector();
       new ConsoleUI().Init();
    }
}
