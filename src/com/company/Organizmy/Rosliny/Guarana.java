package com.company.Organizmy.Rosliny;

import com.company.Organizmy.Organizm;
import com.company.World.World;

public class Guarana extends Roslina {

    public Guarana(int[] lokalizacja, World wold) {
        super(lokalizacja, wold);
        symbol = 'G';
    }

    @Override
    public void collision(Organizm attacker) {
        if (this.sila > attacker.getSila()) {
            this.kill(attacker, false);
        } else {
            attacker.kill(this, true);
            attacker.setSila(attacker.getSila() + 3);
        }
    }
}



