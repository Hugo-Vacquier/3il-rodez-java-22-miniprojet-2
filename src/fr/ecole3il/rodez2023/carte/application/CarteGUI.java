package fr.ecole3il.rodez2023.carte.application;

import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;
import fr.ecole3il.rodez2023.carte.elements.Tuile;
import fr.ecole3il.rodez2023.carte.manipulateurs.GenerateurCarte;
import fr.ecole3il.rodez2023.carte.manipulateurs.AdaptateurAlgorithme;
import fr.ecole3il.rodez2023.carte.path.algo.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.path.algo.AlgorithmeDijkstra;
import fr.ecole3il.rodez2023.carte.path.algo.AlgorithmeAEtoile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class CarteGUI extends JFrame {
	private Carte carte;
	private Case caseDepart;
	private Case caseArrivee;
	private AlgorithmeChemin<Case> algorithme; // Assurez-vous que cette interface accepte un type générique

	public CarteGUI(Carte carte) {
		this.carte = carte;
		this.caseDepart = null;
		this.caseArrivee = null;
		this.algorithme = new AlgorithmeDijkstra<>(); // Assurez-vous que AlgorithmeDijkstra implémente AlgorithmeChemin<Case>

		setTitle("Carte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(carte.getLargeur() * 32, carte.getHauteur() * 32 + 50);
		setLocationRelativeTo(null);

		JPanel cartePanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				dessinerCarte((Graphics2D) g);
			}
		};
		cartePanel.setPreferredSize(new Dimension(carte.getLargeur() * 32, carte.getHauteur() * 32));

		JComboBox<String> algorithmeComboBox = new JComboBox<>(new String[]{"Dijkstra", "A*"});
		algorithmeComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String choix = (String) algorithmeComboBox.getSelectedItem();
				switch (choix) {
					case "Dijkstra":
						algorithme = new AlgorithmeDijkstra<>();
						break;
					case "A*":
						algorithme = new AlgorithmeAEtoile<>();
						break;
				}
			}
		});

		add(algorithmeComboBox, BorderLayout.NORTH);
		add(cartePanel);

		cartePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX() / 32;
				int y = e.getY() / 32;

				if (caseDepart == null) {
					caseDepart = new Case(carte.getTuile(x, y), x, y);
					System.out.println("Case de départ : [" + x + ", " + y + "]");
				} else if (caseArrivee == null) {
					caseArrivee = new Case(carte.getTuile(x, y), x, y);
					System.out.println("Case d'arrivée : [" + x + ", " + y + "]");
					trouverChemin();
				} else {
					caseDepart = new Case(carte.getTuile(x, y), x, y);
					caseArrivee = null;
					System.out.println("Nouvelle case de départ : [" + x + ", " + y + "]");
				}

				cartePanel.repaint();
			}
		});
	}

	private void dessinerCarte(Graphics2D g) {
		for (int x = 0; x < carte.getLargeur(); x++) {
			for (int y = 0; y < carte.getHauteur(); y++) {
				Tuile tuile = carte.getTuile(x, y);
				BufferedImage imageTuile = getTuileImage(tuile);
				g.drawImage(imageTuile, x * 32, y * 32, null);

				if (caseDepart != null && caseDepart.getX() == x && caseDepart.getY() == y) {
					g.setColor(Color.BLUE);
					g.drawRect(x * 32, y * 32, 32, 32);
				}

				if (caseArrivee != null && caseArrivee.getX() == x && caseArrivee.getY() == y) {
					g.setColor(Color.RED);
					g.drawRect(x * 32, y * 32, 32, 32);
				}
			}
		}
	}

	private void trouverChemin() {
		if (caseDepart != null && caseArrivee != null) {
			Chemin chemin = AdaptateurAlgorithme.trouverChemin(algorithme, carte, caseDepart.getX(), caseDepart.getY(), caseArrivee.getX(), caseArrivee.getY());
			System.out.println("Chemin le plus court :");
			for (Case c : chemin.getCases()) {
				System.out.println("[" + c.getX() + ", " + c.getY() + "]");
			}
		}
	}

	private BufferedImage getTuileImage(Tuile tuile) {
		BufferedImage image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		// Implémentez la logique de dessin de l'image de la tuile ici
		g.dispose();
		return image;
	}

	public static void main(String[] args) {
		GenerateurCarte gen = new GenerateurCarte();
		Carte carte = gen.genererCarte(10, 10);
		SwingUtilities.invokeLater(() -> {
			CarteGUI carteGUI = new CarteGUI(carte);
			carteGUI.setVisible(true);
		});
	}
}
