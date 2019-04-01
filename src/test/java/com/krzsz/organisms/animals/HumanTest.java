package com.krzsz.organisms.animals;

import com.krzsz.organisms.Organism;
import com.krzsz.world.World;
import org.junit.Assert;
import org.junit.Test;

public class HumanTest {


    Organism[][] o1 = new Organism[3][4];
    World w1 = new World(o1);
    int[] coord = new int[]{1, 1};
    int[] Bordercoord = new int[]{0, 0};
    Human h1 = new Human(coord, w1);
    Human h2 = new Human(Bordercoord, w1);


    @Test
    public void WhenWPressedHumanYCoordinateDecreasesbyOne() {
        Assert.assertEquals(1, h1.chooseTarget("w")[0]);
        Assert.assertEquals(0, h1.chooseTarget("w")[1]);
    }

    @Test
    public void WhenDPressedHumanYCoordinateIncreasesbyOne() {
        Assert.assertEquals(2, h1.chooseTarget("d")[0]);
        Assert.assertEquals(1, h1.chooseTarget("D")[1]);
    }

    @Test
    public void ChooseTargetReturnNullWhenHumanTriesToGoNorthFromTheMap() {
        Assert.assertEquals(null, h2.chooseTarget("W"));
    }



}