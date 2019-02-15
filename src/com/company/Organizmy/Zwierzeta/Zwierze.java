package com.company.Organizmy.Zwierzeta;

import com.company.Organizmy.Organizm;
import com.company.World.World;

public abstract class Zwierze extends Organizm {


    public Zwierze(int[] lokalizacja, World world) {
        super(lokalizacja, world);
    }


    @Override
    public void akcja() {
        int[] newCoordinates = this.randomCloseCoordinate();
        int newX = newCoordinates[0];
        int newY = newCoordinates[1];
        Organizm defender = world.StanPlanszy[newX][newY];

        if (defender == null) {
            this.moveTo(newCoordinates);
        } else if (defender.getClass() == this.getClass()) {
            this.makeLove(defender);
        } else {
            defender.collision(this);
        }

    }


    public void makeLove(Organizm defender) {
        Organizm mother;

        if (defender.randomFreeCloseCoordinate() != null) {
            mother = defender;
        } else if (this.randomFreeCloseCoordinate() != null) {
            mother = this;
        } else {
            System.out.println("Two " + this.getClass() + "make love. But there's not enough space for child");
            return;
        }

        int[] babyCoordinates = mother.randomFreeCloseCoordinate();
        int babyX = babyCoordinates[0];
        int babyY = babyCoordinates[1];

        try {
            world.StanPlanszy[babyX][babyY]
                    = (Zwierze) this.getClass().getConstructor(int[].class, World.class).newInstance(babyCoordinates, world);
        } catch (Exception ex) {
            System.out.println("New" + this.getClass() + "is born");


        }
    }
}








    /*
    public Zwierze(int[] lokalizacja, Wold wold) {
        super(lokalizacja, wold);
    }




    // after collision on field x,y animal will fight or procreate
    @Override
    public void kolizja(int x, int y) {

        boolean isSameSpiece = wold.StanPlanszy[x][y].getClass() == wold.StanPlanszy[lokalizacja[0]][lokalizacja[1]].getClass();
        boolean defenderHasFreeSpace = wold.findFreePlace(x, y) != null;
        boolean attacerHasFreeSpace = wold.findFreePlace(lokalizacja[0], lokalizacja[1]) != null;

        if (isSameSpiece) {
            if (defenderHasFreeSpace || attacerHasFreeSpace) {
                int newBornX = -1;
                int newBornY = -1;
                if (defenderHasFreeSpace) {
                    newBornX = wold.findFreePlace(x, y)[0];
                    newBornY = wold.findFreePlace(x, y)[1];
                } else if (attacerHasFreeSpace) {
                    newBornX = wold.findFreePlace(lokalizacja[0], lokalizacja[1])[0];
                    newBornY = wold.findFreePlace(lokalizacja[0], lokalizacja[1])[1];
                }

                int[] newBornCoord = new int[]{newBornX, newBornY};
                Wold newBornWorld = this.wold;
                Class c = this.getClass();


                try {
                    wold.StanPlanszy[newBornX][newBornY] = (Zwierze) c.getConstructor(int[].class, Wold.class).newInstance(newBornCoord, newBornWorld);
                } catch (Exception ex) {
                }
                System.out.println("Zwierzątko się rozmnaża");
            } else {
                System.out.println("Stworzenie nie ma wolnego miejsca");
            }


        } else {
            this.walka(x, y);

        }
    }

    public void walka(int x, int y) {
        // następuje walka
        if (this.sila >= wold.StanPlanszy[x][y].getSila()) {
                wold.StanPlanszy[lokalizacja[0]][lokalizacja[1]] = null;
                wold.StanPlanszy[x][y] = this;
                lokalizacja[0] = x;
                lokalizacja[1] = y;
                }
                }
                */





