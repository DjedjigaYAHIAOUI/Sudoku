import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JButton grilleVideButton = new JButton("Partir d'une grille vide");
        JButton chargerGrilleButton = new JButton("Charger une grille depuis un fichier");

        GrilleVide grillePanel = new GrilleVide(new int[9][9]); // Crée une grille vide initiale

        panel.add(grilleVideButton);
        panel.add(chargerGrilleButton);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        grilleVideButton.addActionListener(e -> {
            frame.getContentPane().removeAll(); // Supprime tous les composants précédents
            frame.add(grillePanel); // Ajoute la grille à la fenêtre
            frame.revalidate(); // Met à jour l'affichage
        });

        ChargerGrilleAction chargerGrilleAction = new ChargerGrilleAction(frame, grillePanel); // Passer l'instance de grillePanel
        chargerGrilleButton.addActionListener(chargerGrilleAction);
    }
}
