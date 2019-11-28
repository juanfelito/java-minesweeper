
class UIBoard extends Board {
    
    public String [][] matrix2;
    
    UIBoard (int x, int y, int nm){
        super (x,y,nm);
        this.matrix2 = new String [this.x][this.y];
        
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                this.matrix2[i][j]= "-";                
            }
        }
    }
    
    
    void printBoard2(){
        //System.out.println("- 1 2 3 4 5 6 7 8"); //MAKE IT GENERAL
        for (int i = 0; i < this.y; i++){
            //System.out.print(i + 1 + " ");
            for (int j = 0; j < this.x; j++){
                System.out.print(this.matrix2[j][i] + " ");
            }
            System.out.println();
        }
    }
    
    void goodClick(int x, int y, int value){
        if (matrix[x][y] == 0){
            Integer aux = value;
            this.matrix2[x][y]= aux.toString();
            matrix[x][y] = 1;
        }
    }
    
    void badClick(int x, int y){
        this.matrix2[x][y] = "X";
    }
    
    void zeroClick(int x, int y, int [][] array){
        if (matrix[x][y]==0){
            goodClick (x,y,0);
        
            if ((x > 0 && x < this.x - 1) && (y > 0 && y < this.y - 1)){
                zeroCenters(x,y,array);
            }
            else if ((x == 0 && y == 0) || (x == this.x -1 && y == this.y - 1) || (x == 0 && y == this.y - 1) || (x == this.x -1 && y == 0)){
                zeroCorners(x,y,array);
            }
            else {
                zeroSides(x,y,array);
            }
        }
    }
    
    void zeroCenters (int x, int y, int [][] array){
        for (int i = x - 1; i <= x + 1; i++){
                for (int j = y - 1; j <= y + 1; j++){
                    clicker(i, j, array);
                }
            }
    }
    
    void zeroCorners (int x, int y, int [][] array){
        if (x == 0 && y == 0){
            for (int i = x; i <= x + 1; i++){
                for (int j = y; j <= y + 1; j++){
                    clicker(i, j, array);
                }
            }            
        }
        else if (x == this.x - 1 && y == this.y - 1){
            for (int i = x - 1; i <= x ; i++){
                for (int j = y - 1; j <= y; j++){
                    clicker(i, j, array);
                }
            }
        }
        else if (x == 0 && y == this.y - 1){
            for (int i = x; i <= x + 1; i++){
                for (int j = y - 1; j <= y; j++){
                    clicker(i, j, array);
                }
            }
        }
        else {
            for (int i = x -1; i <= x; i++){
                for (int j = y; j <= y + 1; j++){
                    clicker(i, j, array);
                }
            }
        }
    }
    
    void zeroSides (int x, int y, int [][] array){
        if (x == 0){
            for (int i = x; i <= x + 1; i++){
                for (int j = y - 1; j <= y + 1; j++){
                    clicker(i, j, array);
                }
            }
        }
        else if (y == 0){
            for (int i = x - 1; i <= x + 1; i++){
                for (int j = y; j <= y + 1; j++){
                    clicker(i, j, array);
                }
            }
        }
        else if (x == this.x - 1){
            for (int i = x - 1; i <= x; i++){
                for (int j = y - 1; j <= y + 1; j++){
                    clicker(i, j, array);
                }
            }
        }
        else {
            for (int i = x - 1; i <= x + 1; i++){
                for (int j = y - 1; j <= y; j++){
                    clicker(i, j, array);
                }
            }
        }
    }
    
    void clicker(int i, int j, int [][] array){
        if (array[i][j] == 0){
            zeroClick(i,j,array);
        } else
            goodClick(i,j,array[i][j]);
    }
}