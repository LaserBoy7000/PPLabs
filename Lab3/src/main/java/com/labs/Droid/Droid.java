package com.labs.Droid;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import com.labs.Game.Game;
import com.labs.Game.MessageInterface;
import com.labs.IO.FontColors;
import com.labs.IO.UserIO;

public abstract class Droid {
    protected void SetCharacteristics(int health, int damage, int cooldown, int quality, String name){
        this.cooldown=cooldown; this.damage = damage;
        this.health = health; this.name = name;
        this.quality = quality;
    }

    protected String name;
    protected MessageInterface IO;
    
    public String getName() {
        return name;
    }

    public MessageInterface getIO(){
        return IO;
    }

    protected String AskName() {
        IO.sendln("Give me a name");
        return IO.get();
    }

    protected int damage;

    public int getDamage() {
        return damage;
    }

    protected int health;

    public int getHealth() {
        return health;
    }

    protected int cooldown;
    protected int quality;

    protected int curheat = 0;

    public boolean isReady(){
        if(curheat == 0)
            return true;
        else return false;
    }

    public boolean isDead(){
        return health <= 0;
    }

    public void chill(){
        curheat--;
    }

    public boolean getDamage(int damage){
        health -= damage;
        return isDead();
    }

    public boolean Attack(Droid target){
        curheat = cooldown;
        return target.getDamage(damage);
    }

    public boolean TryAimUserAI(){
        Random rd = new Random();
        int pos = rd.nextInt(1, 3+quality);
        if(pos > 1)
            return true;
        return false;
    }

    public boolean TryAimUser(){
        IO.sendln(name+": Aiming!");
        UserIO io = UserIO.Instance;
        Random rd = new Random();
        int pos = rd.nextInt(1, Game.Width - quality);
        int i = 0;

        io.clearIn();
        
        try {
        io.PaintOutput(FontColors.Red, false);
        for(; i < pos; i++){
            System.out.print('█');
            if(io.isAvailable() > 0){
                io.clearIn();
                System.out.println("");
                IO.sendln(name+": I missed!");
                return false;
            }   
            TimeUnit.MILLISECONDS.sleep(100);
        }

        io.PaintOutput(FontColors.Green, true);
        for(; i < pos+quality; i++){
            System.out.print('█');
            if(io.isAvailable() > 0){
                io.clearIn();
                System.out.println("");
                IO.sendln(name+": We got him!");
                return true;
            }
            TimeUnit.MILLISECONDS.sleep(100); 
        }

        io.PaintOutput(FontColors.Red, false);
        for(; i < Game.Width; i++){
            System.out.print('█');
            if(io.isAvailable() > 0){
                io.clearIn();
                System.out.println("");
                IO.sendln(name+": I missed!");
                return false;
            }   
            TimeUnit.MILLISECONDS.sleep(100);
        }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("");
        IO.sendln(name+": I missed!");
        return false;
    }
}
