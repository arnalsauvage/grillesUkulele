package vue;

import musique.GrilleMorceau;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class FGMPanneauInterface extends JPanel implements KeyListener, ActionListener {
	private static final long serialVersionUID = 1L;

	boolean testeGrille = false;
	private JButton boutonAfficheGrille;
	private JButton btnTransposePlus;
	private JButton btnTransposeMoins;
	private JButton boutonAfficheChordFinder;
	private JTextArea titreGrille;
	private JTextArea texteGrille;
	private int transposition = 0;
	private JLabel lblTranspose = new JLabel("Transpose");
	private JComboBox<String> comboTranspose;
	// Create a file chooser
	private JButton choixOpenFic;
	private JButton choixSaveFic;

	GrilleMorceau maGrille;

	private FGMPanneauMorceau panneauMorceau;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public FGMPanneauInterface() {
		maGrille = new GrilleMorceau();
		requestFocusInWindow();
		addKeyListener(this);

		// Cheek to cheek
		// "F6 F Cdim Gm7-5 C7 F FM7 Dm Am7-5 D7 A# A#9 G#dim Gm7-5 F Am7-5 D7
		// A#9 G#dim C7 C#9 C7 F Cdim C7",

		// Hello Dolly
		// Gestion du combo "transpose"
		String[] transposeStrings = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11" };
		setLayout(new MigLayout("", "[317px][5px][35px][5px][1px][5px][50px][5px][187.00px]", "[22px][][112px][25px]"));

		titreGrille = new JTextArea("Hello Dolly", 1, 80);
		titreGrille.setBounds(12, 11, 684, 29);
		this.add(titreGrille, "cell 0 0 9 1,alignx center,aligny top");

		btnTransposeMoins = new JButton("-");
		btnTransposeMoins.setBounds(119, 47, 42, 29);
		btnTransposeMoins.addActionListener(this);
		this.add(btnTransposeMoins, "cell 0 1,alignx right,aligny top");

		btnTransposePlus = new JButton("+");
		btnTransposePlus.setBounds(64, 47, 50, 29);
		btnTransposePlus.addActionListener(this);
		this.add(btnTransposePlus, "cell 2 1,alignx left,aligny top");
		
		lblTranspose.setBounds(171, 47, 152, 27);
		this.add(lblTranspose, "cell 6 1,alignx left,aligny center");

		comboTranspose = new JComboBox<>(transposeStrings);
		comboTranspose.setBounds(246, 47, 126, 29);
		comboTranspose.setSelectedItem(transposition);
		this.add(comboTranspose, "cell 8 1,alignx left,aligny center");
		comboTranspose.addActionListener(this);

		texteGrille = new JTextArea(
				"C C Am Am CM7 B7 Cdim Dm \n G7 Dm Dm Bb Bb Dm G7 C \n Cdim Dm G7 C C Am Am Gm7 \n C7 F E7 Am Em Am Em D7 \n G7 C Cdim Dm G7",
				6, 80);
		texteGrille.setBounds(2, 87, 694, 95);
		this.add(texteGrille, "cell 0 2 9 1,alignx center,aligny top");

		// Gestion du bouton Open
		choixOpenFic = new JButton("Ouvrir...");
		choixOpenFic.setBounds(723, 11, 95, 42);
		buttonGroup.add(choixOpenFic);
		this.add(choixOpenFic, "flowx,cell 0 3,alignx left,aligny top");
		choixOpenFic.addActionListener(this);

		// Affichage des boutons
		boutonAfficheGrille = new JButton("Affiche grille");
		boutonAfficheGrille.setBounds(723, 114, 95, 36);
		boutonAfficheGrille.addActionListener(this);
		this.add(boutonAfficheGrille, "cell 8 3,alignx right,aligny center");

		// Bouton Chord Finder
		boutonAfficheChordFinder = new JButton("Accord sur mesure");
		boutonAfficheChordFinder.setToolTipText("Choisissez un accord en posant vos doigts sur le uke");
		boutonAfficheChordFinder.setBounds(823, 114, 95, 36);
		boutonAfficheChordFinder.addActionListener(this);
		this.add(boutonAfficheChordFinder, "cell 2 3,alignx right,aligny center");
		
		choixSaveFic = new JButton("Enregistrer...");
		choixSaveFic.setBounds(723, 65, 95, 36);
		this.add(choixSaveFic, "cell 0 3,alignx center,aligny top");
		choixSaveFic.addActionListener(this);
	}

	public String lireFichierTexte(File fichier) {
		StringBuilder machaine = new StringBuilder("");
		// lecture du fichier texte
		try {
			InputStreamReader ipsr;
			try (InputStream ips = new FileInputStream(fichier)) {
				ipsr = new InputStreamReader(ips);
			}
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			titreGrille.setText(br.readLine());
			while ((ligne = br.readLine()) != null) {
				machaine.append(ligne + "\n");
			}
			texteGrille.setText(machaine.toString());
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return machaine.toString();
	}

	public int sauveFichierTexte(File fichier) {
		int retour = 1;
		// on met try si jamais il y a une exception
		try {
			/**
			 * BufferedWriter a besoin d un FileWriter, les 2 vont ensemble, on
			 * donne comme argument le nom du fichier true signifie qu on ajoute
			 * dans le fichier (append), on ne marque pas par dessus
			 * 
			 */
			BufferedWriter output;
			try (FileWriter fw = new FileWriter(fichier, true)) {

				// le BufferedWriter output auquel on donne comme argument le
				// FileWriter fw cree juste au dessus
				output = new BufferedWriter(fw);
			}

			// on marque dans le fichier ou plutot dans le BufferedWriter qui
			// sert comme un tampon(stream)
			output.write(titreGrille.getText() + "\n");
			// on peut utiliser plusieurs fois methode write
			output.write(texteGrille.getText());

			output.flush();
			// ensuite flush envoie dans le fichier, ne pas oublier cette
			// methode pour le BufferedWriter

			output.close();
			// et on le ferme
			System.out.println("fichier créé");
		} catch (IOException ioe) {
			System.out.print("Erreur : impossible d'écrire sur le support !");
			ioe.printStackTrace();
			retour = 0;
			// Boîte du message d'erreur
			JOptionPane.showMessageDialog(null, "Erreur d'écriture sur le fichier ! \n" + fichier, "Erreur",
					JOptionPane.ERROR_MESSAGE);
		}

		return retour;
	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == boutonAfficheGrille) {
			// on enlève les fioritures de la zone de texte avant de traiter
			texteGrille.setText(texteGrille.getText().replace("\n", " "));
			texteGrille.setText(texteGrille.getText().replace("\t", " "));
			texteGrille.setText(texteGrille.getText().replace(" /", "/"));
			texteGrille.setText(texteGrille.getText().replace("/ ", "/"));
			while (texteGrille.getText().contains("  "))
				texteGrille.setText(texteGrille.getText().replace("  ", " "));
			panneauMorceau.setTexteAtraiter(texteGrille.getText());
			panneauMorceau.repaint();
		}
		if (evt.getSource() == btnTransposePlus)
			transposition++;
		if (evt.getSource() == btnTransposeMoins)
			transposition--;
		
		if (evt.getSource() == boutonAfficheChordFinder){
			FenetreAccordPositions fenAP = new FenetreAccordPositions();
			fenAP.init();
			fenAP.setVisible(true);
		}

		if (transposition > 11)
			transposition -= 12;
		if (transposition < 0)
			transposition += 12;

		if (evt.getSource() == comboTranspose)
			transposition = comboTranspose.getSelectedIndex();

		if (evt.getSource() == choixOpenFic) {
			MonFiltre mft = new MonFiltre(new String[] { "txt" }, "Format texte (*.txt)");

			JFileChooser choix = new JFileChooser();
			choix.resetChoosableFileFilters();
			choix.addChoosableFileFilter(mft);
			choix.setFileFilter(mft);
			int returnVal = choix.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = choix.getSelectedFile();
				// This is where a real application would open the file.
				texteGrille.setText(lireFichierTexte(file));
			}
		}
		if (evt.getSource() == choixSaveFic) {
			MonFiltre mft = new MonFiltre(new String[] { "txt" }, "Format texte (*.txt)");

			JFileChooser choix = new JFileChooser();
			choix.resetChoosableFileFilters();
			choix.addChoosableFileFilter(mft);
			choix.setFileFilter(mft);
			choix.setDialogTitle("Enregistrer sous...");
			choix.setApproveButtonText("Enregistrer");
			int returnVal = choix.showOpenDialog(this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = choix.getSelectedFile();
				// This is where a real application would open the file.
				sauveFichierTexte(file);
			}
		}
		panneauMorceau.setTransposition(transposition);
		comboTranspose.setSelectedIndex(transposition);
		lblTranspose.setText("Transpose : ");
		texteGrille.setText(reformateGrilleTexte(8));
		repaint();
	}

	public JPanel getPanneauMorceau() {
		return panneauMorceau;
	}

	public void setPanneauMorceau(FGMPanneauMorceau panneauMorceau) {
		this.panneauMorceau = panneauMorceau;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_F1) {
			// Rien pour le moment
		}
		if (e.getKeyCode() == KeyEvent.VK_F2) {
			// Rien pour le moment
		}
	}

	public String reformateGrilleTexte(int nbAccordsParLigne) {
		StringBuilder retour = new StringBuilder("");
		String entree = texteGrille.getText();

		entree = entree.replace("\n", " ");
		entree = entree.replace("\t", " ");
		while (entree.contains("  "))
			entree = entree.replace("  ", " ");

		String[] tabAccords = entree.split(" ");

		for (int parcours = 0; parcours != tabAccords.length; parcours++) {

			if (((parcours + 1) % nbAccordsParLigne) == 0)
				retour.append (tabAccords[parcours] + "\n");
			else
				retour.append (tabAccords[parcours] + "\t");
		}
		return retour.toString();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// rien pour le moment, mais...
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// rien pour le moment, mais...
	}
}