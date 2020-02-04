package vue;
import musique.AccordNomFamille;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class FenetreAccordPositions extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private PanneauPositionAccord monPanPosition;

	public FenetreAccordPositions() {
		this.setTitle("Accords ukulélé");
		setIconImage(new ImageIcon(this.getClass().getResource("ukulele.png")).getImage());
		monPanPosition = new PanneauPositionAccord();
	}

	public void init() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(monPanPosition);
		this.setSize(800, 600);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2 - this.getWidth()/2, dim.height/2 - this.getHeight()/2);
		this.setVisible(true);
		addKeyListener(monPanPosition);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Rien pour le moment
	}

	public void mouseReleased(MouseEvent e){
	// Rien pour le moment
		
	} 
	
	public static void main(String[] args) {
		AccordNomFamille.creeCatalogueAccords();
		FenetreAccordPositions fen;

		fen = new FenetreAccordPositions();
		fen.init();
	}
}