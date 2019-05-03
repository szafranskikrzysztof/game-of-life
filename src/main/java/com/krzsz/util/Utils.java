package com.krzsz.util;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static boolean actionSucceed(int percentOfProbability) {
        Random rand = new Random();
        int test = rand.nextInt(101);
        return test < percentOfProbability;
    }

    public static void delayBetweenAImoves(long delayInSeconds) throws InterruptedException {
        TimeUnit unit = TimeUnit.SECONDS;
        unit.sleep(delayInSeconds);
    }
}
