package key;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Key {
    protected int x, y;
    protected Image keyImage;
    protected String imagePath;

    public Key(int x, int y, String imagePath) {
        this.x = x;
        this.y = y;

        try {
            keyImage = ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.imagePath = imagePath;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void draw(Graphics g) {
        g.drawImage(keyImage, x, y, null);
    }
}
