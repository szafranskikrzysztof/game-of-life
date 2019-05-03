package com.krzsz.organisms.animals;

import com.krzsz.organisms.Organism;
import com.krzsz.world.World;


public class Fox extends Animal {

    public Fox(int[] coordinate, World world) {
        super(coordinate, world);
        strenght = 4;
        initiative = 7;
        symbol = 'F';
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
        } else if (defender.getStrenght() >= strenght) {
            System.out.println("Fox don't move to field occuppied by stronger animal");
        } else {
            System.out.println(this.getClass().getSimpleName() + " attacks " + defender.getClass().getSimpleName());
            defender.collision(this);
        }
    }

}