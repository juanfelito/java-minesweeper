import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class GUI extends JFrame implements ActionListener, MouseListener{
    static int x = 30;
    static int y = 15;
    static int numberMines = 60;
    
    GameButton[][] closedMines = new GameButton [x][y];
    ImageIcon closedMineIcon = new ImageIcon ("Button1.png");
    ImageIcon selectedMineIcon = new ImageIcon ("Button2.png");
    JPanel mainPanel;
    
    
    GUI(int[][] matrix){
        super("Felipe's Minesweeper");
        setSize(x*35,y*35);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(y,x));
        GameButton.numberIcons[0] = new ImageIcon ("zero.png");
        GameButton.numberIcons[1] = new ImageIcon ("one.png");
        GameButton.numberIcons[2] = new ImageIcon ("two.png");
        GameButton.numberIcons[3] = new ImageIcon ("three.png");
        GameButton.numberIcons[4] = new ImageIcon ("four.png");
        GameButton.numberIcons[5] = new ImageIcon ("five.png");
        GameButton.numberIcons[6] = new ImageIcon ("six.png");
        GameButton.numberIcons[7] = new ImageIcon ("seven.png");
        GameButton.numberIcons[8] = new ImageIcon ("eight.png");
        GameButton.numberIcons[9] = new ImageIcon ("bomb.png");
        GameButton.numberIcons[10] = new ImageIcon ("loosingbomb.png");
        GameButton.numberIcons[11] = new ImageIcon ("Flagged.png");
        GameButton.numberIcons[12] = new ImageIcon ("question.png");
        GameButton.numberIcons[13] = new ImageIcon ("Button1.png");
        GameButton.numberIcons[14] = new ImageIcon ("Flaggedbomb.png");
        GameButton.numberIcons[15] = new ImageIcon ("falseflag.png");
        
        for (int i = 0; i < y; i++){
            for (int j = 0; j < x; j++){
                closedMines[j][i] = new GameButton(matrix[j][i], j, i);
                closedMines[j][i].setMargin(new Insets (0,0,0,0));                                
                //closedMines[j][i].setRolloverIcon(selectedMineIcon);
                //closedMines[j][i].setIcon(numberIcons[matrix[j][i]]);
                closedMines[j][i].addActionListener(this);
                closedMines[j][i].addMouseListener(this);
                mainPanel.add(closedMines[j][i]);                                
            }
        }      
                
        add(BorderLayout.CENTER, mainPanel);
        setVisible(true);
        
        //Resize icons
        
        int buttonW = closedMines[1][1].getWidth();
        int buttonH = closedMines[1][1].getHeight();
        
        Image img = closedMineIcon.getImage();
        Image resized = img.getScaledInstance(buttonW, buttonH, java.awt.Image.SCALE_DEFAULT);
        closedMineIcon = new ImageIcon(resized);
        
        GameButton.resizeIcons(buttonW, buttonH);
        
        for (int i = 0; i < y; i++){
            for (int j = 0; j < x; j++){                
                closedMines[j][i].setIcon(closedMineIcon);
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent evt){
        Object source = evt.getSource();
        
        if (source instanceof GameButton){
            GameButton pushedButton = (GameButton) source;
        
            if (pushedButton.flagStatus == 0){
                if (pushedButton.index == 0){
                    pushedButton.zeroClick(closedMines, pushedButton);
                } else if(pushedButton.index != 9){
                    pushedButton.numberClick(pushedButton);
                    //pushedButton.setEnabled(false);
                } else {
                    pushedButton.lastClick(pushedButton);
                    for (int i = 0; i < x; i++){
                        for (int j = 0; j < y; j++){
                            pushedButton.endingClick(closedMines[i][j]);
                        }
                    }
                }
            }
        } else if (source instanceof JMenuItem){
            JMenuItem clicked = (JMenuItem) source;
            String name = clicked.getText();
            if ("Exit".equals(name)){
                System.exit(0);
            } else if ("New Game".equals(name)){
                
                Board theBoard = new Board(x, y, numberMines);
                new WindowResize(new GUIwithMenu(theBoard.matrix));
            }
        }
    }
    
    public void mouseExited(MouseEvent ev){}
    public void mouseEntered(MouseEvent ev){}
    public void mouseReleased(MouseEvent ev){}
    public void mousePressed(MouseEvent ev){}

    @Override
    public void mouseClicked(MouseEvent e) {
        GameButton pushedButton = (GameButton) e.getSource();
        if(SwingUtilities.isRightMouseButton(e)){
            if(pushedButton.flagStatus == 0){
                pushedButton.flag(pushedButton);
            } else if (pushedButton.flagStatus == 1){
                pushedButton.question(pushedButton);
            } else if (pushedButton.flagStatus == 2){
                pushedButton.resetFlag(pushedButton);
            }           
        }
    }
}

