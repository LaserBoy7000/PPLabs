package com.labs.Droid;

import com.labs.Game.MessageInterface;
import com.labs.IO.FontColors;

public class MachinegunDroid extends Droid {
    public static final int Quality = 3;
    public static final int Cooldown = 0;
    public static final int Health = 100;
    public static final int Damage = 20;
    public static final FontColors Flavour = FontColors.Blue;
    public MachinegunDroid(MessageInterface IO){
        this.IO = IO;
        IO.sendln("Hello I'am a machinegun droid!");
        String name = AskName();
        SetCharacteristics(Health, Damage, Cooldown, Quality, name);
        
    }

    public static String Description(){
        return String.format("Health: %d  Cooldown: %d  Damage: %d  Quality: %d", Health, Cooldown, Damage, Quality);
    }
}
