import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class GrilleVideAction implements ActionListener {
    private static final int GRID_SIZE = 9;
    private static final int CELL_SIZE = 50;
    private static final int GAP_SIZE = 5;

    private JFrame grilleFrame;

    @Override
    public void actionPerformed(ActionEvent e) {
        int[][] grilleVide = new int[GRID_SIZE][GRID_SIZE];
        GrilleVide grillePanel = new GrilleVide(grilleVide);

        grilleFrame = new JFrame("Grille Sudoku");
        grilleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton sauvegarderButton = new JButton("Sauvegarder");
        sauvegarderButton.addActionListener(new SauvegarderActionListener(grilleVide, grilleFrame));
        grilleFrame.getContentPane().add(sauvegarderButton, BorderLayout.SOUTH);

        grilleFrame.add(grillePanel);
        grilleFrame.pack();
        grilleFrame.setSize(CELL_SIZE * GRID_SIZE + GAP_SIZE * (GRID_SIZE + 1), CELL_SIZE * GRID_SIZE + GAP_SIZE * (GRID_SIZE + 1));
        grilleFrame.setVisible(true);
    }
}
