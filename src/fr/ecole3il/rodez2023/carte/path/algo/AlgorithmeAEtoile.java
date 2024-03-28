package fr.ecole3il.rodez2023.carte.path.algo;

import fr.ecole3il.rodez2023.carte.path.acces.Graphe;
import fr.ecole3il.rodez2023.carte.path.acces.Noeud;

import java.util.*;

public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E> {

    private final HeuristicFunction<E> heuristicFunction;

    public AlgorithmeAEtoile(HeuristicFunction<E> heuristicFunction) {
        this.heuristicFunction = heuristicFunction;
    }

    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        // Les maps pour garder le suivi des coûts et des prédécesseurs
        Map<Noeud<E>, Double> gScores = new HashMap<>();
        Map<Noeud<E>, Double> fScores = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> cameFrom = new HashMap<>();

        gScores.put(depart, 0.0);
        fScores.put(depart, heuristicFunction.estimate(depart, arrivee));

        PriorityQueue<Noeud<E>> openSet = new PriorityQueue<>(Comparator.comparing(fScores::get));
        openSet.add(depart);

        while (!openSet.isEmpty()) {
            Noeud<E> current = openSet.poll();
            if (current.equals(arrivee)) {
                return reconstructPath(cameFrom, arrivee);
            }

            for (Noeud<E> neighbor : graphe.getVoisins(current)) {
                double tentativeGScore = gScores.getOrDefault(current, Double.POSITIVE_INFINITY) + graphe.getCoutArete(current, neighbor);

                if (tentativeGScore < gScores.getOrDefault(neighbor, Double.POSITIVE_INFINITY)) {
                    cameFrom.put(neighbor, current);
                    gScores.put(neighbor, tentativeGScore);
                    fScores.put(neighbor, tentativeGScore + heuristicFunction.estimate(neighbor, arrivee));

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return new LinkedList<>(); // Aucun chemin trouvé
    }

    private List<Noeud<E>> reconstructPath(Map<Noeud<E>, Noeud<E>> cameFrom, Noeud<E> current) {
        List<Noeud<E>> totalPath = new LinkedList<>();
        while (current != null) {
            totalPath.add(current);
            current = cameFrom.get(current);
        }
        Collections.reverse(totalPath);
        return totalPath;
    }

    @FunctionalInterface
    public interface HeuristicFunction<E> {
        double estimate(Noeud<E> from, Noeud<E> to);
    }
}
