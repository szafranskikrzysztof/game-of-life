package com.krzsz.organisms.animals;

import com.krzsz.world.World;

public class Sheep extends Animal {

    public Sheep(int[] lokalizacja, World world) {
        super(lokalizacja, world);
        strenght = 3;
        initiative = 4;
        symbol = 'S';
    }


}