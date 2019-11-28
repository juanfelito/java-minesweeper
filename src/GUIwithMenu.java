import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class GUIwithMenu extends GUI {
    
    GUIwithMenu(int[][] matrix){
        super(matrix);
        
        // create menu
        JMenuItem newGame = new JMenuItem ("New Game");
        newGame.addActionListener(this);
        JMenuItem options = new JMenuItem ("Options");
        JMenuItem stats = new JMenuItem ("Statistics");
        JMenuItem exit = new JMenuItem ("Exit");
        exit.addActionListener(this);
        JMenu game = new JMenu ("Game");
        JMenuBar menub = new JMenuBar();
        game.add(newGame);
        game.addSeparator();
        game.add(stats);
        game.add(options);
        game.addSeparator();
        game.add(exit);
        menub.add(game);
        add(BorderLayout.NORTH,menub);
    }
    
    public static void main(String[] arguments){
        Board theBoard = new Board(x, y, numberMines);
        new WindowResize(new GUIwithMenu(theBoard.matrix));
        
    }
    
}

class WindowResize extends ComponentAdapter {
    
    GUIwithMenu b;
    
    WindowResize(GUIwithMenu a){
        super();
        b = a;
        b.mainPanel.addComponentListener(this);
    }
    
    @Override
    public void componentResized (ComponentEvent e){
        int buttonW = b.closedMines[1][1].getWidth();
        int buttonH = b.closedMines[1][1].getHeight();      
                
        GameButton.resizeIcons(buttonW, buttonH);
        
        for (int i = 0; i < GUIwithMenu.y; i++){
            for (int j = 0; j < GUIwithMenu.x; j++){
                ImageIcon ogIcon = (ImageIcon)b.closedMines[j][i].getIcon();
                Image img = ogIcon.getImage();
                Image resized = img.getScaledInstance(buttonW, buttonH, java.awt.Image.SCALE_DEFAULT);
                ImageIcon tempIcon = new ImageIcon(resized);
                b.closedMines[j][i].setIcon(tempIcon);
            }
        }
    }
}