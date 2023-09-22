package game;

import enemy.*;
import key.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;


public class Game {

    private JFrame frame;
    private GamePanel panel;
    private Timer timer;

    private Hero hero;
    private Door door;
    private ArrayList<Key> keys;
    Key specialKeyA;
    Key specialKeyB;
    Key specialKeyC;
    private ArrayList<Enemy> enemies;
    private int score;
    private int lives;

    public Game() {
        frame = new JFrame("Game");
        panel = new GamePanel();
        door = new Door(50, 270);
        keys = new ArrayList<>();
        specialKeyA = new KeyA(Helper.generateRandomPositionX(), Helper.generateRandomPositionY()); // Instantiate the special keys
        specialKeyB = new KeyB(Helper.generateRandomPositionX(), Helper.generateRandomPositionY());
        specialKeyC = new KeyC(Helper.generateRandomPositionX(), Helper.generateRandomPositionY());
        for (int p = 0; p < 1; p++){
            keys.add(specialKeyA);
            keys.add(specialKeyB);
            keys.add(specialKeyC);
            for (int i = 0; i < 5; i++){
                int keyX = Helper.generateRandomPositionX();
                int keyY = Helper.generateRandomPositionY();
                keys.add(new Key(keyX, keyY, "/assets/koin.gif"));
            }
        }
        enemies = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            enemies.add(new Macan());
        }
        enemies.add(new Rahwana());
        enemies.add(new Kumba());
        score = 0;
        lives = 3;
        keys.add(new Nyawa());
        frame.setTitle("GRAB SINTA");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 650);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(panel);
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        timer.start();
        frame.setVisible(true);
        String message = "<html><body style='width: 350px; font-family: Arial, sans-serif;'>" +
                "<h2 style='text-align: center; color: #FFA500;'>Selamat datang di permainan petualangan epik \"Grab Sinta!\"</h2>" +
                "<p style='text-align: justify;'>Bersiaplah untuk memasuki dunia magis dan mengembara bersama Hanoman dalam misi penyelamatan yang penuh tantangan.</p>" +
                "<hr>" +
                "<h3 style='color: #FFA500;'>Kisah:</h3>" +
                "<p style='text-align: justify;'>Kisah dimulai di Kerajaan Jawa, di mana Shinta, seorang putri yang cantik dan berbudi luhur, telah diculik oleh Rahwana, raja iblis yang jahat. Hal ini mengharuskan Hanoman menjadi salah satu di antara tentara yang diutus oleh Rama yang merupakan suami Shinta untuk menyelamatkan istrinya.</p>" +
                "<hr>" +
                "<h3 style='color: #FFA500;'>Misi Game:</h3>" +
                "<p style='text-align: justify;'>Misi game ini adalah membantu Hanoman dalam misi penyelamatan Shinta. Hanoman harus mendapatkan poin sebanyak 1000 untuk menyelamatkan Shinta. Senjata memiliki poin lebih banyak daripada koin. Usahakan untuk mendapatkan koin dan senjata untuk mencapai 1000 poin lebih cepat. Jika Hanoman terkena musuh, maka poin dan hati akan berkurang, agar Hanoman tidak mengulang sesi game dari awal, dia harus mendapatkan hati agar poin dan hatinya kembali terisi.</p>" +
                "<hr>" +
                "<p style='text-align: center;'>Selamat Bermain!</p>" +
                "</body></html>";

        JOptionPane.showMessageDialog(null, message, "Grab Shinta - Permainan Petualangan", JOptionPane.PLAIN_MESSAGE);

    }

    private void gameWon() {
        String message = "<html><body style='width: 300px; font-family: Arial, sans-serif;'>" +
                "<h2 style='text-align: center; color: #008000;'>Selamat!</h2>" +
                "<p style='text-align: center; font-size: 16px;'>Sinta berhasil diselamatkan!</p>" +
                "<div style='text-align: center; margin-top: 20px;'>" +
                "</div>" +
                "<p style='text-align: center; margin-top: 20px; font-size: 14px;'>Terima kasih atas partisipasi Anda dalam permainan.</p>" +
                "</body></html>";

        JOptionPane.showMessageDialog(null, message, "Pesan Selamat", JOptionPane.PLAIN_MESSAGE);

        System.exit(0);
    }

    private void gameOver() {
        String message = "<html><body style='width: 300px; font-family: Arial, sans-serif;'>" +
                "<h2 style='text-align: center; color: #FF0000;'>Maaf!</h2>" +
                "<p style='text-align: center; font-size: 16px;'>Anda kalah dalam permainan. Sinta gagal diselamatkan</p>" +
                "<div style='text-align: center; margin-top: 20px;'>" +
                "</div>" +
                "<p style='text-align: center; margin-top: 20px; font-size: 14px;'>Jangan menyerah! Coba lagi!</p>" +
                "</body></html>";

        JOptionPane.showMessageDialog(null, message, "Pesan Kekalahan", JOptionPane.PLAIN_MESSAGE);

        System.exit(0);
    }
    private void update() {
        for (Enemy enemy : enemies) {
            enemy.move();
        }
        panel.repaint();
    }
    public class GamePanel extends JPanel implements KeyListener {
        private ImageIcon bgImage;
        private JLabel heroLabel;
        public GamePanel() {
            bgImage = new ImageIcon(getClass().getResource("/assets/yard.png"));
            setLayout(null); // Set layout manager to null for manual component positioning
            hero = new Hero();
            ImageIcon heroIcon = new ImageIcon(getClass().getResource(hero.imagePath));
            // Create a JLabel and set the GIF icon
            heroLabel = new JLabel();
            heroLabel.setIcon(heroIcon);
            // Set the position and size of the JLabel
            heroLabel.setBounds(hero.getX(), hero.getY(), heroIcon.getIconWidth(), heroIcon.getIconHeight());
            // Add the JLabel to the JPanel
            add(heroLabel);
            setFocusable(true);
            addKeyListener(this);
        }

        @Override
        public void keyTyped(KeyEvent keyEvent) {
        }

        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_LEFT) {
                hero.moveLeft(); // Move left by 10 pixels
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                hero.moveRight(); // Move right by 10 pixels
            } else if (keyCode == KeyEvent.VK_UP) {
                hero.moveUp();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                hero.moveDown();
            }

            // Update the position of the JLabel
            heroLabel.setBounds(hero.getX(), hero.getY(), heroLabel.getWidth(), heroLabel.getHeight());
            checkCollision();
            panel.repaint();
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }

        private void checkCollision() {
            Rectangle heroRect = new Rectangle(heroLabel.getX(), heroLabel.getY(), 90, 100);
            for (int i = 0; i < keys.size(); i++) {
                Key key = keys.get(i);
                Rectangle keyRect = new Rectangle(key.getX(), key.getY(), 50, 50);
                if (heroRect.intersects(keyRect)) {
                    keys.remove(i);
                    score += 100;
                    int keyX = Helper.generateRandomPositionX();
                    int keyY = Helper.generateRandomPositionY();
                    if (key instanceof KeyA) {
                        JOptionPane.showMessageDialog(null, "<html><body style='padding: 10px ; border: 3px solid purple; width: 350px'>" +
                                        "<h2 style='text-align: center'>Selamat!</h2>" +
                                        "<p style='text-align: justify'>Anda mendapatkan:</p>" +
                                        "<h3 style='text-align: center'>🔥PANAH BRAHMASTRA🔥</h3>" +
                                        "<p style='text-align: justify'>Panah ini merupakan senjata paling kuat dan mematikan dalam kitab Ramayana. Dalam kisah \"Anoman\" dan \"Sinta\", Rama menggunakan panah Brahmastra untuk mengalahkan Rahwana dan membebaskan Sinta.</p>" +
                                        "<p style='text-align: justify'>Brahmastra adalah senjata surgawi yang hanya bisa digunakan oleh dewa atau makhluk surgawi lainnya. Panah ini memiliki kekuatan besar dan dapat menghancurkan segalanya dalam jangkauannya.</p></body></html>",
                                "Informasi Senjata", JOptionPane.PLAIN_MESSAGE);
                    } else if (key instanceof KeyB) {
                        JOptionPane.showMessageDialog(null, "<html><body style='padding: 10px ; border: 3px solid purple;width: 350px'>" +
                                        "<h2 style='text-align: center'>Selamat!</h2>" +
                                        "<p style='text-align: justify'>Anda mendapatkan:</p>" +
                                        "<h3 style='text-align: center'>🔥PEDANG CHANDRAHASA🔥</h3>" +
                                        "<p style='text-align: justify'>Pedang Chandrahasa merupakan pedang berkekuatan magis yang diberikan oleh Dewa Siwa kepada Rahwana (tokoh antagonis dalam cerita Ramayana). Chandrahasa mampu memancarkan cahaya yang menyilaukan dan memiliki potensi untuk menyebabkan kerusakan besar.</p></body></html>",
                                "Informasi Senjata", JOptionPane.PLAIN_MESSAGE);
                    } else if (key instanceof KeyC) {
                        JOptionPane.showMessageDialog(null, "<html><body style='padding: 10px ; border: 3px solid purple;width: 350px'>" +
                                        "<h2 style='text-align: center'>Selamat!</h2>" +
                                        "<p style='text-align: justify'>Anda mendapatkan:</p>" +
                                        "<h3 style='text-align: center'>🔥NAGASTRA🔥</h3>" +
                                        "<p style='text-align: justify'>Nagastra adalah salah satu jenis divyastra (senjata surgawi) berupa panah naga yang" +
                                        " memiliki kekuatan besar dan mematikan. Ketika dilepaskan, panah ini dapat mengeluarkan suara" +
                                        " menggelegar dan memuntahkan api yang membara. Nagastra memiliki kemampuan untuk menembus pertahanan musuh dan menghancurkan segala yang ada di sekitarnya.</p></body></html>",
                                "Informasi Senjata", JOptionPane.PLAIN_MESSAGE);
                    } else if (key instanceof Nyawa && (lives==1 || lives<3)){
                        keys.add(new Nyawa());
                        lives++;
                    } else if (lives==1 || lives<3){
                        keys.add(new Nyawa());
                    } else {
                        keys.add(new Key(keyX, keyY, "/assets/koin.gif"));
                    }

                }
            }

            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemy = enemies.get(i);
                Rectangle enemyRect = new Rectangle(enemy.getX(), enemy.getY(), 40, 40);

                if (heroRect.intersects(enemyRect)) {
                    score -= enemy.getScoreReduction();
                    if (score <= 0 || lives < 0) {
                        gameOver();
                    } else {
                        lives--;
                    }
                }
            }

            Rectangle doorRect = new Rectangle(door.getX(), door.getY(), 40, 40);
            if (heroRect.intersects(doorRect) && score >= 1000) {
                gameWon();
            }

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bgImage.getImage(), 0, 30, getWidth(), getHeight(), null);
            //point
            for (Key key : keys) {
                key.draw(g);
            }
            //musuh
            for (Enemy enemy : enemies) {
                enemy.draw(g);
            }
//
            door.draw(g);
//            hero.draw(g);
            g.drawString("Score: " + score, 10, 20);
            g.drawString("Lives: " + lives, 100, 20);

        }
    }

    public static void main(String[] args) {
        new Game();
    }
}
