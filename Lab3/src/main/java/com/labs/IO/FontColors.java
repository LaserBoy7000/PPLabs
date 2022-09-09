package com.labs.IO;

public enum FontColors {
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
