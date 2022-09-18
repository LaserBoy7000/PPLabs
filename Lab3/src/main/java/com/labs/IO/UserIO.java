package com.labs.IO;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinDef.*;
import java.io.IOException;
import java.util.Arrays;
import com.sun.jna.Pointer;

public class UserIO {
    public static UserIO Instance = new UserIO();
    HANDLE hnd;
    HWND hwd;
    K32 k32;
    WinUs wu;
    char[] buf;
    short[] col;
    Vec2 size;


    public UserIO(){
        InitNative();
        //should get info
        buf = new char[1];
        col = new short[1];
    }

    public void SetConsoleBufferSize(int height, int width){
        K32.COORD.ByValue s = new K32.COORD.ByValue();
        s.X=(short)width; s.Y=(short)height;
        k32.SetConsoleScreenBufferSize(hnd, s);
        size = new Vec2(height, width);
        buf = new char[height*width];
        Arrays.fill(buf, ' ');
        col = new short[height*width];
        Arrays.fill(col, FontColors.White.getValue());

    }

    public int isAvailable(){
        int n = 0;
        try{
            n = System.in.available();
        } catch(Exception e) {System.out.println(e.getStackTrace());}
        return n;
    }

    public void clearIn(){
        int a = isAvailable();
        try {
            for(int i = 0; i < a; i++)
                System.in.read();
        }
        catch (IOException e) { e.printStackTrace();}
    }

    public void SetConsoleSize(int height, int width){
        SetConsoleBufferSize(1000, 1000);
        SetConsoleWindowSize(height-1, width-1);
        SetConsoleBufferSize(height, 1000);
        SetConsoleBufferSize(height, width); ///////////////////////////////////
    }

    public void SetConsoleWindowSize(int height, int width){
        K32.SMALL_RECT r = new K32.SMALL_RECT();
        r.Top=0; r.Left=0;
        r.Right=(short)width; r.Bottom=(short)height;
        k32.SetConsoleWindowInfo(hnd, 1, r);
    }

    public void WriteBuffer(char[][] bufer, short[][] colors){
        col = col.clone();
        int w = bufer[0].length;
        int h = bufer.length;
        
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                buf[i*w+j] = bufer[i][j];
                col[i*w+j] = colors[i][j];
            }
        }

        Put();        
    }

    void Put(){
        K32.COORD sz = new K32.COORD();
        sz.X = (short)size.X();
        sz.Y = (short)size.Y();

        K32.COORD.ByValue ps = new K32.COORD.ByValue();
        ps.X = 0;
        ps.Y = 0;

        int[] res = new int[1];

        k32.WriteConsoleOutputAttribute(hnd, col, sz.X*sz.Y, ps, res);
        k32.WriteConsoleOutputCharacterW(hnd, buf, sz.X*sz.Y, ps, res);
    }

    public void PaintOutput(BackgroundColors bk, boolean bkIntense, FontColors ftc, boolean ftIntense){
        k32.SetConsoleTextAttribute(hnd, (short)(bk.getValue() | (bkIntense ? 0x0080 : 0) | ftc.getValue() | (ftIntense ? 0x0008 : 0)));
    }

    public void PaintOutput(BackgroundColors bk, boolean bkIntense){
        k32.SetConsoleTextAttribute(hnd, (short)(bk.getValue() | (bkIntense ? 0x0080 : 0)));
    }

    public void PaintOutput(FontColors ftc, boolean ftIntense){
        k32.SetConsoleTextAttribute(hnd, (short)(ftc.getValue() | (ftIntense ? 0x0008 : 0)));
    }

    public void Clear(){
        short colr = FontColors.White.getValue();
        for (int i =0; i < buf.length; i++) {
            buf[i] = ' ';
            col[i] = colr;
        }
        Put();
        K32.COORD.ByValue ps = new K32.COORD.ByValue();
        ps.X = 0;
        ps.Y = 0;
        k32.SetConsoleCursorPosition(hnd, ps);
        PaintOutputStd();
    }

    public void PaintOutputStd(){
        k32.SetConsoleTextAttribute(hnd, FontColors.White.getValue());
    }

    public void SetConsolePosition(int x, int y){
        wu.SetWindowPos(hwd, new HWND(Pointer.NULL), x, y, 0, 0, 0x0001 | 0x0040);
    }

    void InitNative(){
        k32 = K32.INSTANCE;
        hnd = k32.GetStdHandle(-11);
        hwd = k32.GetConsoleWindow();
        wu = WinUs.INSTANCE;
    }

    public void LockConsole(){
        HMENU m = wu.GetSystemMenu(hwd, 0);
        //minimize
        wu.DeleteMenu(m,  new UINT(0xF020), new UINT(0));
        //maximize
        wu.DeleteMenu(m,  new UINT(0xF030), new UINT(0));
        //resize
        wu.DeleteMenu(m,  new UINT(0xF000), new UINT(0));
    }
}
