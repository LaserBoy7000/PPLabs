package com.labs.UIAPI;

import com.google.common.base.Supplier;
import com.labs.core.service.DependenciesInjector;
import com.labs.core.service.IIdentityService;

public abstract class UserCommand<T> implements ICommand<T> {
    private Supplier<CommandResult<T>> ChildExecutable;
    private Supplier<CommandResult<Object>> ObjectiveExecutable;
    private IIdentityService Id;

    protected UserCommand(Supplier<CommandResult<T>> executable, Supplier<CommandResult<Object>> objective){
        ChildExecutable = executable;
        ObjectiveExecutable = objective;
        Id = (IIdentityService)DependenciesInjector.get(IIdentityService.class);
    }

    @Override
    public CommandResult<T> execute(){
        if(!Id.isUserAvailable())
            return new CommandResult<T>(null, "WARNING: You cannot execute this command being unauthorized", false);
        return ChildExecutable.get();
    }

    @Override
    public CommandResult<Object> executeAsObjective(){
        if(!Id.isUserAvailable())
            return new CommandResult<Object>(null, "WARNING: You cannot execute this command being unauthorized", false);
        return ObjectiveExecutable.get();
    }
}
