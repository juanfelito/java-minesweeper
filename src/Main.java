import java.util.*;

class Main{
    public static void main (String [] arguments){
        Board theBoard = new Board (15,7,15);
        theBoard.placeMines();
        theBoard.placeNumbers();
        theBoard.printBoard();
        
        UIBoard UI = new UIBoard (15,7,15);
        UI.printBoard2();
        //UI.printBoard();
        
        Scanner input = new Scanner(System.in);
        String Status = "Good";
        
        while (Status.equals("Good")){
            int x = input.nextInt() - 1;
            int y = input.nextInt() - 1;
            
            if (theBoard.matrix[x][y] == 9){
                for (int i = 0; i < UI.x; i++){
                    for (int j = 0; j < UI.y; j++){
                        if(theBoard.matrix[i][j] != 9){
                            UI.goodClick(i, j, theBoard.matrix[i][j]);
                        }
                        else {
                            UI.badClick(i, j);
                        }
                    }
                }
                System.out.println("\nGAME OVER\n");
                UI.printBoard2();
                Status = "DEAD";
            }
            else if (theBoard.matrix[x][y] == 0){
                UI.zeroClick(x,y,theBoard.matrix);
                UI.printBoard2();
                //UI.printBoard();
            }
            else {
                UI.goodClick(x, y, theBoard.matrix[x][y]);
                UI.printBoard2();
                //UI.printBoard();
            }
        }
        
    }
    
}