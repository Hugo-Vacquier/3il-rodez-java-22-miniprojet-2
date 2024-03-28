package fr.ecole3il.rodez2023.carte.application;

import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.manipulateurs.GenerateurCarte;
import fr.ecole3il.rodez2023.carte.path.acces.Graphe;
import fr.ecole3il.rodez2023.carte.path.acces.Noeud;
import fr.ecole3il.rodez2023.carte.path.algo.AlgorithmeDijkstra;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExempleCLI {

	public static void main(String[] args) {
		// Création d'une carte
		Carte carte = GenerateurCarte.genererCarte(20, 20);

		// Création d'un graphe à partir de la carte
		Graphe<Case> graphe = new Graphe<>();
		Map<Case, Noeud<Case>> caseNoeudMap = new HashMap<>();

		// Remplissage du graphe avec des nœuds basés sur les cases de la carte
		for (int x = 0; x < carte.getLargeur(); x++) {
			for (int y = 0; y < carte.getHauteur(); y++) {
				Case caseActuelle = carte.getCaseAt(x, y);
				Noeud<Case> noeud = new Noeud<>(caseActuelle);
				graphe.ajouterNoeud(noeud);
				caseNoeudMap.put(caseActuelle, noeud);

				// Connecter le noeud avec ses voisins ici
				// ...
			}
		}

		// Création des nœuds de départ et d'arrivée basée sur les cases
		Case caseDepart = carte.getCaseAt(0, 0); // par exemple, le coin supérieur gauche
		Noeud<Case> depart = caseNoeudMap.get(caseDepart);

		Case caseArrivee = carte.getCaseAt(carte.getLargeur() - 1, carte.getHauteur() - 1); // par exemple, le coin inférieur droit
		Noeud<Case> arrivee = caseNoeudMap.get(caseArrivee);

		// Utilisation de l'algorithme de Dijkstra
		AlgorithmeDijkstra<Case> algorithme = new AlgorithmeDijkstra<>();
		List<Noeud<Case>> cheminList = algorithme.trouverChemin(graphe, depart, arrivee);

		// Affichage du chemin
		if (!cheminList.isEmpty()) {
			cheminList.forEach(noeud -> System.out.println(noeud.getValeur()));
		} else {
			System.out.println("Aucun chemin trouvé.");
		}
	}
}
