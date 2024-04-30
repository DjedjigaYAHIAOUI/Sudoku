import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JButton grilleVideButton = new JButton("Partir d'une grille vide");
        JButton chargerGrilleButton = new JButton("Charger une grille depuis un fichier");

        panel.add(grilleVideButton);
        panel.add(chargerGrilleButton);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == grilleVideButton) {
                    // Créez une instance de GrilleVide
                    int[][] grilleVide = new int[9][9];
                    GrilleVide grillePanel = new GrilleVide(grilleVide);

                    JFrame grilleFrame = new JFrame("Grille Sudoku");
                    grilleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    grilleFrame.add(grillePanel);
                    grilleFrame.pack();
                    grilleFrame.setVisible(true);
                } else if (e.getSource() == chargerGrilleButton) {
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showOpenDialog(frame);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
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

                            JPanel gridPanel = new GrilleVide(gridArray);
                            JFrame grilleFrame = new JFrame("Grille Sudoku");
                            grilleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            grilleFrame.add(gridPanel);
                            grilleFrame.pack();
                            grilleFrame.setVisible(true);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(frame, "Erreur lors du chargement du fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        };

        grilleVideButton.addActionListener(actionListener);
        chargerGrilleButton.addActionListener(actionListener);
    }
}
