package musique;

import java.util.ArrayList;
import java.util.HashMap;

public class Position {

	// Liste pour stocker l'ensemble des positions correspondant à des accords
	// Toto trier les positions par complexité croissante pour avoir map->1er élément  = accords le plus simple
	static HashMap<String, ArrayList<Position>> toutesLesPositionsDaccord = new HashMap<>();

	// Corde non jouée = -1 sinon, numéro de frette
	private Integer[] valCorde;

	// On demande la construction d'une position à partir de la
	// manière la plus simple de jouer un accord
	public Position(Accord monAccord) {
		valCorde = new Integer[4];
		chercheAccord(monAccord);
	}

	// Constructeur par les 4 valeurs des cordes 1 à 4
	public Position(int a, int b, int c, int d) {
		valCorde = new Integer[4];
		setPosition(a, b, c, d);
	}

	// initialise la valeur pour une corde
	public void setCorde(int maCorde, int valeur) {
		if ((maCorde > 0 && maCorde < 5) && (valeur >= -1 && valeur < 16))
			valCorde[maCorde - 1] = valeur;
	}

	// renvoie la valeur pour une corde
	public int getCorde(int maCorde) {
		if ((maCorde > 0 && maCorde < 5))
			return (valCorde[maCorde - 1]);
		else
			return -2;
	}

	// affecte la position à partir des 4 valeurs des 4 frettes
	public void setPosition(int corde1, int corde2, int corde3, int corde4) {
		setCorde(1, corde1);
		setCorde(2, corde2);
		setCorde(3, corde3);
		setCorde(4, corde4);
	}

	// évalue la difficulté d'une position
	public int difficulte() {
		int ecartement;
		int difficulte;
		int minVal = getPositionBasse(); // Case la plus basse jouée
		int maxVal = getPositionHaute(); // Case la plus haute jouée

		ecartement = maxVal - minVal + 1;
		// Moins il y a de notes de jouées, plus c'est facile !
		int nbreCordesJouees = 0;
		for (int i = 0; i < valCorde.length; i++) {
			if (valCorde[i] > 0)
				nbreCordesJouees++;
		}
		difficulte = nbreCordesJouees;

		// Un écartement de + de 2 , c'est plus dur !
		if (ecartement > 2)
			difficulte += ecartement - 2;

		// Plus on est loin du début du manche, + ça fait peur !
		if (minVal > 2 && minVal != Integer.MAX_VALUE)
			difficulte += minVal - 2;
		return difficulte;
	}

	public int getPositionBasse() {
		int minVal = Integer.MAX_VALUE; // Case la plus basse jouée
		for (int i = 0; i < valCorde.length; i++) {
			if (valCorde[i] > 0 && valCorde[i] < minVal)
				minVal = valCorde[i];
		}
		return minVal;
	}

	public int getPositionHaute() {
		int maxVal = Integer.MIN_VALUE; // Case la plus haute jouée

		for (int i = 0; i < valCorde.length; i++) {
			if (valCorde[i] > maxVal)
				maxVal = valCorde[i];
		}
		return maxVal;
	}

	// cherche la position la plus facile pour un accord
	// TODO : s'appuyer sur le dictionnaire des accords construit
	public void chercheAccord(Accord monAccord) {
		int bestA = 999;
		int bestB = 999;
		int bestC = 999;
		int bestD = 999;
		int bestDifficulte = 999;
		Accord chAccord;
		Ukulele monUke = new Ukulele();

		// On essayera en position frette 0 à 11
		for (int frette = -1; frette < 12; frette++) {
			// Espacement de 4 frettes pour toutes les cordes
			for (int a = 0; a < 5; a++) {
				for (int b = 0; b < 5; b++) {
					for (int c = 0; c < 5; c++) {
						for (int d = 0; d < 5; d++) {
							// On cherche les accords avec ces notes
							chAccord = monUke.trouveAccordPosition(frette + a, frette + b, frette + c, frette + d);
							if (chAccord != null) {
								// On teste les 4 renversements
								for (int j = 1; j < 5; j++) {
									chAccord.renverseAccord();
									// Si l'accord est l'accord cherché
									if (chAccord.equals(monAccord)) {
										// On évalue la difficulté
										setPosition(frette + a, frette + b, frette + c, frette + d);
										if (difficulte() < bestDifficulte) {
											bestA = frette + a;
											bestB = frette + b;
											bestC = frette + c;
											bestD = frette + d;
											bestDifficulte = difficulte();
										}
									}

								}
							}
						}
					}
				}
			}

		}
		setPosition(bestA, bestB, bestC, bestD);
	}

