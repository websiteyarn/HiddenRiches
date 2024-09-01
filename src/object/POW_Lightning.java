package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class POW_Lightning extends SuperObject {
    
    GamePanel gp;
    
    public POW_Lightning(GamePanel gp){
        this.gp = gp;
        
        name = "Boots";
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/boost.png"));
            image = uTool.scaleImage(image, gp.TILE_SIZE, gp.TILE_SIZE);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
