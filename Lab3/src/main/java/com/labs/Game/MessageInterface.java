package com.labs.Game;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.labs.IO.FontColors;

public class MessageInterface{
    final BiConsumer<String, FontColors> s;
    final BiConsumer<String, FontColors> l;
    final Supplier<String> g;
    final BiConsumer<String, FontColors> e;
    FontColors flavour = FontColors.White;
    FontColors mask = null;
    public MessageInterface(BiConsumer<String, FontColors> send, BiConsumer<String, FontColors> sendln, Supplier<String> get, BiConsumer<String, FontColors> error){
        s = send; g = get; e = error; l = sendln;
    }
    public MessageInterface(MessageInterface base, FontColors flavour){
        this.flavour = flavour;
        s = base.s; e=base.e; l=base.l; g=base.g;
    }
    public void send(String msg) {s.accept(msg, mask != null ? mask : flavour);}
    public void sendln(String msg) {l.accept(msg, mask != null ? mask : flavour);}
    public String get() {return g.get();}
    public void error(String msg) {e.accept(msg, mask != null ? mask : flavour);}
    
    public void AddMask(FontColors mask){
        this.mask = mask;
    }

    public void RemoveMask(){
        mask = null;
    }
}