
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SauvegarderAction implements ActionListener {
    private JFrame fenetre;
    private GrilleVide grillePanel;
    public SauvegarderAction(JFrame fenetre, GrilleVide grillePanel) {
        this.fenetre = fenetre;
        this.grillePanel = grillePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(fenetre);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            grillePanel.sauvegarderGrille(selectedFile);
            JOptionPane.showMessageDialog(fenetre, "Grille sauvegardée avec succès !");
        }
    }
}
