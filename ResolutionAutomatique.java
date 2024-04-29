public class ResolutionAutomatique {
    private int[][] grille;

    public ResolutionAutomatique(int[][] grille) {
        this.grille = grille;
    }

    public void resoudre() {
        if (resoudreSudoku()) {
            System.out.println("Grille résolue avec succès !");
        } else {
            System.out.println("La grille n'a pas de solution.");
        }
    }

    public int[][] getGrille() {
        return grille;
    }

    private boolean resoudreSudoku() {
        int[] pos = trouverCaseVide();
        int row = pos[0];
        int col = pos[1];

        // Si aucune case vide n'est trouvée, le sudoku est résolu
        if (row == -1 && col == -1) {
            return true;
        }

        // Essayer les chiffres de 1 à 9 dans la case vide
        for (int num = 1; num <= 9; num++) {
            if (estValide(row, col, num)) {
                grille[row][col] = num;

                // Résoudre récursivement le reste du sudoku
                if (resoudreSudoku()) {
                    return true;
                }

                // Si l'affectation ne mène pas à une solution, revenir en arrière et essayer un autre numéro
                grille[row][col] = 0;
            }
        }
        return false;
    }

    private boolean estValide(int row, int col, int num) {
        // Vérifier la ligne
        for (int x = 0; x < grille.length; x++) {
            if (grille[row][x] == num) {
                return false;
            }
        }

        // Vérifier la colonne
        for (int x = 0; x < grille.length; x++) {
            if (grille[x][col] == num) {
                return false;
            }
        }

        // Vérifier la sous-grille
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grille[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[] trouverCaseVide() {
        int[] pos = {-1, -1};

        for (int row = 0; row < grille.length; row++) {
            for (int col = 0; col < grille[0].length; col++) {
                if (grille[row][col] == 0) {
                    pos[0] = row;
                    pos[1] = col;
                    return pos;
                }
            }
        }
        return pos;
    }
}
