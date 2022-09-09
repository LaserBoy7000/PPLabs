package com.labs.IO;

public enum BackgroundColors {
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
