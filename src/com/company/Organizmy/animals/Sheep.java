package com.company.Organizmy.animals;

import com.company.world.World;

public class Sheep extends Animal {

    public Sheep(int[] lokalizacja, World world) {
        super(lokalizacja, world);
        strenght = 4;
        initiative = 4;
        symbol = 'S';
    }


}