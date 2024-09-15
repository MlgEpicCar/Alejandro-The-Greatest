// MlgEpicCar
// Alejandro the Greatest

// Main.java
// 2023/11/28 - Now

package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        System.out.println("hello world");

        // WINDOW PARAMETERS
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Alejandro the Greatest");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();

    }
    
}
