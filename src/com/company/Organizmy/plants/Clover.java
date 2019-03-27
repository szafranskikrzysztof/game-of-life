package com.company.Organizmy.plants;

import com.company.world.World;

public class Clover extends Plant {

    public Clover(int[] lokalizacja, World world) {
        super(lokalizacja, world);
        symbol = 'M';
    }

    @Override
    public void action() {
        super.action();
        super.action();
        super.action();
    }
}
