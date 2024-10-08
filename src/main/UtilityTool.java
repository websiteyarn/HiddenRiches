package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {
    
    // method
    public BufferedImage scaleImage(BufferedImage original, int width, int height){
        
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g = scaledImage.createGraphics();
        g.drawImage(original, 0,0, width, height, null);
        g.dispose();
        
        return scaledImage;
    }
    
}
