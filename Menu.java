import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class Menu extends JFrame implements ActionListener {
    private JButton autoResolutionButton;
    private JButton manualResolutionButton;

    public Menu() {
        setTitle("Menu");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));

        autoResolutionButton = new JButton("Résolution Automatique");
        autoResolutionButton.addActionListener(this);
        add(autoResolutionButton);

        manualResolutionButton = new JButton("Résolution Manuelle");
        manualResolutionButton.addActionListener(this);
        add(manualResolutionButton);
    }



    public void actionPerformed(ActionEvent e) {
    if (e.getSource() == autoResolutionButton) {
        int[][] grille = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        long startTime = System.nanoTime();
        ResolutionAutomatique resolution = new ResolutionAutomatique(grille);
        resolution.resoudre();
        long endTime = System.nanoTime();
        long elapsedTimeInMillis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

        new GrilleResolueInterface(grille).setVisible(true);
        JOptionPane.showMessageDialog(null, "Grille résolue en " + elapsedTimeInMillis + " millisecondes.");
    } else if (e.getSource() == manualResolutionButton) {
        // Insérer ici le code pour la résolution manuelle
    }
}


    private void afficherGrille(int[][] grille) {
        JFrame frame = new JFrame("Grille Résolue");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(grille.length, grille[0].length));

        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                JTextField textField = new JTextField(String.valueOf(grille[i][j]));
                textField.setEditable(false);
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setFont(new Font("Arial", Font.BOLD, 20));
                panel.add(textField);
            }
        }

        frame.add(new JScrollPane(panel));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }
}
