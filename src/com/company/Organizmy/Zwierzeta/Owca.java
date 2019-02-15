package com.company.Organizmy.Zwierzeta;

import com.company.World.World;

public class Owca extends Zwierze {

    public Owca (int[] lokalizacja, World world) {
        super(lokalizacja, world);
        sila = 4;
        inicjatywa = 4;
        symbol = 'O';
    }


}