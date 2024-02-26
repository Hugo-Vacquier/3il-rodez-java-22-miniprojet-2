package fr.ecole3il.rodez2023.carte.elements;

import java.util.ArrayList;
import java.util.List;

public class Noeud<E> {
    private E valeur; // La valeur du nœud
    private List<Noeud<E>> voisins; // La liste des nœuds voisins

    // Constructeur qui initialise le nœud avec une valeur et une liste de voisins vide
    public Noeud(E valeur) {
        this.valeur = valeur;
        this.voisins = new ArrayList<>();
    }

    // Méthode pour obtenir la valeur du nœud
    public E getValeur() {
        return valeur;
    }

    // Méthode pour obtenir la liste des nœuds voisins
    public List<Noeud<E>> getVoisins() {
        return voisins;
    }

    // Méthode pour ajouter un voisin à la liste des voisins
    public void ajouterVoisin(Noeud<E> voisin) {
        this.voisins.add(voisin);
    }
}
