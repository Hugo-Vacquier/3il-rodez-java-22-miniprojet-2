package fr.ecole3il.rodez2023.carte.path.acces;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;

public class Graphe<E> {
    private Map<Noeud<E>, LinkedList<Noeud<E>>> noeuds = new LinkedHashMap<>();

    public void ajouterNoeud(Noeud<E> noeud) {
        noeuds.putIfAbsent(noeud, new LinkedList<>());
    }

    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
        noeuds.get(depart).addFirst(arrivee); // Changement pour ajouter au début pour optimiser certaines opérations
        // La gestion du coût nécessiterait une structure de données complémentaire ou un ajustement de la classe Noeud
    }

    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        return new LinkedList<>(noeuds.getOrDefault(noeud, new LinkedList<>()));
    }
}
