package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import main.*;

public class Player extends Entity{
    
    KeyEventListener keyListener;
    
    public final int screenX;
    public final int screenY; 
    public int chestNum;
    public int speedCounter;
    public boolean countPlayerSpeedUp = false;
    public boolean countPlayerSpeedDown = false;    
    int playerSpeedUpCounter;
    int playerSpeedDownCounter;

    public Player(GamePanel gamePanel, KeyEventListener keyListener) {
        super(gamePanel);
        // assigning parameter to var so we can access gamePanel na pinasa
        
        this.keyListener = keyListener;
        screenX = gamePanel.SCREEN_WIDTH/2 - (gamePanel.TILE_SIZE/2);
        screenY = gamePanel.SCREEN_HEIGHT/2 - (gamePanel.TILE_SIZE/2);
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        this.playerImage();
        this.defaultValues();
    }
    
    public void playerImage(){
        // sets the player image
        System.out.println("loading player image");
        up1 = setupImage("Hunter Run Backward 1");
        up2 = setupImage("Hunter Run Backward 2");
        down1 = setupImage("Hunter Run Forward 1");
        down2 = setupImage("Hunter Run Forward 2");
        left1 = setupImage("Hunter Run Left 1");
        left2 = setupImage("Hunter Run Left 2");
        right1 = setupImage("Hunter Run Right 1");
        right2 = setupImage("Hunter Run Right 2");
        System.out.println("all player load complete");
    }
    
    public BufferedImage setupImage(String imgName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/player/"+ imgName + ".png"));
            image = uTool.scaleImage(image, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }
    
    public void defaultValues(){
        // sets defaults values at the beginning of the game
        
        worldXLocation = gamePanel.TILE_SIZE * 40;
        worldYLocation = gamePanel.TILE_SIZE * 40;
        speed = 5;
        direction = "left";
        chestNum = 0;
    }
    
    public void update(){
        /* 
         * tells what image should be displayed
         * checks collision (tile, object)
         * modify worldX/Y Location 
         */
        
        // change image only if key pressed
        if(keyListener.upKey || keyListener.downKey || keyListener.leftKey || keyListener.rightKey){
            // tells what image should be displayed
            if(keyListener.upKey == true){
                direction = "up"; 
            }

            if(keyListener.downKey == true){
                direction = "down";
            }

            if(keyListener.leftKey == true){
                direction = "left";
            }

            if(keyListener.rightKey == true){
                direction = "right";
            }

            // check tile collision
            collisionOn = false;
            // determines the value of collisionOn
            gamePanel.collisionChecker.checkTile(this);
            
            // check object collision
            int objIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickupObject(objIndex);
            
            // check npc collision
            int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
            interactNPC(npcIndex);
            
            // collisionOn = true, player will NOT move
            // collisionOn = false, player move
            // player can move only when the tile is not solid
            if(collisionOn == false){
                switch(direction){
                    case "up": 
                        worldYLocation -= speed;
                        break;
                    case "down":
                        worldYLocation += speed;
                        break;
                    case "left":
                        worldXLocation -= speed;
                        break;
                    case "right":
                        worldXLocation += speed;
                        break;                        
                }
            }
            
            spriteCounter++;
            // passes 12 frames of 60 FPS until it changes the img
            if(spriteCounter > 12){
                if(spriteNum == 1)
                    spriteNum = 2;
                else if(spriteNum == 2)
                    spriteNum = 1;
                spriteCounter = 0;
            }      
        }
        
        if(countPlayerSpeedUp == true){
            playerSpeedUp();
        }
        if(countPlayerSpeedDown==true){
            playerSpeedDown();
        }  
        
    }
    
    public void draw(Graphics2D g){
        // draw player image based on direction
        
        BufferedImage img = null;
        
        switch(direction){
            case "up":
                if(spriteNum == 1){
                    img = up1;
                }
                if(spriteNum == 2){
                    img = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    img = down1;
                }
                if(spriteNum == 2){
                    img = down2;
                }
                break;                
            case "left":
                if(spriteNum == 1){
                    img = left1;
                }
                if(spriteNum == 2){
                    img = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    img = right1;
                }
                if(spriteNum == 2){
                    img = right2;
                }       
                break;                
        }
        g.drawImage(img, screenX, screenY, null);
    }
    
    public void pickupObject(int i){
        // if i = 999, no object collision
        
        if(i != 999){
            String ObjName = gamePanel.obj[i].name;
            
            switch(ObjName){
                case "Chest":
                    chestNum++;
                    gamePanel.obj[i] = null;
                    gamePanel.ui.messageOn = true;
                    gamePanel.ui.notifMessage = "You obtained a treasure!";
                    break;
                case "Fake Chest":
                    gamePanel.obj[i] = null;
                    gamePanel.ui.messageOn = true;
                    gamePanel.ui.notifMessage = "Empty treasure chest :((";
                    break;
                case "Obstacle":
                    speed = 0;
                    countPlayerSpeedDown = true;
                    gamePanel.ui.messageOn = true;
                    gamePanel.ui.notifMessage = "Player freezes for 5 seconds.";
                    break;
                case "Boots":
                    gamePanel.obj[i] = null;
                    speed += 2;
                    countPlayerSpeedUp = true;
                    gamePanel.ui.messageOn = true;
                    gamePanel.ui.notifMessage = "Rapid Charge activated!";
                    break;   
                case "Door":
                    if(chestNum == 5){
                        for (Entity npc : gamePanel.npc) {
                            if(npc!=null)
                                npc.speed = 0;
                        }
                        gamePanel.gameState = gamePanel.GAMEWON_STATE;
                    }
                    break;
                case "Pirate Freeze":
                    gamePanel.pirateFreezeOn = true;
                    gamePanel.npc[0].speed = 0;
                    gamePanel.ui.messageOn = true;
                    gamePanel.ui.notifMessage = "Pirate freezes for 5 seconds.";
                    break;
            }
        }
    }
    
    public void interactNPC(int i){
        if(i != 999){
//            System.out.println("You are hitting an NPC");
        }
    }
    
    private void playerSpeedUp(){
        playerSpeedUpCounter++;
        
        //System.out.println("up: "+playerSpeedUpCounter);
        if(playerSpeedUpCounter == 1200){
            countPlayerSpeedUp = false;
            speed = 5;
            playerSpeedUpCounter = 0;
        }
    }
    
    private void playerSpeedDown(){
        playerSpeedDownCounter++;
        
       // System.out.println("down: "+playerSpeedDownCounter);
        if(playerSpeedDownCounter == 300){
            countPlayerSpeedDown = false;
            speed = 5;
            playerSpeedDownCounter = 0;
        }
    }    
}
