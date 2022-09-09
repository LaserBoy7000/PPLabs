package com.labs.Game.Logger;

import com.labs.Game.MessageInterface;

public class IOLogger implements ILogger {
    MessageInterface io;
    public IOLogger(MessageInterface IO) {
        io = IO;
    }

    @Override
    public void Writeln(String data) {
        io.sendln(data);
    }
    
}
