package com.company.Organizmy.plants;

import com.company.Organizmy.Organism;
import com.company.world.World;


public abstract class Plant extends Organism implements Cloneable {


    Plant(int[] lokalizacja, World world) {
        super(lokalizacja, world);
        strenght = 0;
        initiative = 0;
    }

    @Override
    public void kill(Organism loser, Boolean takeLoserPlace) {
        String winerName = this.getClass().getSimpleName();
        String loserName = loser.getClass().getSimpleName();

        super.kill(loser, takeLoserPlace);

        // In case of collision, plant always disappear from the gameboard (is eaten)
        int plantX = this.coordinate[0];
        int plantY = this.coordinate[1];
        World.gameBoard[plantX][plantY] = null;

        System.out.println("Poisonous " + winerName + " is eaten by " + loserName + " and kills it.");
    }

    @Override
    public void action() {
        int[] newCoordinate = this.randomFreeCloseCoordinate();
        if (actionSucceed(30) && newCoordinate != null) {
            try {
                World.gameBoard[newCoordinate[0]][newCoordinate[1]] = (Plant) this.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getClass().getSimpleName() + " spreads across the world");
        } else {
            System.out.println(this.getClass().getSimpleName() + " tries to spread but fails");
        }
    }


}


