import javax.swing.*;

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

        grilleVideButton.addActionListener(new GrilleVideAction());
        chargerGrilleButton.addActionListener(new ChargerGrilleAction(frame));
    }
}
