import javax.swing.*;
import java.awt.*;

public class GrilleResolueInterface extends JFrame {
    private static class CaseGrille extends JTextField {
        public CaseGrille(int valeur) {
            if (valeur != 0) {
                setText(String.valueOf(valeur));
                setEditable(false);
            }
        }
    }

    public GrilleResolueInterface(int[][] grilleResolue) {
        setTitle("Grille Résolue");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(9, 9));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                add(new CaseGrille(grilleResolue[i][j]));
            }
        }

        pack();
        setVisible(true);
    }
}
