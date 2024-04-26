import javax.swing.*;
import java.awt.*;
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

    public JTextField getCaseSelectionnee() {
        return caseSelectionnee;
    }

    public void setCaseSelectionnee(JTextField caseSelectionnee) {
        this.caseSelectionnee = caseSelectionnee;
    }

    public void afficherGrille() {
        cases = new JTextField[grille.length][grille[0].length];

        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                JTextField textField = new JTextField(grille[i][j] == 0 ? "" : String.valueOf(grille[i][j]));
                boolean editable = grille[i][j] == 0;
                configurerChampTexte(textField, editable);
                cases[i][j] = textField;
                add(textField);
            }
        }
    }

    private void configurerChampTexte(JTextField textField, boolean editable) {
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        textField.setEditable(editable);

        if (editable) {
            textField.setDocument(new JTextFieldLimit(1));
            textField.addMouseListener(new GrilleMouseListener(this));
        }
    }

    public boolean estChiffreValide(JTextField textField) {
        String text = textField.getText().trim();
        if (text.isEmpty()) {
            return true;
        }

        try {
            int chiffre = Integer.parseInt(text);
            int row = cases.length;
            int col = cases[0].length;
            int x = -1, y = -1;

            // Trouver les indices de la case sélectionnée
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (cases[i][j] == textField) {
                        x = i;
                        y = j;
                        break;
                    }
                }
            }

            boolean ligneValide = estChiffreValideDansLigne(chiffre, x);
            boolean colonneValide = estChiffreValideDansColonne(chiffre, y);
            boolean regionValide = estChiffreValideDansRegion(chiffre, x, y);

            if (ligneValide && colonneValide && regionValide) {
                return true;
            } else {
                textField.setText("");
                return false;
            }
        } catch (NumberFormatException e) {
            textField.setText("");
            return false;
        }
    }

    private boolean estChiffreValideDansLigne(int chiffre, int row) {
        int col = cases[0].length;

        for (int j = 0; j < col; j++) {
            if (grille[row][j] == chiffre && j != caseSelectionnee.getX() / caseSelectionnee.getWidth()) {
                return false;
            }
        }
        return true;
    }

    private boolean estChiffreValideDansColonne(int chiffre, int col) {
        int row = cases.length;

        for (int i = 0; i < row; i++) {
            if (grille[i][col] == chiffre && i != caseSelectionnee.getY() / caseSelectionnee.getHeight()) {
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
                if (grille[i][j] == chiffre && (i != row || j != col)) {
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
            if (grille[i][j] == 0 || !cases[i][j].getText().matches("^[1-9]$")) {
                return false; // Si une case est vide ou contient un chiffre invalide, la grille n'est pas complète
            }
        }
    }
    return true; // Si aucune case vide ni aucun chiffre invalide n'est trouvé, la grille est complète
}
}
