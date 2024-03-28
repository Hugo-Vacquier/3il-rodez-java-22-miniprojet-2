package fr.ecole3il.rodez2023.carte.manipulateurs;

import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Tuile;
import java.util.Random;


public class GenerateurCarte {
    private static final Random random = new Random();

    public static Carte genererCarte(int largeur, int hauteur) {
        Tuile[] values = Tuile.values();
        Tuile[][] tuiles = new Tuile[largeur][hauteur];
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                tuiles[x][y] = values[random.nextInt(values.length)];
            }
        }
        return new Carte(tuiles);
    }

    public static void afficherCarte(Carte carte) {
        for (int y = 0; y < carte.getHauteur(); y++) {
            for (int x = 0; x < carte.getLargeur(); x++) {
                System.out.print(carte.getTuile(x, y).name().charAt(0) + " ");
            }
            System.out.println();
        }
    }
}
