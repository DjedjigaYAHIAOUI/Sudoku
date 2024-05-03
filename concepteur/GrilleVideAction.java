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

        grilleFrame.add(grillePanel); // Ajouter la grillePanel à la JFrame

        JButton sauvegarderButton = new JButton("Sauvegarder");
        sauvegarderButton.addActionListener(new SauvegarderActionListener(grilleVide, grilleFrame));

        JPanel buttonPanel = new JPanel(); // Créer un JPanel pour le bouton
        buttonPanel.add(sauvegarderButton); // Ajouter le bouton au JPanel

        grilleFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH); // Ajouter le JPanel au bas de la JFrame

        grilleFrame.pack();
        grilleFrame.setSize(CELL_SIZE * GRID_SIZE + GAP_SIZE * (GRID_SIZE + 1), CELL_SIZE * GRID_SIZE + GAP_SIZE * (GRID_SIZE + 1));
        grilleFrame.setVisible(true);
    }
}
