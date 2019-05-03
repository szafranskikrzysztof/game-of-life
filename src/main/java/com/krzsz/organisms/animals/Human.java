package com.krzsz.organisms.animals;

import com.krzsz.organisms.Organism;
import com.krzsz.powers.*;
import com.krzsz.world.World;

import java.util.Scanner;

public class Human extends Animal {


    private static boolean isCommandCorrect;


    public Human(int[] coordinate, World world) {
        super(coordinate, world);
        strenght = 5;
        initiative = 4;
        symbol = 'H';
    }

    @Override
    public void action() {
        Immortality.IMMORTALITY_COUNTER--;
        MagicalElixir.ELIXIR_COUNTER--;
        GazelleSpeed.GAZELLE_SPEED_COUNTER--;
        AlzureShield.ALZURE_SHIELD_COUNTER--;
        Deforestation.DEFORESTATION_COUNTER--;
        isCommandCorrect = true;
        int[] newCoordinates;
        System.out.println("Human current strength is " + getStrenght());
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
        int[] vector = new int[]{deltaX, deltaY};
        vector = GazelleSpeed.GazelleSpeedEffect(vector);

        if (checkIfDestinationIsOnMap(vector)) {
            isCommandCorrect = true;
            int newX = coordinate[0] + vector[0];
            int newY = coordinate[1] + vector[1];
            return new int[]{newX, newY};
        } else {
            isCommandCorrect = false;
            System.out.println("You can't go outside the map, try again");
            return null;
        }
    }


    private boolean checkIfDestinationIsOnMap(int[] vector) {
        int newX = coordinate[0] + vector[0];
        int newY = coordinate[1] + vector[1];
        return newX >= 0 && newX < World.width
                && newY >= 0 && newY < World.height;
    }


    private void chooseAbility() {
        boolean dontRepeat;
        do {
            System.out.println("Choose superpower: \n" +
                    "\"'0' to activate immortality (for 5 turns)  \n" +
                    "\"'1' to activate magical elixir (super strength for 10 turns)  \n" +
                    "\"'2' to activate gazelle speed  (possibility to move 2 fields at once (for 5 turns)) \n" +
                    "\"'3' to activate Alzure Shield (animals can't attack you for 5 turns) \n" +
                    "\"'4' to activate deforestation (annihilates all beings on fields close to human)");
            Scanner scanner = new Scanner(System.in);
            String scan = scanner.nextLine();

            switch (scan) {
                case "0":
                    dontRepeat = activation(new Immortality());
                    break;
                case "1":
                    dontRepeat = activation(new MagicalElixir());
                    break;
                case "2":
                    dontRepeat = activation(new GazelleSpeed());
                    break;
                case "3":
                    dontRepeat = activation(new AlzureShield());
                    break;
                case "4":
                    dontRepeat = activation(new Deforestation());
                    break;
                case "5":
                    action();
                    dontRepeat = true;
                    break;
                default:
                    System.out.println("illegal command. You can type: \n" +
                            "'0' to activate immortality (for 5 turns) \n" +
                            "'1' to activate magical elixir (strength rises to 10 and then slowly comes back to normal value) \n" +
                            "'2' to activate Gazelle Speed (possibility to move 2 fields at once (for 5 turns)) \n" +
                            "'3' to activate Alzure Shield (animals can't attack you for 5 turns) \n" +
                            "'4' to activate deforestation (annihilates all beings on fields close to human \n" +
                            "'5' to choose move direction");
                    dontRepeat = false;
            }

        } while (!dontRepeat);
    }


    @Override
    public void collision(Organism attacker) {
        if (Immortality.IMMORTALITY_COUNTER < 6 && AlzureShield.ALZURE_SHIELD_COUNTER < 6) {
            super.collision(attacker);
            return;
        }
        if (AlzureShield.ALZURE_SHIELD_COUNTER >= 6) {
            collisionWithAlzureShield(attacker);
            return;
        }
        collisionWithImmortality(attacker);
    }


    private boolean activation(Power power) {
        if (power.getCOUNTER() < 0) {
            power.setCOUNTER((byte) 11);
            System.out.println(power.getName() + " activated for 5 turns.");
            if (power.getName().equals("Magical Elixir")) {
                MagicalElixir.HUMAN_START_STRENGTH = (byte) this.strenght;
            }
            if (power.getName().equals("Deforestation")) {
                deforestation();
            }
            return true;
        }
        System.out.println(power.getName() + " is not ready yet. Wait another " + power.getCOUNTER() + " turns");
        return false;
    }


    private void collisionWithImmortality(Organism attacker) {
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

    private void collisionWithAlzureShield(Organism attacker) {
        System.out.println(attacker.getClass().getSimpleName() + " can't go through alzure shield and must withdraw");
        int[] newCoordinate = this.randomFreeCloseCoordinate();
        if (newCoordinate != null) {
            attacker.moveTo(newCoordinate);
        }
    }

    private void deforestation() {
        System.out.println("Human commits deforestation. All nearby nature dies.");
        for (int dX = -1; dX <= 1; dX++) {
            for (int dY = -1; dY <= 1; dY++) {
                if (dX == 0 && dY == 0) {
                    continue;
                }
                if (checkIfDestinationIsOnMap(new int[]{dX, dY})) {
                    int x = coordinate[0] + dX;
                    int y = coordinate[1] + dY;
                    if (World.gameBoard[x][y] != null) {
                        World.gameBoard[x][y].setCoordinate(new int[]{-10, -10});
                        World.gameBoard[x][y] = null;
                    }
                }
            }
        }
    }

    @Override
    public int getStrenght() {
        if (MagicalElixir.ELIXIR_COUNTER > strenght) {
            return MagicalElixir.ELIXIR_COUNTER;
        }
        return strenght;
    }
}



