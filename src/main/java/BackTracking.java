public class BackTracking extends Algorithm {
    private String sudokuBoard[][];
    String [] validNumbers;
    private int size;
    private int backTrackingCount=0;

    public BackTracking(String board[][]){
        super(board);
        this.size = getSize();
        this.sudokuBoard = getSudokuBoard();
        this.validNumbers = getValidSymbols();
        this.backTrackingCount=0;

    }
    @Override
    public boolean solveSudoku( ) {
        int row = -1;
        int col = -1;

        setBackTrackingCount(this.backTrackingCount);
        boolean isEmpty = true;


        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {

                if (this.sudokuBoard[i][j].equals("0"))
                {
                    row = i;
                    col = j;

                    // we still have some remaining
                    // missing values in Sudoku
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty)
            {
                break;
            }
        }
        // no empty space left
        if (isEmpty)
        {
            return true;
        }

        // else for each-row backtrack
        for (String i : validNumbers)
        {
            //System.out.println("valid number is : "+i);
            if (isOk(this.sudokuBoard, row, col, i ))
            {   this.backTrackingCount++;
                this.sudokuBoard[row][col]=i;
                setValue(row,col,i);
                if (solveSudoku())
                {
                    // print(board, n);
                    return true;
                }
                else
                {
                    this.sudokuBoard[row][col]="0"; // replace it
                    setValue(row,col,"0");


                }
            }
        }
        this.setSudokuBoard(this.sudokuBoard);

        return false;
    }
}

