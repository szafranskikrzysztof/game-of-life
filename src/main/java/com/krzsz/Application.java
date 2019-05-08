package com.krzsz;

import com.krzsz.world.World;

public class Application {
    public static void main(String[] args) {
        GamePlay.startMessage();
        World GameWorld = GamePlay.createWorld();
        GamePlay.turnStarter(GameWorld);
        GamePlay.endMessage(GameWorld);
    }

}
