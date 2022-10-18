package com.labs.UIAPI.command.identity;

import com.labs.UIAPI.CommandResult;
import com.labs.UIAPI.ICommand;
import com.labs.core.service.DependenciesInjector;
import com.labs.core.service.IIdentityService;

public class RemoveUserCommand implements ICommand<Object> {
    private String Name;
    private String Surname;
    private IIdentityService Id;

    public RemoveUserCommand(String name, String surname){
        Name = name; Surname = surname;
        Id = (IIdentityService)DependenciesInjector.get(IIdentityService.class);
    }

    @Override
    public CommandResult<Object> executeAsObjective() throws Exception {
        boolean rs = Id.removeUser(Name, Surname);
        if(rs)
            return new CommandResult<Object>(null, "User " +Name +' ' +Surname+ "Was Successfuly removed", rs);
        else return new CommandResult<Object>(null, Id.getLastOperationStatus(), rs);
    }

    @Override
    public CommandResult<Object> execute() throws Exception {
        return executeAsObjective();
    }
    
}
