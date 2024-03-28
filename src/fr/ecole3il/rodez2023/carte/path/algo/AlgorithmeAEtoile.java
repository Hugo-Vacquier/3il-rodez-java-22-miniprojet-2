package fr.ecole3il.rodez2023.carte.path.algo;

import fr.ecole3il.rodez2023.carte.path.acces.Graphe;
import fr.ecole3il.rodez2023.carte.path.acces.Noeud;
import java.util.*;

public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E> {

    private final HeuristicFunction<E> heuristicFunction;

    // Le constructeur exige une implémentation concrète de la fonction heuristique,
    // ce qui permet une grande flexibilité pour adapter l'algorithme à différents contextes.
    public AlgorithmeAEtoile(HeuristicFunction<E> heuristicFunction) {
        this.heuristicFunction = Objects.requireNonNull(heuristicFunction, "La fonction heuristique ne peut être null.");
    }

    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Double> gScores = new HashMap<>();
        Map<Noeud<E>, Double> fScores = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> cameFrom = new HashMap<>();

        gScores.put(depart, 0.0);
        fScores.put(depart, heuristicFunction.estimate(depart, arrivee));

        // Utilise une file de priorité pour gérer l'ensemble ouvert. Les noeuds sont triés par leur score F.
        PriorityQueue<Noeud<E>> openSet = new PriorityQueue<>(Comparator.comparingDouble(fScores::get));
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

        // Retourner une liste vide si aucun chemin n'a été trouvé.
        return new LinkedList<>();
    }

    // Reconstitue le chemin trouvé en remontant depuis le noeud d'arrivée jusqu'au noeud de départ.
    private List<Noeud<E>> reconstructPath(Map<Noeud<E>, Noeud<E>> cameFrom, Noeud<E> current) {
        LinkedList<Noeud<E>> totalPath = new LinkedList<>();
        while (current != null) {
            totalPath.addFirst(current); // Ajoute au début pour éviter l'appel à Collections.reverse()
            current = cameFrom.get(current);
        }
        return totalPath;
    }

    @FunctionalInterface
    public interface HeuristicFunction<E> {
        double estimate(Noeud<E> from, Noeud<E> to);
    }
}
