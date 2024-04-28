
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SauvegarderAction implements ActionListener {
    private JFrame fenetre;
    private Grille grillePanel;

    public SauvegarderAction(JFrame fenetre, Grille grillePanel) {
        this.fenetre = fenetre;
        this.grillePanel = grillePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Vérifier si la grille est complète
        if (grillePanel.estGrilleComplete()) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(fenetre);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                grillePanel.sauvegarderGrille(selectedFile);
            }
        } else {
            JOptionPane.showMessageDialog(fenetre, "La grille n'est pas complète. Veuillez la remplir avant de sauvegarder.");
        }
    }
}

