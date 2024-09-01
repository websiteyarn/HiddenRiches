package main;

import entity.AI;
import object.POW_Lightning;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_FakeChest;
import object.OBS_Mud;
import object.POW_Ice;

public class AssetSetter {
    // places all obj on the map
    // sets obj's worldX and worldY

    GamePanel gamePanel;
    
    public AssetSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    
    public void setObject(){
        gamePanel.obj[0] = new OBJ_Chest(gamePanel);
        gamePanel.obj[0].worldXLocation = gamePanel.TILE_SIZE * 45;
        gamePanel.obj[0].worldYLocation = gamePanel.TILE_SIZE * 4;
   
        gamePanel.obj[1] = new OBJ_Chest(gamePanel);
        gamePanel.obj[1].worldXLocation = gamePanel.TILE_SIZE * 27;
        gamePanel.obj[1].worldYLocation = gamePanel.TILE_SIZE * 8;        
        
        gamePanel.obj[2] = new OBJ_Chest(gamePanel);
        gamePanel.obj[2].worldXLocation = gamePanel.TILE_SIZE * 17;
        gamePanel.obj[2].worldYLocation = gamePanel.TILE_SIZE * 40; 
        
        gamePanel.obj[3] = new OBJ_Chest(gamePanel);
        gamePanel.obj[3].worldXLocation = gamePanel.TILE_SIZE * 46;
        gamePanel.obj[3].worldYLocation = gamePanel.TILE_SIZE * 21; 

        gamePanel.obj[4] = new OBJ_Chest(gamePanel);
        gamePanel.obj[4].worldXLocation = gamePanel.TILE_SIZE * 3;
        gamePanel.obj[4].worldYLocation = gamePanel.TILE_SIZE * 12;         
        
        gamePanel.obj[5] = new OBJ_Door(gamePanel);
        gamePanel.obj[5].worldXLocation = gamePanel.TILE_SIZE * 2;
        gamePanel.obj[5].worldYLocation = gamePanel.TILE_SIZE * 47;  

        gamePanel.obj[6] = new POW_Lightning(gamePanel);
        gamePanel.obj[6].worldXLocation = gamePanel.TILE_SIZE * 44;
        gamePanel.obj[6].worldYLocation = gamePanel.TILE_SIZE * 10;
        
        gamePanel.obj[7] = new OBJ_FakeChest(gamePanel);
        gamePanel.obj[7].worldXLocation = gamePanel.TILE_SIZE * 46;
        gamePanel.obj[7].worldYLocation = gamePanel.TILE_SIZE * 27;
        
        gamePanel.obj[8] = new OBJ_FakeChest(gamePanel);
        gamePanel.obj[8].worldXLocation = gamePanel.TILE_SIZE * 33;
        gamePanel.obj[8].worldYLocation = gamePanel.TILE_SIZE * 21;
        
        gamePanel.obj[9] = new OBJ_FakeChest(gamePanel);
        gamePanel.obj[9].worldXLocation = gamePanel.TILE_SIZE * 45;
        gamePanel.obj[9].worldYLocation = gamePanel.TILE_SIZE * 45;
        
        gamePanel.obj[10] = new OBJ_FakeChest(gamePanel);
        gamePanel.obj[10].worldXLocation = gamePanel.TILE_SIZE * 11;
        gamePanel.obj[10].worldYLocation = gamePanel.TILE_SIZE * 21;
                
        gamePanel.obj[11] = new OBS_Mud(gamePanel);
        gamePanel.obj[11].worldXLocation = gamePanel.TILE_SIZE * 8;
        gamePanel.obj[11].worldYLocation = gamePanel.TILE_SIZE * 5;
        
        gamePanel.obj[12] = new OBS_Mud(gamePanel);
        gamePanel.obj[12].worldXLocation = gamePanel.TILE_SIZE * 7;
        gamePanel.obj[12].worldYLocation = gamePanel.TILE_SIZE * 9;
        
        gamePanel.obj[13] = new OBS_Mud(gamePanel);
        gamePanel.obj[13].worldXLocation = gamePanel.TILE_SIZE * 10;
        gamePanel.obj[13].worldYLocation = gamePanel.TILE_SIZE * 12; 
        
        gamePanel.obj[14] = new OBS_Mud(gamePanel);
        gamePanel.obj[14].worldXLocation = gamePanel.TILE_SIZE * 24;
        gamePanel.obj[14].worldYLocation = gamePanel.TILE_SIZE * 3;
        
        gamePanel.obj[15] = new OBS_Mud(gamePanel);
        gamePanel.obj[15].worldXLocation = gamePanel.TILE_SIZE * 25;
        gamePanel.obj[15].worldYLocation = gamePanel.TILE_SIZE * 3; 
        
        gamePanel.obj[16] = new OBS_Mud(gamePanel);
        gamePanel.obj[16].worldXLocation = gamePanel.TILE_SIZE * 28;
        gamePanel.obj[16].worldYLocation = gamePanel.TILE_SIZE * 2; 
        
        gamePanel.obj[17] = new OBS_Mud(gamePanel);
        gamePanel.obj[17].worldXLocation = gamePanel.TILE_SIZE * 29;
        gamePanel.obj[17].worldYLocation = gamePanel.TILE_SIZE * 2; 
        
        gamePanel.obj[18] = new POW_Ice(gamePanel);
        gamePanel.obj[18].worldXLocation = gamePanel.TILE_SIZE * 47;
        gamePanel.obj[18].worldYLocation = gamePanel.TILE_SIZE * 6;  
        
        gamePanel.obj[19] = new POW_Ice(gamePanel);
        gamePanel.obj[19].worldXLocation = gamePanel.TILE_SIZE * 13;
        gamePanel.obj[19].worldYLocation = gamePanel.TILE_SIZE * 3;   

        gamePanel.obj[20] = new OBS_Mud(gamePanel);
        gamePanel.obj[20].worldXLocation = gamePanel.TILE_SIZE * 37;
        gamePanel.obj[20].worldYLocation = gamePanel.TILE_SIZE * 45;   
        
        gamePanel.obj[21] = new OBS_Mud(gamePanel);
        gamePanel.obj[21].worldXLocation = gamePanel.TILE_SIZE * 38;
        gamePanel.obj[21].worldYLocation = gamePanel.TILE_SIZE * 45; 
        
        gamePanel.obj[22] = new OBS_Mud(gamePanel);
        gamePanel.obj[22].worldXLocation = gamePanel.TILE_SIZE * 37;
        gamePanel.obj[22].worldYLocation = gamePanel.TILE_SIZE * 44;         
        
        gamePanel.obj[23] = new OBS_Mud(gamePanel);
        gamePanel.obj[23].worldXLocation = gamePanel.TILE_SIZE * 38;
        gamePanel.obj[23].worldYLocation = gamePanel.TILE_SIZE * 44;   
        
        gamePanel.obj[24] = new OBJ_FakeChest(gamePanel);
        gamePanel.obj[24].worldXLocation = gamePanel.TILE_SIZE * 22;
        gamePanel.obj[24].worldYLocation = gamePanel.TILE_SIZE * 18; 
        
        gamePanel.obj[25] = new OBS_Mud(gamePanel);
        gamePanel.obj[25].worldXLocation = gamePanel.TILE_SIZE * 14;
        gamePanel.obj[25].worldYLocation = gamePanel.TILE_SIZE * 46;
       
        gamePanel.obj[26] = new OBS_Mud(gamePanel);
        gamePanel.obj[26].worldXLocation = gamePanel.TILE_SIZE * 19;
        gamePanel.obj[26].worldYLocation = gamePanel.TILE_SIZE * 47;
        
        gamePanel.obj[27] = new OBS_Mud(gamePanel);
        gamePanel.obj[27].worldXLocation = gamePanel.TILE_SIZE * 3;
        gamePanel.obj[27].worldYLocation = gamePanel.TILE_SIZE * 14;
        
        gamePanel.obj[28] = new OBS_Mud(gamePanel);
        gamePanel.obj[28].worldXLocation = gamePanel.TILE_SIZE * 7;
        gamePanel.obj[28].worldYLocation = gamePanel.TILE_SIZE * 34;
        
        gamePanel.obj[29] = new OBS_Mud(gamePanel);
        gamePanel.obj[29].worldXLocation = gamePanel.TILE_SIZE * 8;
        gamePanel.obj[29].worldYLocation = gamePanel.TILE_SIZE * 34;      
        
        gamePanel.obj[30] = new OBS_Mud(gamePanel);
        gamePanel.obj[30].worldXLocation = gamePanel.TILE_SIZE * 19;
        gamePanel.obj[30].worldYLocation = gamePanel.TILE_SIZE * 31;
        
        gamePanel.obj[31] = new OBS_Mud(gamePanel);
        gamePanel.obj[31].worldXLocation = gamePanel.TILE_SIZE * 20;
        gamePanel.obj[31].worldYLocation = gamePanel.TILE_SIZE * 31;     
        
        gamePanel.obj[32] = new OBS_Mud(gamePanel);
        gamePanel.obj[32].worldXLocation = gamePanel.TILE_SIZE * 24;
        gamePanel.obj[32].worldYLocation = gamePanel.TILE_SIZE * 32;
        
        gamePanel.obj[33] = new OBS_Mud(gamePanel);
        gamePanel.obj[33].worldXLocation = gamePanel.TILE_SIZE * 25;
        gamePanel.obj[33].worldYLocation = gamePanel.TILE_SIZE * 32; 
    }     
    
    public void setNPC(){
        gamePanel.npc[0] = new AI(gamePanel);
        gamePanel.npc[0].worldXLocation = gamePanel.TILE_SIZE * 3;
        gamePanel.npc[0].worldYLocation = gamePanel.TILE_SIZE * 3;

    }
    
}
