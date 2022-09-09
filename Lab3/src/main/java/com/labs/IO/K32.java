package com.labs.IO;
import com.sun.jna.*;
import java.util.Arrays;
import java.util.List;
import com.sun.jna.platform.win32.Kernel32;

public interface K32 extends Kernel32{
    public K32 INSTANCE = (K32)Native.load("kernel32.dll", K32.class);

    public int SetConsoleScreenBufferSize(HANDLE hConsoleOutput, COORD.ByValue dwSize);
    public int SetConsoleWindowInfo(HANDLE hConsoleOutput, int bAbsolute, SMALL_RECT lpConsoleWindow);
    int WriteConsoleOutputAttribute(HANDLE hConsoleOutput, short[] lpAttribute, int nLength, COORD.ByValue dwWriteCoord, int[] lpNumberOfAttrsWritten);
    int WriteConsoleOutputCharacterW(HANDLE hConsoleOutput, char[] lpCharacter, int nLength, COORD.ByValue dwWriteCoord, int[] lpNumberOfCharsWritten);
    int SetConsoleTextAttribute(HANDLE hConsoleOutput, short wAttributes);
    int SetConsoleCursorPosition(HANDLE hConsoleOutput, COORD.ByValue dwCursorPosition);
    //public void WriteConsoleOutputW(HANDLE hConsoleOut, CHAR_INFO[] lpBuffer, COORD dwBufferSize, COORD dwBufferCoord, SMALL_RECT lpWriteRegion);


    public static class COORD extends Structure{
        public short X;
        public short Y;
        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("X", "Y");
        }
        public static class ByValue extends COORD implements Structure.ByValue { }
    }

    public static class UnionChar extends Union {
        public UnionChar() {}
        public UnionChar(char c) {
            setType(char.class);
            UnicodeChar = c;
        }

        public UnionChar(byte c) {
            setType(byte.class);
            AsciiChar = c;
        }

        public char UnicodeChar;
        public byte AsciiChar;
    }

    public static class SMALL_RECT extends Structure {
        public short Left;
        public short Top;
        public short Right;
        public short Bottom;
        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("Left", "Top", "Right", "Bottom");
        }
    }
}
