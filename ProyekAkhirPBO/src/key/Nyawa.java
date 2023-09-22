package key;

import game.Helper;

import java.awt.*;

public class Nyawa extends Key {
    private int x, y;
    private Image image;


    public Nyawa() {
            super(Helper.generateRandomPositionX(), Helper.generateRandomPositionY(), "/assets/nyawa.png");
        }
}
