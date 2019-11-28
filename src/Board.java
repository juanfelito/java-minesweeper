import java.awt.Point;

class Board {
    int x;
    int y;
    int numberMines;
    public int [][] matrix;
    Point [] mines;
    
    Board(int x, int y, int nm){
        this.x = x;
        this.y = y;
        numberMines = nm;
        matrix = new int [x][y];
        mines = new Point [nm];
        placeMines();
        placeNumbers();
    }
    
    void placeMines(){
        for (int i = 0; i < this.numberMines; i++){
            this.mines[i] = new Point ((int)Math.floor(Math.random()*this.x), (int) Math.floor(Math.random()*this.y)); // EVITAR REPETIDOS
            matrix [this.mines[i].x][this.mines[i].y] = 9;
        }        
    }
    
    void placeNumbers(){
        for (int i = 0; i < this.x; i++){
            for (int j = 0; j < this.y; j++){
                if (this.matrix[i][j] == 9){
                }
                else if ((i == 0 && j == 0) || (i == this.x -1 && j == this.y - 1) || (i == 0 && j == this.y - 1) || (i == this.x -1 && j == 0)){
                    setCorners(i,j);
                }
                else if ((i > 0 && i < this.x - 1) && (j > 0 && j < this.y - 1)){
                    setCenters(i,j);
                }
                else {
                    setSides(i,j);
                }
            }
        }
    }
    
    void setCorners(int x, int y){
        if (x == 0 && y == 0){
            for (int i = x; i <= x + 1; i++){
                for (int j = y; j <= y + 1; j++){
                    if (this.matrix[i][j]==9){
                        this.matrix[x][y]++;
                    }
                }
            }            
        }
        else if (x == this.x - 1 && y == this.y - 1){
            for (int i = x - 1; i <= x ; i++){
                for (int j = y - 1; j <= y; j++){
                    if (this.matrix[i][j]==9){
                        this.matrix[x][y]++;
                    }
                }
            }
        }
        else if (x == 0 && y == this.y - 1){
            for (int i = x; i <= x + 1; i++){
                for (int j = y - 1; j <= y; j++){
                    if (this.matrix[i][j]==9){
                        this.matrix[x][y]++;
                    }
                }
            }
        }
        else {
            for (int i = x -1; i <= x; i++){
                for (int j = y; j <= y + 1; j++){
                    if (this.matrix[i][j]==9){
                        this.matrix[x][y]++;
                    }
                }
            }
        }
    }
    
    void setCenters (int x, int y){
        for (int i = x - 1; i <= x + 1; i++){
                for (int j = y - 1; j <= y + 1; j++){
                    if (this.matrix[i][j]==9){
                        this.matrix[x][y]++;
                    }
                }
            }
    }
    
    void setSides (int x, int y){
        if (x == 0){
            for (int i = x; i <= x + 1; i++){
                for (int j = y - 1; j <= y + 1; j++){
                    if (this.matrix[i][j]==9){
                        this.matrix[x][y]++;
                    }
                }
            }
        }
        else if (y == 0){
            for (int i = x - 1; i <= x + 1; i++){
                for (int j = y; j <= y + 1; j++){
                    if (this.matrix[i][j]==9){
                        this.matrix[x][y]++;
                    }
                }
            }
        }
        else if (x == this.x - 1){
            for (int i = x - 1; i <= x; i++){
                for (int j = y - 1; j <= y + 1; j++){
                    if (this.matrix[i][j]==9){
                        this.matrix[x][y]++;
                    }
                }
            }
        }
        else {
            for (int i = x - 1; i <= x + 1; i++){
                for (int j = y - 1; j <= y; j++){
                    if (this.matrix[i][j]==9){
                        this.matrix[x][y]++;
                    }
                }
            }
        }
    }
    
    void printBoard(){
        for (int i = 0; i < this.y; i++){
            for (int j = 0; j < this.x; j++){
                System.out.print(this.matrix[j][i] + " ");
            }
            System.out.println();
        }
    }
    
}