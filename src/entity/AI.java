package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;


public class AI extends Entity{
    
    public AI(GamePanel gamePanel) {
        super(gamePanel);
        
        direction="left";
        speed = 4;
        AIImage();
        onPath = true;
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
    }
    
    public void AIImage(){
        System.out.println("loading npc image");
        up1 = setupImage("Pirate Run Backward 1.png");
        up2 = setupImage("Pirate Run Backward 2.png");
        down1 = setupImage("Pirate Run Forward 1.png");
        down2 = setupImage("Pirate Run Forward 2.png");
        left1 = setupImage("Pirate Run Left 1.png");
        left2 = setupImage("Pirate Run Left 2.png");
        right1 = setupImage("Pirate Run Right 1.png");
        right2 = setupImage("Pirate Run Right 2.png");
        System.out.println("all npc load complete");   
        System.out.println(speed);

    }    
    
    public BufferedImage setupImage(String imgName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            
            image = ImageIO.read(getClass().getResourceAsStream("/res/ai/"+ imgName));
            image = uTool.scaleImage(image, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
            
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }
                
    @Override
    public void setAction(){
        if(gamePanel.gameState == gamePanel.PLAY_STATE){
            if(onPath == true){
                // int goalCol = 40;
                // int goalRow = 40;
                int goalCol =(gamePanel.player.worldXLocation + gamePanel.player.solidArea.x)/gamePanel.TILE_SIZE;
                int goalRow =(gamePanel.player.worldYLocation + gamePanel.player.solidArea.y)/gamePanel.TILE_SIZE;
                searchPath(goalCol, goalRow);
            }            
        }

    }
}