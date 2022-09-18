package com.labs.Game.Logger;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLogger implements ILogger {
    BufferedWriter wr;
    public FileLogger(String filename){
        Path p = Paths.get(filename);
        if(!Files.exists(p, LinkOption.NOFOLLOW_LINKS))
            try {
                Files.createFile(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        try {
            wr = new BufferedWriter(new FileWriter(p.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finalize(){
        try {
            wr.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void Writeln(String data) {
        try {
            wr.write(data+'\n');
            wr.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
