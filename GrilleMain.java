import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GrilleMain {
    public static void main(String[] args) {
        JFrame choixFenetre = new JFrame("Choix de la Grille");
        choixFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Bouton pour choisir une grille vide
        JButton grilleVideButton = new JButton("Grille Vide");
        grilleVideButton.addActionListener(e -> {
            choisirGrille(true);
            choixFenetre.dispose(); // Fermer la fenêtre de choix après sélection
        });

        // Bouton pour choisir une grille partiellement remplie
        JButton grillePartielButton = new JButton("Grille Partiellement Remplie");
        grillePartielButton.addActionListener(e -> {
            choisirGrille(false);
            choixFenetre.dispose(); // Fermer la fenêtre de choix après sélection
        });

        JPanel choixPanel = new JPanel();
        choixPanel.setLayout(new GridLayout(2, 1));
        choixPanel.add(grilleVideButton);
        choixPanel.add(grillePartielButton);

        choixFenetre.getContentPane().add(choixPanel, BorderLayout.CENTER);
        choixFenetre.setSize(300, 200);
        choixFenetre.setVisible(true);
    }

    // Méthode pour choisir et afficher la grille correspondante
    private static void choisirGrille(boolean estVide) {
        int[][] grille;
        if (estVide) {
            grille = new int[9][9]; // Création d'une grille vide
        } else {
            // Création d'une grille partiellement remplie (à adapter selon vos besoins)
            grille = new int[][] {
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
        }

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