	// Parcourt le manche pour remplir le dico
	public static void construitDico() {
		Accord chAccord;
		Ukulele monUke = new Ukulele();
		Position maPosition;

		// On essayera en position frette 0 à 11
		for (int i = 0; i < 12; i++) {
			// Espacement de 4 frettes pour toutes les cordes
			for (int corde1 = -1; corde1 < 5; corde1++) {
				for (int corde2 = -1; corde2 < 5; corde2++) {
					for (int corde3 = -1; corde3 < 5; corde3++) {
						for (int corde4 = -1; corde4 < 5; corde4++) {

							// On cherche les accords avec ces notes
							maPosition = new Position(calculeFretteJouee(i, corde1), calculeFretteJouee(i, corde2),
									calculeFretteJouee(i, corde2), calculeFretteJouee(i, corde4));
							chAccord = monUke.trouveAccordPosition(calculeFretteJouee(i, corde1),
									calculeFretteJouee(i, corde2), calculeFretteJouee(i, corde2),
									calculeFretteJouee(i, corde4));
							String lesNoms;
							lesNoms = chAccord.chercheTypeAccord(true);

							// On explose les sous chaines dans un tableau
							String[] tabAccords = lesNoms.split(";");
							// Pour chaque nom séparé par un point virgule :
							for (int pchaine = 0; i < tabAccords.length; i++) {
								// Ajouter la position courante comme
								// interprétation de l'accord
								if (!toutesLesPositionsDaccord.containsKey(tabAccords[pchaine]))
									toutesLesPositionsDaccord.put(tabAccords[pchaine], new ArrayList<Position>());
								ArrayList<Position> maListe = toutesLesPositionsDaccord.get(tabAccords[pchaine]);
								maListe.add(maPosition);
								toutesLesPositionsDaccord.put(tabAccords[pchaine], maListe);
								System.out
										.println("Ajout de la  " + maPosition + " dans l'accord" + tabAccords[pchaine]);
							}

						}
					}
				}
			}

		}
	}

	// calcule la frette jouee selon les indices de parcours
	private static int calculeFretteJouee(int fretteBase, int fretteCorde) {
		int fretteJouee;
		if (fretteCorde == -1)
			fretteJouee = -1;
		else if (fretteCorde == 0)
			fretteJouee = 0;
		else
			fretteJouee = fretteCorde + fretteBase;
		return fretteJouee;
	}

	// Procédure de test
	public static void main(String[] args) {
		Ukulele monuke;
		Position maPosition;
		monuke = new Ukulele();
		Accord accordTrouve;
		String nomAccord;
		AccordNomFamille.creeCatalogueAccords();
		for (int a = 0; a < 5; a++)
			for (int b = 0; b < 5; b++)
				for (int c = 0; c < 5; c++)
					for (int d = 0; d < 5; d++) {
						accordTrouve = monuke.trouveAccordPosition(a, b, c, d);
						nomAccord = accordTrouve.chercheTypeAccord(true);
						if (!nomAccord.equals("")) {
							// On récupère tous les accords séparés par des ;
							String[] split = nomAccord.split(";");
							for (int i = 0; i < split.length; i++) {
								maPosition = new Position(a, b, c, d);
								System.out.print(split[i] + " " + a + b + c + d + " diff: " + maPosition.difficulte());
								System.out.println(maPosition);
							}
						}
					}
		construitDico();
		System.out.println(">>>>> Toutes les positions d'accord :\n" + toutesLesPositionsDaccord);
	}

	public String toString() {
		return ("position : " + valCorde[0] + valCorde[1] + valCorde[2] + valCorde[3]);
	}
}