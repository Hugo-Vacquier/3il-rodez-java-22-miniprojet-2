package fr.ecole3il.rodez2023.carte.application;

import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Chemin;
import fr.ecole3il.rodez2023.carte.manipulateurs.GenerateurCarte;

public class ExempleCLI {

	public static void main(String[] args) {
		Carte carte = GenerateurCarte.genererCarte(20, 20); // Ajustement des dimensions pour variété
		AlgorithmeDijkstra algorithme = new AlgorithmeDijkstra(); // Utilisation explicite pour simplifier l'exemple

		Chemin chemin = algorithme.trouverChemin(carte, 0, 0, 19, 19); // Changement des coordonnées pour un exemple différent
		chemin.afficherChemin();
	}
}
