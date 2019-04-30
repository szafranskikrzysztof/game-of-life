package com.krzsz.organisms.animals;

import com.krzsz.world.World;

public class Wolf extends Animal {

    public Wolf(int[] coordinate, World world) {
        super(coordinate, world);
        strenght = 9;
        initiative = 5;
        symbol = 'W';
    }


}
