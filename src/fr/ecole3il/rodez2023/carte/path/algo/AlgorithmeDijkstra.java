package fr.ecole3il.rodez2023.carte.path.algo;

import fr.ecole3il.rodez2023.carte.path.acces.Graphe;
import fr.ecole3il.rodez2023.carte.path.acces.Noeud;


import java.util.*;

public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E> {
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Noeud<E>> predecessors = new HashMap<>();
        Map<Noeud<E>, Double> distances = new HashMap<>();
        PriorityQueue<Noeud<E>> queue = new PriorityQueue<>(
                Comparator.comparing(n -> distances.getOrDefault(n, Double.POSITIVE_INFINITY))
        );

        distances.put(depart, 0.0);
        graphe.getNoeuds().forEach(n -> {
            if (!n.equals(depart)) distances.put(n, Double.POSITIVE_INFINITY);
            queue.add(n);
        });

        while (!queue.isEmpty()) {
            Noeud<E> current = queue.poll();
            if (current.equals(arrivee)) break;

            graphe.getVoisins(current).forEach(neighbor -> {
                double alternateDist = distances.get(current) + graphe.getCoutArete(current, neighbor);
                if (alternateDist < distances.get(neighbor)) {
                    distances.put(neighbor, alternateDist);
                    predecessors.put(neighbor, current);
                    // Mise à jour de la queue
                    queue.remove(neighbor);
                    queue.add(neighbor);
                }
            });
        }

        List<Noeud<E>> path = new LinkedList<>();
        for (Noeud<E> at = arrivee; at != null; at = predecessors.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path; // Renvoie le chemin du départ à l'arrivée
    }
}
