package fr.ecole3il.rodez2023.carte.elements;

public class Carte {
    private Tuile[][] tuiles;

    public Carte(Tuile[][] tuiles) {
        this.tuiles = new Tuile[tuiles.length][];
        for (int i = 0; i < tuiles.length; i++) {
            this.tuiles[i] = tuiles[i].clone(); // Approche de copie défensive
        }
    }

    public Tuile getTuile(int x, int y) {
        if (x < 0 || x >= getLargeur() || y < 0 || y >= getHauteur()) {
            throw new IndexOutOfBoundsException("Coordonnées hors de la carte.");
        }
        return tuiles[x][y];
    }

    public int getLargeur() {
        return tuiles.length;
    }

    public int getHauteur() {
        return tuiles[0].length;
    }

    public Case getCaseAt(int x, int y) {
        if (x < 0 || x >= getLargeur() || y < 0 || y >= getHauteur()) {
            throw new IndexOutOfBoundsException("Coordonnées hors de la carte.");
        }
        return new Case(getTuile(x, y), x, y);
    }
}
