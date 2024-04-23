import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GrilleMouseListener extends MouseAdapter {
    private Grille grille;

    public GrilleMouseListener(Grille grille) {
        this.grille = grille;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTextField clickedField = (JTextField) e.getSource();
        if (!clickedField.isEditable()) {
            return;
        }
        if (grille.getCaseSelectionnee() != null && grille.getCaseSelectionnee() != clickedField) {
            if (!grille.estChiffreValide(grille.getCaseSelectionnee())) {
                grille.getCaseSelectionnee().setText(grille.getCaseSelectionnee().getText());
            }
        }
        grille.setCaseSelectionnee(clickedField);
    }
}
