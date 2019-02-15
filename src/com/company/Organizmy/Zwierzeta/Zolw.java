package com.company.Organizmy.Zwierzeta;

import com.company.World.World;

public class Zolw extends Zwierze {

    public Zolw(int[] lokalizacja, World world) {
        super(lokalizacja, world);
        sila = 2;
        inicjatywa = 1;
        symbol = 'Z';
    }
}