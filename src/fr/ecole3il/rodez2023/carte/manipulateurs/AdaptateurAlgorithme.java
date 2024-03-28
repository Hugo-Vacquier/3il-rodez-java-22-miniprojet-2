package fr.ecole3il.rodez2023.carte.manipulateurs;

import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;
import fr.ecole3il.rodez2023.carte.path.acces.Graphe;
import fr.ecole3il.rodez2023.carte.path.acces.Noeud;
import fr.ecole3il.rodez2023.carte.path.algo.AlgorithmeChemin;

import java.util.ArrayList;
import java.util.List;

public class AdaptateurAlgorithme {
    // Supposons que cette méthode existe dans la classe Carte
    public static Case getCaseAt(Carte carte, int x, int y) {
        // Implémentez cette méthode selon la logique de votre application
        return new Case(carte.getTuile(x, y), x, y);
    }

    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee) {
        Graphe<Case> graphe = creerGraphe(carte);
        Noeud<Case> depart = new Noeud<>(getCaseAt(carte, xDepart, yDepart));
        Noeud<Case> arrivee = new Noeud<>(getCaseAt(carte, xArrivee, yArrivee));

        List<Noeud<Case>> noeudsChemin = algorithme.trouverChemin(graphe, depart, arrivee);
        List<Case> casesChemin = new ArrayList<>();
        for (Noeud<Case> noeud : noeudsChemin) {
            casesChemin.add(noeud.getValeur());
        }
        return new Chemin(casesChemin);
    }

    // Reste des méthodes telles que creerGraphe, ajouterAretesVoisines, etc.
}
