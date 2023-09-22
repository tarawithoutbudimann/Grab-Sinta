package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Hero {
    protected int x, y;
    public String imagePath;


    public Hero() {
        this.x = 1100;
        this.y = 270;
        this.imagePath = "/assets/anoman.gif";

    }

    public void moveLeft() {
        x -= 20;
    }


    public void moveRight() {
        x += 20;
    }


    public void moveUp() {
        y -= 20;
    }
    public void moveDown() {
        y += 20;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

//    public void draw(Graphics g) {
//        g.drawImage(heroImage, x, y, null);
//    }
}