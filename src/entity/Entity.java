package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;

public class Entity {   
    GamePanel gamePanel;

    public int worldXLocation;
    public int worldYLocation;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public boolean onPath = false;
    
    public Entity(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    
    public void draw(GamePanel gamePanel, Graphics2D g2d){
        // positioning the map on screen
        int screenXTile = worldXLocation - gamePanel.player.worldXLocation + gamePanel.player.screenX;
        int screenYTile = worldYLocation - gamePanel.player.worldYLocation + gamePanel.player.screenY;

        if(worldXLocation + gamePanel.TILE_SIZE > gamePanel.player.worldXLocation - gamePanel.player.screenX &&
            worldXLocation - gamePanel.TILE_SIZE < gamePanel.player.worldXLocation + gamePanel.player.screenX &&
            worldYLocation + gamePanel.TILE_SIZE > gamePanel.player.worldYLocation - gamePanel.player.screenY &&
            worldYLocation - gamePanel.TILE_SIZE < gamePanel.player.worldYLocation + gamePanel.player.screenY){
            
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
        
            g2d.drawImage(img, screenXTile, screenYTile, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
        }
    }
    
    public void update(){
        /* 
         * tells what image should be displayed
         * checks collision (tile, object, player)
         * modify worldX/Y Location 
         */
        
        setAction();
        checkCollision();
        
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
    
    public void checkCollision(){
        collisionOn = false;
        gamePanel.collisionChecker.checkTile(this);
        gamePanel.collisionChecker.checkObject(this, false);
        gamePanel.collisionChecker.checkPlayer(this);
    }
    
    public void searchPath(int  goalCol, int goalRow){
        int startCol = (worldXLocation + solidArea.x)/gamePanel.TILE_SIZE;
        int startRow =  (worldYLocation + solidArea.y)/gamePanel.TILE_SIZE;
        
        int x = Math.abs(startCol - goalCol);
        int y = Math.abs(startRow - goalRow);
        
//        if((x+y) <10)
//            gamePanel.tileManager.drawPath = true;
//        else
//            gamePanel.tileManager.drawPath = false;

        if((x + y) < 10){
            gamePanel.ui.warningOn= true;
        }
        else{
            gamePanel.ui.warningOn = false;
        }
        
        if(startRow == goalRow && startCol == goalCol){
            speed = 0;
            gamePanel.player.speed=0;
            onPath = false;        
            gamePanel.gameState = gamePanel.GAMEOVER_STATE;
        }
        else{
            gamePanel.route.setNodes(startCol, startRow, goalCol, goalRow);

            // if path was found
            if(gamePanel.route.UniformCostSearch() == true){
                // next world x and world y
                // based on the current NPC's position, findout the relative position of the next node

                int nextX = gamePanel.route.pathList.get(0).col * gamePanel.TILE_SIZE;
                int nextY  = gamePanel.route.pathList.get(0).row * gamePanel.TILE_SIZE;

                int entityLeftWorldX = worldXLocation + solidArea.x;
                int entityRightWorldX = worldXLocation + solidArea.x + solidArea.width;
                int entityTopWorldY = worldYLocation + solidArea.y;
                int entityBottomWorldY = worldYLocation + solidArea.y + solidArea.height;

                if(entityTopWorldY > nextY && entityLeftWorldX >= nextX && entityRightWorldX < nextX + gamePanel.TILE_SIZE){
                    direction = "up";
                }
                else if(entityTopWorldY < nextY && entityLeftWorldX >= nextX && entityRightWorldX < nextX + gamePanel.TILE_SIZE){
                    direction = "down";
                }     
                else if(entityTopWorldY >= nextY && entityBottomWorldY < nextY + gamePanel.TILE_SIZE){
                    // left or right
                    if(entityLeftWorldX > nextX){
                        direction = "left";
                    }
                    if(entityLeftWorldX < nextX){
                        direction = "right";
                    }
                }
                else if(entityTopWorldY > nextY && entityLeftWorldX > nextX){
                    // up or left
                    direction = "up";
                    if(collisionOn == true){
                        direction = "left";
                    }
                }
                else if(entityTopWorldY > nextY && entityLeftWorldX < nextX){
                    // up or right
                    direction = "up";
                    if(collisionOn == true){
                        direction = "right";
                    }
                }
                else if(entityTopWorldY < nextY && entityLeftWorldX > nextX){
                    // down or left
                    direction = "down";
                    if(collisionOn==true){
                        direction = "left";
                    }                
                }
                else if(entityTopWorldY < nextY && entityLeftWorldX < nextX){
                    // down or right
                    direction = "down";
                    if(collisionOn==true){
                        direction = "right";
                    }                
                }
                /*
                // if it reaches the goal, stop the search
                int nextCol = gamePanel.route.pathList.get(0).col;
                int nextRow = gamePanel.route.pathList.get(0).row;
                if(nextCol == goalCol && nextRow == goalRow){
                    onPath = false;
                    speed = 0;
                }
                */
            }
        }
    }
    
    public void setAction(){}

}
