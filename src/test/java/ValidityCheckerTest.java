import org.junit.Test;

import java.io.File;
import java.io.IOException;

//Manually check the generateed file
public class ValidityCheckerTest {
    @Test
    public void testConstructor() throws IOException {
        ValidityChecker validityChecker = new ValidityChecker("Kanak.txt","output.txt");
        String board[][]= validityChecker.getSudokuBoard();
        int backTrackingCount=0;
        int onePossibleCount=0;
        int humanKindCount=0;
        long totalTimeTaken1=0;
        long totalTimeTaken2=0;
        BackTracking backTracking=new BackTracking(board);
        validityChecker.checkValidity(board, board[0].length);
        long startTime = System.nanoTime();


        long startTime3 = System.nanoTime();
        backTracking.solveSudoku();
        long endTime3 = System.nanoTime();
        long totalTimeTaken3=endTime3-startTime3;
        backTrackingCount=backTracking.getBackTrackingCount();
        board = backTracking.getGameBoard();
        backTracking.display();
        long endTime = System.nanoTime();
        long totalTimeTaken=endTime-startTime;
        File fout = new File("out.txt");
        validityChecker.writeFile(fout,board,onePossibleCount,humanKindCount,backTrackingCount,totalTimeTaken,totalTimeTaken1,totalTimeTaken2,totalTimeTaken3);

    }
    @Test
    public void testWriteErrorFile() throws IOException {
            ValidityChecker validityChecker = new ValidityChecker("invalid.txt", "invalidTestOutput.txt");
    }
}