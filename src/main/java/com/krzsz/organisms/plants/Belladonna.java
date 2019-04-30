package com.krzsz.organisms.plants;

import com.krzsz.world.World;

public class Belladonna extends Plant {

    public Belladonna(int[] coordinate, World world) {
        super(coordinate, world);
        this.strenght = 99;
        symbol = 'B';
    }
}
