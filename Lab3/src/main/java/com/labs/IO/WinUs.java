package com.labs.IO;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;

public interface WinUs extends User32{
    public WinUs INSTANCE = (WinUs)Native.load("user32.dll", WinUs.class);
    
    BOOL DeleteMenu(HMENU hMenu, UINT uPosition, UINT uFlags);
    HMENU GetSystemMenu(HWND hWnd, int bRevert);
}
