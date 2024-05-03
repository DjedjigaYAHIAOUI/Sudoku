import javax.swing.*;
import java.awt.*;
import java.io.*;

public class GrilleVide extends JPanel {
    
    private int[][] grille;
    private JTextField[][] cases;
    private JTextField caseSelectionnee;
    private int rowSelectionnee = -1; // Variable pour stocker l'indice de ligne de la case sélectionnée
    private int colSelectionnee = -1; // Variable pour stocker l'indice de colonne de la case sélectionnée

    public GrilleVide(int[][] grille) {
        this.grille = grille;
        setLayout(new GridLayout(grille.length, grille[0].length));
        afficherGrille();
    }

    public JTextField getCaseSelectionnee() {
        return caseSelectionnee;
    }

    public void setCaseSelectionnee(JTextField caseSelectionnee) {
        this.caseSelectionnee = caseSelectionnee;
        // Mettre à jour les indices de ligne et de colonne de la case sélectionnée
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[0].length; j++) {
                if (cases[i][j] == caseSelectionnee) {
                    rowSelectionnee = i;
                    colSelectionnee = j;
                    return;
                }
            }
        }
    }
public void afficherGrille() {
    cases = new JTextField[grille.length][grille[0].length];

    for (int i = 0; i < grille.length; i++) {
        for (int j = 0; j < grille[0].length; j++) {
            JTextField textField = new JTextField(grille[i][j] == 0 ? "" : String.valueOf(grille[i][j]));
            if (grille[i][j] == 0) {
                configurerChampTexteGrilleVide(textField, true);
            } else {
                textField.setEditable(true); // Les chiffres initialement présents ne sont pas éditables
                textField.setBackground(Color.LIGHT_GRAY);
            }
            cases[i][j] = textField;
            add(textField);
        }
    }
}



   // Méthode pour configurer les champs de texte dans la grille vide
private void configurerChampTexteGrilleVide(JTextField textField, boolean editable) {
    textField.setHorizontalAlignment(JTextField.CENTER);
    textField.setFont(new Font("Arial", Font.BOLD, 20));
    textField.setEditable(editable);

    if (editable) {
        textField.setDocument(new JTextFieldLimit(4));
        textField.addMouseListener(new GrilleMouseListener(this));
    }
}

// Méthode pour configurer les champs de texte dans la grille partiellement remplie
private void configurerChampTexteGrillePartielle(JTextField textField) {
    textField.setHorizontalAlignment(JTextField.CENTER);
    textField.setFont(new Font("Arial", Font.BOLD, 20));
    textField.setEditable(true); // Tous les champs de texte sont éditables
    textField.setDocument(new JTextFieldLimit(4));
    textField.addMouseListener(new GrilleMouseListener(this));
}


    public boolean estChiffreValide(JTextField textField) {
        String text = textField.getText().trim();
        if (text.isEmpty()) {
            return true; // Si la case est vide, elle est toujours considérée comme valide
        }

        try {
            int chiffre = Integer.parseInt(text);

            // Vérifier si le chiffre est contradictoire avec les autres chiffres dans la ligne, colonne et région
            if (estChiffreValideDansLigne(chiffre, rowSelectionnee) &&
                estChiffreValideDansColonne(chiffre, colSelectionnee) &&
                estChiffreValideDansRegion(chiffre, rowSelectionnee, colSelectionnee)) {
                // Si le chiffre n'est pas contradictoire, le conserver dans la grille principale
                grille[rowSelectionnee][colSelectionnee] = chiffre;
                return true;
            }

            // Réinitialiser la case et retourner false si le chiffre est contradictoire
            textField.setText("");
            return false;

        } catch (NumberFormatException e) {
            textField.setText("");
            return false;
        }
    }

    private boolean estChiffreValideDansLigne(int chiffre, int row) {
        for (int j = 0; j < grille[0].length; j++) {
            if (j != colSelectionnee && grille[row][j] == chiffre) {
                return false;
            }
        }
        return true;
    }

    private boolean estChiffreValideDansColonne(int chiffre, int col) {
        for (int i = 0; i < grille.length; i++) {
            if (i != rowSelectionnee && grille[i][col] == chiffre) {
                return false;
            }
        }
        return true;
    }

    private boolean estChiffreValideDansRegion(int chiffre, int row, int col) {
        int regionRow = row / 3 * 3;
        int regionCol = col / 3 * 3;

        for (int i = regionRow; i < regionRow + 3; i++) {
            for (int j = regionCol; j < regionCol + 3; j++) {
                if ((i != row || j != col) && grille[i][j] == chiffre) {
                    return false;
                }
            }
        }
        return true;
    }

    public void sauvegarderGrille(File file) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (int[] ligne : grille) {
                for (int chiffre : ligne) {
                    writer.print(chiffre);
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
                if (!cases[i][j].getText().matches("^[1-9]$")) {
                    return false; // Si une case contient un chiffre invalide, la grille n'est pas complète
                }
            }
        }
        return true; // Si aucune case vide ni aucun chiffre invalide n'est trouvé, la grille est complète
    }

    public boolean estGrilleValide() {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                JTextField textField = cases[i][j];
                if (!estChiffreValide(textField)) {
                    return false; // Si une case contient un chiffre invalide, la grille n'est pas valide
                }
            }
        }
        return true; // Si aucune case ne contient un chiffre invalide, la grille est valide
    }
}
