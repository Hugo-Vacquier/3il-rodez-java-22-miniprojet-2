package fr.ecole3il.rodez2023.carte.elements;

import fr.ecole3il.rodez2023.carte.path.algo.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.path.acces.Graphe;
import fr.ecole3il.rodez2023.carte.path.acces.Noeud;


import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class RechercheCheminSimple<E> implements AlgorithmeChemin<E> {

    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        // Ceci est une implémentation de base et doit être remplacée par un véritable algorithme de recherche de chemin.
        List<Noeud<E>> chemin = new ArrayList<>();

        // Logique simplifiée pour illustrer l'approche
        // Dans une implémentation réelle, utilisez par exemple l'algorithme de Dijkstra ou A* pour trouver le chemin.
        if (depart.equals(arrivee)) {
            chemin.add(depart);
            return chemin;
        }

        // Un pseudo-code pour trouver un chemin (à remplacer par un véritable algorithme)
        Noeud<E> courant = depart;
        List<Noeud<E>> voisins = graphe.getVoisins(courant);

        while (!voisins.isEmpty() && !courant.equals(arrivee)) {
            // Sélectionner un voisin (ceci est juste un exemple et ne garantit pas de trouver le chemin optimal)
            Noeud<E> prochain = voisins.get(0);
            chemin.add(prochain);
            if (prochain.equals(arrivee)) {
                break;
            }
            courant = prochain;
            voisins = graphe.getVoisins(courant);
        }

        if (!chemin.contains(arrivee)) {
            return Collections.emptyList(); // Aucun chemin trouvé
        }

        return chemin;
    }
}
