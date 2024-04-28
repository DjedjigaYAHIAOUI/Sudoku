import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Menu extends JFrame implements ActionListener {
    private JButton autoResolutionButton;
    private JButton manualResolutionButton;
    private JButton loadGridButton;
    private int[][] grille; // Variable pour stocker la grille chargée

    public Menu() {
        setTitle("Menu");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        autoResolutionButton = new JButton("Résolution Automatique");
        autoResolutionButton.addActionListener(this);
        add(autoResolutionButton);

        manualResolutionButton = new JButton("Résolution Manuelle");
        manualResolutionButton.addActionListener(this);
        add(manualResolutionButton);

        loadGridButton = new JButton("Charger Grille");
        loadGridButton.addActionListener(this);
        add(loadGridButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == autoResolutionButton) {
            if (grille != null) { // Vérifier si la grille est chargée
                // Implémentez ici la résolution automatique
                JOptionPane.showMessageDialog(this, "Fonctionnalité de résolution automatique à implémenter.");
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez charger une grille avant de résoudre automatiquement.");
            }
        } else if (e.getSource() == manualResolutionButton) {
            try {
                // Lancer GrilleMain
                ProcessBuilder pb = new ProcessBuilder("java", "GrilleMain");
                pb.redirectErrorStream(true);
                Process p = pb.start();

                // Afficher la sortie du processus dans la console
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == loadGridButton) {
            // Implémentez ici le chargement de la grille depuis un fichier
            JOptionPane.showMessageDialog(this, "Fonctionnalité de chargement de grille à implémenter.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }
}
