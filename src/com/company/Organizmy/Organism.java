package com.company.Organizmy;

import com.company.Organizmy.animals.Human;
import com.company.world.World;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Organism implements java.lang.Comparable<Organism>, Cloneable {
    protected static World world;
    protected int[] coordinate;
    protected int initiative;
    protected int strenght;
    protected char symbol;
    private LocalDateTime birthDate;

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public Organism(int[] lokalizacja, World world) {
        this.coordinate = lokalizacja;
        Organism.world = world;
        this.birthDate = LocalDateTime.now();
    }

    public abstract void action();

    public void collision(Organism attacker) {
        if (this.strenght > attacker.getStrenght() ||
                (this.strenght == attacker.getStrenght()
                        && this.getBirthDate().isBefore(attacker.getBirthDate()))) {
            this.kill(attacker, false);
        } else {
            attacker.kill(this, true);
        }
    }

    public void kill(Organism loser, Boolean takeLoserPlace) {
        /* This method does not make loser null. It only makes loser's place on gameboard null */
        int losX = loser.coordinate[0];
        int losY = loser.coordinate[1];


        if(loser instanceof Human){
            System.out.println("Player dies");
            world.setIshumanAlive(false);
        }

        World.gameBoard[losX][losY] = null;

        if (takeLoserPlace) {
            moveTo(loser.coordinate);
        }

        // this method should be overrode - each organism should print its own message
    }

    public void moveTo(int[] newCoordinates) {
        int oldX = this.coordinate[0];
        int oldY = this.coordinate[1];
        int newX = newCoordinates[0];
        int newY = newCoordinates[1];

        // todo check if it don't generate null pointer
        World.gameBoard[oldX][oldY] = null;
        World.gameBoard[newX][newY] = this;

        this.coordinate[0] = newX;
        this.coordinate[1] = newY;
        System.out.println(this.getClass().getSimpleName() + " moves.");
    }

    @Override
    public int compareTo(Organism o) {
        int initiativeComparision = this.initiative - o.initiative;
        if (initiativeComparision == 0 && this.birthDate.isBefore(o.birthDate)) {
            return -1;
        }
        return initiativeComparision;
    }

    public static void printOrganism(Organism organism) {
        if (organism == null) {
            System.out.print("- \t");
        } else {
            System.out.print(organism.symbol + "\t");
        }
    }

    /**
     * Method returns random coordinate close to object. Object coordinate also could be drawn.
     **/
    public int[] randomCloseCoordinate() {
        int x = coordinate[0] + new Random().nextInt(3) - 1;
        int y = coordinate[1] + new Random().nextInt(3) - 1;

        while (x < 0 || y < 0 || y >= World.height || x >= World.width) {
            x = coordinate[0] + new Random().nextInt(3) - 1;
            y = coordinate[1] + new Random().nextInt(3) - 1;
        }

        return new int[]{x, y};
    }


    /**
     * Method returns random free coordinate close to specific object or  returns null if there's no such coordinate
     **/
    public int[] randomFreeCloseCoordinate() {
        int x = coordinate[0];
        int y = coordinate[1];
        List<int[]> freeCoordinates = new ArrayList<>();

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0
                        && j >= 0
                        && i < World.width
                        && j < World.height
                        && World.gameBoard[i][j] == null) {
                    freeCoordinates.add(new int[]{i, j});
                }
            }
        }

        if (freeCoordinates.size() == 0) {
            return null;
        }

        return freeCoordinates.get(new Random().nextInt(freeCoordinates.size()));
    }


    protected boolean actionSucceed(int percentOfProbability) {
        Random rand = new Random();
        int test = rand.nextInt(101);
        return test < percentOfProbability;
    }


    public int getStrenght() {
        return strenght;
    }

    public void setStrenght(int strenght) {
        this.strenght = strenght;
    }


    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        Organism.world = world;
    }
}
