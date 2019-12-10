public abstract class Algorithm {


    private String sudokuBoard [][];
    private String[] validSymbols;
    private int size;
    private int backTrackingCount=0;
    private int humanKindCount;
    private int onePossibleSolutionCount;

    /*
    @param board
    constructor
     */
    public Algorithm(String board[][]){
        this.sudokuBoard=new String[board[0].length][board[0].length];
        this.size=board[0].length;
        this.validSymbols=new String[this.size];
        for(int i=1;i<board[0].length+1;i++){
            for (int j=0; j<board[0].length;j++) {
                this.sudokuBoard[i-1][j] = board[i][j];
            }}

        for (int i = 0; i < board[0].length; i++) {

            this.validSymbols[i] = board[0][i];
        }

    }

    //to get size of the board
    public int getSize() {
        return size;
    }

    public String[][] getGameBoard(){
        String[][] tempBoard=new String[size+1][size];
        for(int i=0;i<size+1;i++){
            for (int j=0; j<size;j++) {
                if(i==0)
                {
                    for (int k=0; k<size;k++)
                        tempBoard[0][k]=validSymbols[k];
                }
                else
                    tempBoard[i][j]=this.sudokuBoard[i-1][j];
            }
            }
        return tempBoard;
    }

    //to get board
    public String[][] getSudokuBoard() {
        return sudokuBoard;
    }

    //to set sudoku board
    public void setSudokuBoard(String[][] sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    //to get valid symbols of sudoku
    public String[] getValidSymbols() {
        return validSymbols;
    }

    public void setValue(int row, int col, String number)
    {
        this.sudokuBoard[row][col]=number;
    }

    public void setBackTrackingCount(int backTrackingCount) {
        this.backTrackingCount = backTrackingCount;
    }

    public void setHumanKindCount(int humanKindCount) {
        this.humanKindCount = humanKindCount;
    }

    public int getBackTrackingCount() {
        return backTrackingCount;
    }

    public int getHumanKindCount() {
        return humanKindCount;
    }

    public int getOnePossibleSolutionCount() {
        return onePossibleSolutionCount;
    }

    public void setOnePossibleSolutionCount(int onePossibleSolutionCount) {
        this.onePossibleSolutionCount = onePossibleSolutionCount;
    }

    //to check if it is valid in row
    public boolean valid_in_row(int row) {
        int count = 0;
        for (int i = 0; i < this.sudokuBoard[0].length; i++) {
            if (this.sudokuBoard[row][i].equals("0")) {
                count++;
            }
        }
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    //to check if it is valid in column
    public boolean valid_in_column(int col) {
        int count = 0;
        for (int i = 0; i < this.sudokuBoard[0].length; i++) {
            if (this.sudokuBoard[i][col].equals("0")) {
                count++;
            }
        }
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    //to check if it is valid in grid
    public boolean valid_in_grid(int row, int col) {
        int count = 0;
        String [] grid = getgrid(row,col);
        for(int i =0; i<this.sudokuBoard[0].length ; ++i){
            if(grid[i].equals("0")){
                count++;
            }
        }
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    //to get a particular row of sudoku
    public String[] getrow(int row){
        String [] row_to_return = new String[size];
        for(int i = 0; i<size; ++i){
            row_to_return[i]= this.sudokuBoard[row][i];
        }
        return row_to_return;
    }

    //to get a particular column of sudoku
    public String[] getcolumn(int col){
        String [] column_to_return = new String[size];
        for(int i = 0; i<size; ++i){
            column_to_return[i]= this.sudokuBoard[i][col];
        }
        return column_to_return;
    }

    //to get a particular grid of sudoku
    public String[] getgrid(int row, int col){
        String [] grid = new String[size];
        int l = 0;
        Double sqrt = Math.sqrt(size);
        int squareRoot = sqrt.intValue();
        int r = row - row % squareRoot;
        int c = col - col % squareRoot;
        for (int i = r; i < r + squareRoot; i++) {

            for (int j = c; j < c + squareRoot; j++) {
                grid[l] =this.sudokuBoard[i][j];
                l++;
            }
        }
        return grid;
    }



    // we check if a possible number is already in a row
    public boolean isInRow(String board[][],int row, String number) {
        for (int i = 0; i < board[0].length; i++)
            if (board[row][i].equals(number))
                return false;

        return true;
    }

    // we check if a possible number is already in a column
    public boolean isInCol(String board[][], int col, String number) {
        for (int i = 1; i < board[0].length; i++)
            if (board[i][col].equals(number))
                return false;

        return true;
    }

    // we check if a possible number is in its 3x3 box
    public boolean isInBox(String board[][], int row, int col, String number, int size) {
        Double sqrt = Math.sqrt(size);
        int squareRoot = sqrt.intValue();
        int r = row - row % squareRoot;
        int c = col - col % squareRoot;
        for (int i = r; i < r + squareRoot; i++) {

            for (int j = c; j < c + squareRoot; j++) {
                if(board[i][j].equals(number))
                    return false;
            }
        }
        return true;
    }

    // combined method to check if a number possible to a row,col position is ok
    public boolean isOk(String board[][], int row, int col, String number) {
        return isInRow(board, row, number)  &&  isInCol(board, col, number)  &&  isInBox(board, row, col, number, board[0].length);
    }

    //Abstract method to solve sudoku
    public abstract boolean solveSudoku();

    //to display the sudoku
    public void display() {
        for (int i = 0; i < sudokuBoard[0].length; i++) {
            for (int j = 0; j < sudokuBoard[0].length; j++) {
                System.out.print(" " + sudokuBoard[i][j]);
            }

            System.out.println();
        }

        System.out.println();



    }





}

