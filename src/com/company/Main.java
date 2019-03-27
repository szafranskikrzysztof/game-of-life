package com.company;

import com.company.world.World;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {


        System.out.println("Welcome to the Game of Life!  " +
                "\n It's a simple console game in which you play lone human " +
                "\n trying to survive in harsh enviroment dominated by deadly animals." +
                "\n Each animals, plant and human are represented by one character" +

                "\n H - Human" +
                "\n A - Antilope    \t G - Grass" +
                "\n F - Fox         \t C - Clover" +
                "\n S - Sheep       \t U - Guarana" +
                "\n W - Wolf        \t B - Belladonna" +
                "\n Z - Turtle" +
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

        boolean isCorrect = false;
        int width = 2;
        int height =2;

        while (!isCorrect) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("Please type world width");
                width = scanner.nextInt();
                System.out.println("Please type world height");
                height = scanner.nextInt();
                if(width > 0 && height > 0){isCorrect = true;}
            } catch (java.util.InputMismatchException e) {
                System.out.println("width and height must be positive integers!");
                isCorrect = false;

            }

        }

        World GameWorld = World.lifeCreator(width, height);
        System.out.println("World was generated. Thats how it looks now:");
        GameWorld.printWorld();



        while (World.isIshumanAlive()) {
            GameWorld.StartNewTurn();
        }


    }


}
