package com.labs.Game;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.labs.Droid.Droid;
import com.labs.Droid.DroidBuildException;
import com.labs.Droid.MachinegunDroid;
import com.labs.Droid.StandartDroid;
import com.labs.Droid.TankDroid;
import com.labs.Game.Logger.LoggerFactory;
import com.labs.IO.BackgroundColors;
import com.labs.IO.FontColors;
import com.labs.IO.UserIO;

public class Game {
    UserIO con;
    public static  final int Height = 24;
    public static final int Width = 48;
    final static String[] baner = new String[]{
    "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░",
    "░░░░░░░███░█░█░███░░██░░███░███░███░██░░░░░░░░░",
    "░░░░░░░░█░░█░█░█░░░░█░█░█░█░█░█░░█░░█░█░░░░░░░░",
    "░░░░░░░░█░░███░███░░█░█░██░░█░█░░█░░█░█░░░░░░░░",
    "░░░░░░░░█░░█░█░█░░░░█░█░█░█░█░█░░█░░█░█░░░░░░░░",
    "░░░░░░░░█░░█░█░███░░██░░█░█░███░███░██░░░░░░░░░",
    "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░",
    "░░░░░░░░████░░██░░█████░█████░█░░░░████░░░░░░░░",
    "░░░░░░░░█░░█░████░░░█░░░░░█░░░█░░░░█░░░░░░░░░░░",
    "░░░░░░░░███░░█░░█░░░█░░░░░█░░░█░░░░████░░░░░░░░",
    "░░░░░░░░█░░█░████░░░█░░░░░█░░░█░░░░█░░░░░░░░░░░",
    "░░░░░░░░█░░█░█░░█░░░█░░░░░█░░░█░░░░█░░░░░░░░░░░",
    "░░░░░░░░████░█░░█░░░█░░░░░█░░░████░████░░░░░░░░",
    "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░"};
    Map<Droid, DroidType> droids = new HashMap();
    List<DroidType> types;
    MessageInterface IO;
    
    public Game(){
        InitEnvironmet();
        InitMessageInterface();
        InitDroids();
        main();
    }

    void main(){
        while(true){
           Sendln("Enter the command (type help for help)", null);
           Aggregate(Get());
           //зробити створення арени та вибір дроїдів
           //зробити у дроїдів функцію пострілу на базі рулетки в консоль
        }
    }

    void Aggregate(String command){
        switch(command){
            case "help":
                Sendln("'create' - create new droid", FontColors.White);
                Sendln("'list' - get droids list", FontColors.White);
                Sendln("'start' - start new arena", FontColors.White);
                Sendln("'exit' - quit game", FontColors.Red);
            break;

            case "create":
                CreateDroid();
            break;

            case "list":
                DroidList(droids);
            break;

            case "start":
                Fight f = BuildFight();
                f.Start();
            break;

            case "exit":
                con.PaintOutputStd();
                System.exit(0);
        }
    }

    void InitDroids(){
        types = new ArrayList<>();
        types.add(new DroidType("StandartDroid",StandartDroid.class, StandartDroid.Description(), StandartDroid.Flavour));
        types.add(new DroidType("TankDroid", TankDroid.class, TankDroid.Description(), TankDroid.Flavour));
        types.add(new DroidType("MachinegunDroid", MachinegunDroid.class, MachinegunDroid.Description(), MachinegunDroid.Flavour));
    }

    void InitMessageInterface(){
        IO = new MessageInterface(
          (String s, FontColors с) -> Send(s, с),  
          (String s, FontColors с) -> Sendln(s, с),
          () -> Get(),
          (String e, FontColors с) -> Sendln("Error appeared: "+e, с));
    }

    void DroidList(Map<Droid, DroidType> map){
        Sendln("Available droids:", FontColors.Green);
        int i = 1;
        for (Map.Entry<Droid, DroidType> dr : map.entrySet()) {
            Droid d = dr.getKey();
            DroidType t = dr.getValue();
            Sendln("("+i+") ["+t.Name+"] "+d.getName(), t.Flavour);
            i++;
        }
    }

    void Send(String data, FontColors color){
        con.PaintOutput(FontColors.Purple, true);
        if(color != null)
            con.PaintOutput(color, true);
        System.out.print(data);
    }

    void Sendln(String data, FontColors color){
        con.PaintOutput(FontColors.Purple, true);
        System.out.print("@.@ > ");
        if(color != null)
            con.PaintOutput(color, true);
        System.out.println(data);
    }

    String Get(){
        con.PaintOutput(FontColors.White, false);
        System.out.print("@.@ > ");
        con.PaintOutput(FontColors.Yellow, true);
        return System.console().readLine();
    }

    void CreateDroid(){
        Sendln("Chose the type:", null);
        int i = 1;
        for (DroidType dr : types) {
            Sendln(String.format("(%d) %s: %s", i, dr.Name, dr.Description), dr.Flavour);
            i++;
        }

        i = Integer.parseInt(Get());
        DroidType trg = types.get(i-1);
        Constructor c;

        try
        {
            c = trg.Class.getConstructor(MessageInterface.class);
        } 
        
        catch (NoSuchMethodException e) 
        {  
            IO.error("Could not find appropriate constructor");
            return;
        }
        if(c == null) return;
        
        Droid obj;

        try
        {
            obj = (Droid)c.newInstance(new MessageInterface(IO, trg.Flavour));
        }
        catch (Exception e)
        {
            IO.error("Could not find appropriate constructor");
            return;
        }
        droids.put(obj, trg);
    }

    Fight BuildFight(){
        Fight fight = new Fight();
        fight.AddLogger(LoggerFactory.FACTORY.GetInstance(new MessageInterface(IO, FontColors.Cyan)));
        
        Sendln("Do you wish to record a game? ('file name' or '')", null);
        String nm = Get();
        if(!nm.equals(""))
            fight.AddLogger(LoggerFactory.FACTORY.GetInstance(nm+".txt"));

        Map<Droid, DroidType> candidates = new HashMap();
        candidates.putAll(droids);
        
        Sendln("Set your team size", null);
        int tm = Integer.parseInt(Get());
        for(int i = 0; i < tm; i++){
            Sendln("Select droid for team 1:", null);
            DroidList(candidates);
            int d = Integer.parseInt(Get());
            Droid dr = (Droid)candidates.keySet().toArray()[d-1];
            fight.Add1(dr);
            candidates.remove(dr);
        }

        Sendln("Set enemy team size", null);
        tm = Integer.parseInt(Get());
        for(int i = 0; i < tm; i++){
            Sendln("Select droid for team 2:", null);
            DroidList(candidates);
            int d = Integer.parseInt(Get());
            Droid dr = (Droid)candidates.keySet().toArray()[d-1];
            fight.Add2(dr);
            candidates.remove(dr);
        }

        Sendln("Select the number of rounds", null);
        tm = Integer.parseInt(Get());

        fight.SetRounds(tm);

        return fight;
    }

    void InitEnvironmet(){
       con = UserIO.Instance;
       con.SetConsoleSize(Height, Width);
       con.SetConsolePosition(100, 100);
       con.LockConsole();
       con.Clear();
       con.PaintOutput(BackgroundColors.Yellow, false, FontColors.Green, true);
       for (String string : baner) {
         System.out.println(string);
       }
       con.PaintOutputStd();
    }

    class DroidType{
        public final String Name;
        public final Class Class;
        public final String Description;
        public final FontColors Flavour;
        public DroidType(String name, Class ref, String descr, FontColors flavour){
            Class = ref; Description = descr; Flavour = flavour; Name = name;
        }
    }
}
