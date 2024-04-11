import javax.swing.*;
import java.awt.*;

public class Grille extends JPanel {
    private int[][] grille;

    public Grille(int[][] grille) {
        this.grille = grille;
        setLayout(new GridLayout(grille.length, grille[0].length));
        afficherGrille();
    }

    public void afficherGrille() {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                JTextField textField = new JTextField(grille[i][j] == 0 ? "" : String.valueOf(grille[i][j]));
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setFont(new Font("Arial", Font.BOLD, 20));
                textField.setEditable(grille[i][j] == 0);
                add(textField);
            }
        }
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
        });
    }
}
