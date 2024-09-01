package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class POW_Ice extends SuperObject{
    
    GamePanel gp;
    
    public POW_Ice(GamePanel gp){
        this.gp = gp;
        name = "Pirate Freeze";
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/ice.png"));
            image = uTool.scaleImage(image, gp.TILE_SIZE, gp.TILE_SIZE);
        } catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
