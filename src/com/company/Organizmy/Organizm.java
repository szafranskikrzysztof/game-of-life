package com.company.Organizmy;

import com.company.World.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Organizm implements Comparable<Organizm>, Cloneable{
    protected int inicjatywa =0;
    protected int sila = 0;
    protected int[] lokalizacja;
    protected int turaNarodzin;
    protected char symbol;
    protected World world;

    public Organizm(int[] lokalizacja, World world) {
        this.lokalizacja = lokalizacja;
        this.world = world;
    }




    @Override
    public int compareTo(Organizm o) {
        if (this.getInicjatywa() > o.getInicjatywa()) {
            return 1;
        } else if ((this.getInicjatywa() == o.getInicjatywa()) && (this.turaNarodzin < o.turaNarodzin)) {
            return 1;
        } else {
            return -1;
    }

}




    public int getInicjatywa() {
        return inicjatywa;
    }

    public void setInicjatywa(int inicjatywa) {
        this.inicjatywa = inicjatywa;
    }

    public int getSila() {
        return sila;
    }

    public void setSila(int sila) {
        this.sila = sila;
    }



    public int[] getLokalizacja() {
        return lokalizacja;
    }

    public void setLokalizacja(int[] lokalizacja) {
        this.lokalizacja = lokalizacja;
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
        this.world = world;
    }

    public abstract void akcja();



    public void rysowanie() {
        System.out.print(this.symbol);
    }

    // returns random coordinate close to object
    public int[] randomCloseCoordinate() {
        int newX;
        int newY;
        do {
            newX = lokalizacja[0] + new Random().nextInt(3) - 1;
            newY = lokalizacja[1] + new Random().nextInt(3) - 1;
        }
        while (newX < 0 || newY < 0 || newX >= world.szerokosc || newY >= world.wysokosc);
        return new int[]{newX, newY};
    }

    // find random free coordinate close to specyfic object or returns null
    public int[] randomFreeCloseCoordinate() {
        int x = this.getLokalizacja()[0];
        int y = this.getLokalizacja()[1];
        List<int[]> freeCoordinates = new ArrayList<>();
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (i >= 0 && j >= 0 && i < world.szerokosc && j < world.wysokosc && world.StanPlanszy[i][j] == null) {
                    int[] freeCoordinate = new int[]{i, j};
                    freeCoordinates.add(freeCoordinate);
                }
            }
        }

        if (freeCoordinates.size() == 0) {
            return null;
        } else {
            Random rand = new Random();
            return freeCoordinates.get(rand.nextInt(freeCoordinates.size()));
        }
    }


    public boolean actionSucceed(int percentOfProbability) {
        Random rand = new Random();
        int test = rand.nextInt(101);
        return test < percentOfProbability ? true : false;
    }


    public void kill (Organizm loser, Boolean takePlace) {
        int winX = this.lokalizacja[0];
        int winY = this.lokalizacja[1];
        int losX = loser.getLokalizacja()[0];
        int losY = loser.getLokalizacja()[1];

        this.lokalizacja[0] = losX;
        this.lokalizacja[1] = losY;
        loser = null;

        if(takePlace) {
            world.StanPlanszy[losX][losY] = this;
            world.StanPlanszy[winX][winY] = null;
            System.out.println(this.getClass() + "zwycięża i zabija " + loser.getClass());
        }

    }


    public void collision(Organizm attacker) {
        if (this.sila > attacker.getSila()) {
            this.kill(attacker, false);
        } else {
            attacker.kill(this, true);
        }
    }

    public void moveTo(int[] coordinates){
        int oldX = this.lokalizacja[0];
        int oldY = this.lokalizacja[1];
        int newX = coordinates[0];
        int newY = coordinates[1];

        world.StanPlanszy[oldX][oldY] = null;
        world.StanPlanszy[newX][newY] = this;

        this.lokalizacja[0] = newX;
        this.lokalizacja[1] = newY;
        System.out.println(this.getClass() + "porusza się.");
    }
}
