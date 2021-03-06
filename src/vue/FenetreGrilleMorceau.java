package vue;

import musique.AccordNomFamille;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreGrilleMorceau extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private FGMPanneauInterface panInterface;
	private FGMPanneauMorceau panMorceau;

	public FenetreGrilleMorceau() {
		this.setTitle("Grilles ukul�l�");
		setIconImage(new ImageIcon(this.getClass().getResource("ukulele.png")).getImage());
		panInterface = new FGMPanneauInterface();
		panMorceau = new FGMPanneauMorceau();
		panInterface.setPanneauMorceau(panMorceau);
	}

	public void init() {
		this.setSize(1000, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(panMorceau);
		this.getContentPane().add(panInterface);
		this.pack();
		this.setVisible(true);
		addKeyListener(panInterface);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// rien encore
	}

	public static void main(String[] args) {
		AccordNomFamille.creeCatalogueAccords();
		FenetreGrilleMorceau fen;

		fen = new FenetreGrilleMorceau();
		fen.init();
		fen.isCursorSet();
	}
}