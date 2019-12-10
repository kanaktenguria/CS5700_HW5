public class Humakind extends Algorithm {



    private String sudokuBoard[][];
    private String[] validNumbers;
    private int size;
    private int humanKindCount;

    public Humakind(String board[][]) {

        super(board);
        this.size = getSize();
        this.validNumbers = getValidSymbols();
        this.sudokuBoard = getSudokuBoard();
        this.humanKindCount=0;

    }

    @Override
    public boolean solveSudoku() {


        int initial_zero_count = 0;
        int final_zero_count = 0;
        humanKindCount++;

//        System.out.println(initial_zero_count);
//        System.out.println(final_zero_count);
        for (int i = 0; i < size; i++) {
            for (int j=0; j < size; ++j) {
                if (this.sudokuBoard[i][j].equals("0")) {
                    initial_zero_count++;
                }
            }
        }

        for(int i =0; i<size; ++i){
            for (int j = 0 ; j<size ; ++j){
                if(this.sudokuBoard[i][j].equals("0")){
                    if(valid_in_row(i)){
                        String [] check_row = getrow(i);
                        for(int k =0; k<size; ++k){
                            for(int l = 0; l<size; ++l){
                                if (validNumbers[k].equals(check_row[l])){
                                    break;
                                }
                                if(l == size-1){
                                    this.sudokuBoard[i][j]=validNumbers[k];
                                }
                            }
                        }
                    }
                    if(valid_in_column(j)){
                        String [] check_column = getcolumn(j);
                        for(int k =0; k<size; ++k){
                            for(int l = 0; l<size; ++l){
                                if (validNumbers[k].equals(check_column[l])){
                                    break;
                                }
                                if(l == size-1){
                                    this.sudokuBoard[i][j]=validNumbers[k];
                                }
                            }
                        }
                    }
                    if(valid_in_grid(i,j)){
                        String [] check_grid = getgrid(i,j);
                        for(int k =0; k<size; ++k){
                            for(int l = 0; l<size; ++l){
                                if (validNumbers[k].equals(check_grid[l])){
                                    break;
                                }
                                if(l == size-1){
                                    this.sudokuBoard[i][j]=validNumbers[k];
                                }
                            }
                        }
                    }
                }
            }
        }
        for(int i = 0; i<size; ++i){
            for(int j = 0; j<size; ++j){
                if (this.sudokuBoard[i][j].equals("0")){
                    final_zero_count++;
                }
            }
        }
        if(initial_zero_count != final_zero_count){
            solveSudoku();
        }
        setSudokuBoard(this.sudokuBoard);
        setHumanKindCount(this.humanKindCount);
        return true;
    }
}


