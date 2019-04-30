package com.krzsz.organisms.animals;

import com.krzsz.organisms.Organism;
import com.krzsz.organisms.plants.Plant;
import com.krzsz.world.World;

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