import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class ChargerGrilleButtonListener implements ActionListener {
    private JFrame frame;

    public ChargerGrilleButtonListener(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ChargerGrilleAction chargerGrilleAction = new ChargerGrilleAction(frame, null);
        chargerGrilleAction.actionPerformed(e);
    }
}
