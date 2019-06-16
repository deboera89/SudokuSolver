public class SolverMain {

    public static int[][] board;

    public static void main(String[] args) {
        SolverClass ssc = new SolverClass();
        board = ssc.setupSudoku();

        ssc.solveBoard(board);

        ssc.printBoard();

    }



}
