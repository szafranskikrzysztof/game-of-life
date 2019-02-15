package com.company.Organizmy.Zwierzeta;

import com.company.World.World;

import java.util.Scanner;

public class Czlowiek extends Zwierze {


    public Czlowiek(int[] lokalizacja, World world) {
        super(lokalizacja, world);
        sila = 5;
        inicjatywa = 4;
        symbol = 'C';
    }


    @Override
    public void akcja() {
        System.out.println("Twoj ruch, sterujesz klawiszami wasd ");
        Scanner scanner = new Scanner(System.in);
        String scan = scanner.nextLine();
        int deltax = 0;
        int deltay = 0;

        while (true) {
            switch (scan) {
                case "w":
                    deltay = 1;
                case "s":
                    deltay = -1;
                case "a":
                    deltax = -1;
                case "d":
                    deltax = 1;
                default:
                    System.out.println("nieprawidłowa komenda, spróbuj ponownie");
                    deltax = 10000;

            }

            int nowyX = lokalizacja[0] + deltax;
            int nowyY = lokalizacja[1] + deltay;

            if (nowyX < 0 || nowyX > world.szerokosc || nowyY < 0 || nowyY > world.wysokosc) {
                System.out.println("Nie możesz poruszyć się w tym kierunku. Spróbuj ponownie");
            } else {
                if (world.StanPlanszy[nowyX][nowyY] == null) {
                    world.StanPlanszy[lokalizacja[0]][lokalizacja[1]] = null;
                    world.StanPlanszy[nowyX][nowyY] = this;

                    lokalizacja[0] = nowyX;
                    lokalizacja[1] = nowyY;

                } else {

                }
            }
        }
    }


}
