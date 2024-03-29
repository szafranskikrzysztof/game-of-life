package com.krzsz.world;

import com.krzsz.organisms.Organism;
import com.krzsz.organisms.OrganismFactoryImpl;
import com.krzsz.organisms.animals.Human;
import com.krzsz.util.PropertiesUtils;
import com.krzsz.util.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;




public class World {
    public static Organism[][] gameBoard;
    public static int height;
    public static int width;

    private static boolean ishumanAlive = true;



    public World() {
    }

    public World(Organism[][] gameBoard) {
        World.gameBoard = gameBoard;
        height = gameBoard[0].length;
        width = gameBoard.length;
    }

    /**
     * Creates randomly generated world of given dimensions filled with random number of animals
     **/
    public static World lifeCreator(int width, int height) {
        World world = new World(new Organism[width][height]);
        OrganismFactoryImpl organismFactory = new OrganismFactoryImpl(world);
        world.addHumanToWorld();

        // adds random number of animals on game board
        for (int i = 0; i < randomNumberOfAnimals(); i++) {
            int[] coordinate = world.randomFreeCoordinate();
            int x = coordinate[0];
            int y = coordinate[1];
            gameBoard[x][y] = organismFactory.create(coordinate);
        }
        return world;
    }

    public void StartNewTurn() throws InterruptedException {
        for (Organism organism : orderOfMoves()) {
            if (!getIsHumanAlive()) {
                return;
            }
            if (organism != null && organism.getCoordinate()[0]>=0) {
                organism.action();
                Utils.delayBetweenAImoves(PropertiesUtils.INSTANCE.getPropertyIntValue("util.delayBetweenAImoves"));
                printWorld();
            }
        }

    }

    public void printWorld() {
        System.out.println("-----------------------");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Organism.printOrganism(gameBoard[x][y]);
            }
            System.out.print("\n");
        }
        System.out.println("----------------------");
    }

    private int[] randomFreeCoordinate() {
        Random rand = new Random();
        int x = rand.nextInt(width);
        int y = rand.nextInt(height);
        while (gameBoard[x][y] != null) {
            x = rand.nextInt(width);
            y = rand.nextInt(height);
        }
        return new int[]{x, y};
    }

     private static List<Organism> orderOfMoves() {
             return Arrays.stream(gameBoard)
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Methods returns random number of organisms depending on game board dimensions
     **/
    private static int randomNumberOfAnimals() {
        Random rand = new Random();
        int minOfAnimals = (int) (height * width * PropertiesUtils.INSTANCE.getPropertyIntValue("world.minFill")/100d);
        int maxOfAnimals = (int) (height * width * PropertiesUtils.INSTANCE.getPropertyIntValue("world.maxFill")/100d);
        return rand.nextInt(maxOfAnimals - minOfAnimals) + minOfAnimals;
    }

    /**
     * Creates single man and put it in random place on game board
     **/
    private void addHumanToWorld() {
        int[] coordinate = randomFreeCoordinate();
        int x = coordinate[0];
        int y = coordinate[1];
        gameBoard[x][y] = new Human(coordinate, this);
    }

    public static int getNumberOfLivingCreatures() {
        return (int) Arrays.stream(World.gameBoard).flatMap(Arrays::stream).filter(Objects::nonNull).count();
    }

    public void setIsHumanAlive(boolean ishumanAlive) {
        World.ishumanAlive = ishumanAlive;
    }

    public static boolean getIsHumanAlive() {
        return ishumanAlive;
    }
}


