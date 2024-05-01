
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GrilleMouseListener extends MouseAdapter {
    private GrilleVide grillevide;

    public GrilleMouseListener(GrilleVide grillevide) {
        this.grillevide = grillevide;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTextField clickedField = (JTextField) e.getSource();
        if (!clickedField.isEditable()) {
            return;
        }
        if (grillevide.getCaseSelectionnee() != null && grillevide.getCaseSelectionnee() != clickedField) {
            if (!grillevide.estChiffreValide(grillevide.getCaseSelectionnee())) {
                grillevide.getCaseSelectionnee().setText(grillevide.getCaseSelectionnee().getText());
            }
        }
        grillevide.setCaseSelectionnee(clickedField);
    }
}
