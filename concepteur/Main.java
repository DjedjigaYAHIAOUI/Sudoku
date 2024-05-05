import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     
        JButton grilleVideButton = new JButton("Partir d'une grille vide");
        JButton chargerGrilleButton = new JButton("Charger une grille");

       
        grilleVideButton.setBackground(new Color(50, 50, 200)); 
        grilleVideButton.setForeground(Color.WHITE);
        grilleVideButton.setFont(new Font("Arial", Font.BOLD, 14));
        grilleVideButton.setPreferredSize(new Dimension(180, 50));
	
        chargerGrilleButton.setBackground(new Color(50, 50, 200)); 
        chargerGrilleButton.setForeground(Color.WHITE);
        chargerGrilleButton.setFont(new Font("Arial", Font.BOLD, 14));
        chargerGrilleButton.setPreferredSize(new Dimension(180, 50)); 

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10)); 
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	
        buttonPanel.add(grilleVideButton);
        buttonPanel.add(chargerGrilleButton);

     
        frame.add(buttonPanel);

        frame.setSize(450, 600); 
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true); 

        
        grilleVideButton.addActionListener(new GrilleVideButtonListener(frame));
        chargerGrilleButton.addActionListener(new ChargerGrilleButtonListener(frame));
    }

    
    public static void afficherGrille(JFrame frame, GrilleVide grillePanel) {
      
        frame.getContentPane().removeAll();

        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE); 

      
        JButton sauvegarderButton = new JButton("Sauvegarder");
        sauvegarderButton.addActionListener(new SauvegarderAction(frame, grillePanel));

      
        JPanel bottomButtonPanel = new JPanel();
        bottomButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomButtonPanel.setBackground(Color.WHITE);
        bottomButtonPanel.add(sauvegarderButton);

        panel.add(grillePanel, BorderLayout.CENTER);
        panel.add(bottomButtonPanel, BorderLayout.SOUTH);

       
        frame.add(panel);

       
        frame.revalidate();
        frame.repaint();
    }
}
