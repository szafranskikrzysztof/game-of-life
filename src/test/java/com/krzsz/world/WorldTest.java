package com.krzsz.world;

import com.krzsz.organisms.Organism;
import com.krzsz.organisms.animals.Human;
import com.krzsz.organisms.animals.Wolf;
import com.krzsz.organisms.plants.Belladonna;
import com.krzsz.organisms.plants.Grass;
import org.junit.Assert;
import org.junit.Test;

public class WorldTest {
    Organism[][] o1 = new Organism[3][4];
    World w1 = new World(o1);
    int[] coord1 = new int[]{1, 1};
    int[] coord2 = new int[]{1, 2};
    int[] coord3 = new int[]{1, 3};

    Human h1 = new Human(coord1, w1);
    Wolf w2 = new Wolf(coord2, w1);
    Grass g1 = new Grass(coord3, w1);
    Belladonna b1 = new Belladonna(coord3, w1);

@Test
    public void orderOfMovesShouldSortOrganismByInitiative(){
    Assert.assertEquals(w2, World.orderOfMoves().get(0));


}




}