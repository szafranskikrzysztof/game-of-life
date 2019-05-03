package com.krzsz.powers;

public class AlzureShield implements Power {
    public static byte ALZURE_SHIELD_COUNTER;

    public AlzureShield() {
    }

    @Override
    public byte getCOUNTER() {
        return ALZURE_SHIELD_COUNTER;
    }

    @Override
    public void setCOUNTER(byte counter) {
        ALZURE_SHIELD_COUNTER = counter;
    }

    @Override
    public String getName() {
        return "Alzure shield";
    }

}