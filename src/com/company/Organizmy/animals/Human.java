package com.company.Organizmy.animals;

import com.company.Organizmy.Organism;
import com.company.world.World;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Human extends Animal {
    private static short turnCounter = 0;
    private static byte immortalityCounter = 0;
    private static byte elixirCounter = 0;
    private static byte gazelleCounter = 0;
    private static byte alzureCounter = 0;
    private static byte holocaustCounter = 0;
    boolean isAlive = true;


    public Human(int[] coordinate, World world) {
        super(coordinate, world);
        strenght = 5;
        initiative = 4;
        symbol = 'H';
    }


    @Override
    public void action() {
        int[] newCoordinates = chooseDirection();
        if (newCoordinates == null) {
            return;
        }

        int newX = newCoordinates[0];
        int newY = newCoordinates[1];

        Organism defender = world.gameBoard[newX][newY];

        if (defender == null) {
            this.moveTo(newCoordinates);
        } else {
            defender.collision(this);
        }
    }


    public int[] chooseDirection() {
        int deltaY = 0;
        int deltaX = 0;

        boolean askAgain = false;
        int[] newCoord = null;

        do {
            System.out.println("Your move. You can move with 'wsad' keys \n" +
                    "or type 'e' to use one of your special abilities");
            Scanner scanner = new Scanner(System.in);
            String scan = scanner.nextLine();

            switch (scan) {
                case "w":
                    deltaY = -1;
                    break;
                case "s":
                    deltaY = 1;
                    break;
                case "a":
                    deltaX = -1;
                    break;
                case "d":
                    deltaX = 1;
                    break;
                case "e":
                    chooseAbility();
                    return null;
                default:
                    System.out.println("illegal command. You can type: \n" +
                            "'w' to go up \n" +
                            "'a' to go left \n" +
                            "'s' to go down \n" +
                            "'d' to go right");
                    deltaY = 0;
                    askAgain = true;

            }
            int newY = coordinate[0] + deltaY;
            int newX = coordinate[1] + deltaX;
            newCoord = new int[]{newY, newX};
            if (newX < 0 || newX > world.height || newY < 0 || newY > world.width) {
                System.out.println("You can't go outside the map, try again");
                askAgain = true;
            } else {
                askAgain = false;
            }
        } while (askAgain);
        return newCoord;
    }


    public void chooseAbility() {
        boolean repeat = false;
        do {
            System.out.println("You have ");
            Scanner scanner = new Scanner(System.in);
            String scan = scanner.nextLine();

            switch (scan) {
                case "0":
                    if (immortalityCounter > 0) {
                        System.out.println("Immortality will be availble again in " + immortalityCounter + "turns");
                        repeat = true;
                    } else {
                        immortalityCounter = 10;
                    }
                    break;
                case "1":
                    if (elixirCounter > 0) {
                        System.out.println("Magical elixir will be availble again in " + elixirCounter + "turns");
                        repeat = true;
                    } else {
                        elixirCounter = 10;
                    }
                    break;
                case "2":
                    if (gazelleCounter > 0) {
                        System.out.println("Antilope speed will be availble again in " + gazelleCounter + "turns");
                        repeat = true;
                    } else {
                        gazelleCounter = 10;
                    }
                    break;
                case "3":
                    if (alzureCounter > 0) {
                        System.out.println("Alzure shield will be availble again in " + alzureCounter + "turns");
                        repeat = true;
                    } else {
                        alzureCounter = 10;
                    }
                    break;
                case "4":
                    if (holocaustCounter > 0) {
                        System.out.println("Nuclear holocaust will be availble again in " + holocaustCounter + "turns");
                        repeat = true;
                    } else {
                        holocaustCounter = 10;
                    }
                    break;
                default:
                    System.out.println("illegal command. You can type: \n" +
                            "'0' to activate immortality (for 5 turns) \n" +
                            "'1' to activate magical elixir (super strength for 10 turns) \n" +
                            "'2' to gain possibility to move 2 fields at once (for 5 turns) \n" +
                            "'3' to activate Alzure Shield (animals can't attack you for 5 turns) \n" +
                            "'4' to activate deforestation (annihilates all beings on fields close to human");
                    repeat = true;

            }

        } while (repeat);
    }


    @Override
    public void collision(Organism attacker) {

        if (immortalityCounter > 0) {
            immortality(attacker);
            immortalityCounter--;
        }
        if (elixirCounter > 0) {
        }
        if (gazelleCounter > 0) {
        }
        if (alzureCounter > 0) {
        }
        if (holocaustCounter > 0) {
        }

        super.collision(attacker);
    }


    public void immortality(Organism attacker) {
        if (this.strenght > attacker.getStrenght() || this.getBirthDate().isBefore(attacker.getBirthDate())) {
            this.kill(attacker, false);
        } else {
            System.out.println(attacker.getClass().getSimpleName() + " tries but can't kill man");
        }
    }


}



