import java.io.*;

public class ValidityChecker {
    private int sudokuSize;
    private String[][] sudokuArray;
    private String[][] sudokuBoard;
    private String sudokuElements[];
    private File fout;
    private File input;

    public ValidityChecker(String inputFile, String outputFile) throws IOException {
        input = new File(inputFile);
        if(outputFile!=null){
            fout = new File(outputFile);
        }
        getSudoku();
    }

    public void getSudoku() throws IOException {
        FileReader fr = new FileReader(input);
        BufferedReader br = new BufferedReader(fr);
        this.sudokuSize = Integer.valueOf(br.readLine());

        BufferedReader reader = new BufferedReader(new FileReader(input));
        int lines = 0;
        while (reader.readLine() != null) {
            lines++;
        }
        reader.close();
        if(sudokuSize!=lines-2){
            System.out.println("Not a 4x4, 9x9, 16x16, 25x25, or 36x36");
            if(fout!=null){
            writeErrorFile("Not a 4x4, 9x9, 16x16, 25x25, or 36x36");
            }
            System.exit(1); 
       }

        this.sudokuArray = new String[sudokuSize + 1][sudokuSize];
        this.sudokuBoard = new String[sudokuSize+1][sudokuSize];

        //Copy sudoku problem to sudoku array and replaces "-" with 0
        for (int sudokuLine = 0; sudokuLine <= sudokuSize; sudokuLine++)   {
            String currentLine = br.readLine();
            String temp[] = currentLine.split(" ");
            for (int element = 0; element < temp.length; element++) {
                if (temp[element].equals("-")) {
                    sudokuArray[sudokuLine][element] = "0";
                } else {
                    sudokuArray[sudokuLine][element] = temp[element];
                }
            }
        }

        //Copy sudoku elements (1,2,3,4 or A,B,C,D) to an array
        sudokuElements =new String[sudokuSize];
        for(int i = 0; i< sudokuArray[0].length; i++){
            this.sudokuElements[i]= sudokuArray[0][i];
        }

        //Copy problem to array to be solved.
        for (int i = 0; i < sudokuSize + 1; i++) {
            for (int n = 0; n < sudokuSize; n++) {
                this.sudokuBoard[i][n] = sudokuArray[i][n];
            }
        }
    }
    public String[][] getSudokuBoard(){
        return sudokuBoard;
    }
    public int getSudokuSize(){
        return sudokuSize;
    }

