import javax.swing.JFrame;
import java.awt.GridLayout;

public class GrilleResolueInterface extends JFrame {

    public GrilleResolueInterface(int[][] grilleResolue) {
        setTitle("Grille Résolue");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(9, 9));

        for (int[] ligne : grilleResolue) {
            for (int valeur : ligne) {
                add(new CaseGrille(valeur));
            }
        }

        pack();
        setVisible(true);
    }
}
