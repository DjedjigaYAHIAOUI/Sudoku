public class SudokuSolver {
    private static final int TAILLE = 9;

    public boolean resoudreSudoku(int[][] grille) {
        int ligne = -1;
        int colonne = -1;
        boolean estVide = true;

        // Trouver une cellule vide
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                if (grille[i][j] == 0) {
                    ligne = i;
                    colonne = j;
                    estVide = false;
                    break;
                }
            }
            if (!estVide) {
                break;
            }
        }

        // Si aucune cellule vide n'est trouvée, le puzzle est résolu
        if (estVide) {
            return true;
        }

        // Essayer les chiffres de 1 à 9
        for (int num = 1; num <= TAILLE; num++) {
            if (estSecuritaire(grille, ligne, colonne, num)) {
                grille[ligne][colonne] = num;
                if (resoudreSudoku(grille)) {
                    return true;
                } else {
                    grille[ligne][colonne] = 0; // Retour en arrière
                }
            }
        }

        // Déclencher le retour en arrière
        return false;
    }

    private boolean estSecuritaire(int[][] grille, int ligne, int colonne, int num) {
        // Vérifier si le chiffre n'est pas déjà présent dans la ligne, la colonne et la boîte 3x3 actuelle
        return !utiliseDansLigne(grille, ligne, num) && !utiliseDansColonne(grille, colonne, num) && !utiliseDansBoite(grille, ligne - ligne % 3, colonne - colonne % 3, num);
    }

    private boolean utiliseDansLigne(int[][] grille, int ligne, int num) {
        for (int colonne = 0; colonne < TAILLE; colonne++) {
            if (grille[ligne][colonne] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean utiliseDansColonne(int[][] grille, int colonne, int num) {
        for (int ligne = 0; ligne < TAILLE; ligne++) {
            if (grille[ligne][colonne] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean utiliseDansBoite(int[][] grille, int ligneDebut, int colonneDebut, int num) {
        for (int ligne = 0; ligne < 3; ligne++) {
            for (int colonne = 0; colonne < 3; colonne++) {
                if (grille[ligne + ligneDebut][colonne + colonneDebut] == num) {
                    return true;
                }
            }
        }
        return false;
    }
}
