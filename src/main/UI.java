package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import object.OBJ_Chest;
import object.OBJ_Diamond;
import object.OBS_Mud;
import object.POW_Ice;
import object.POW_Lightning;

public class UI {
    GamePanel gamePanel;
    Font arial_40;
    Font arial_80;
    Font showc_70;
    Font showc_30;
    Font twocen_20;
    Font twocen_70;
    BufferedImage gemImage;
    Graphics2D g;
    public int commandNum = 0;
    BufferedImage mapImage;
    BufferedImage chestImage;
    BufferedImage boostImage;
    BufferedImage iceImage;
    BufferedImage mudImage;
    BufferedImage warningImage;
    public boolean messageOn = false;
    public boolean warningOn = false;
    public String notifMessage = "";
    int messageCounter = 0;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        arial_80 = new Font("Arial",Font.PLAIN,80);
        showc_70 = new Font("Showcard Gothic",Font.PLAIN, 70);
        showc_30 = new Font("Showcard Gothic",Font.PLAIN,30);
        twocen_20 = new Font("TW Cen MT", Font.PLAIN, 20);
        twocen_70 = new Font("TW Cen MT", Font.BOLD, 70);
        // Bookman Old Style
       
        OBJ_Diamond gem = new OBJ_Diamond(gamePanel);
        gemImage = gem.image;
        OBJ_Chest chest = new OBJ_Chest(gamePanel);
        chestImage = chest.image;
        POW_Lightning boost = new POW_Lightning(gamePanel);
        boostImage = boost.image;
        POW_Ice ice = new POW_Ice(gamePanel);
        iceImage = ice.image;    
        OBS_Mud mud = new OBS_Mud(gamePanel);
        mudImage = mud.image;   
        try {
            mapImage = ImageIO.read(getClass().getResourceAsStream("/maps/map1.png"));
            warningImage = ImageIO.read(getClass().getResourceAsStream("/object/warning.png"));
        } catch (IOException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void draw(Graphics2D g){  
        this.g = g;

        // title state
        if(gamePanel.gameState == gamePanel.TITLE_STATE){
           drawTitleState();
        }
        
        // play state
        if(gamePanel.gameState == gamePanel.PLAY_STATE) {
            drawPlayState();
            
            if(messageOn == true){
                notifyPlayer(notifMessage);
            }
            
            if(warningOn == true){
                warnPlayer();
            }
        }
        
        // end - won
        if(gamePanel.gameState == gamePanel.GAMEWON_STATE){
            drawGameFinished();
        }
        
        // end - lost
        if(gamePanel.gameState == gamePanel.GAMEOVER_STATE){
            drawGameOverScreen();
        }
        
        
    }
    
    public void drawTitleState(){
        g.setColor(new Color(0x1b263b));
        g.fillRect(0,0, gamePanel.SCREEN_WIDTH, gamePanel.SCREEN_HEIGHT);
        String text;
        int textLength;
        int x;
        int y;

        g.setFont(showc_70);
        g.setColor(Color.white);
        text = "The Hidden Riches:";
        textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        x = (gamePanel.SCREEN_WIDTH/2) - textLength/2;
        y = gamePanel.TILE_SIZE*2;
        g.drawString(text, x, y);

        g.setColor(new Color(0xffb703));
        g.setFont(new Font("TW Cen MT", Font.BOLD, 40));
        text = "The Great Treasure Hunt";
        textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        x = (gamePanel.SCREEN_WIDTH/2) - textLength/2;
        y = y + gamePanel.TILE_SIZE;
        g.drawString(text, x, y);
        
        // mechanics
        g.setColor(Color.WHITE);
        g.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        text = "Game Tips";
        textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        x = (gamePanel.SCREEN_WIDTH/2) - textLength/2;
        y = y + gamePanel.TILE_SIZE*2;
        g.drawString(text, x, y);
        
        g.setFont(twocen_20);
        text = " -  get 5 real treasure to win the game";
        textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        x = (gamePanel.SCREEN_WIDTH/2) - textLength/2;
        y = y + gamePanel.TILE_SIZE - 10;
        g.drawString(text, x, y);
        g.drawImage(chestImage, x - gamePanel.TILE_SIZE,y-25,35,35,null);
        
        text = " -  get object to activate Rapid Charge";
        textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        x = (gamePanel.SCREEN_WIDTH/2) - textLength/2;
        y = y + gamePanel.TILE_SIZE - 10;
        g.drawString(text, x, y);
        g.drawImage(boostImage, x - gamePanel.TILE_SIZE +10,y-25,35,35,null);
        
        text = " -  bump to the object to activate Pirate Freeze";
        textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        x = (gamePanel.SCREEN_WIDTH/2) - textLength/2;
        y = y + gamePanel.TILE_SIZE - 10;
        g.drawString(text, x, y);
        g.drawImage(iceImage, x - 38, y - 20, 30, 30,null);
        
        text = " -  must avoid!!";
        textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        x = (gamePanel.SCREEN_WIDTH/2) - textLength/2;
        y = y + gamePanel.TILE_SIZE - 10;
        g.drawString(text, x, y);
        g.drawImage(mudImage, x - 38, y - 20, 30,30,null);
        
        // menu
        g.setFont(showc_30);
        text = "PLAY";
        textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        x = (gamePanel.SCREEN_WIDTH/2) - textLength/2;
        y = y+gamePanel.TILE_SIZE*2;
        g.drawString(text, x, y);
        
        if(commandNum == 0){
            g.setStroke(new BasicStroke(2));
            g.drawLine(x, y+5, x+textLength, y+5);
        }
        
        text = "QUIT";
        textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        x = (gamePanel.SCREEN_WIDTH/2) - textLength/2;
        y = y + gamePanel.TILE_SIZE;
        g.drawString(text, x, y);
        
        if(commandNum == 1){
            g.setStroke(new BasicStroke(2));
            g.drawLine(x, y+5, x+textLength, y+5);
        }
                
    }
    
    public void drawPlayState(){
        for(int i = gamePanel.player.chestNum; i > 0; i--){
            g.drawImage(gemImage,60*i, 50, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE,null);
        } 
        
        g.setColor(new Color(0,0,0,70));
        g.drawImage(mapImage, 30, gamePanel.SCREEN_HEIGHT - 180, 150, 150, null);
    }
    
    public void drawGameFinished(){
        String text;
        int textLength;
        int x;
        int y;
        g.setColor(new Color(0,0,0,90));
        g.fillRect(0, 0, gamePanel.SCREEN_WIDTH, gamePanel.SCREEN_HEIGHT);
        g.setFont(showc_30);
        g.setColor(Color.white);
        text = "You won!";
        textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        x = (gamePanel.SCREEN_WIDTH/2) - textLength/2;
        y = gamePanel.TILE_SIZE*2;
        g.drawString(text, x, y);

        g.setFont(twocen_70);
        g.setColor(Color.orange);
        text = "Congratulations!";
        textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        x = (gamePanel.SCREEN_WIDTH/2) - textLength/2;
        y = gamePanel.TILE_SIZE*4;
        g.drawString(text, x, y);

        // menu
        g.setFont(showc_30);
        g.setColor(Color.WHITE);
        text = "PLAY AGAIN";
        textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        x = (gamePanel.SCREEN_WIDTH/2) - textLength/2;
        y = y + gamePanel.TILE_SIZE*6;
        g.drawString(text, x, y);
        
        if(commandNum == 0){
            g.setStroke(new BasicStroke(2));
            g.drawLine(x, y+5, x+textLength, y+5);
        }
        
        text = "QUIT";
        textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        x = (gamePanel.SCREEN_WIDTH/2) - textLength/2;
        y = y + gamePanel.TILE_SIZE;
        g.drawString(text, x, y);
        
        if(commandNum == 1){
            g.setStroke(new BasicStroke(2));
            g.drawLine(x, y+5, x+textLength, y+5);
        }        
        
    }
    
    public void drawGameOverScreen(){
        String text;
        int textLength;
        int x;
        int y;
        g.setColor(new Color(0,0,0,90));
        g.fillRect(0, 0, gamePanel.SCREEN_WIDTH, gamePanel.SCREEN_HEIGHT);
        g.setFont(showc_30);
        g.setColor(Color.white);
        text = "You lost!";
        textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        x = (gamePanel.SCREEN_WIDTH/2) - textLength/2;
        y = gamePanel.TILE_SIZE*2;
        g.drawString(text, x, y);

        g.setFont(twocen_70);
        g.setColor(Color.red);
        text = "Game Over.";
        textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        x = (gamePanel.SCREEN_WIDTH/2) - textLength/2;
        y = gamePanel.TILE_SIZE*4;
        g.drawString(text, x, y);

        // menu
        g.setFont(showc_30);
        g.setColor(Color.WHITE);
        text = "TRY AGAIN";
        textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        x = (gamePanel.SCREEN_WIDTH/2) - textLength/2;
        y = y + gamePanel.TILE_SIZE*6;
        g.drawString(text, x, y);
        
        if(commandNum == 0){
            g.setStroke(new BasicStroke(2));
            g.drawLine(x, y+5, x+textLength, y+5);
        }
        
        text = "QUIT";
        textLength = (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
        x = (gamePanel.SCREEN_WIDTH/2) - textLength/2;
        y = y + gamePanel.TILE_SIZE;
        g.drawString(text, x, y);
        
        if(commandNum == 1){
            g.setStroke(new BasicStroke(2));
            g.drawLine(x, y+5, x+textLength, y+5);
        }    

    }
    
    public void notifyPlayer(String message){

        int textLength = (int)g.getFontMetrics().getStringBounds(notifMessage, g).getWidth();
        int x = (gamePanel.SCREEN_WIDTH/2) - textLength/2;
        g.setColor(Color.white);
        g.setFont(twocen_20);

        g.drawString(notifMessage, x, gamePanel.SCREEN_HEIGHT - 80);
        messageCounter++;
        
        if(messageCounter == 120){
            messageOn = false;
            messageCounter = 0;
        }
    }
    
    public void warnPlayer(){
        String warningText = "The opponent is near.";
        int x = (gamePanel.SCREEN_WIDTH/2) - 40;
        int y = gamePanel.SCREEN_HEIGHT - 40;
        g.setColor(Color.red);
        g.setFont(new Font("TW Cen MT", Font.BOLD, 20));

        g.drawString(warningText, x, y);
        g.drawImage(warningImage, x - 40, gamePanel.SCREEN_HEIGHT - 60, 30, 30, null);
        
    }
}

