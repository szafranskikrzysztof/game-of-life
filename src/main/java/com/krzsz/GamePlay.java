package com.krzsz;

import com.krzsz.world.World;

import java.util.Scanner;

class GamePlay {



    static void startMessage() {
        System.out.println("Welcome to the Game of Life!  " +
                "\n It's a simple console game in which you play lone human " +
                "\n trying to survive in harsh enviroment dominated by deadly animals." +
                "\n Each animals, plant and human are represented by one character" +
                "\n Your goal is simple - dominate and kill everything on the map" +

                "\n H - Human" +
                "\n A - Antilope    \t G - Grass" +
                "\n F - Fox         \t C - Clover" +
                "\n S - Sheep       \t U - UltraGuarana" +
                "\n W - Wolf        \t B - Belladonna" +
                "\n T - Turtle" +
                "\n");

        boolean isReady = false;
        while (!isReady) {
            System.out.println("Are you ready to start new game? y/n");
            Scanner scanner = new Scanner(System.in);
            String scan = scanner.nextLine();

            switch (scan) {
                case "y":
                    System.out.println("Thats all you had to say!");
                    isReady = true;
                    break;
                case "n":
                    System.out.println("Ok, take your time");
                    break;
                default:
                    System.out.println("well, you should type 'y' or 'n'. I'm just a simple console game \n I don't understand anything else");
            }
        }
    }

    static World createWorld() {
        boolean isCorrect = false;
        int width = 2;
        int height = 2;

        while (!isCorrect) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("Please type world width (min.3)");
                width = scanner.nextInt();
                System.out.println("Please type world height (min. 3)");
                height = scanner.nextInt();
                if (width > 2 && height > 2) {
                    isCorrect = true;
                } else {
                    System.out.println("width and height must be positive integers not smaller than 3!");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("width and height must be positive integers not smaller than 3!");
                isCorrect = false;
            }
        }
        World GameWorld = World.lifeCreator(width, height);
        System.out.println("World was generated. Thats how it looks now:");
        GameWorld.printWorld();
        return GameWorld;
    }

    static void turnStarter(World gameWorld) {
        while (World.getIsHumanAlive() && World.getNumberOfLivingCreatures() > 1) {
            try {
                gameWorld.StartNewTurn();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void endMessage(World gameWorld) {
        if (World.getIsHumanAlive()) {
            System.out.println("Game over. You won!");
        } else {
            System.out.println("You lost!");
        }
        gameWorld.printWorld();

    }


}
