package com.krzsz.powers;

public class MagicalElixir implements Power {
    public static byte ELIXIR_COUNTER;
    public static byte HUMAN_START_STRENGTH;

    public MagicalElixir() {
    }

    @Override
    public byte getCOUNTER() {
        return ELIXIR_COUNTER;
    }

    @Override
    public void setCOUNTER(byte counter) {
        ELIXIR_COUNTER = counter;
    }

    @Override
    public String getName() {
        return "Magical Elixir";
    }

}