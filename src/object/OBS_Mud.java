package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBS_Mud extends SuperObject{
    GamePanel gp;
    public OBS_Mud(GamePanel gp){
        this.gp = gp;
        name = "Obstacle";
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/mud.png"));
            image = uTool.scaleImage(image, gp.TILE_SIZE, gp.TILE_SIZE);
        } catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
