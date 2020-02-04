package vue;

import musique.GrilleMorceau;

import javax.swing.*;
import java.awt.*;

public class FGMPanneauMorceau extends JPanel  {
	private static final long serialVersionUID = 1L;

	String texteAtraiter = "";
	GrilleMorceau maGrille;
	int transposition;

	public FGMPanneauMorceau() {
		maGrille = new GrilleMorceau();
	}

	@Override
	public void paintComponent(Graphics g) {
		// Point de d�part du dessin
		int x = 10;
		int y = 300;

		// Largeur et hauteur d'un diagramme
		int maTaillex = 60;
		int maTailley = 80;

		// On efface la zone de dessin
		this.setBackground(Color.white);
		g.clearRect(x, y, this.getWidth(), this.getHeight());

		testeGrille(g, 8, x, y, maTaillex, maTailley);
	}

	private void testeGrille(Graphics g, int accordsParLigne, int x, int y, int maTaillex, int maTailley) {
		// On supprime les doubles espaces dans le texte des accords
		while (texteAtraiter.contains("  "))
			texteAtraiter = (texteAtraiter.replace("  ", " "));
		if (texteAtraiter.length() > 2) {
			maGrille.setAccords(texteAtraiter);
			maGrille.transpose(transposition);
			maGrille.afficheMorceau(g, accordsParLigne, x, y, maTaillex, maTailley);
		}
	}

	public String getTexteAtraiter() {
		return texteAtraiter;
	}

	public void setTexteAtraiter(String texteAtraiter) {
		this.texteAtraiter = texteAtraiter;
	}

	public int getTransposition() {
		return transposition;
	}

	public void setTransposition(int transposition) {
		this.transposition = transposition;
	}
}