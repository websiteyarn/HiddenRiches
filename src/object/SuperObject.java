package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.UtilityTool;

public class SuperObject {
    // parent class for all object class
    public BufferedImage image;
    public String name;
    public boolean collision;
    public int worldXLocation, worldYLocation;
    // whole object is collision sensitive
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    UtilityTool uTool = new UtilityTool();

    public void draw(GamePanel gamePanel, Graphics2D g2d){
        // positioning the map on screen
        int screenXTile = worldXLocation - gamePanel.player.worldXLocation + gamePanel.player.screenX;
        int screenYTile = worldYLocation - gamePanel.player.worldYLocation + gamePanel.player.screenY;

        if(worldXLocation + gamePanel.TILE_SIZE > gamePanel.player.worldXLocation - gamePanel.player.screenX &&
            worldXLocation - gamePanel.TILE_SIZE < gamePanel.player.worldXLocation + gamePanel.player.screenX &&
            worldYLocation + gamePanel.TILE_SIZE > gamePanel.player.worldYLocation - gamePanel.player.screenY &&
            worldYLocation - gamePanel.TILE_SIZE < gamePanel.player.worldYLocation + gamePanel.player.screenY){

            // draw map tile image on the screen
            g2d.drawImage(image, screenXTile, screenYTile,null);
        }
    }
    
    
}
