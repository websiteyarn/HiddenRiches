package main;

import entity.Entity;


public class CollisionChecker {
    
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    
    // collision on solid tiles
    public void checkTile(Entity entity){
        // use this method to check player AND NPC collision to solid tile 
        
        // player solid are in the map
        int entityLeftWorldX = entity.worldXLocation + entity.solidArea.x;
        int entityRightWorldX = entity.worldXLocation + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldYLocation + entity.solidArea.y;
        int entityBottomWorldY = entity.worldYLocation + entity.solidArea.y + entity.solidArea.height;
        
        // used to determine the col and row of colliding tiles
        int entityLeftCol = entityLeftWorldX/gamePanel.TILE_SIZE;
        int entityRightCol = entityRightWorldX/gamePanel.TILE_SIZE;
        int entityTopRow = entityTopWorldY/gamePanel.TILE_SIZE;
        int entityBottomRow = entityBottomWorldY/gamePanel.TILE_SIZE;
        
        int tileNum1, tileNum2;
        
        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gamePanel.TILE_SIZE;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if(gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gamePanel.TILE_SIZE;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gamePanel.TILE_SIZE;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                if(gamePanel.tileManager.tile[tileNum1].collision == true || gamePanel.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gamePanel.TILE_SIZE;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tileNum1].collision == true|| gamePanel.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }                
                break;
        }
    }   
    
    // collision on Objects 
    public int checkObject(Entity entity, boolean player){
        // check if the entity colliding to the obj is player or not
        
        // index of the object
        int index = 999;
        
        // testing collision for ALL object
        for(int i = 0; i < gamePanel.obj.length; i++){
            if(gamePanel.obj[i] != null){
                // get entity's solid area position in world map
                entity.solidArea.x = entity.worldXLocation + entity.solidArea.x;
                entity.solidArea.y = entity.worldYLocation + entity.solidArea.y;
                
                // get object's solid area position in world map
                gamePanel.obj[i].solidArea.x = gamePanel.obj[i].worldXLocation + gamePanel.obj[i].solidArea.x;
                gamePanel.obj[i].solidArea.y = gamePanel.obj[i].worldYLocation + gamePanel.obj[i].solidArea.y;
                
                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gamePanel.obj[i].solidArea)){
                            // some objects are not solid / collisionOn = true
                            if(gamePanel.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                // passes the index of the object collided with
                                index = i;
                            }
                            // ignore if non-player
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gamePanel.obj[i].solidArea)){
                            if(gamePanel.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                // passes the index of the object collided with
                                index = i;
                            }
                            // ignore if non-player                            
                        }                        
                        break;                        
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gamePanel.obj[i].solidArea)){
                            if(gamePanel.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                // passes the index of the object collided with
                                index = i;
                            }
                            // ignore if non-player
                        }
                        break;                        
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gamePanel.obj[i].solidArea)){
                            if(gamePanel.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                // passes the index of the object collided with
                                index = i;
                            }
                            // ignore if non-player
                        }                        
                        break;                        
                }
                
                // reset solid area x,y or they will increase every time
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gamePanel.obj[i].solidArea.x = gamePanel.obj[i].solidAreaDefaultX;
                gamePanel.obj[i].solidArea.y = gamePanel.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    
    public int checkEntity(Entity entity, Entity[] target){
        // check if player is colliding with an entity
        // NPC is solid / collisionOn = true
        
        // index of the npc
        int index = 999;
        
        for(int i = 0; i < target.length; i++){
            if(target[i] != null){
                // get entity's solid area position in world map
                entity.solidArea.x = entity.worldXLocation + entity.solidArea.x;
                entity.solidArea.y = entity.worldYLocation + entity.solidArea.y;
                
                // get npc's solid area position in world map
                target[i].solidArea.x = target[i].worldXLocation + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldYLocation + target[i].solidArea.y;
                
                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;                           
                        }                        
                        break;                        
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;                        
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }                        
                        break;                        
                }
                
                // reset solid area x,y or they will increase every time
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }
    
    public void checkPlayer(Entity entity){
        // check if NPC is colliding with the player
        
        // get entity's solid area position in world map
        entity.solidArea.x = entity.worldXLocation + entity.solidArea.x;
        entity.solidArea.y = entity.worldYLocation + entity.solidArea.y;

        // get object's solid area position in world map
        gamePanel.player.solidArea.x = gamePanel.player.worldXLocation + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldYLocation + gamePanel.player.solidArea.y;

        switch(entity.direction){
            case "up":
                entity.solidArea.y -= entity.speed;
                if(entity.solidArea.intersects(gamePanel.player.solidArea)){
                    entity.collisionOn = true;
                    entity.speed = 0;
                    gamePanel.player.speed=0;
                    entity.onPath = false;
                    gamePanel.gameState = gamePanel.GAMEOVER_STATE;
                }
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                if(entity.solidArea.intersects(gamePanel.player.solidArea)){
                    entity.collisionOn = true;
                    entity.speed = 0;
                    gamePanel.player.speed=0;
                    entity.onPath = false;
                    gamePanel.gameState = gamePanel.GAMEOVER_STATE;
                }                        
                break;                        
            case "left":
                entity.solidArea.x -= entity.speed;
                if(entity.solidArea.intersects(gamePanel.player.solidArea)){
                    entity.collisionOn = true;
                    entity.speed = 0;
                    gamePanel.player.speed=0;
                    entity.onPath = false;
                    gamePanel.gameState = gamePanel.GAMEOVER_STATE;
                }
                break;                        
            case "right":
                entity.solidArea.x += entity.speed;
                if(entity.solidArea.intersects(gamePanel.player.solidArea)){
                    entity.collisionOn = true;
                    entity.speed = 0;
                    gamePanel.player.speed=0;
                    entity.onPath = false;
                    gamePanel.gameState = gamePanel.GAMEOVER_STATE;
                }                        
                break;                        
        }

        // reset solid area x,y or they will increase every time
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
    }
}
