package com.krzsz.organisms;

import com.krzsz.organisms.animals.*;
import com.krzsz.organisms.plants.Belladonna;
import com.krzsz.organisms.plants.Clover;
import com.krzsz.organisms.plants.Grass;
import com.krzsz.organisms.plants.UltraGuarana;
import com.krzsz.world.World;

import java.util.Random;

public class OrganismFactoryImpl implements OrganismFactory {
    private World world;

    public OrganismFactoryImpl(World world) {
        this.world = world;
    }

    @Override
    public Organism create(int[] coordinate) {
        Random rand = new Random();
        int animalID = rand.nextInt(9);
        switch (animalID) {
            case 0:
                return new Antilope(coordinate, world);
            case 1:
                return new Fox(coordinate, world);
            case 2:
                return new Sheep(coordinate, world);
            case 3:
                return new Wolf(coordinate, world);
            case 4:
                return new Turtle(coordinate, world);
            case 5:
                return new UltraGuarana(coordinate, world);
            case 6:
                return new Clover(coordinate, world);
            case 7:
                return new Grass(coordinate, world);
            case 8:
                return new Belladonna(coordinate, world);
            default:
                throw new IllegalArgumentException("Failure during organism creation");
        }
    }
}
