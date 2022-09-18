package com.labs.Droid;
import com.labs.Game.MessageInterface;
import com.labs.IO.FontColors;

public class TankDroid extends Droid {
    public static final int Quality = 3;
    public static final int Cooldown = 2;
    public static final int Health = 150;
    public static final int Damage = 75;
    public static final FontColors Flavour = FontColors.Red;
    public TankDroid(MessageInterface IO){
        this.IO = IO;
        IO.sendln("Hello I'am a tank droid!");
        String name = AskName();
        SetCharacteristics(Health, Damage, Cooldown, Quality, name);
    }

    public static String Description(){
        return String.format("Health: %d  Cooldown: %d  Damage: %d  Quality: %d", Health, Cooldown, Damage, Quality);
    }
}
