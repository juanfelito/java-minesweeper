
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.*;

class GameButton extends JButton{
    static ImageIcon[] numberIcons = new ImageIcon [16];
    static int absX = GUI.x, absY = GUI.y;  // Se puede cambiar por referencia a GUI.x y GUI.y
    
    int index;
    boolean clicked;    
    int buttonX;
    int buttonY;
    int flagStatus;
    
    GameButton(int i, int x, int y){
        super();
        index = i;
        buttonX = x;
        buttonY = y;
        clicked = false;
        flagStatus = 0;
    }
    
    static void resizeIcons (int w, int h){
        for (int i = 0; i < numberIcons.length; i++){
            Image img = numberIcons[i].getImage();
            Image img2 = img.getScaledInstance(w, h, java.awt.Image.SCALE_DEFAULT);
            numberIcons[i] = new ImageIcon(img2);
        }        
    }
    
    void flag(GameButton button){
        if (!button.clicked){
            button.setIcon(numberIcons[11]);
            button.flagStatus = 1;
        }
    }
    
    void question(GameButton button){
        if (!button.clicked){
            button.setIcon(numberIcons[12]);
            button.flagStatus = 2;
        }
    }
    
    void resetFlag(GameButton button){
        if (!button.clicked){
            button.setIcon(numberIcons[13]);
            button.flagStatus = 0;
        }
    }
    
    void numberClick (GameButton button){
        if (!button.clicked){
            button.setIcon(numberIcons[button.index]);
            button.clicked = true;
        }
    }
    
    void lastClick (GameButton button){
        if (!button.clicked){
            button.setIcon(numberIcons[10]);
            button.clicked = true;
        }
    }
    
    void endingClick (GameButton button){
        if (button.flagStatus == 1 && button.index == 9){
            button.setIcon(numberIcons[14]);
        } else if (button.flagStatus == 1 && button.index != 9){
            button.setIcon(numberIcons[15]);
        }else if (!button.clicked){
            button.setIcon(numberIcons[button.index]);
            button.clicked = true;
        }
    }
    
    void zeroClick (GameButton [][] buttons, GameButton button){
        if (!button.clicked){
            numberClick (button);
        
           if ((button.buttonX > 0 && button.buttonX < absX - 1) && (button.buttonY > 0 && button.buttonY < absY - 1)){
                zeroCenters(button, buttons);
            }
            else if ((button.buttonX == 0 && button.buttonY == 0) || (button.buttonX == absX -1 && button.buttonY == absY - 1)
                    || (button.buttonX == 0 && button.buttonY == absY - 1) || (button.buttonX == absX -1 && button.buttonY == 0)){
                zeroCorners(button, buttons);
            }
            else {
                zeroSides(button, buttons);
            }
        }
    }
    
    void zeroCenters (GameButton button, GameButton[][] buttons){
        for (int i = button.buttonX - 1; i <= button.buttonX + 1; i++){
                for (int j = button.buttonY - 1; j <= button.buttonY + 1; j++){
                    clicker(buttons[i][j], buttons);
                }
            }
    }
    
    void zeroCorners (GameButton button, GameButton[][] buttons){
        if (button.buttonX == 0 && button.buttonY == 0){
            for (int i = button.buttonX; i <= button.buttonX + 1; i++){
                for (int j = button.buttonY; j <= button.buttonY + 1; j++){
                    clicker(buttons[i][j], buttons);
                }
            }            
        }
        else if (button.buttonX == absX - 1 && button.buttonY == absY - 1){
            for (int i = button.buttonX - 1; i <= button.buttonX ; i++){
                for (int j = button.buttonY - 1; j <= button.buttonY; j++){
                    clicker(buttons[i][j], buttons);
                }
            }
        }
        else if (button.buttonX == 0 && button.buttonY == absY - 1){
            for (int i = button.buttonX; i <= button.buttonX + 1; i++){
                for (int j = button.buttonY - 1; j <= button.buttonY; j++){
                    clicker(buttons[i][j], buttons);
                }
            }
        }
        else {
            for (int i = button.buttonX -1; i <= button.buttonX; i++){
                for (int j = button.buttonY; j <= button.buttonY + 1; j++){
                    clicker(buttons[i][j], buttons);
                }
            }
        }
    }
    
    void zeroSides (GameButton button, GameButton[][] buttons){
        if (button.buttonX == 0){
            for (int i = button.buttonX; i <= button.buttonX + 1; i++){
                for (int j = button.buttonY - 1; j <= button.buttonY + 1; j++){
                    clicker(buttons[i][j], buttons);
                }
            }
        }
        else if (button.buttonY == 0){
            for (int i = button.buttonX - 1; i <= button.buttonX + 1; i++){
                for (int j = button.buttonY; j <= button.buttonY + 1; j++){
                    clicker(buttons[i][j], buttons);
                }
            }
        }
        else if (button.buttonX == absX - 1){
            for (int i = button.buttonX - 1; i <= button.buttonX; i++){
                for (int j = button.buttonY - 1; j <= button.buttonY + 1; j++){
                    clicker(buttons[i][j], buttons);
                }
            }
        }
        else {
            for (int i = button.buttonX - 1; i <= button.buttonX + 1; i++){
                for (int j = button.buttonY - 1; j <= button.buttonY; j++){
                    clicker(buttons[i][j], buttons);
                }
            }
        }
    }
    
    void clicker(GameButton b, GameButton[][] bs){
        if (b.index == 0){
            zeroClick(bs, b);
        } else
            numberClick(b);
    }
}