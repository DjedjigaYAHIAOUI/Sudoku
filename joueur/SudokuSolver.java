public class SudokuSolver {
    public boolean resoudreSudoku(int[][] grille) {
        int N = grille.length;
        int row = -1;
        int col = -1;
        boolean isEmpty = true;

        // Recherche d'une cellule vide dans la grille
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grille[i][j] == 0) {
                    row = i;
                    col = j;

                    // Il y a une cellule vide, la grille n'est pas vide
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        // S'il n'y a pas de cellule vide, la grille est résolue
        if (isEmpty) {
            return true;
        }

        // Essayer de placer un chiffre de 1 à 9 dans la cellule vide
        for (int num = 1; num <= 9; num++) {
            if (SudokuHelper.estValide(grille, row, col, num)) { // Modification ici
                grille[row][col] = num;
                if (resoudreSudoku(grille)) {
                    return true;
                }
                grille[row][col] = 0; // Annuler le placement s'il ne mène pas à une solution
            }
        }

        // Aucun chiffre de 1 à 9 ne fonctionne dans cette cellule
        return false;
    }
}

class SudokuHelper {
    public static boolean estValide(int[][] grille, int row, int col, int num) {
        // Vérification de la ligne
        for (int j = 0; j < 9; j++) {
            if (grille[row][j] == num && j != col) {
                return false;
            }
        }

        // Vérification de la colonne
        for (int i = 0; i < 9; i++) {
            if (grille[i][col] == num && i != row) {
                return false;
            }
        }

        // Vérification du bloc 3x3
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (grille[i][j] == num && (i != row || j != col)) {
                    return false;
                }
            }
        }

        return true;
    }
}
