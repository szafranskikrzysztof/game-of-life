package com.krzsz.powers;

public class Deforestation implements Power {
    public static byte DEFORESTATION_COUNTER;

    public Deforestation() {
    }

    @Override
    public byte getCOUNTER() {
        return DEFORESTATION_COUNTER;
    }

    @Override
    public void setCOUNTER(byte counter) {
        DEFORESTATION_COUNTER = counter;
    }

    @Override
    public String getName() {
        return "Deforestation";
    }

}