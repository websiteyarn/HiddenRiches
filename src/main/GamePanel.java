package main;

import ai.UniformCostSearch;
import entity.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import object.SuperObject;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable{
    
    // Game Panel

    public final int TILE_SIZE = 48; // 48px
    public final int SCREEN_COLUMN = 16;
    public final int SCREEN_ROW = 12;
    public final int SCREEN_WIDTH = TILE_SIZE * SCREEN_COLUMN;
    public final int SCREEN_HEIGHT = TILE_SIZE * SCREEN_ROW;
    
    // Game Thread
    Thread mainThread;
    
    // FPS
    final int FPS = 60;
    
    // Key Listener
    KeyEventListener keyListener = new KeyEventListener(this);
   
    // Player
    public Player player = new Player(this, keyListener);
    
    // Tile
    public TileManager tileManager = new TileManager(this);
    
    // WORLD
    public final int WORLD_COLUMN = 50;
    public final int WORLD_ROW = 50;
    public final int WORLD_WIDTH = TILE_SIZE * WORLD_COLUMN;
    public final int WORLD_HEIGHT = TILE_SIZE * WORLD_ROW;
    
    // Collision
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    
    // Objects
    public SuperObject obj[] = new SuperObject[40]; //40 objs
    public AssetSetter assetSetter = new AssetSetter(this);
    
    // AI
    public Entity npc[] = new AI[10];
    public int freezeCounter = 0;
    public boolean pirateFreezeOn = false;
    
    // UI
    public UI ui = new UI(this);
    
    // Game State
    public int gameState;
    public final int TITLE_STATE = 0;
    public final int PLAY_STATE = 1;
    public final int GAMEOVER_STATE = 2;
    public final int GAMEWON_STATE = 3;
    
    // Path Finder
    public UniformCostSearch route = new UniformCostSearch(this);
    
    public GamePanel(){
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyListener);
        this.setFocusable(true);
    }

    public void gameThreadStart(){
        mainThread = new Thread(this);
        mainThread.start();
    }
    
    @Override
    public void run() {
        
        double drawInterval = 1000000000/FPS; // 1s/60
        double nextDrawTime = System.nanoTime() + drawInterval;        
        
        // Game Loop
        while(mainThread != null){
            updateScreen();
            repaint();
            
            if(pirateFreezeOn == true){
                freezePirate();
            }    
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
                
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void updateScreen(){
        // update AI and player location, images
        
        player.update();
        
        for(int i = 0; i < npc.length; i++){
            if(npc[i] != null){
                npc[i].update();
            }
        }
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        
        // title screen
        if(gameState == TITLE_STATE){
            ui.draw(g2d);
        }
        else {
            // Tile
            tileManager.draw(g2d);

            // object
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    obj[i].draw(this, g2d);
                }
            }

            // AI
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].draw(this,g2d);
                }
            }

            // passes the paintcomponent that player class can draw into
            player.draw(g2d);

            // UI
            ui.draw(g2d);     
        }
        g2d.dispose();
    }
    
    public void setupGame(){
        // sets the default location of the object and AI
        assetSetter.setObject();
        assetSetter.setNPC();
        gameState = TITLE_STATE;
    }
        
    private void freezePirate(){
        freezeCounter++;
        
        if(freezeCounter > 300){
            pirateFreezeOn = false;
            npc[0].speed = 4;
            freezeCounter = 0;
        }
    }
    public void RetryGame(){
        // try again
        assetSetter.setObject();
        assetSetter.setNPC();
        player.defaultValues();
    }
    
}
