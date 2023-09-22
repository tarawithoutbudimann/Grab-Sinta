package enemy;

import game.Helper;

public class Macan extends Enemy{

    public Macan() {
        super(Helper.generateRandomPositionX(), Helper.generateRandomPositionY(), Helper.generateRandomValue(), Helper.generateRandomValue(), 50, "/assets/macan.gif");
    }
}
