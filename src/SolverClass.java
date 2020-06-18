import java.awt.event.ActionListener;
import java.util.stream.IntStream;

class SolverClass {
private int[][] board = new int[9][9];
private int intLW = 3;

    int[][] setupSudoku(int iLW) {
        /*
        Empty board will look like this
        0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0 0

         */
        intLW = iLW;
        int tmpLW = intLW*intLW;


        int[][] board = new int[tmpLW][tmpLW];
        for (int i = 0; i < tmpLW; i++) { // i is column
            for (int x = 0; x < tmpLW; x++) { // x is row
                board[i][x] = 0;
            }
        }

        return board;
    }

    boolean solveBoard(int[][] b) {

        // solve the board with recursion
        // function will go through each cell with numbers 1 to 9 until a positive result is found

        int tmpLW = intLW*intLW;

        board = b;
        for (int i = 0; i < tmpLW; i++) { // i is columns
            for (int x = 0; x < tmpLW; x++) { // x is rows

                if (board[i][x] == 0) {
                    for (int y = 1; y <= tmpLW; y++) {
                        board[i][x] = y;
                        if(isValid(board, i, x, y) && solveBoard(board)) {
                            return true;
                        }
                        board[i][x] = 0;
                    }
                    return false;
                }

            }

        }
        return true;
    }

    int[][] returnBoard() {
        return board;
    }

    private boolean isValid(int[][] board, int column, int row, int toCheck) {
        return (checkRow(board, row, toCheck) && checkColumn(board, column, toCheck) && checkCell(board, column, row, toCheck));
    }
    private boolean checkCell(int[][] board, int column, int row, int toCheck) {
        int tmpLW = intLW*intLW;

        int tmp = 0;
        int subsectionRowStart = (row / intLW) * intLW;
        int subsectionRowEnd = subsectionRowStart + intLW;

        int subsectionColumnStart = (column / intLW) * intLW;
        int subsectionColumnEnd = subsectionColumnStart + intLW;


        for (int i = subsectionColumnStart; i < subsectionColumnEnd; i++) {
            for (int x = subsectionRowStart; x < subsectionRowEnd; x++) {
                if (board[i][x] == toCheck) tmp++;
                if (tmp > 1) return false;
            }
        }
        return true;

    }



    private boolean checkRow(int[][] board, int row, int toCheck) {
        // give a tmp buffer of 1 match as there is allowed to be 1 in the row
        int tmpLW = intLW*intLW;

        int tmp = 0;
        for (int i = 0; i < tmpLW; i++) {
            if (board[i][row] == toCheck) tmp++;
            if (tmp > 1) return false;
        }
        return true;
    }

    private boolean checkColumn(int[][] board, int column, int toCheck) {
        int tmpLW = intLW*intLW;

        int tmp = 0;
        for (int i = 0; i < tmpLW; i++){
            if (board[column][i] == toCheck) tmp++;
            if (tmp > 1) return false;
        }
        return true;
    }


}
