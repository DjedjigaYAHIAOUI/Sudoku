
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
    int row = 0, col = 0; // Initialisation de row et col en dehors de la boucle
    boolean isEmpty = true;

    // Recherche d'une case vide
    for (row = 0; row < 9; row++) {
        for (col = 0; col < 9; col++) {
            if (grille[row][col] == 0) {
                isEmpty = false;
                break;
            }
        }
        if (!isEmpty) {
            break;
        }
    }

    // Si aucune case vide n'est trouvée, le sudoku est résolu
    if (isEmpty) {
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
        for (int x = 0; x < 9; x++) {
            if (grille[row][x] == num) {
                return false;
            }
        }

        // Vérifier la colonne
        for (int x = 0; x < 9; x++) {
            if (grille[x][col] == num) {
                return false;
            }
        }

        // Vérifier la sous-grille 3x3
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
}
