package com.company.world;

import com.company.Organizmy.Organism;
import com.company.Organizmy.plants.*;
import com.company.Organizmy.animals.*;

import java.util.*;
import java.util.concurrent.TimeUnit;


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

    public void StartNewTurn() throws InterruptedException {
        for (Organism organism : orderOfMoves()) {
            if (!isIshumanAlive()) {
                return;
            }
            if (organism != null) {
                organism.action();
                stopFor2Seconds();
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

    private void stopFor2Seconds() throws InterruptedException {
        TimeUnit unit = TimeUnit.SECONDS;
        long delay = 2L;
        unit.sleep(delay);
    }

    private static List<Organism> orderOfMoves() {
        List<Organism> existingLife = new ArrayList<>();
        for (Organism[] organisms : gameBoard) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                if (organisms[j] != null) {
                    existingLife.add(organisms[j]);
                }
            }
        }
        Collections.sort(existingLife);
        return existingLife;
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

    /**
     * Methods returns random number of organisms depending on game board dimensions
     **/
    private static int randomNumberOfAnimals() {
        Random rand = new Random();
        int minOfAnimals = (int) (height * width * 0.3d);
        int maxOfAnimals = (int) (height * width * 0.6d);
        return rand.nextInt(maxOfAnimals - minOfAnimals) + minOfAnimals;
    }

    /**
     * Creates single man and put it in random place on game board
     **/
    private void addHumanToWorld() {
        int[] coordinate = randomFreeCoordinate();
        int x = coordinate[0];
        int y = coordinate[1];
        gameBoard[x][y] = new Human(randomFreeCoordinate(), this);
    }


    /**
     * Creates randomly generated world of given dimensions filled with random number of animals
     **/
    public static World lifeCreator(int width, int height) {
        World world = new World(new Organism[width][height]);
        world.addHumanToWorld();

        // adds random number of animals on game board
        for (int i = 0; i < randomNumberOfAnimals(); i++) {

            int[] coordinate = world.randomFreeCoordinate();
            int x = coordinate[0];
            int y = coordinate[1];

            Random rand = new Random();
            int animalID = rand.nextInt(9);
            switch (animalID) {
                case 0:
                    gameBoard[x][y] = new Antilope(coordinate, world);
                    break;
                case 1:
                    gameBoard[x][y] = new Fox(coordinate, world);
                    break;
                case 2:
                    gameBoard[x][y] = new Sheep(coordinate, world);
                    break;
                case 3:
                    gameBoard[x][y] = new Wolf(coordinate, world);
                    break;
                case 4:
                    gameBoard[x][y] = new Turtle(coordinate, world);
                    break;

                case 5:
                    gameBoard[x][y] = new Guarana(coordinate, world);
                    break;
                case 6:
                    gameBoard[x][y] = new Clover(coordinate, world);
                    break;
                case 7:
                    gameBoard[x][y] = new Grass(coordinate, world);
                    break;
                case 8:
                    gameBoard[x][y] = new Belladonna(coordinate, world);
                    break;
                default:
                    throw new IllegalArgumentException("Failure during world generation");
            }
        }
        return world;
    }

    public static void setHeight(int height) {
        World.height = height;
    }

    public static void setWidth(int width) {
        World.width = width;
    }

    public void setIshumanAlive(boolean ishumanAlive) {
        World.ishumanAlive = ishumanAlive;
    }

    public static boolean isIshumanAlive() {
        return ishumanAlive;
    }
}


