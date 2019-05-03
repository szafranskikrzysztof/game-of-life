package com.krzsz.powers;

public class Immortality implements Power {
    public static byte IMMORTALITY_COUNTER;

    public Immortality() {
    }

    @Override
    public byte getCOUNTER() {
        return IMMORTALITY_COUNTER;
    }

    @Override
    public void setCOUNTER(byte counter) {
        IMMORTALITY_COUNTER = counter;
    }

    @Override
    public String getName() {
        return "Immortality";
    }

}