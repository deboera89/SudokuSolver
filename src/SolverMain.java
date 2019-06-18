import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolverMain {

    private static int[][] board;
    private static JTextField[][] jt = new JTextField[9][9];
    private static SolverClass ssc;
    public static void main(String[] args) {

        showGUI();

        ssc = new SolverClass();
        board = ssc.setupSudoku();

       // ssc.solveBoard(board);
       // ssc.printBoard();

    }

    private static void showGUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        Border blackline = BorderFactory.createLineBorder(Color.black);
        int[] border = {0,0,0,0};


        JFrame frame = new JFrame("Simple Sudoku Solver");
        frame.setSize(500,500);
        JPanel panel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();

        panel.setLayout(gbl);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.11;
        gbc.weighty = 0.2;

        for (int i = 0; i < 9; i++) { // i is column
            for (int x = 0; x < 9; x++) { // x is row

                jt[i][x] = new JTextField(2 );
                jt[i][x].setHorizontalAlignment(JTextField.CENTER);
                border = getBorderWidth(i, x);

                jt[i][x].setBorder(new MatteBorder(border[0],border[1], border[2], border[3], Color.black));

                panel.add(jt[i][x], gbc);
                gbc.gridx++;

            }
            gbc.gridy++;
            gbc.gridx = 0;
        }


        JButton jb = new JButton("Solve");
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                loadBoard();
                ssc.solveBoard(board);
                solveToGUI();
            }
        });
        panel.add(jb, gbc);

        JButton jbC = new JButton("Clear");
        gbc.gridx = 5;
        gbc.gridwidth = 4;
        jbC.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                clearBoard();
            }
        });
        panel.add(jbC, gbc);


        frame.add(panel);

        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
    private static int[] getBorderWidth(int row, int column) {

        int[] tmpBorder = {1,1,1,1};

        if (column == 0) tmpBorder[1] = 3;
        if (row == 0) tmpBorder[0] = 3;
        if ((row+1)%3 == 0) tmpBorder[2] = 3;
        if ((column+1)%3 == 0) tmpBorder[3] = 3;


        return tmpBorder;

    }
    private static void clearBoard(){
        for (int i = 0; i < 9; i++) { // i is column
            for (int x = 0; x < 9; x++) { // x is row
                jt[i][x].setText("");
            }
        }


    }
    private static void solveToGUI() {

        int[][] b = ssc.returnBoard();

        for (int i = 0; i < 9; i++) { // i is column
            for (int x = 0; x < 9; x++) { // x is row
                jt[i][x].setText(String.valueOf(b[i][x]));
            }
        }
    }

    private static void loadBoard() {
        for (int i = 0; i < 9; i++) { // i is column
            for (int x = 0; x < 9; x++) { // x is row
                if (jt[i][x].getText().isEmpty()) {
                    board[i][x] = 0;
                } else {
                    board[i][x] = Integer.valueOf(jt[i][x].getText());
                }
            }
        }
    }
}

