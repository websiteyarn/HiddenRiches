package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;


public class OBJ_Diamond extends SuperObject{
    GamePanel gp;
    public OBJ_Diamond(GamePanel gp){
        this.gp = gp;
        name = "Gem";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/diamond.png"));
            image = uTool.scaleImage(image, gp.TILE_SIZE, gp.TILE_SIZE);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
