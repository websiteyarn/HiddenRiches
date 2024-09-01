package object;

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class OBJ_FakeChest extends SuperObject{
    
    GamePanel gp;
    
    public OBJ_FakeChest(GamePanel gp){
        this.gp = gp;
        
        name = "Fake Chest";
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/chest.png"));
            image = uTool.scaleImage(image, gp.TILE_SIZE, gp.TILE_SIZE);
        } catch (IOException e){
            e.printStackTrace();
        }        
    }
}
