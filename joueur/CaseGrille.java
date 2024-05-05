import javax.swing.JTextField;

public class CaseGrille extends JTextField {
    public CaseGrille(int valeur) {
        if (valeur != 0) {
            setText(String.valueOf(valeur));
            setEditable(false);
        }
    }
}
