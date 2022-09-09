package com.labs.Game;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.labs.Droid.Droid;
import com.labs.Game.Logger.ILogger;

public class Fight {
    int rounds;
    ArrayList<Droid> team1;
    int tm1c=0, tm2c=0;
    int tm1kl=0, tm2kl=0; 
    ArrayList<Droid> team2;
    ArrayList<ILogger> loggers;

    public Fight(){
        team1 = new ArrayList<>();
        team2 = new ArrayList<>();
        loggers = new ArrayList<>();
    }

    public void AddLogger(ILogger output){
        loggers.add(output);
    }

    void Writeln(String str){
        for (ILogger lg : loggers) {
            lg.Writeln(str);
        }
    }

    public void SetRounds(int i){
        rounds = i;
    }

    public void Add1(Droid droid){
        team1.add(droid);
    }

    public void Add2(Droid droid){
        team2.add(droid);
    }

    public void Start(){
        for(int i = 1; i <= rounds; i++)
        {
            Writeln("---Round "+ i +"---");
            TeamUser();
            TeamAI();
            if(team1.size() == 0){
                Writeln("Team 1 was eliminated, GAME OVER");
                return;
            }
            if(team2.size() == 0){
                Writeln("Team 2 was eliminated, GAME OVER");
                return;
            }
        }
        if(tm1kl > tm2kl)
            Writeln("Team 1 won, GAME OVER");
        else if(tm1kl < tm2kl)
            Writeln("Team 2 won, GAME OVER");
        else  Writeln("The game is draw, GAME OVER");
        return;
    }

    void TeamUser(){
        for (Droid dr : team1) {
            if(!dr.isReady()){
                dr.chill();
                continue;
            }

            if(dr.TryAimUser()){
                Droid trg = Random(team2);
                if(dr.Attack(trg)){
                    Writeln(dr.getName()+" Killed "+trg.getName());
                    tm1kl++;
                    team2.remove(trg);
                } else{
                    Writeln(dr.getName()+" Shoot "+trg.getName());
                }
            } else
                Writeln(dr.getName()+" Missed");
        }
    }

    void TeamAI(){
        for (Droid dr : team2) {
            if(!dr.isReady()){
                dr.chill();
                continue;
            }

            if(dr.TryAimUserAI()){
                Droid trg = Random(team1);
                if(dr.Attack(trg)){
                    Writeln(dr.getName()+" Killed "+trg.getName());
                    tm2kl++;
                    team1.remove(trg);
                } else{
                    Writeln(dr.getName()+" Shoot "+trg.getName());
                }
            } else Writeln(dr.getName()+" Missed");
        }
    }


    Droid Random(List<Droid> team){
        Random rd = new Random();
        int id = 0;
        if(team.size() > 1)
            id = rd.nextInt(0, team.size());
        return team.get(id);
    }
}
