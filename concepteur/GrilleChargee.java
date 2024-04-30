import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class GrilleChargee extends JPanel {
    private int[][] grille;

    public GrilleChargee(File file) {
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            grille = new int[lines.size()][];
            for (int i = 0; i < lines.size(); i++) {
                String[] values = lines.get(i).trim().split("\\s+");
                grille[i] = new int[values.length];
                for (int j = 0; j < values.length; j++) {
                    grille[i][j] = Integer.parseInt(values[j]);
                }
            }
            afficherGrille();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            // Gestion de l'erreur de chargement du fichier
        }
    }

    private void afficherGrille() {
        setLayout(new GridLayout(9, 9));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Ajout d'une bordure à la grille
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                JPanel cell = new JPanel();
                cell.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;
                gbc.fill = GridBagConstraints.BOTH;
                JTextField textField = new JTextField(grille[i][j] == 0 ? "" : String.valueOf(grille[i][j]));
                textField.setFont(new Font("Arial", Font.BOLD, 20));
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setEditable(false); // Empêche la modification des valeurs chargées
                cell.add(textField, gbc);
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Ajout d'une bordure entre les cellules
                add(cell);
            }
        }
    }
}
