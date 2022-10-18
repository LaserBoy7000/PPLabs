package com.labs.UIAPI.command.identity;
import com.labs.UIAPI.CommandResult;
import com.labs.UIAPI.ICommand;
import com.labs.core.service.DependenciesInjector;
import com.labs.core.service.IIdentityService;

public class AuthorizeUserCommand implements ICommand<Object> {
    private IIdentityService id;
    private String Name;
    private String Surname;
    private String Password;

    public AuthorizeUserCommand(String name, String surname, String password){
        id = (IIdentityService)DependenciesInjector.get(IIdentityService.class);
        Name = name;
        Surname = surname;
        Password = password;
    }

    @Override
    public CommandResult<Object> executeAsObjective() throws Exception {
        boolean rs = id.register(Name, Surname, Password);
        if(rs)
            return new CommandResult<Object>(null, "Authorization was successful", true);
        return new CommandResult<Object>(null, id.getLastOperationStatus(), false);
    }

    @Override
    public CommandResult<Object> execute() throws Exception {
        return executeAsObjective();
    }
    
}
