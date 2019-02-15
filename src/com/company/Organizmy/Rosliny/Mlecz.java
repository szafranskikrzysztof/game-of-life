package com.company.Organizmy.Rosliny;

import com.company.World.World;

public class Mlecz extends Roslina {

    public Mlecz(int[] lokalizacja, World world) {
        super(lokalizacja, world);
        symbol = 'M';
    }

    @Override
    public void akcja() {
        super.akcja();
        super.akcja();
        super.akcja();
    }
}