    public void checkValidity(String[][] board, int length) throws IOException {
        try {
            String invalidMessage= null;
            String sudokuBoard[][]= new String[sudokuSize][sudokuSize];
            Boolean isInvalid= false;
            int ip,jp;
            for (ip=1; ip<sudokuSize+1; ip++){
                for(jp=0;jp<sudokuSize;jp++){
                    for(int k=0; k<sudokuElements.length;k++) {
                        if (board[ip][jp].equals(sudokuElements[k]) || board[ip][jp] == "0") {
                            isInvalid = false;
                            break;
                        } else {
                            isInvalid = true;
                            invalidMessage= "Invalid symbol";
                        }
                    }
                    if(isInvalid) {
                        jp = sudokuSize + 2;
                        ip = sudokuSize + 2;
                        break;
                    }
                }
            }

            for (int i=1; i<sudokuSize+1; i++){
                for(int j=0;j<sudokuSize;j++){
                    sudokuBoard[i-1][j]=board[i][j];
                }
            }
            if (sudokuBoard == null ) {
                System.out.println("Sudoku is null");
                invalidMessage="Sudoku is null";
                isInvalid = true;
            }
            for (int i=1; i<sudokuSize; i++)
            {
                if( sudokuBoard[i].length != sudokuSize){
                    System.out.println("Invalid Format");
                    invalidMessage="Invalid Format";
                    isInvalid = true;
                    break;
                }}
            for (int i=1; i<sudokuSize; i++)
            {
                if( sudokuBoard.length != sudokuSize){
                    System.out.println("Not proper size");
                    invalidMessage="Not proper size";
                    isInvalid = true;
                    break;
                }
            }
            //Check Each Column
            for (int i = 0; i < sudokuSize; i++) {
                String[] colBoard = new String[sudokuSize];
                for (int j = 0; j < sudokuSize; j++) {
                    colBoard[j] = sudokuBoard[j][i];
                    //System.out.println("values in colBoard are : "+colBoard[j].getVal());
                }
                int m = 0;
                while (m < colBoard.length) {
                    int n = 0;
                    while (n < colBoard.length) {
                        //  System.out.println("value of m and n are : "+colBoard[m].getVal()+","+colBoard[n].getVal());
                        if (colBoard[m].equals(colBoard[n])) {
                            if (m == n) {
                                //System.out.println("value of m and n are equal");
                            } else {
                                if (Integer.parseInt(colBoard[m]) == 0 && Integer.parseInt(colBoard[m]) == 0) {
                                    //          System.out.println("value of m and n are zero");
                                } else {
                                    System.out.println("Invalid column count");
                                    invalidMessage="Invalid column count";
                                    isInvalid = true;
                                    break;
                                }
                            }
                        }
                        n++;
                    }
                    m++;
                }
            }
            //Check Each Row
            for (int i = 0; i < sudokuSize; i++) {
                String[] rowBoard = new String[sudokuSize];
                for (int j = 0; j < sudokuSize; j++) {
                    rowBoard[j] = sudokuBoard[i][j];
                    //  System.out.println("values in rowBoard are : "+rowBoard[j].getVal());
                }
                int m = 0;
                while (m < rowBoard.length) {
                    int n = 0;
                    while (n < rowBoard.length) {
                        //    System.out.println("value of m and n are : "+rowBoard[m].getVal()+","+rowBoard[n].getVal());
                        if (rowBoard[m].equals(rowBoard[n])) {
                            if (m == n) {
                                //System.out.println("value of m and n are equal");
                            } else {
                                if (Integer.parseInt(rowBoard[m]) == 0 && Integer.parseInt(rowBoard[m]) == 0) {
                                    //              System.out.println("value of m and n are zero");
                                } else {
                                    System.out.println("Invalid row count");
                                    invalidMessage="Invalid row count";
                                    isInvalid = true;
                                    break;
                                }
                            }
                        }
                        n++;
                    }
                    m++;
                }
            }
            // check that num is not repeated in subgrid
            for (int i = 0; i < sudokuSize; i++) {
                for (int j = 0; j < sudokuSize; j++) {
                    String[] subGrid = getSubGrid(i, j, String.valueOf(sudokuBoard[i][j]), sudokuSize, sudokuBoard);
                    for (int p = 0; p < subGrid.length; p++) {
                        //System.out.println("subGrid values are : "+subGrid[p].getVal());
                    }
                    int m = 0;
                    while (m < subGrid.length) {
                        int n = 0;
                        while (n < subGrid.length) {
                            //  System.out.println("value of m and n are : "+subGrid[m].getVal()+","+subGrid[n].getVal());
                            if (subGrid[m].equals(subGrid[n])) {
                                if (m == n) {
                                    //System.out.println("value of m and n are equal");
                                } else {
                                    if (Integer.parseInt(subGrid[m]) == 0 && Integer.parseInt(subGrid[m]) == 0) {
                                        //            System.out.println("value of m and n are zero");
                                    } else {
                                        System.out.println("Number repeated in subgrid");
                                        invalidMessage="Number repeated in subgrid";
                                        isInvalid = true;
                                        break;
                                    }
                                }
                            }
                            n++;
                        }
                        m++;
                    }
                }
            }
            if(isInvalid){
                System.out.println(invalidMessage);
                if(fout!=null){
                    writeErrorFile(invalidMessage);
                }
                System.exit(1);
            }
        } catch (Exception e1) {
            String error= e1.toString();
            if(error.equals("java.lang.NullPointerException")){
                System.out.println("Invalid: not formatted correctly");
                writeErrorFile("Invalid: not formatted correctly");
                System.exit(1);
            }
        }
    }
    //to get a particular row of sudoku
    public String[] getSubGrid(int row, int column, String number, int size, String board[][]){
        String [] subGridValues = new String[size];
        Double sqrt = Math.sqrt(size);
        int squareRoot = sqrt.intValue();
        int r = row - row%squareRoot;
        int c = column - column%squareRoot;
        int k = 0;
        for(int i = r; i<r+squareRoot; i++){

            for(int j = c; j<c+squareRoot; j++){
                subGridValues[k] = board[i][j];
                k++;
            }
        }

        return subGridValues;
    }

    public  void writeFile(File output, String [][]board, int BTCount, int OPCount, int HKCount, long timeTaken, long time1, long time2, long time3) throws IOException {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output)));

        bw.write(String.valueOf(board[0].length));
        bw.newLine();
        for (int i = 0; i < this.sudokuSize + 1; i++) {
            for (int j = 0; j < this.sudokuSize; j++) {
                if (this.sudokuArray[i][j].equals("0")) {
                    bw.write("-");
                    bw.write(" ");
                } else {
                    bw.write(this.sudokuArray[i][j]);
                    bw.write(" ");
                }
            }
            bw.newLine();

        }

        bw.newLine();
        bw.newLine();
        bw.write("Solution:");
        bw.newLine();
        bw.newLine();

        for (int i = 1; i < this.sudokuSize + 1; i++) {
            for (int j = 0; j < this.sudokuSize; j++) {
                bw.write(board[i][j]);
                bw.write(" ");
            }
            bw.newLine();
        }

        bw.newLine();
        bw.newLine();

        bw.write("Total Time:   ");
        bw.write(String.valueOf(timeTaken)+"   ns");
        bw.newLine();
        bw.newLine();
        bw.newLine();
        bw.write("Strategy               ");
        bw.write("Uses          ");
        bw.write("Time   ");
        bw.newLine();
        bw.write("One Possible Solution  ");
        bw.write(OPCount+"      ");
        bw.write(String.valueOf(time1)+"  ns");
        bw.newLine();
        bw.write("Human Kind Strategy    ");
        bw.write(HKCount+"      ");
        bw.write(String.valueOf(time2)+"  ns");
        bw.newLine();
        bw.write("BackTracking           ");
        bw.write(BTCount+"      ");
        bw.write(String.valueOf(time3)+"  ns");
        bw.close();
    }
    public  void writeErrorFile(String invalidMessage) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(input));
        BufferedWriter writer = new BufferedWriter(new FileWriter(fout));

        //... Loop as long as there are input lines.
        String line = null;
        while ((line=reader.readLine()) != null) {
            writer.write(line);
            writer.newLine();   // Write system dependent end of line.
        }

        writer.newLine();

        writer.write(invalidMessage);

        writer.close();
    }
}
