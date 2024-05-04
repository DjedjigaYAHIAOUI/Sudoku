import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class GrilleVideButtonListener implements ActionListener {
    private JFrame frame;

    public GrilleVideButtonListener(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GrilleVide grillePanel = new GrilleVide(new int[9][9]);
        Main.afficherGrille(frame, grillePanel);
    }
}
