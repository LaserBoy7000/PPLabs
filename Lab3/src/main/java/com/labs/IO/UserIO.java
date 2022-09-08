package com.labs.IO;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.Pointer;

public class UserIO {
    public static UserIO Instance = new UserIO();
    HANDLE hnd;
    HWND hwd;
    K32 k32;
    WinUs wu;

    public UserIO(){
        InitNative();
    }

    public void SetConsoleBufferSize(int height, int width){
        K32.COORD.ByValue s = new K32.COORD.ByValue();
        s.X=(short)width; s.Y=(short)height;
        k32.SetConsoleScreenBufferSize(hnd, s);
    }

    public void SetConsoleSize(int height, int width){
        SetConsoleBufferSize(1000, 1000);
        SetConsoleWindowSize(height-1, width-1);
        SetConsoleBufferSize(height, 1000);
        SetConsoleBufferSize(height, width);
    }

    public void SetConsoleWindowSize(int height, int width){
        K32.SMALL_RECT r = new K32.SMALL_RECT();
        r.Top=0; r.Left=0;
        r.Right=(short)width; r.Bottom=(short)height;
        k32.SetConsoleWindowInfo(hnd, 1, r);
    }

    public void WriteBuffer(char[][] bufer, short[][] colors){
        int w = bufer[0].length;
        int h = bufer.length;
        char[] dat = new char[w*h];
        short[] col = new short[w*h];
        
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                dat[i*w+j] = bufer[i][j];
                col[i*w+j] = colors[i][j];
            }
        }

        K32.COORD sz = new K32.COORD();
        sz.X = (short)w;
        sz.Y = (short)h;

        K32.COORD.ByValue ps = new K32.COORD.ByValue();
        ps.X = 0;
        ps.Y = 0;

        K32.SMALL_RECT buf = new K32.SMALL_RECT();
        buf.Top=0; buf.Left=0;
        buf.Right = sz.X;
        buf.Bottom = sz.Y;

        int[] res = new int[1];

        k32.WriteConsoleOutputAttribute(hnd, col, h*w, ps, res);
        k32.WriteConsoleOutputCharacterW(hnd, dat, h*w, ps, res);
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
