package com.company.World;

import com.company.Organizmy.Organizm;
import com.company.Organizmy.Rosliny.Guarana;
import com.company.Organizmy.Rosliny.Mlecz;
import com.company.Organizmy.Rosliny.Trawa;
import com.company.Organizmy.Rosliny.WilczeJagody;
import com.company.Organizmy.Zwierzeta.*;

import java.util.*;

public class World {
    public Organizm[][] StanPlanszy;
    public int szerokosc = StanPlanszy == null ? 0 : this.StanPlanszy[0].length;
    public int wysokosc = StanPlanszy == null ? 0 : this.StanPlanszy.length;
    private int tura = 0;

    public World() {
    }

    public void setStanPlanszy(Organizm[][] stanPlanszy) {
        StanPlanszy = stanPlanszy;
    }

    public World(Organizm[][] stanPlanszy) {
        StanPlanszy = stanPlanszy;
    }

    public void wykonajTure() {
    }

    public TreeMap<Organizm, Integer> moveOrder() {
        TreeMap<Organizm, Integer> inicjatywaOrganizmow = new TreeMap<>();
        for (Organizm[] kolejnyRzad : this.StanPlanszy) {
            for (Organizm kolejnePole : kolejnyRzad) {
                if (kolejnePole != null) {
                    inicjatywaOrganizmow.put(kolejnePole, kolejnePole.getInicjatywa());
                }
            }
        }
        return inicjatywaOrganizmow;
    }


    public void drawWorld() {
        for (int i = 0; i < StanPlanszy.length; i++) {
            for (int j = 0; j < StanPlanszy[i].length; j++) {

                if (StanPlanszy[i][j] == null) {
                    System.out.print("-");
                    System.out.print("\t");
                } else {
                    StanPlanszy[i][j].rysowanie();
                    System.out.print("\t");
                }
            }
            System.out.println(" ");
        }

    }


    // creates randomly generated world with given dimensions filled with random number of animals
    public static World generateLife(int m, int n) {
        World firstWorld = new World();
        Organizm[][] enviroment = new Organizm[m][n];
        firstWorld.StanPlanszy = enviroment;
        firstWorld.szerokosc = m;
        firstWorld.wysokosc = n;


        // decides how many number of animals will appear in enviroment
        Random rand = new Random();
        Double minOfAnimals = m * n * 0.3;
        Double maxOfAnimals = m * n * 0.6;
        int numberOfAnimals = rand.nextInt(maxOfAnimals.intValue() - minOfAnimals.intValue()) + minOfAnimals.intValue();

        // creates one man and put it in random place in enviroment
        int randX = rand.nextInt(m);
        int randY = rand.nextInt(n);
        int[] coordinate = new int[]{randX, randY};
        enviroment[randX][randY] = new Czlowiek(coordinate, firstWorld);

        // fills enviroment with random number of animals
        for (int i = 0; i < numberOfAnimals; i++) {
            while (enviroment[randX][randY] != null) {
                randX = rand.nextInt(m);
                randY = rand.nextInt(n);
            }
            coordinate = new int[]{randX, randY};

            // draws spiece of animal
            int j = rand.nextInt(9);


            switch (j) {
                case 0:
                    enviroment[randX][randY] = new Antylopa(coordinate, firstWorld);
                    break;
                case 1:
                    enviroment[randX][randY] = new Lis(coordinate, firstWorld);
                    break;
                case 2:
                    enviroment[randX][randY] = new Owca(coordinate, firstWorld);
                    break;
                case 3:
                    enviroment[randX][randY] = new Wilk(coordinate, firstWorld);
                    break;
                case 4:
                    enviroment[randX][randY] = new Zolw(coordinate, firstWorld);
                    break;

                case 5:
                    enviroment[randX][randY] = new Guarana(coordinate, firstWorld);
                    break;
                case 6:
                    enviroment[randX][randY] = new Mlecz(coordinate, firstWorld);
                    break;
                case 7:
                    enviroment[randX][randY] = new Trawa(coordinate, firstWorld);
                    break;
                case 8:
                    enviroment[randX][randY] = new WilczeJagody(coordinate, firstWorld);
                    break;
                default:
                    throw new IllegalArgumentException("Failure during world generation");
            }


        }
        return firstWorld;
    }

}


