package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Door {
    private int x, y;
    private Image image;

    public Door(int x, int y) {
        this.x = x;
        this.y = y;

        try {
            image = ImageIO.read(getClass().getResource("/assets/sinta.png"));
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

    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }
}
