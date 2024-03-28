package fr.ecole3il.rodez2023.carte.manipulateurs;

import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;
import fr.ecole3il.rodez2023.carte.elements.Tuile;
import fr.ecole3il.rodez2023.carte.path.acces.Graphe;
import fr.ecole3il.rodez2023.carte.path.acces.Noeud;
import fr.ecole3il.rodez2023.carte.path.algo.AlgorithmeChemin;

import java.util.ArrayList;
import java.util.List;

public class AdaptateurAlgorithme {

    public static Graphe<Case> creerGraphe(Carte carte) {
        Graphe<Case> graphe = new Graphe<>();
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();
        Case[][] cases = new Case[largeur][hauteur];

        // Créer tous les nœuds (cases) et les ajouter au graphe
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Tuile tuileCourante = carte.getTuile(x, y);
                cases[x][y] = new Case(tuileCourante, x, y);
                graphe.ajouterNoeud(new Noeud<>(cases[x][y]));
            }
        }

        // Ajouter des arêtes entre les cases voisines
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                if (x > 0) { // Ajouter une arête à gauche
                    graphe.ajouterArete(new Noeud<>(cases[x][y]), new Noeud<>(cases[x - 1][y]), 1.0);
                }
                if (y > 0) { // Ajouter une arête en haut
                    graphe.ajouterArete(new Noeud<>(cases[x][y]), new Noeud<>(cases[x][y - 1]), 1.0);
                }
                // Répétez pour les autres directions si nécessaire
                // Assurez-vous de ne pas ajouter des arêtes qui sortent de la carte
            }
        }

        return graphe;
    }

    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee) {
        Graphe<Case> graphe = creerGraphe(carte);
        Noeud<Case> depart = new Noeud<>(carte.getCaseAt(xDepart, yDepart));
        Noeud<Case> arrivee = new Noeud<>(carte.getCaseAt(xArrivee, yArrivee));

        List<Noeud<Case>> noeudsChemin = algorithme.trouverChemin(graphe, depart, arrivee);
        List<Case> casesChemin = new ArrayList<>();
        for (Noeud<Case> noeud : noeudsChemin) {
            casesChemin.add(noeud.getValeur());
        }
        return new Chemin(casesChemin);
    }

    // Reste des méthodes...
}
