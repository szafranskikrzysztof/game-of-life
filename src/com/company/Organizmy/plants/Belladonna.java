package com.company.Organizmy.plants;

import com.company.world.World;

public class Belladonna extends Plant {

    public Belladonna(int[] coordinate, World world) {
        super(coordinate, world);
        this.strenght = 99;
        symbol = 'B';
    }
}
