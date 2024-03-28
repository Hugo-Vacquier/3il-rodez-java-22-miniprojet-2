package fr.ecole3il.rodez2023.carte.elements;

import java.util.List;

public class Chemin {
    private List<Case> cases;

    public Chemin(List<Case> cases) {
        this.cases = cases;
    }

    public List<Case> getCases() {
        return cases;
    }

    public void afficherChemin() {
        cases.forEach(c -> System.out.printf("(%d, %d) -> %s%n", c.getX(), c.getY(), c.getTuile().name()));
    }
}
