package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import main.*;

public class TileManager {
    
    GamePanel gamePanel;
    public Tile[] tile;
    public int mapTileNum[][];
    public boolean drawPath = false;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        // 10 obj of tile class
        tile = new Tile[10];
        // stores the array dimension of the map
        mapTileNum = new int[gamePanel.WORLD_COLUMN][gamePanel.WORLD_ROW];
        tileImage();
        loadMap(".\\src\\maps\\map1.txt");
    }
    
    public void tileImage(){

        setupImage(0,"tile0",false);
        setupImage(1,"tile5",false);
        setupImage(2,"brick",true);
        
    }
    
    public void setupImage(int index, String imagePath, boolean collision){
        try {
            
            UtilityTool uTool = new UtilityTool();
            
            tile[index] = new Tile();
            tile[index].img = ImageIO.read(getClass().getResourceAsStream("/res/tiles/"+ imagePath +".png"));
            tile[index].img = uTool.scaleImage(tile[index].img, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
            tile[index].collision = collision;
            
        } catch (IOException ex) {
            Logger.getLogger(TileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    public void loadMap(String filePath){
        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            int col = 0;
            int row = 0;
            
            while(sc.hasNextLine()){
                // System.out.println(sc.nextLine());
                
                while(col < gamePanel.WORLD_COLUMN && row < gamePanel.WORLD_ROW){
                    // gets the string
                    String line = sc.nextLine();
                    
                    // from the string, parse each tile number
                    while(col < gamePanel.WORLD_COLUMN){
                        String[] numbers = line.split(" ");
                        int num = Integer.parseInt(numbers[col]);
                        mapTileNum[col][row] = num;
                        col++;
                    }
                    
                    // proceed to next row
                    if(col == gamePanel.WORLD_COLUMN){
                        col = 0;
                        row++;
                    }
                }
            }
            
            sc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void draw(Graphics2D g){
        
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gamePanel.WORLD_COLUMN && worldRow < gamePanel.WORLD_ROW){
            int tileNum = mapTileNum[worldCol][worldRow];
            
            // position of tile in the map
            int worldX = worldCol * gamePanel.TILE_SIZE;
            int worldY = worldRow * gamePanel.TILE_SIZE;
            
            // positioning the map on screen
            int screenXTile = worldX - gamePanel.player.worldXLocation + gamePanel.player.screenX;
            int screenYTile = worldY - gamePanel.player.worldYLocation + gamePanel.player.screenY;
            
            // checks if tile is within screen range
            if(worldX + gamePanel.TILE_SIZE > gamePanel.player.worldXLocation - gamePanel.player.screenX &&
                worldX - gamePanel.TILE_SIZE < gamePanel.player.worldXLocation + gamePanel.player.screenX &&
                worldY + gamePanel.TILE_SIZE > gamePanel.player.worldYLocation - gamePanel.player.screenY &&
                worldY - gamePanel.TILE_SIZE < gamePanel.player.worldYLocation + gamePanel.player.screenY){
                
                // draw map tile image on the screen
                g.drawImage(tile[tileNum].img, screenXTile, screenYTile, null);
            }
            
            worldCol++;

            if(worldCol == gamePanel.WORLD_COLUMN){
                worldCol = 0;
                worldRow++;
            }
        }
    
        if(drawPath == true){
            g.setColor(new Color(255,0,0,70));
            
            for(int i = 0; i < gamePanel.route.pathList.size(); i++){
                int worldX = gamePanel.route.pathList.get(i).col * gamePanel.TILE_SIZE;
                int worldY = gamePanel.route.pathList.get(i).row * gamePanel.TILE_SIZE;
                int screenX = worldX - gamePanel.player.worldXLocation + gamePanel.player.screenX;
                int screenY = worldY - gamePanel.player.worldYLocation + gamePanel.player.screenY;
                
                g.fillRect(screenX, screenY, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE);
                
            }
        }

        
// screen
        
//        while(col < gamePanel.SCREEN_COLUMN && row < gamePanel.SCREEN_ROW){
//            int tileNum = mapTileNum[col][row];
//            
//            g.drawImage(tile[tileNum].img, xLoc, yLoc, gamePanel.TILE_SIZE, gamePanel.TILE_SIZE, null);
//            
//            col++;
//            xLoc += gamePanel.TILE_SIZE;
//            
//            if(col == gamePanel.SCREEN_COLUMN){
//                col = 0;
//                xLoc = 0;
//                row++;
//                yLoc += gamePanel.TILE_SIZE;
//            }
//        }        
        
    }
}
