package com.krzsz.organisms.animals;

import com.krzsz.organisms.Organism;
import com.krzsz.util.Utils;
import com.krzsz.world.World;

import java.util.Random;

public class Antilope extends Animal {

    public Antilope(int[] coordinate, World world) {
        super(coordinate, world);
        strenght = 4;
        initiative = 4;
        symbol = 'A';
    }

    @Override
    public void collision(Organism attacker) {
        if (Utils.actionSucceed(100)) {
            int[] oldCoord = this.coordinate.clone();
            int[] escapeCoord = randomFreeCloseCoordinate();
            if (escapeCoord != null) {
                moveTo(escapeCoord);
            }
            attacker.moveTo(oldCoord);
            System.out.println("Antilope escapes from " + attacker.getClass().getSimpleName() + " who takes its place");
        } else {
            super.collision(attacker);
        }
    }

    @Override
    // Antilope can move through 2 field at once
    public int[] randomCloseCoordinate() {
        int newX;
        int newY;
        do {
            newX = coordinate[0] + new Random().nextInt(5) - 2;
            newY = coordinate[1] + new Random().nextInt(5) - 2;
        }
        while (newX < 0 || newY < 0 || newX >= World.height || newY >= World.width);
        return new int[]{newX, newY};
    }


}