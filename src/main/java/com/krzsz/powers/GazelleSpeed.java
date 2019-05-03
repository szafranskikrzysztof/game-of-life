package com.krzsz.powers;

import com.krzsz.util.Utils;

public class GazelleSpeed implements Power {
    public static byte GAZELLE_SPEED_COUNTER;

    public GazelleSpeed() {
    }

    @Override
    public byte getCOUNTER() {
        return GAZELLE_SPEED_COUNTER;
    }

    @Override
    public void setCOUNTER(byte counter) {
        GAZELLE_SPEED_COUNTER = counter;
    }

    @Override
    public String getName() {
        return "Gazelle Speed";
    }


    /**
     * GazelleSpeedEffect in first 3 turns from activation(from 10 to 8 inclusive) doubles vector of move
     * in next 2 turns (7 and 6) doubles vector of movement with 50% chance of success
     */
    public static int[] GazelleSpeedEffect(int[] vector) {
        if (GAZELLE_SPEED_COUNTER < 6) {
            return vector;
        }

        if (GAZELLE_SPEED_COUNTER >= 8 || Utils.actionSucceed(50)) {
            return new int[]{vector[0] * 2, vector[1] * 2};
        }

        return  vector;
    }


}

