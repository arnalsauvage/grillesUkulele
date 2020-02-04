package musique;

import java.io.Serializable;

public class Position implements Serializable {
	private static final long serialVersionUID = 1591886239005541649L;
    // Corde non jou�e = -1 sinon, num�ro de frette
    private Integer[] valCorde;

    // On demande la construction d'une position � partir de la mani�re la plus simple de jouer un accord
    //	public Position(Accord monAccord) {
    //		valCorde = new Integer[4];
    //		chercheAccord(monAccord);
    //	}

    // Constructeur par les 4 valeurs des cordes 1 � 4
    public Position(int a, int b, int c, int d) {
        valCorde = new Integer[4];
        setPosition(a, b, c, d);
    }

    // initialise la valeur pour une corde
    public void setCorde(int maCorde, int valeur) {
        if ((maCorde > 0 && maCorde < 5) && (valeur >= -1 && valeur < 16)) valCorde[maCorde - 1] = valeur;
    }

    // renvoie la valeur pour une corde
    public int getCorde(int maCorde) {
        if ((maCorde > 0 && maCorde < 5)) return (valCorde[maCorde - 1]);
        else return -2;
    }

    // affecte la position � partir des 4 valeurs des 4 frettes
    public void setPosition(int corde1, int corde2, int corde3, int corde4) {
        setCorde(1, corde1);
        setCorde(2, corde2);
        setCorde(3, corde3);
        setCorde(4, corde4);
    }

    // �value la difficult� d'une position
    public int difficulte() {
        int ecartement;
        int difficulte;
        int minVal = getPositionBasse(); // Case la plus basse jou�e
        int maxVal = getPositionHaute(); // Case la plus haute jou�e

        ecartement = maxVal - minVal + 1;
        // Moins il y a de notes de jou�es, plus c'est facile !
        int nbreCordesJouees = 0;
        for (int i = 0; i < valCorde.length; i++) {
            if (valCorde[i] > 0) nbreCordesJouees++;
        }
        difficulte = nbreCordesJouees;

        // Un �cartement de + de 2 , c'est plus dur !
        if (ecartement > 2) difficulte += ecartement - 2;

        // Plus on est loin du d�but du manche, + �a fait peur !
        if (minVal > 2 && minVal != Integer.MAX_VALUE) difficulte += minVal - 2;
        return difficulte;
    }

    public int getPositionBasse() {
        int minVal = Integer.MAX_VALUE; // Case la plus basse jou�e
        for (int i = 0; i < valCorde.length; i++) {
            if (valCorde[i] > 0 && valCorde[i] < minVal) minVal = valCorde[i];
        }
        return minVal;
    }

    public int getPositionHaute() {
        int maxVal = Integer.MIN_VALUE; // Case la plus haute jou�e

        for (int i = 0; i < valCorde.length; i++) {
            if (valCorde[i] > maxVal) maxVal = valCorde[i];
        }
        return maxVal;
    }

    public String toString() {
        return ("position : " + valCorde[0] + valCorde[1] + valCorde[2] + valCorde[3]);
    }
}