package com.krzsz.organisms.plants;

import com.krzsz.organisms.Organism;
import com.krzsz.world.World;

public class UltraGuarana extends Plant {

    public UltraGuarana(int[] coordinate, World world) {
        super(coordinate, world);
        symbol = 'U';
    }

    @Override
    public void collision(Organism attacker) {
        attacker.kill(this, true);
        attacker.setStrenght(attacker.getStrenght() + 3);
    }
}




