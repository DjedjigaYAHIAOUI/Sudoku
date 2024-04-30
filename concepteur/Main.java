import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

        grilleVideButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Créez une instance de GrilleVide
                int[][] grilleVide = new int[9][9];
                GrilleVide grillePanel = new GrilleVide(grilleVide);

                JFrame grilleFrame = new JFrame("Grille Sudoku");
                grilleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                grilleFrame.add(grillePanel);
                grilleFrame.pack();
                grilleFrame.setVisible(true);
            }
        });

        chargerGrilleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        List<String> lines = Files.readAllLines(selectedFile.toPath());
                        StringBuilder sb = new StringBuilder();
                        for (String line : lines) {
                            sb.append(line).append("\n");
                        }
                        JTextArea grilleArea = new JTextArea(sb.toString());
                        grilleArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

                        JScrollPane scrollPane = new JScrollPane(grilleArea);

                        JFrame grilleFrame = new JFrame("Grille Sudoku");
                        grilleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        grilleFrame.add(scrollPane);
                        grilleFrame.pack();
                        grilleFrame.setVisible(true);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Erreur lors du chargement du fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
