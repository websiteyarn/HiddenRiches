package main;

import javax.swing.*;

public class Main extends JFrame{
    GamePanel gamePanel = new GamePanel();
    
    Main(){
        // Game Panel
        this.add(gamePanel);
                
        this.setTitle("The Hidden Riches");
        ImageIcon img = new ImageIcon("icon.png");
        setIconImage(img.getImage());
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        gamePanel.setupGame();
        gamePanel.gameThreadStart();
    }

    public static void main(String[] args) {
        Main m = new Main();
    }
}
