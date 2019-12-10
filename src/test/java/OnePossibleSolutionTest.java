import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class OnePossibleSolutionTest {
    @Test
    public void onepossibleSolutionSolve() throws IOException {
        ValidityChecker validityChecker = new ValidityChecker("OPSTestInput.txt",null);
        String board[][]= validityChecker.getSudokuBoard();
        OnePossibleSolution onePossibleSolution= new OnePossibleSolution(board);
        assertEquals(onePossibleSolution.getSize(),board[0].length);
        onePossibleSolution.solveSudoku();
        assertEquals(2,onePossibleSolution.getOnePossibleSolutionCount());
    }
}