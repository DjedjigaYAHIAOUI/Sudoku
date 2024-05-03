import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création des boutons
        JButton grilleVideButton = new JButton("Partir d'une grille vide");
        JButton chargerGrilleButton = new JButton("Charger une grille");

        // Personnalisation des boutons
        grilleVideButton.setBackground(new Color(50, 50, 200)); // Bleu foncé
        grilleVideButton.setForeground(Color.WHITE);
        grilleVideButton.setFont(new Font("Arial", Font.BOLD, 14));
        grilleVideButton.setPreferredSize(new Dimension(180, 50)); // Diviser la longueur par deux
        chargerGrilleButton.setBackground(new Color(50, 50, 200)); // Bleu foncé
        chargerGrilleButton.setForeground(Color.WHITE);
        chargerGrilleButton.setFont(new Font("Arial", Font.BOLD, 14));
        chargerGrilleButton.setPreferredSize(new Dimension(180, 50)); // Diviser la longueur par deux

        // Ajout des boutons au JPanel principal
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10)); // Utilisation d'une disposition en grille avec un espacement de 10 pixels
        buttonPanel.setBackground(Color.WHITE); // Changer la couleur de fond
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Ajout d'une marge autour des boutons
        buttonPanel.add(grilleVideButton);
        buttonPanel.add(chargerGrilleButton);

        // Ajout du JPanel des boutons à la JFrame
        frame.add(buttonPanel);

        frame.setSize(450, 600); // Définition de la taille de la fenêtre
        frame.setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
        frame.setVisible(true); // Rendre la fenêtre visible

        // Gestionnaires d'événements pour les boutons
        grilleVideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GrilleVide grillePanel = new GrilleVide(new int[9][9]);
                afficherGrille(frame, grillePanel);
            }
        });

        chargerGrilleButton.addActionListener(new ChargerGrilleAction(frame, null));
    }

    // Méthode pour afficher la grille
    private static void afficherGrille(JFrame frame, GrilleVide grillePanel) {
        // Suppression des composants actuels du JFrame
        frame.getContentPane().removeAll();

        // Création d'un JPanel pour organiser les composants
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE); // Changer la couleur de fond

        // Création du bouton de sauvegarde
        JButton sauvegarderButton = new JButton("Sauvegarder");
        sauvegarderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implémentez votre logique de sauvegarde ici
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    grillePanel.sauvegarderGrille(selectedFile);
                    JOptionPane.showMessageDialog(frame, "Grille sauvegardée avec succès !");
                }
            }
        });

        // Création d'un JPanel pour le bouton de sauvegarde
        JPanel bottomButtonPanel = new JPanel();
        bottomButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomButtonPanel.setBackground(Color.WHITE);
        bottomButtonPanel.add(sauvegarderButton);

        // Ajout de la grille et du bouton de sauvegarde au JPanel principal
        panel.add(grillePanel, BorderLayout.CENTER);
        panel.add(bottomButtonPanel, BorderLayout.SOUTH);

        // Ajout du JPanel principal à la JFrame
        frame.add(panel);

        // Rafraîchissement de la fenêtre pour refléter les modifications
        frame.revalidate();
        frame.repaint();
    }
}
