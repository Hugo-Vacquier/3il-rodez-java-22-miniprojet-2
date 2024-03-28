package fr.ecole3il.rodez2023.carte.path.algo;

import fr.ecole3il.rodez2023.carte.path.acces.Graphe;
import fr.ecole3il.rodez2023.carte.path.acces.Noeud;


import java.util.List;

/**
 * Interface définissant les méthodes requises pour implémenter un algorithme de recherche de chemin.
 *
 * @param <E> le type d'élément contenu dans les nœuds du graphe.
 */
public interface AlgorithmeChemin<E> {
    /**
     * Trouve le chemin le plus court entre deux nœuds dans un graphe.
     *
     * @param graphe Le graphe dans lequel effectuer la recherche.
     * @param depart Le nœud de départ.
     * @param arrivee Le nœud d'arrivée.
     * @return Une liste de nœuds représentant le chemin le plus court de {@code depart} à {@code arrivee}.
     *         Retourne une liste vide si aucun chemin n'est trouvé.
     */
    List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee);
}
