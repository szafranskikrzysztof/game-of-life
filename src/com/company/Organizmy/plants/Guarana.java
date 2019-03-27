package com.company.Organizmy.plants;

import com.company.Organizmy.Organism;
import com.company.world.World;

public class Guarana extends Plant {

    public Guarana(int[] coordinate, World world) {
        super(coordinate, world);
        symbol = 'U';
    }

    @Override
    public void collision(Organism attacker) {
        attacker.kill(this, true);
        attacker.setStrenght(attacker.getStrenght() + 3);
    }
}




