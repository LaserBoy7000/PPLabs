package com.labs.UIAPI.command.constant;

import com.labs.UIAPI.CommandResult;
import com.labs.UIAPI.ICommand;
import com.labs.core.service.DependenciesInjector;
import com.labs.core.service.IConstantProvider;

public class SetConstantCommand implements ICommand<Object> {

    private String Name;
    private Double Value;
    private IConstantProvider Cp;

    public SetConstantCommand(String title, Double value){
        Name = title; Value = value;
        Cp = (IConstantProvider)DependenciesInjector.get(IConstantProvider.class);
    }

    @Override
    public CommandResult<Object> executeAsObjective() throws Exception {
        boolean rs = Cp.setConstant(Name, Value);
        if(rs)
            return new CommandResult<Object>(null, Name + " was successfuly set to " + Value, true);
        return new CommandResult<Object>(null, Cp.getLastOperationStatus(), false);
    }

    @Override
    public CommandResult<Object> execute() throws Exception {
        return executeAsObjective();
    }
}
