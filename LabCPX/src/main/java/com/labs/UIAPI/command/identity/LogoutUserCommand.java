package com.labs.UIAPI.command.identity;

import com.google.common.base.Supplier;
import com.labs.UIAPI.CommandResult;
import com.labs.UIAPI.UserCommand;
import com.labs.core.service.DependenciesInjector;
import com.labs.core.service.IIdentityService;

public class LogoutUserCommand extends UserCommand<Object> {

    public LogoutUserCommand() {
        super(new Supplier<CommandResult<Object>>() {
            @Override
            public CommandResult<Object> get() {
                return e(); 
            }
            }, 
            new Supplier<CommandResult<Object>>() {
            @Override
            public CommandResult<Object> get() {
                return e(); 
            }
        });
    }
    
    private static CommandResult<Object> e(){
        IIdentityService s = (IIdentityService)DependenciesInjector.get(IIdentityService.class);
        boolean rs = s.logOut();
        if(rs)
            return new CommandResult<Object>(null, "Successfuly logged out", true);
        return new CommandResult<Object>(null, s.getLastOperationStatus(), false);
    }
}