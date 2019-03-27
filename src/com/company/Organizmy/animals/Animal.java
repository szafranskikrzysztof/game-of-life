package com.company.Organizmy.animals;

import com.company.Organizmy.Organism;
import com.company.Organizmy.plants.Plant;
import com.company.world.World;

public abstract class Animal extends Organism {


    Animal(int[] lokalizacja, World world) {
        super(lokalizacja, world);
    }

    @Override
    public void action() {
        int[] newCoordinates = this.randomCloseCoordinate();
        int newX = newCoordinates[0];
        int newY = newCoordinates[1];
        Organism defender = World.gameBoard[newX][newY];

        if (defender == this) {
            return;
        }

        if (defender == null) {
            this.moveTo(newCoordinates);
        } else if (defender.getClass().getSimpleName()
                .equals(this.getClass().getSimpleName())) {
            this.makeLove(defender);
        } else {
            System.out.println(this.getClass().getSimpleName() + " attacks " + defender.getClass().getSimpleName());
            defender.collision(this);
        }
    }


    @Override
    public void kill(Organism loser, Boolean takeLoserPlace) {
        String winnerName = this.getClass().getSimpleName();
        String loserName = loser.getClass().getSimpleName();

        if (loser instanceof Plant) {
            System.out.println(winnerName + " eats " + loserName);
        }
        if (loser instanceof Animal) {
            System.out.println(winnerName + " kills " + loserName);
        }

        super.kill(loser, takeLoserPlace);
    }


    void makeLove(Organism defender) {
        /* This block checks if there's free place near parent organisms */
        Organism mother;

        if (defender.randomFreeCloseCoordinate() != null) {
            mother = defender;
        } else if (this.randomFreeCloseCoordinate() != null) {
            mother = this;
        } else {
            System.out.println("Two " + this.getClass().getSimpleName() + " make love. But there's not enough space for child");
            return;
        }

        int[] babyCoordinates = mother.randomFreeCloseCoordinate();
        int babyX = babyCoordinates[0];
        int babyY = babyCoordinates[1];

        try {
            World.gameBoard[babyX][babyY]
                    = this.getClass()
                    .getConstructor(int[].class, World.class)
                    .newInstance(babyCoordinates, world);
            System.out.println("New " + this.getClass().getSimpleName() + " is born");
        } catch (Exception ex) {
            System.out.println("it's complicated. Love problems");
        }
    }
}








    /*
    public Animal(int[] coordinate, Wold wold) {
        super(coordinate, wold);
    }




    // after collision on field x,y animal will fight or procreate
    @Override
    public void kolizja(int x, int y) {

        boolean isSameSpiece = wold.gameBoard[x][y].getClass() == wold.gameBoard[coordinate[0]][coordinate[1]].getClass();
        boolean defenderHasFreeSpace = wold.findFreePlace(x, y) != null;
        boolean attacerHasFreeSpace = wold.findFreePlace(coordinate[0], coordinate[1]) != null;

        if (isSameSpiece) {
            if (defenderHasFreeSpace || attacerHasFreeSpace) {
                int newBornX = -1;
                int newBornY = -1;
                if (defenderHasFreeSpace) {
                    newBornX = wold.findFreePlace(x, y)[0];
                    newBornY = wold.findFreePlace(x, y)[1];
                } else if (attacerHasFreeSpace) {
                    newBornX = wold.findFreePlace(coordinate[0], coordinate[1])[0];
                    newBornY = wold.findFreePlace(coordinate[0], coordinate[1])[1];
                }

                int[] newBornCoord = new int[]{newBornX, newBornY};
                Wold newBornWorld = this.wold;
                Class c = this.getClass();


                try {
                    wold.gameBoard[newBornX][newBornY] = (Animal) c.getConstructor(int[].class, Wold.class).newInstance(newBornCoord, newBornWorld);
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
        if (this.strenght >= wold.gameBoard[x][y].getStrenght()) {
                wold.gameBoard[coordinate[0]][coordinate[1]] = null;
                wold.gameBoard[x][y] = this;
                coordinate[0] = x;
                coordinate[1] = y;
                }
                }
                */





