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
        }
    }

    public boolean estChiffreValide(JTextField textField) {
        String text = textField.getText().trim();
        if (text.isEmpty()) {
            return true; 
        }

        try {
            int chiffre = Integer.parseInt(text);
            boolean ligneValide = estChiffreValideDansLigne(chiffre, textField);
            boolean colonneValide = estChiffreValideDansColonne(chiffre, textField);
            boolean regionValide = estChiffreValideDansRegion(chiffre, textField);
            
            if (ligneValide && colonneValide && regionValide) {
                return true;
            } else {
                textField.setText(""); 
                return false;
            }
        } catch (NumberFormatException e) {
            textField.setText(""); // Effacer le texte s'il n'est pas un nombre
            return false;
        }
    }

    private boolean estChiffreValideDansLigne(int chiffre, JTextField textField) {
        int row = cases.length;

        for (int i = 0; i < row; i++) {
            if (cases[i][textField.getX() / textField.getWidth()].getText().equals(String.valueOf(chiffre)) && cases[i][textField.getX() / textField.getWidth()] != textField) {
                return false;
            }
        }
        return true;
    }

    private boolean estChiffreValideDansColonne(int chiffre, JTextField textField) {
        int col = cases[0].length;

        for (int i = 0; i < col; i++) {
            if (cases[textField.getY() / textField.getHeight()][i].getText().equals(String.valueOf(chiffre)) && cases[textField.getY() / textField.getHeight()][i] != textField) {
                return false;
            }
        }
        return true;
    }

    private boolean estChiffreValideDansRegion(int chiffre, JTextField textField) {
        int row = cases.length;
        int col = cases[0].length;

        int regionRow = textField.getX() / textField.getWidth() / 3 * 3;
        int regionCol = textField.getY() / textField.getHeight() / 3 * 3;

        for (int i = regionRow; i < regionRow + 3; i++) {
            for (int j = regionCol; j < regionCol + 3; j++) {
                if (cases[i][j].getText().equals(String.valueOf(chiffre)) && cases[i][j] != textField) {
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
                if (grille[i][j] == 0 && !cases[i][j].getText().matches("[1-9]?")) {
                    return false;
                }
            }
        }
        return true;
    }
}
