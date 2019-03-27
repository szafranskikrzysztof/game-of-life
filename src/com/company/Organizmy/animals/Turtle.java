package com.company.Organizmy.animals;

import com.company.Organizmy.Organism;
import com.company.world.World;

public class Turtle extends Animal {

    public Turtle(int[] coordinate, World world) {
        super(coordinate, world);
        strenght = 2;
        initiative = 1;
        symbol = 'T';
    }

    @Override
    // there's 75% chance that turtle won't move at all
    public void action() {
        if (actionSucceed(25)) {
            super.action();
        } else {
            System.out.println("Turtle is too lazy to move.");
        }
    }

    @Override
    // turtle cannot be killed by animals with strenght lower than 5
    public void collision(Organism attacker) {
        if (attacker.getStrenght() > 5) {
            super.collision(attacker);
        } else {
            System.out.println(attacker.getClass().getSimpleName() + " can't defeat turtle and must withdraw");
        }
    }


}