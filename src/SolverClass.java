import java.util.stream.IntStream;

public class SolverClass {
private int[][] board = new int[9][9];


    int[][] setupSudoku() {
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
        int[][] board = new int[9][9];
        for (int i = 0; i < 9; i++) { // i is column
            for (int x = 0; x < 9; x++) { // x is row
                board[i][x] = 0;

            }
        }

        return board;
    }

    boolean solveBoard(int[][] b) {
        board = b;
        for (int i = 0; i < 9; i++) { // i is columns
            for (int x = 0; x < 9; x++) { // x is rows

                if (board[i][x] == 0) {
                    for (int y = 1; y <= 9; y++) {
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
        if(checkRow(board, row, toCheck) && checkColumn(board, column, toCheck) && checkCell(board, column, row, toCheck)) {
            return true;
        }
        return false;
    }
    private boolean checkCell(int[][] board, int column, int row, int toCheck) {
        int tmp = 0;
        int subsectionRowStart = (row / 3) * 3;
        int subsectionRowEnd = subsectionRowStart + 3;

        int subsectionColumnStart = (column / 3) * 3;
        int subsectionColumnEnd = subsectionColumnStart + 3;


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

        int tmp = 0;
        for (int i = 0; i < 9; i++) {
            if (board[i][row] == toCheck) tmp++;
            if (tmp > 1) return false;
        }
        return true;
    }

    private boolean checkColumn(int[][] board, int column, int toCheck) {
        int tmp = 0;
        for (int i = 0; i < 9; i++){
            if (board[column][i] == toCheck) tmp++;
            if (tmp > 1) return false;
        }
        return true;
    }


}
