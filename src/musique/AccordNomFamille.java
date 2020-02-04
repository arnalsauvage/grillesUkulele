package musique;

import java.util.ArrayList;

public class AccordNomFamille {
    // Liste pour stocker l'ensemble des accords
    static ArrayList<AccordNomFamille> mesAccords = new ArrayList<>();

    private ArrayList<Integer> degres; // Degrés des accords, exemple 1,5,8 pour
    // accord majeur
    private String nomAccord;

    ///////////////////////// Constructeurs /////////////////////////////////
    // constructeur par défaut
    public AccordNomFamille() {
        degres = new ArrayList<>();
        degres.add(1);
        nomAccord = "X";
    }

    // constructeur par copie
    public AccordNomFamille(AccordNomFamille autreAccord) {
        degres = new ArrayList<>();
        for (Integer monInt : autreAccord.getDegres()) { degres.add(monInt); }
        //degres = (ArrayList<Integer>) autreAccord.getDegres().clone();
        nomAccord = autreAccord.getNomAccord();
    }

    // Constructeur par nom + tableau d'entiers
    public AccordNomFamille(String nom, Integer[] degres) {
        this.degres = new ArrayList<>();
        setDegres(degres);
        setNomAccord(nom);
    }

    public static String monDico() {
        StringBuilder dico = new StringBuilder("");

        for (AccordNomFamille monAccord : AccordNomFamille.mesAccords) {
            dico.append(monAccord + "\n");
        }
        return dico.toString();
    }

    ///////////////////////// Getters & Setters //////////

    // Renvoie les degrés de l'accord désigné par son nom de famille
    public static String getNomDeFamilleAccord(String nom) {
        String nomDeFamille;
        int debut = 1;
        if (nom.length() > 1) {
            String car2 = nom.substring(1, 2);
            if ((car2.equalsIgnoreCase("b")) || car2.equals("#")) debut = 2;
        }
        nomDeFamille = nom.substring(debut, nom.length());
        return nomDeFamille;
    }

    // Renvoie les degrés de l'accord désigné par son nom de famille
    public static ArrayList<Integer> getDegres(String nom) {
        for (AccordNomFamille monANF : AccordNomFamille.mesAccords) {
            if (monANF.nomAccord.equals(nom)) return (monANF.degres);
        }
        return new ArrayList<>();
    }

    // Renvoie le nom de famille de l'accord identifié par ses degrés
    public static String getNomFamille(ArrayList<Integer> degres) {
        for (AccordNomFamille monANF : AccordNomFamille.mesAccords) {
            if (monANF.degres.equals(degres)) return (monANF.nomAccord);
        }
        return "ko";
    }

    public static void creeCatalogueAccords() {
        AccordNomFamille monAccord;

        Integer[] powerChord = {1, 8};
        Integer[] accordMajeur = {1, 5, 8};
        Integer[] accordMajeurAugmente = {1, 5, 9};
        Integer[] accordMajeur6 = {1, 5, 8, 10};
        Integer[] accordMineur = {1, 4, 8};
        Integer[] accordMineur6 = {1, 4, 8, 10};
        Integer[] accordDiminue = {1, 4, 7};
        Integer[] accordDiminue7 = {1, 4, 7, 10};
        Integer[] accordMajeur7 = {1, 5, 8, 11};
        Integer[] accordMajeur7maj = {1, 5, 8, 12};
        Integer[] accordMineur7 = {1, 4, 8, 12};
        Integer[] accordMineur7min = {1, 4, 8, 11};
        Integer[] accordMineur7quinteB = {1, 4, 7, 11};
        Integer[] accordMajeur79 = {1, 3, 5, 11};
        Integer[] accordMajeur9 = {1, 5, 8, 15};

        monAccord = new AccordNomFamille();
        monAccord.setDegres(accordMajeur);
        monAccord.setNomAccord("");
        monAccord.ajouteAlaListe();

        monAccord = new AccordNomFamille("m", accordMineur);
        monAccord.ajouteAlaListe();

        monAccord = new AccordNomFamille("M7", accordMajeur7maj);
        monAccord.ajouteAlaListe();

        monAccord = new AccordNomFamille("7", accordMajeur7);
        monAccord.ajouteAlaListe();
        monAccord = new AccordNomFamille("5", powerChord);
        monAccord.ajouteAlaListe();
        monAccord = new AccordNomFamille("aug", accordMajeurAugmente);
        monAccord.ajouteAlaListe();
        monAccord = new AccordNomFamille("6", accordMajeur6);
        monAccord.ajouteAlaListe();
        monAccord = new AccordNomFamille("m6", accordMineur6);
        monAccord.ajouteAlaListe();
        monAccord = new AccordNomFamille("dim", accordDiminue);
        monAccord.ajouteAlaListe();
        monAccord = new AccordNomFamille("dim7", accordDiminue7);
        monAccord.ajouteAlaListe();
        monAccord = new AccordNomFamille("m7", accordMineur7min);
        monAccord.ajouteAlaListe();
        monAccord = new AccordNomFamille("°", accordMineur7);
        monAccord.ajouteAlaListe();
        monAccord = new AccordNomFamille("mmaj7", accordMineur7);
        monAccord.ajouteAlaListe();
        monAccord = new AccordNomFamille("m75b", accordMineur7quinteB);
        monAccord.ajouteAlaListe();
        monAccord = new AccordNomFamille("m6", accordMineur6);
        monAccord.ajouteAlaListe();
        monAccord = new AccordNomFamille("9", accordMajeur9);
        monAccord.ajouteAlaListe();
        monAccord = new AccordNomFamille("79", accordMajeur79);
        monAccord.ajouteAlaListe();
    }

    @Override
    public String toString() {
        return "Accord " + nomAccord + degres;
    }

    public ArrayList<Integer> getDegres() {
        return degres;
    }

    public void setDegres(ArrayList<Integer> degres) {
        this.degres = degres;
    }

    public void setDegres(Integer[] degres) {
        this.degres = new ArrayList<>();
        for (Integer monInt : degres) {
            this.degres.add(monInt);
        }
    }

    public void ajouteDegre(Integer degre) {
        if (!degres.contains(degre)) this.degres.add(degre);
    }


    ///////////////////////// Statique /////////////////////////////////

    public String getNomAccord() {
        return nomAccord;
    }

    public void setNomAccord(String nomAccord) {
        this.nomAccord = nomAccord;
    }

    private void ajouteAlaListe() {
        mesAccords.add(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((degres == null) ? 0 : degres.hashCode());
        result = prime * result + ((nomAccord == null) ? 0 : nomAccord.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AccordNomFamille other = (AccordNomFamille) obj;
        if (degres == null) {
            if (other.degres != null) return false;
        } else if (!degres.equals(other.degres)) {
            return false;
        }
        if (nomAccord == null) {
            if (other.nomAccord != null) return false;
        } else if (!nomAccord.equals(other.nomAccord)) {
            return false;
        }
        return true;
    }
}
