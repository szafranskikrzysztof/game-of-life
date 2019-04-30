package com.krzsz.organisms.plants;

import com.krzsz.world.World;

public class Clover extends Plant {

    public Clover(int[] lokalizacja, World world) {
        super(lokalizacja, world);
        symbol = 'C';
    }

    @Override
    public void action() {
        super.action();
        super.action();
        super.action();
    }
}
