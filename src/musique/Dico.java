package musique;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Dico implements Serializable {
	private static final long serialVersionUID = 6048244558074728131L;
	HashMap<String, Position> leDico;

	// Constructeur
	public Dico() {
		leDico = new HashMap<>();
	}

	// Renvoie la position pour un accord décrit par une chaîne
	public Position get(String cle) {
		// Si la clé est donnée avec un bémol, on la convertit en dièse
		if ((cle.length()>1)&&(cle.substring(1, 2).equals("b"))){
			Accord monAccord =new Accord(cle);
			cle = monAccord.ecritEnDiese();
		}
		if (leDico.containsKey(cle))
			return leDico.get(cle);
		else
			return null;
	}
	
	public int getSize()
	{
		return leDico.size();
	}

	// Supprime une entrée du dico
	public void supprime(String cle) {
		if (leDico.containsKey(cle))
			leDico.remove(cle);
	}

	// Ecrit / remplace une entrée dans le dico
	public void remplace(String cle, Position maPosition) {
		leDico.put(cle, maPosition);
	}

	// On ajoute l'accord au dico s'il est plus simple que l'existant
	public void ajouteAccord(String nom, Position laPosition) {
		Position autrePosition;

		if (nom.length() == 0 || laPosition == null)
			return;

		autrePosition = leDico.get(nom);
		if (autrePosition == null)
			leDico.put(nom, laPosition);
		else {
			if (autrePosition.difficulte() > laPosition.difficulte()) {
				leDico.put(nom, laPosition);
			}
		}
	}

	// Remplit le dico avec l'accord le plus simple à jouer pour tous les types
	// d'accords et toutes les notes
	public void remplitDico() {
		Ukulele monUku = new Ukulele();

		// Douze premières frettes de base
		for (int i = 0; i < 11; i++) {
			for (int a = 0; a < 6; a++) {
				for (int b = 0; b < 6; b++) {
					for (int c = 0; c < 6; c++) {
						for (int d = 0; d < 6; d++) {
							traitePosition(monUku, i, a, b, c, d);
						}
					}
				}
			}
		}
	}

	private void traitePosition(Ukulele monUku, int frette, int a, int b, int c, int d) {
		Position maPosition;
		Accord monAccord;
		String machaine;
		maPosition = new Position(frette + a, frette + b, frette + c, frette + d);
		monAccord = monUku.trouveAccordPosition(frette + a, frette + b, frette + c, frette + d);
		machaine = monAccord.chercheTypeAccord(true);

		if (!machaine.equals("")) {
			// On récupère tous les accords séparés par des ';'
			String[] split = machaine.split(";");
			for (int index = 0; index < split.length; index++) {
				ajouteAccord(split[index], maPosition);
				// System.out.println("Dico : ajoute "+split[index]+" :"+(frette+a)+(frette+b)+(frette+c)+(frette+d)+"-");
			}
		}
	}

	// Affiche le contenu du dico en mode console
	public void afficheConsole() {
		Position laPosition;
		String snom;
		Set<String> set = leDico.keySet();

		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			snom = itr.next();
			laPosition = get(snom);
			System.out.println(snom + laPosition);
		}
	}
}
