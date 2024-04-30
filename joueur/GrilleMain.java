import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GrilleMain {
    // Méthode pour choisir et afficher la grille correspondante
    public static void choisirGrille() {
        int[][] grille = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        JFrame fenetre = new JFrame("Grille Sudoku");
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Grille grillePanel = new Grille(grille); // Utilisation de la grille sélectionnée
        fenetre.getContentPane().add(grillePanel, BorderLayout.CENTER);

        // Création de l'action pour le bouton de sauvegarde
        ActionListener sauvegarderAction = new SauvegarderAction(fenetre, grillePanel);

        // Bouton de sauvegarde
        JButton sauvegarderButton = new JButton("Sauvegarder");
        sauvegarderButton.addActionListener(sauvegarderAction);
        fenetre.getContentPane().add(sauvegarderButton, BorderLayout.SOUTH);

        fenetre.setSize(600, 600);
        fenetre.setVisible(true);
    }
}
