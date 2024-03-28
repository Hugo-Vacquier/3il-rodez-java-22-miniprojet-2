package fr.ecole3il.rodez2023.carte.elements;

import java.util.HashSet;
import java.util.Set;

public class Noeud<E> {
    private E valeur;
    private Set<Noeud<E>> voisins; // Changement de List à Set pour garantir l'unicité

    public Noeud(E valeur) {
        this.valeur = valeur;
        this.voisins = new HashSet<>();
    }

    public E getValeur() {
        return valeur;
    }

    public Set<Noeud<E>> getVoisins() { // Changement du type de retour
        return voisins;
    }

    public void ajouterVoisin(Noeud<E> voisin) {
        this.voisins.add(voisin);
    }
}
