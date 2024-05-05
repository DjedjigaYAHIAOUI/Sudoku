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
        setTitle("Joueur");
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
            if (grille != null) {
                SudokuSolver solver = new SudokuSolver();
                boolean solved = solver.resoudreSudoku(grille);

                if (solved) {
                    afficherGrilleResolue(grille);
                } else {
                    JOptionPane.showMessageDialog(this, "Impossible de résoudre la grille.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez charger une grille avant de résoudre automatiquement.");
            }
        } else if (e.getSource() == manualResolutionButton) {
            if (grille != null) {
                GrilleMain.resolutionManuelle(grille); // Appel de la méthode pour la résolution manuelle
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez charger une grille avant de résoudre manuellement.");
            }
        } else if (e.getSource() == loadGridButton) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    grille = chargerGrille(selectedFile);
                    if (grille != null) {
                        afficherGrilleAvecSauvegarde(grille); // Afficher la grille chargée avec le bouton de sauvegarde
                        JOptionPane.showMessageDialog(this, "Grille chargée avec succès.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Erreur lors du chargement de la grille.");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Erreur lors du chargement de la grille : " + ex.getMessage());
                }
            }
        }
    }

    private int[][] chargerGrille(File file) throws IOException {
        int[][] grille = new int[9][9];
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        int row = 0;
        while ((line = reader.readLine()) != null && row < 9) {
            for (int col = 0; col < 9; col++) {
                grille[row][col] = Character.getNumericValue(line.charAt(col));
            }
            row++;
        }
        reader.close();
        return grille;
    }

    private void afficherGrilleResolue(int[][] grilleResolue) {
        JFrame frame = new JFrame("Grille Résolue");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(9, 9));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JTextField textField = new JTextField();
                textField.setText(String.valueOf(grilleResolue[i][j]));
                textField.setEditable(false);
                textField.setHorizontalAlignment(JTextField.CENTER);
                gridPanel.add(textField);
            }
        }

        // Bouton de sauvegarde
        JButton sauvegarderButton = new JButton("Sauvegarder");
        sauvegarderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ajoutez ici la logique pour sauvegarder la grille
                JOptionPane.showMessageDialog(frame, "Grille sauvegardée avec succès.");
            }
        });

        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(sauvegarderButton, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    private void afficherGrilleAvecSauvegarde(int[][] grille) {
        JFrame frame = new JFrame("Grille Sudoku");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        Grille grillePanel = new Grille(grille); // Utilisation de la grille sélectionnée
        frame.getContentPane().add(grillePanel, BorderLayout.CENTER);

        // Bouton de sauvegarde
        JButton sauvegarderButton = new JButton("Sauvegarder");
        sauvegarderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ajoutez ici la logique pour sauvegarder la grille
                JOptionPane.showMessageDialog(frame, "Grille sauvegardée avec succès.");
            }
        });
        frame.getContentPane().add(sauvegarderButton, BorderLayout.SOUTH);

        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Menu::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        new Menu().setVisible(true);
    }
}
