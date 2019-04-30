package com.krzsz.organisms.animals;

import com.krzsz.organisms.Organism;
import com.krzsz.world.World;

import java.util.Scanner;

public class Human extends Animal {


    private static byte immortalityCounter = 0;
    private static byte elixirCounter = 0;
    private static byte gazelleCounter = 0;
    private static byte alzureCounter = 0;
    private static byte deforestationCounter = 0;
    private static boolean isCommandCorrect;


    public Human(int[] coordinate, World world) {
        super(coordinate, world);
        strenght = 5;
        initiative = 4;
        symbol = 'H';
    }


    @Override
    public void action() {
        immortalityCounter--;
        elixirCounter--;
        gazelleCounter--;
        alzureCounter--;
        deforestationCounter--;
        isCommandCorrect = true;
        int[] newCoordinates;

        do {
            newCoordinates = chooseTarget(scanCommand());
        } while (!isCommandCorrect || newCoordinates == null);

        int newX = newCoordinates[0];
        int newY = newCoordinates[1];
        Organism defender = World.gameBoard[newX][newY];

        if (coordinate == newCoordinates) {
            return;
        }

        if (defender == null) {
            moveTo(newCoordinates);
        } else {
            defender.collision(this);
        }
    }


    private String scanCommand() {
        System.out.println("Your move. You can move with 'wsad' keys \n" +
                "or type 'e' to use one of your special abilities");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    private int[] chooseTarget(String command) {
        int deltaX = 0;
        int deltaY = 0;

        switch (command.toLowerCase()) {
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
                isCommandCorrect = true;
                chooseAbility();
                return this.coordinate;
            default:
                isCommandCorrect = false;
                System.out.println("illegal command. You can type: \n" +
                        "'w' to go up \n" +
                        "'a' to go left \n" +
                        "'s' to go down \n" +
                        "'d' to go right");
                return null;
        }

        if (checkIfDestinationIsOnMap(deltaX, deltaY)) {
            isCommandCorrect = true;
            int newX = coordinate[0] + deltaX;
            int newY = coordinate[1] + deltaY;
            return new int[]{newX, newY};
        } else {
            isCommandCorrect = false;
            System.out.println("You can't go outside the map, try again");
            return null;
        }
    }


    private boolean checkIfDestinationIsOnMap(int deltaX, int deltaY) {
        int newX = coordinate[0] + deltaX;
        int newY = coordinate[1] + deltaY;
        return newX >= 0 && newX < World.width
                && newY >= 0 && newY < World.height;
    }


    private void chooseAbility() {
        boolean repeat = false;
        System.out.println("Choose superpower: \n" +
                "\"'0' to activate Immortality (for 5 turns)  \n" +
                "\"'1' to activate magical elixir (super strength for 10 turns)  \n" +
                "\"'2' to gain possibility to move 2 fields at once (for 5 turns) \n" +
                "\"'3' to activate Alzure Shield (animals can't attack you for 5 turns) \n" +
                "\"'4' to activate deforestation (annihilates all beings on fields close to human)");
        do {
            Scanner scanner = new Scanner(System.in);
            String scan = scanner.nextLine();

            switch (scan) {
                case "0":
                    repeat = !immortalityActivation();
                    break;
                case "1":
                    //  repeat = !elixirActivation();
                    break;
                case "2":
                    //  repeat = !gazelleActivation();
                    break;
                case "3":
                    // repeat = !alzureActivation();
                    break;
                case "4":
                    // repeat = !deforestationActivation();
                    break;
                default:
                    System.out.println("illegal command. You can type: \n" +
                            "'0' to activate Immortality (for 5 turns) \n" +
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
        if (immortalityCounter < 6) {
            super.collision(attacker);
            return;
        }
        Immortality(attacker);
    }


    private boolean immortalityActivation() {
        if (immortalityCounter <= 0) {
            immortalityCounter = 10;
            System.out.println("Immortality activated for 5 turns.");
            return true;
        }

        System.out.println("Immortality is not ready yet. Wait another " + immortalityCounter + " turns");
        return false;
    }


    private void Immortality(Organism attacker) {
        if (this.strenght > attacker.getStrenght() ||
                this.strenght == attacker.getStrenght() &&
                        this.getBirthDate().isBefore(attacker.getBirthDate())) {
            this.kill(attacker, false);
        } else {
            System.out.println("Human is immortal. " + attacker.getClass().getSimpleName() + " can't kill him");

            // Human tries to escape, if he don't have enough place, he stays.
            int[] newCoordinates = this.randomFreeCloseCoordinate();
            int[] oldCoordinates = this.coordinate.clone();
            if (newCoordinates == null) {
                return;
            }
            this.moveTo(newCoordinates);
            attacker.moveTo(oldCoordinates);
        }
    }


    public void magicElixir() {

    }

    public void antilopeSpeed() {

    }

    public void alzureShield() {

    }

    public void nuclearHolocaust() {

    }

    public static byte getImmortalityCounter() {
        return immortalityCounter;
    }
}



