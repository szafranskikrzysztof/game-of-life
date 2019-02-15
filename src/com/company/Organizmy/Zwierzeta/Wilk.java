package com.company.Organizmy.Zwierzeta;

import com.company.World.World;

public class Wilk extends Zwierze {

    public Wilk (int[] lokalizacja, World world) {
        super(lokalizacja, world);
        sila = 9;
        inicjatywa = 5;
        symbol = 'W';
    }


}
