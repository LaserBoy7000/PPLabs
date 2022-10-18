package com.labs.UI.impl.СonsoleUI;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import com.labs.UI.impl.СonsoleUI.Platform.Customization;
import com.labs.UI.impl.СonsoleUI.Platform.Customization.BackgroundColors;
import com.labs.UIAPI.CommandResult;
import com.labs.UIAPI.ICommand;
import com.labs.UIAPI.IObjectiveCommand;
import com.labs.UIAPI.TESTExceptionCommand;
import com.labs.UIAPI.TESTNoSuccCommand;
import com.labs.UIAPI.TESTObjectCommand;
import com.labs.UIAPI.command.constant.SetConstantCommand;
import com.labs.UIAPI.command.identity.AuthorizeUserCommand;
import com.labs.UIAPI.command.identity.LogoutUserCommand;
import com.labs.core.service.DependenciesInjector;
import com.labs.core.service.IIdentityService;
import com.labs.UI.IUserInterface;

public class ConsoleUI implements IUserInterface {

    String curentPrefix = "@-no user-@=> ";
    final Customization.FontColors PREFIX_COLOR = Customization.FontColors.Green;
    final Customization.FontColors WARNING_COLOR = Customization.FontColors.Yellow;
    final Customization.FontColors FATAL_COLOR = Customization.FontColors.Red;
    List<Thread> ThreadPool = new ArrayList<>();

    public void Init() 
    {
        HiMessage();
        MessageLoop();
    }

    void MessageLoop()
    {
        while(true)
        {
            String line = Read();
            line = line.trim();
            String[] params = line.split(" +");

            if(params.length == 0) continue;

            switch(params[0])
            {
                case "Select":
                    Select(params);
                    break;

                case "Create":
                    Create(params);
                    break;

                case "Update":
                    Update(params);
                    break;

                case "Login":
                    Login(params);
                    break;

                case "Logout":
                    Logout(params);
                    break;

                case "Constant":
                    Constant(params);
                    break;

                case "exit":
                    KillAll();
                    System.exit(0);
                    break;

                default:
                    PrintError("WARNING: Invalid command", false);
                    break;
            }
        }
    }

    //IO TEXT
    
    void HiMessage()
    {
        Print("*** Вітаю у світі бухгалтерії, друже! :D ***", false);
    }

    void Print(String text, boolean newLine)
    {
        if(newLine){
            System.out.println("");
            PrintPrefix();
            System.out.println(text);
            PrintPrefix();
        } else {
            PrintPrefix();
            System.out.println(text);
        }
    }

    void PrintPrefix(){
        RequestPrefix();
        Customization.INSTANCE.SetFontColor(PREFIX_COLOR, BackgroundColors.None);
        System.out.print(curentPrefix);
        Customization.INSTANCE.SetFontDefault();
    }

    String Read()
    {
        PrintPrefix();
        return System.console().readLine();
    }

    void PrintError(String message, boolean newLine)
    {
        RequestPrefix();
        if(message.contains("WARNING"))
            Customization.INSTANCE.SetFontColor(WARNING_COLOR, BackgroundColors.None);
        else
            Customization.INSTANCE.SetFontColor(FATAL_COLOR, BackgroundColors.None);
        if(!newLine){
            System.out.print(curentPrefix);
            System.out.println(message);
            return;
        }
        System.out.print("\n"+curentPrefix);
        System.out.println(message);
        Customization.INSTANCE.SetFontColor(PREFIX_COLOR, BackgroundColors.None);
        System.out.print(curentPrefix);
        Customization.INSTANCE.SetFontDefault();

    }

    void RequestPrefix()
    {
        IIdentityService id = (IIdentityService)DependenciesInjector.get(IIdentityService.class);
        if(!id.isUserAvailable())
            curentPrefix = curentPrefix.replaceFirst("-.*-", "-no user-");
        else curentPrefix = curentPrefix.replaceFirst("-.*-", "-"+id.getCurrentUser().getName()+"-");
    }

    //INTERNAL COMMAND HANDLERS

    void Select(String[] context)
    {
        ICommand<Object> c = new TESTObjectCommand();
        Enqueue(c); 
    }

    void Create(String[] context){
        ICommand<String> c = new TESTExceptionCommand();
        Enqueue(c);
    }

    void Update(String[] context){
        ICommand<String> c = new TESTNoSuccCommand();
        Enqueue(c);
    }

    void Login(String[] context){
        if(context.length < 4){
            PrintError("ERROR: Not enough params", false);
            return;
        }
        IObjectiveCommand c = new AuthorizeUserCommand(context[1], context[2], context[3]);
        Enqueue(c);
    }

    void Logout(String[] context){
        LogoutUserCommand c = new LogoutUserCommand();
        Enqueue(c);
    }

    void Constant(String[] context){
        if(context.length < 2){
            PrintError("ERROR: Not enough params", false);
            return;
        }

        switch(context[1]){

            case "set":
                if(context.length < 4){
                    PrintError("ERROR: Not enough params", false);
                    return;
                }

                double d;
                try
                {
                    d = Double.parseDouble(context[3]);
                } 
                catch(Exception e) 
                {
                    PrintError("ERROR: " +context[3]+ "had to be a number, but was not", false);
                    return;
                }
                IObjectiveCommand c = new SetConstantCommand(context[2], d);
                Enqueue(c);
            break;

            default:
                PrintError("ERROR: Wrong param " + context[1], false);
            break;
        }
    }

    //THREADING

    void TerminateThread(Thread thread){
        ThreadPool.remove(thread);
        thread.interrupt();
        TryRunNext();
    }

    void TryRunNext(){
        if(!ThreadPool.isEmpty() && ThreadPool.get(0).getState() == State.NEW)
            ThreadPool.get(0).start();
    }

    void Enqueue(final IObjectiveCommand command){
        Thread th = new Thread(
            new RunableCommand(
                command, 
                new Consumer<String>() {
                    @Override
                    public void accept(String x){ Print(x, true); }
                },
                new Consumer<String>() {
                    @Override
                    public void accept(String x){ PrintError(x, true); }
                },
                new Consumer<Thread>() {
                    @Override
                    public void accept(Thread x){ TerminateThread(x); }
                }));
        ThreadPool.add(th);
        TryRunNext();
    }

    void KillAll(){
        if(!ThreadPool.isEmpty())
        ThreadPool.get(0).interrupt();
        ThreadPool.clear();
    }

    public class RunableCommand implements Runnable {
        private IObjectiveCommand command;
        private Consumer<String> out;
        private Consumer<String> outErr;
        private Consumer<Thread> terminate;

        public RunableCommand(IObjectiveCommand command, Consumer<String> output, Consumer<String> errorOutput, Consumer<Thread> terminator) {
           this.command = command;
           out = output;
           outErr = errorOutput;
           terminate = terminator;
        }
  
        public void run() {
            try {
                CommandResult<Object> res = command.executeAsObjective();
                if(res.IsSucceed)
                    if(res.Result != null)
                        out.accept(res.Message + "\n" + res.Result.toString());
                    else  out.accept(res.Message);
                else 
                    outErr.accept(res.Message);
                terminate.accept(Thread.currentThread());
            } catch (Exception e){
                outErr.accept(e.getMessage());
                terminate.accept(Thread.currentThread());
            }
        }
    }
}
