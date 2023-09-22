package enemy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;


public abstract class Enemy {
    private int x, y;
    private int velocityX, velocityY;
    private Image image;
    private int scoreReduction;

    public Enemy(int x, int y, int velocityX, int velocityY, int scoreReduction, String getImage) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.scoreReduction = scoreReduction;

        try {
            image = ImageIO.read(getClass().getResource(getImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move() {
        x += velocityX;
        y += velocityY;

        if (x < 100 || x > 1000) {
            velocityX *= -1;
        }

        if (y < 30 || y > 500) {
            velocityY *= -1;
        }
    }

    public int getScoreReduction() {
        return scoreReduction;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }
}
