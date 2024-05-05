import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GrilleMain {
    // Méthode pour choisir et afficher la grille correspondante
    public static void choisirGrille(int[][] grille) {
        JFrame fenetre = new JFrame("Grille Sudoku");
        configurerFenetre(fenetre, grille);
        JButton validerButton = new JButton("Valider");
        validerButton.addActionListener(new ValiderButtonActionListener(fenetre));
        fenetre.getContentPane().add(validerButton, BorderLayout.NORTH);
        fenetre.setSize(600, 600);
        fenetre.setVisible(true);
    }

    // Méthode pour la résolution manuelle du Sudoku
    public static void resolutionManuelle(int[][] grille) {
        JFrame fenetre = new JFrame("Résolution Manuelle du Sudoku");
        configurerFenetre(fenetre, grille);
        JButton validerButton = new JButton("Valider");
        validerButton.addActionListener(new ValiderButtonActionListener(fenetre));
        fenetre.getContentPane().add(validerButton, BorderLayout.NORTH);
        fenetre.setSize(600, 600);
        fenetre.setVisible(true);
    }

    // Méthode pour configurer la fenêtre avec la grille et les boutons
    private static void configurerFenetre(JFrame fenetre, int[][] grille) {
        fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Grille grillePanel = new Grille(grille); // Utilisation de la grille sélectionnée
        fenetre.getContentPane().add(grillePanel, BorderLayout.CENTER);

        // Création du bouton de sauvegarde
        JButton sauvegarderButton = new JButton("Sauvegarder");
        fenetre.getContentPane().add(sauvegarderButton, BorderLayout.SOUTH);
    }
}

// Classe ActionListener pour le bouton de validation
class ValiderButtonActionListener implements ActionListener {
    private JFrame fenetre;

    public ValiderButtonActionListener(JFrame fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Grille grillePanel = (Grille) fenetre.getContentPane().getComponent(0); // Récupération de la grille
        if (grillePanel.estGrilleComplete()) {
            JOptionPane.showMessageDialog(fenetre, "Félicitations ! La grille est complète !");
        } else {
            JOptionPane.showMessageDialog(fenetre, "La grille n'est pas encore complète.");
        }
    }
}
