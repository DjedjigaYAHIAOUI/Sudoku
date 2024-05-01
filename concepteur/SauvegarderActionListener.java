import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class SauvegarderActionListener implements ActionListener {
    private int[][] grille;
    private JFrame parentFrame;

    public SauvegarderActionListener(int[][] grille, JFrame parentFrame) {
        this.grille = grille;
        this.parentFrame = parentFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(parentFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            sauvegarderGrille(selectedFile);
        }
    }

    private void sauvegarderGrille(File file) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (int[] ligne : grille) {
                for (int chiffre : ligne) {
                    writer.print(chiffre);
                }
                writer.println();
            }
            JOptionPane.showMessageDialog(parentFrame, "Grille sauvegardée avec succès !", "Sauvegarde réussie", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentFrame, "Erreur lors de la sauvegarde de la grille", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
