public class OnePossibleSolution extends Algorithm {

    private String sudokuBoard[][];
    private String[] validNumbers;
    private int size;
    private int onePossibleSolutionCount;


    public OnePossibleSolution(String board[][]) {

        super(board);
        this.size = getSize();
        this.validNumbers = getValidSymbols();
        this.sudokuBoard = getSudokuBoard();
        this.onePossibleSolutionCount=0;
    }

    @Override
    public boolean solveSudoku() {

        int initial_zero_count = 0;
        int final_zero_count = 0;
        this.onePossibleSolutionCount++;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; ++j) {
                if (sudokuBoard[i][j].equals("0")) {
                    initial_zero_count++;
                }
            }
        }

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (sudokuBoard[i][j].equals("0")) {
                    for (String num : validNumbers) {
                        if (isOk(this.sudokuBoard, i, j, num)) {
                            this.sudokuBoard[i][j] = num;
                            setValue(i, j, num);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (sudokuBoard[i][j].equals("0")) {
                    final_zero_count++;
                }
            }
        }
        if (initial_zero_count != final_zero_count) {
            solveSudoku();
        }
        setSudokuBoard(this.sudokuBoard);
        setOnePossibleSolutionCount(this.onePossibleSolutionCount);
        return true;
    }
}
