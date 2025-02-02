import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ChargerGrilleAction implements ActionListener {
    private JFrame parentFrame;
    private GrilleVide gridPanel; 
    public ChargerGrilleAction(JFrame parentFrame, GrilleVide gridPanel) {
        this.parentFrame = parentFrame;
        this.gridPanel = gridPanel;
    }

   
    public void chargerGrille(File selectedFile) {
        try {
            List<String> lines = Files.readAllLines(selectedFile.toPath());
            List<List<Integer>> gridValues = new ArrayList<>();

            for (String line : lines) {
                List<Integer> rowValues = new ArrayList<>();
                for (char c : line.toCharArray()) {
                    if (Character.isDigit(c)) {
                        rowValues.add(Character.getNumericValue(c));
                    }
                }
                if (!rowValues.isEmpty()) {
                    gridValues.add(rowValues);
                }
            }

            int[][] gridArray = new int[gridValues.size()][];
            for (int i = 0; i < gridValues.size(); i++) {
                List<Integer> row = gridValues.get(i);
                int[] rowArray = new int[row.size()];
                for (int j = 0; j < row.size(); j++) {
                    rowArray[j] = row.get(j);
                }
                gridArray[i] = rowArray;
            }

            
            gridPanel = new GrilleVide(gridArray);
            parentFrame.getContentPane().removeAll(); 
            parentFrame.add(gridPanel); 
            parentFrame.revalidate(); 
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(parentFrame, "Erreur lors du chargement du fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(parentFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            chargerGrille(selectedFile); 
        }
    }
}
