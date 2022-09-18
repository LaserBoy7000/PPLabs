package com.labs.Droid;
import com.labs.Game.MessageInterface;
import com.labs.IO.FontColors;

public class StandartDroid extends Droid {
    static final int Quality = 5;
    static final int Cooldown = 1;
    static final int Health = 100;
    static final int Damage = 25;
    public static final FontColors Flavour = FontColors.Green;
    public StandartDroid(MessageInterface IO){
        this.IO = IO;
        IO.sendln("Hello I'am a standard droid!");
        String name = AskName();
        SetCharacteristics(Health, Damage, Cooldown, Quality, name);
    }

    public static String Description(){
        return String.format("Health: %d  Cooldown: %d  Damage: %d  Quality: %d", Health, Cooldown, Damage, Quality);
    }
}
