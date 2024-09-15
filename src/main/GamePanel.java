// MlgEpicCar
// Alejandro the Greatest

// GamePanel.java
// 2023/11/28 - Now

package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50; // change this when you change the world map
    public final int maxWorldRow = 50; // change this when you change the world map

    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this,keyH);

    // PLAYER DEFAULT POSITION
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black); // Background Color (Black)
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    // GAME LOOP
    public void run() {

        double drawInterval = 1000000000/FPS; // 0.01667 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {

            // UPDATE POSITION
            update();

            // DRAW (draw updated position)
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000; // converting nanoseconds into milliseconds for thread.sleep

                if(remainingTime < 0) { // failsafe for if someone's computer is worse then a nintendo gameboy
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime); // WARNING: this may be off by milliseconds. past you decided that being off by milliseconds isn't a big enough problem to switch to a delta game loop, but current you may

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) { // ima keep it real, idk what this does
                e.printStackTrace();
            }
        }
    }

    // METHODS
    public void update() { // UPDATE METHOD

        player.update();
    }
    public void paintComponent(Graphics g) { // DRAW METHOD

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        // put these in layers (highest = bottom)
        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}
