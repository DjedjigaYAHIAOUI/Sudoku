import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GrilleMain {
    public static void main(String[] args) {
         int[][] grille = {
	    {0, 0, 0, 0, 9, 5, 0, 0, 4},
                {5, 3, 0, 4, 0, 8, 7, 0, 2},
                {0, 0, 0, 7, 0, 0, 6, 0, 3},
                {9, 0, 0, 0, 3, 4, 0, 8, 0},
                {0, 4, 0, 0, 1, 0, 0, 7, 0},
                {0, 2, 0, 5, 7, 0, 0, 0, 6},
                {4, 0, 9, 0, 0, 2, 0, 0, 0},
                {6, 0, 7, 9, 0, 3, 0, 2, 1},
                {2, 0, 0, 6, 5, 0, 0, 0, 0}
        };


        SwingUtilities.invokeLater(() -> {
            JFrame fenetre = new JFrame("Grille Sudoku");
            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Grille grillePanel = new Grille(grille);
            fenetre.getContentPane().add(grillePanel, BorderLayout.CENTER);

            // Bouton de sauvegarde
            JButton sauvegarderButton = new JButton("Sauvegarder");
            sauvegarderButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showSaveDialog(fenetre);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        grillePanel.sauvegarderGrille(selectedFile);
                    }
                }
            });
            fenetre.getContentPane().add(sauvegarderButton, BorderLayout.SOUTH);

            fenetre.setSize(600, 600);
            fenetre.setVisible(true);
        });
    }
}
