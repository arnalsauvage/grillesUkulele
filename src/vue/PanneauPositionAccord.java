package vue;

import musique.Accord;
import musique.Position;
import musique.Ukulele;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanneauPositionAccord extends JPanel implements KeyListener, ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;

	String nomAccord = "";
	Accord monAccord;
	Position maPosition;
	Ukulele monUke;
	
	int xPPA;
	int yPPA;
	int maTaillex;
	int maTailley;
	int transposition;
	Diagramme monDiagramme = null;
	Canvas monCanvas;
	private JTextArea texte;

	public PanneauPositionAccord() {
		requestFocusInWindow();

		monAccord = new Accord("C");
		maPosition = new Position(0, 0, 0, 3);
		this.addMouseListener(this);
		// Point de départ du dessin
		xPPA = 30;
		yPPA = 50;
		maTaillex = 240;
		maTailley = 300;

		this.setSize(maTaillex, maTailley);
		monCanvas = new Canvas();
		monCanvas.setSize(maTaillex + xPPA, (int) (maTailley * 1.33));
		monCanvas.setBounds(xPPA, yPPA, (int) (maTaillex * 1.33 + xPPA),(int) (maTailley * 1.33));
		monCanvas.addMouseListener(this);
		texte = new JTextArea();
		this.add(texte);
		this.add(monCanvas);
		monUke = new Ukulele();
	}

	@Override
	public void paintComponent(Graphics g) {

		// On efface la zone de dessin
		monCanvas.getGraphics().setColor(Color.white);
		monCanvas.getGraphics().clearRect(0,0, monCanvas.getWidth(), monCanvas.getHeight());
		if (monDiagramme == null)
			monDiagramme = new Diagramme(monCanvas.getGraphics(), xPPA, yPPA + maTailley/4 , maTaillex,  (int) ( maTailley * 0.75));
		monDiagramme.dessine(maPosition, monAccord);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int monx;
		int mony;
		monx = e.getX();
		mony = e.getY();

		// Clic dans le diagramme
			int maColonne = 1 + (monx - xPPA) / (maTaillex / 4);
			int maLigne =  (mony - yPPA + maTailley / 10) / (maTailley / 5) - 1;

			maPosition.setCorde(maColonne, maLigne);
			monDiagramme.dessine(maPosition, monAccord);
			texte.setText("Monx : " + monx + " mony : " + mony + " col :" + maColonne + " ligne : " + maLigne);
			monAccord = monUke.trouveAccordPosition(maPosition);
			texte.setText("Accord(s) : " + monAccord.chercheTypeAccord(true));
			repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Rien pour le moment...

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Rien pour le moment...
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Rien pour le moment...
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Rien pour le moment...
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Rien pour le moment...
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Rien pour le moment...
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Rien pour le moment...

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Rien pour le moment...

	}
}