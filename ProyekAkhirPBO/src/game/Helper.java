package game;

import java.util.Random;

public final class Helper {
    public static int generateRandomPositionX() {
        Random rand = new Random();
        return rand.nextInt(1000 - 300) + 300;
    }

    public static int generateRandomPositionY() {
        Random rand = new Random();
        return rand.nextInt(500 - 25) + 25;
    }
    public static int generateRandomValue() {
        Random rand = new Random();
        return rand.nextInt(-1 - (-3) + 1) + (-3);
    }


}
