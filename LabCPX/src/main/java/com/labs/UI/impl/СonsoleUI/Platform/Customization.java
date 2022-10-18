package com.labs.UI.impl.СonsoleUI.Platform;
import com.sun.jna.platform.win32.WinNT.HANDLE;

public class Customization {
    final HANDLE Console;
    final public static Customization INSTANCE = new Customization();
    private Customization()
    {
        Console = K32.INSTANCE.GetStdHandle(-11);
    }
    
    public void SetFontColor(FontColors font, BackgroundColors background){
        
        K32.INSTANCE.SetConsoleTextAttribute(Console, (short)(font.getValue() | background.getValue()));
    }

    public void SetFontDefault(){
        SetFontColor(FontColors.White, BackgroundColors.None);
    }
    
    public enum FontColors {
        None((short)0),
        Red((short)0x0004),
        Blue((short)0x0001),
        Green((short)0x0002),
        Yellow((short)(0x0004|0x0002)),
        Purple((short)(0x0004|0x0001)),
        Cyan((short)(0x0002|0x0001)),
        White((short)(0x0004|0x0001|0x0002));
    
        private final short value;
    
        FontColors(final short newValue) {
            value = newValue;
        }
    
        public short getValue() { return value; }
    }

    public enum BackgroundColors {
        None((short)0),
        Red((short)0x0040),
        Blue((short)0x0010),
        Green((short)0x0020),
        Yellow((short)(0x0040|0x0020)),
        Purple((short)(0x0040|0x0010)),
        Cyan((short)(0x0020|0x0010)),
        White((short)(0x0040|0x0010|0x0020));

        private final short value;

        BackgroundColors(final short newValue) {
            value = newValue;
        }

        public short getValue() { return value; }
    }
}
