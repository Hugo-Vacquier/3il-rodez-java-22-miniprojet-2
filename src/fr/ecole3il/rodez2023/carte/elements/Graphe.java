package fr.ecole3il.rodez2023.carte.elements;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graphe<E> {
    private Map<Noeud<E>, List<Noeud<E>>> noeuds = new HashMap<>();
    private Map<String, Double> coutsArete = new HashMap<>();

    public void ajouterNoeud(Noeud<E> noeud) {
        noeuds.putIfAbsent(noeud, new ArrayList<>());
    }

    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
        ajouterNoeud(depart);
        ajouterNoeud(arrivee);
        noeuds.get(depart).add(arrivee);
        // Pour un graphe non orienté, ajouter également l'arête inverse
        // noeuds.get(arrivee).add(depart);
        String cle = genererCleArete(depart, arrivee);
        coutsArete.put(cle, cout);
    }

    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        String cle = genererCleArete(depart, arrivee);
        return coutsArete.getOrDefault(cle, Double.POSITIVE_INFINITY);
    }

    public List<Noeud<E>> getNoeuds() {
        return new ArrayList<>(noeuds.keySet());
    }

    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        return noeuds.getOrDefault(noeud, new ArrayList<>());
    }

    private String genererCleArete(Noeud<E> depart, Noeud<E> arrivee) {
        return depart.getValeur().toString() + "->" + arrivee.getValeur().toString();
    }
}
