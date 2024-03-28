package fr.ecole3il.rodez2023.carte.elements;

public class Case {
    private final Tuile tuile;
    private final int x, y;

    public Case(Tuile tuile, int x, int y) {
        this.tuile = tuile;
        this.x = x;
        this.y = y;
    }

    public Tuile getTuile() {
        return tuile;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("Case {tuile=%s, x=%d, y=%d}", tuile, x, y);
    }
}
