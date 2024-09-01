package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEventListener implements KeyListener{
    GamePanel gp;
    public boolean upKey, downKey, leftKey, rightKey;
    
    public KeyEventListener(GamePanel gp){
        this.gp = gp;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(gp.gameState == gp.TITLE_STATE){
            if(code == KeyEvent.VK_UP){
                if(gp.ui.commandNum == 1)
                    gp.ui.commandNum = 0;
            }
            if(code == KeyEvent.VK_DOWN){
                if(gp.ui.commandNum == 0)
                    gp.ui.commandNum = 1;
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.PLAY_STATE;
                }
                else
                    System.exit(0);
            }
        }
        
        if(gp.gameState == gp.PLAY_STATE){
            if(code == KeyEvent.VK_UP){
                upKey = true;
            }

            if(code == KeyEvent.VK_DOWN){
                downKey = true;
            }

            if(code == KeyEvent.VK_LEFT){
                leftKey = true;
            }

            if(code == KeyEvent.VK_RIGHT){
                rightKey = true;
            }
        }
        
        if(gp.gameState == gp.GAMEOVER_STATE){
            if(code == KeyEvent.VK_UP){
                if(gp.ui.commandNum == 1)
                    gp.ui.commandNum = 0;
            }
            if(code == KeyEvent.VK_DOWN){
                if(gp.ui.commandNum == 0)
                    gp.ui.commandNum = 1;
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.RetryGame();
                    gp.gameState = gp.PLAY_STATE;
                    defaultKeys();
                    gp.ui.messageOn = false;
                }
                else
                    System.exit(0);
            }
        }
        
        if(gp.gameState == gp.GAMEWON_STATE){
            if(code == KeyEvent.VK_UP){
                if(gp.ui.commandNum == 1)
                    gp.ui.commandNum = 0;
            }
            if(code == KeyEvent.VK_DOWN){
                if(gp.ui.commandNum == 0)
                    gp.ui.commandNum = 1;
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.RetryGame();
                    gp.gameState = gp.PLAY_STATE;
                    defaultKeys();
                    gp.ui.messageOn = false;
                }
                else
                    System.exit(0);
            }
        }        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(gp.gameState == gp.PLAY_STATE){
            if(code == KeyEvent.VK_UP){
                upKey = false;
            }

            if(code == KeyEvent.VK_DOWN){
                downKey = false;
            }

            if(code == KeyEvent.VK_LEFT){
                leftKey = false;
            }

            if(code == KeyEvent.VK_RIGHT){
                rightKey = false;
            }
        }
    }
    private void defaultKeys(){
        upKey = false;
        downKey = false;
        leftKey = false;
        rightKey = false;
    }
    
}
