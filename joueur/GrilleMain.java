import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GrilleMain {
    // Méthode pour choisir et afficher la grille correspondante
    public static void choisirGrille(int[][] grille) {
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

    // Méthode pour la résolution manuelle du Sudoku
    public static void resolutionManuelle(int[][] grille) {
        JFrame fenetre = new JFrame("Résolution Manuelle du Sudoku");
        fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Grille grillePanel = new Grille(grille); // Utilisation de la grille sélectionnée
        fenetre.getContentPane().add(grillePanel, BorderLayout.CENTER);

        fenetre.setSize(600, 600);
        fenetre.setVisible(true);
    }
}
