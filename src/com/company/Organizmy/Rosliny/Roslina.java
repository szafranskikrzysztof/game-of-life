package com.company.Organizmy.Rosliny;

import com.company.Organizmy.Organizm;
import com.company.World.World;


public abstract class Roslina extends Organizm implements Cloneable {


    public Roslina(int[] lokalizacja, World world) {
        super(lokalizacja, world);
        sila = 0;
        inicjatywa = 0;
    }



    @Override
    public void akcja() {
        int[] newCoordinate = this.randomFreeCloseCoordinate();
        if (actionSucceed(80) && newCoordinate != null) {
            try {
                world.StanPlanszy[newCoordinate[0]][newCoordinate[1]] = (Roslina) this.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getClass() + "rozsiewa się");
        } else {
            System.out.println(this.getClass() + " nie rozsiała się");
        }
    }


}


