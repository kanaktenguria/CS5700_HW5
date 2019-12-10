import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class AlgorithmTest {
    @Test
    public void testConstructor() throws IOException {
        ValidityChecker validityChecker = new ValidityChecker("testTemplate.txt",null);
        String board[][]= validityChecker.getSudokuBoard();
        BackTracking backTracking = new BackTracking(board);
        backTracking.getGameBoard();
        String board1[][]=backTracking.getGameBoard();
        for (int i=0; i<backTracking.getSize()+1;i++) {
            for (int j=0; j<backTracking.getSize();j++){

                assertEquals(board[i][j], board1[i][j]);
            }}

        String []validSymbols=new String[backTracking.getSize()];
        for (int j=0; j<backTracking.getSize();j++) {
            validSymbols[j]=board1[0][j];
        }

        String [] validSymbols1= backTracking.getValidSymbols();

        for (int j=0; j<backTracking.getSize();j++){
            assertEquals(validSymbols1[j], validSymbols[j]);
        }

        backTracking.getSudokuBoard();
        backTracking.setSudokuBoard(board1);
        backTracking.setValue(1,2,"8");
        board=backTracking.getSudokuBoard();
        assertEquals(board[1][2],"8");

        backTracking.setBackTrackingCount(8);
        assertEquals(backTracking.getBackTrackingCount(),8);
        backTracking.setOnePossibleSolutionCount(2);
        assertEquals(backTracking.getOnePossibleSolutionCount(),2);
        backTracking.setHumanKindCount(10);
        assertEquals(backTracking.getHumanKindCount(),10);


        backTracking.valid_in_row(1);
        backTracking.valid_in_column(2);
        backTracking.valid_in_grid(1,2);

        String row[]=backTracking.getrow(5);
        for (int j=0; j<backTracking.getSize();j++){
            assertEquals(row[j],board1[5][j]);
        }

        String column[]=backTracking.getcolumn(8);
        for (int j=0; j<backTracking.getSize();j++){
            assertEquals(column[j],board1[j][8]);
        }

        backTracking.getgrid(2,4);


        assertEquals(backTracking.isInRow(board,1,"2"),true);
        assertEquals(backTracking.isInCol(board,1,"2"),true);
        assertEquals(backTracking.isInBox(board,1,3,"2",board.length),false);
        assertEquals(backTracking.isOk(board,1,3,"2"),false);

        backTracking.display();

    }
}