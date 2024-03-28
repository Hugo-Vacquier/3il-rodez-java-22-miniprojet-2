package fr.ecole3il.rodez2023.carte.path.acces;

import java.util.*;

public class Graphe<E> {

    private Map<Noeud<E>, LinkedList<Noeud<E>>> noeuds = new LinkedHashMap<>();
    private Map<Pair<Noeud<E>, Noeud<E>>, Double> coutsAretes = new HashMap<>();

    public void ajouterNoeud(Noeud<E> noeud) {
        noeuds.putIfAbsent(noeud, new LinkedList<>());
    }

    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
        noeuds.get(depart).addFirst(arrivee);
        coutsAretes.put(new Pair<>(depart, arrivee), cout);
    }

    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        return coutsAretes.getOrDefault(new Pair<>(depart, arrivee), Double.POSITIVE_INFINITY);
    }

    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        return new LinkedList<>(noeuds.getOrDefault(noeud, new LinkedList<>()));
    }

    // Une classe auxiliaire pour représenter les paires de nœuds.
    private class Pair<U, V> {
        private final U first;
        private final V second;

        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }

        // Vous devez redéfinir equals et hashCode pour utiliser Pair comme clé dans une Map.
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) &&
                    Objects.equals(second, pair.second);
        }

        public Set<Noeud<E>> getNoeuds() {
            return noeuds.keySet();
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }
    public Set<Noeud<E>> getNoeuds() {
        return noeuds.keySet();
    }
}
