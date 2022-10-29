package com.labs;
import java.util.logging.Level;

import com.labs.UI.impl.СonsoleUI.ConsoleUI;
import com.labs.core.service.DependenciesInjector;

public class Main 
{
    public static void main(String[] args)
    {
       java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
       new ConsoleUI(new DependenciesInjector()).Init();
    }
}
