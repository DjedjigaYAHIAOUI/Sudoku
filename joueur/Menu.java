import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    private JButton autoResolutionButton;
    private JButton manualResolutionButton;
    private JButton loadGridButton;
    private int[][] grille; // Variable pour stocker la grille chargée

    public Menu() {
        setTitle("Joeur");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        autoResolutionButton = new JButton("Résolution Automatique");
        autoResolutionButton.addActionListener(this);
        add(autoResolutionButton);

        manualResolutionButton = new JButton("Résolution Manuelle"); // Ajout du bouton de résolution manuelle
        manualResolutionButton.addActionListener(this);
        add(manualResolutionButton); // Ajout du bouton de résolution manuelle

        loadGridButton = new JButton("Charger Grille");
        loadGridButton.addActionListener(this);
        add(loadGridButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == autoResolutionButton) {
            if (grille != null) { // Vérifier si la grille est chargée
                long startTime = System.nanoTime();
                ResolutionAutomatique resolution = new ResolutionAutomatique(grille);
                resolution.resoudre();
                long endTime = System.nanoTime();
                long elapsedTimeInMillis = (endTime - startTime) / 1000000;

                afficherGrilleResolue(resolution.getGrille());
                JOptionPane.showMessageDialog(this, "Grille résolue en " + elapsedTimeInMillis + " millisecondes.");
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez charger une grille avant de résoudre automatiquement.");
            }
        } else if (e.getSource() == manualResolutionButton) { // Correction de l'accolade
            try {
                // Lancer GrilleMain
                ProcessBuilder pb = new ProcessBuilder("java", "GrilleMain");
                pb.redirectErrorStream(true);
                Process p = pb.start();

                // Afficher la sortie du processus dans la console
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == loadGridButton) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    grille = chargerGrille(selectedFile);
                    if (grille != null) {
                        JOptionPane.showMessageDialog(this, "Grille chargée avec succès.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Erreur lors du chargement de la grille.");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Erreur lors du chargement de la grille : " + ex.getMessage());
                }
            }
        }
    }

    private int[][] chargerGrille(File file) throws IOException {
        int[][] grille = new int[9][9];
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        int row = 0;
        while ((line = reader.readLine()) != null && row < 9) {
            String[] values = line.split(",");
            for (int col = 0; col < 9 && col < values.length; col++) {
                grille[row][col] = Integer.parseInt(values[col].trim());
            }
            row++;
        }
        reader.close();
        return grille;
    }

    private void afficherGrilleResolue(int[][] grilleResolue) {
        JFrame frame = new JFrame("Grille Résolue");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(9, 9));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JTextField textField = new JTextField();
                textField.setText(String.valueOf(grilleResolue[i][j]));
                textField.setEditable(false);
                frame.add(textField);
            }
        }
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Menu::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        new Menu().setVisible(true);
    }
}
