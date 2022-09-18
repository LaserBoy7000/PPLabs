package com.labs.Game.Logger;

import com.labs.Game.MessageInterface;

public class LoggerFactory {
    public static final LoggerFactory FACTORY = new LoggerFactory();
    public ILogger GetInstance(MessageInterface IO){
        return (ILogger)new IOLogger(IO);
    }
    public ILogger GetInstance(String path){
        return (ILogger)new FileLogger(path);
    }
}
