import javax.swing.*;
import java.awt.*;

public class GrilleResolueInterface extends JFrame {
    public GrilleResolueInterface(int[][] grille) {
        setTitle("Grille Résolue");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(grille.length, grille[0].length));

        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                JTextField textField = new JTextField(String.valueOf(grille[i][j]));
                textField.setEditable(false);
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setFont(new Font("Arial", Font.BOLD, 20));
                add(textField);
            }
        }

        pack();
        setLocationRelativeTo(null);
    }
}
