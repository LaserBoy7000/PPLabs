package com.labs.IO;

public class Vec2 {
    int x;
    int y;
    public Vec2(int x, int y){
        this.x = x; this.y = y;  
    }

    public int X(){
        return x;
    }

    public int Y(){
        return y;
    }

    public void X(int x){
       this.x = x;
    }

    public void Y(int y){
        this.y = y;
    }
}
