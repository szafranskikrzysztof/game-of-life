package com.krzsz.organisms.plants;

import com.krzsz.organisms.Organism;
import com.krzsz.util.PropertiesUtils;
import com.krzsz.world.World;


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
    // todo poprawić tworzenie się nowych organizmów. W tej chwili pojawiają się niekoniecznie na sąsiednich polach.
    public void action() {
        int[] newCoordinate = this.randomFreeCloseCoordinate();
        if (actionSucceed(PropertiesUtils.INSTANCE.getPropertyIntValue("plant.probabilityOfSpread")) && newCoordinate != null) {
            try {
                World.gameBoard[newCoordinate[0]][newCoordinate[1]] = (Plant) this.clone();
                World.gameBoard[newCoordinate[0]][newCoordinate[1]].setCoordinate(newCoordinate);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getClass().getSimpleName() + " spreads across the world");
        } else {
            System.out.println(this.getClass().getSimpleName() + " tries to spread but fails");
        }
    }


}


