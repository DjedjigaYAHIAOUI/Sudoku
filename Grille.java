import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class Grille extends JPanel {
    private int[][] grille;
    private JTextField[][] cases;
    private JTextField caseSelectionnee;

    public Grille(int[][] grille) {
        this.grille = grille;
        setLayout(new GridLayout(grille.length, grille[0].length));
        afficherGrille();
    }

    public void afficherGrille() {
        cases = new JTextField[grille.length][grille[0].length];

        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                JTextField textField = new JTextField(grille[i][j] == 0 ? "" : String.valueOf(grille[i][j]));

                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setFont(new Font("Arial", Font.BOLD, 20));
                textField.setEditable(grille[i][j] == 0);

                textField.addMouseListener(new MouseAdapter {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JTextField clickedField = (JTextField) e.getSource();
                        if (caseSelectionnee != null && caseSelectionnee != clickedField) {
                            if (!estChiffreValide(caseSelectionnee)) {
                                caseSelectionnee.setText("");
                            }
                        }
                        caseSelectionnee = clickedField;
                    }
                });

                cases[i][j] = textField;
                add(textField);
            }
        }
    }

    private boolean estChiffreValide(JTextField textField) {
        int ligne = -1, colonne = -1;

        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[0].length; j++) {
                if (cases[i][j] == textField) {
                    ligne = i;
                    colonne = j;
                    break;
                }
            }
        }

        int chiffreSaisi;
        try {
            chiffreSaisi = Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            return false;
        }

        if (!estChiffreValideDansLigne(chiffreSaisi, ligne) || !estChiffreValideDansColonne(chiffreSaisi, colonne) || !estChiffreValideDansRegion(chiffreSaisi, ligne, colonne)) {
            return false;
        }

        return true;
    }

    private boolean estChiffreValideDansLigne(int chiffre, int ligne) {
        for (int colonne = 0; colonne < grille[0].length; colonne++) {
            if (grille[ligne][colonne] == chiffre) {
                return false;
            }
        }
        return true;
    }

    private boolean estChiffreValideDansColonne(int chiffre, int colonne) {
        for (int ligne = 0; ligne < grille.length; ligne++) {
            if (grille[ligne][colonne] == chiffre) {
                return false;
            }
        }
        return true;
    }

    private boolean estChiffreValideDansRegion(int chiffre, int ligne, int colonne) {
        int regionLigne = ligne / 3 * 3;
        int regionColonne = colonne / 3 * 3;

        for (int i = regionLigne; i < regionLigne + 3; i++) {
            for (int j = regionColonne; j < regionColonne + 3; j++) {
                if (grille[i][j] == chiffre) {
                    return false;
                }
            }
        }
        return true;
    }

    public void sauvegarderGrille(File file) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (int i = 0; i < grille.length; i++) {
                for (int j = 0; j < grille[0].length; j++) {
                    writer.print(grille[i][j]);
                }
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean estGrilleComplete() {
    for (int i = 0; i < grille.length; i++) {
        for (int j = 0; j < grille[0].length; j++) {
            // Vérifier si une case est encore vide
            if (grille[i][j] == 0 && !cases[i][j].getText().matches("[1-9]")) {
                return false;
            }
        }
    }
    return true;
}

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
            fenetre.getContentPane().add(grillePanel);
            fenetre.setSize(600, 600);
            fenetre.setVisible(true);

            JButton sauvegarderButton = new JButton("Sauvegarder");
            sauvegarderButton.addActionListener(e -> {
                if (grillePanel.estGrilleComplete()) {
                    JFileChooser fileChooser = new JFileChooser();
                    int choice = fileChooser.showSaveDialog(null);
                    if (choice == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        grillePanel.sauvegarderGrille(selectedFile);
                    }
                } else {
                    JOptionPane.showMessageDialog(fenetre, "Veuillez remplir toutes les cases pour sauvegarder la grille.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            });

            fenetre.add(sauvegarderButton, BorderLayout.SOUTH);
        });
    }
}
