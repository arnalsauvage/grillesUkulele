package tests;

import musique.Accord;
import musique.AccordNomFamille;
import musique.NoteNom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccordTest {

	@Test
	public void testEqualsSurAccordMajeur7() {
		Accord monAccord;
		NoteNom maNote;
		Integer[] accordMajeur7 = { 1, 5, 8, 13 };

		maNote = new NoteNom("C");
		monAccord = new Accord(maNote, accordMajeur7);

		// Teste la méthode egale pour un accord identique à 4 degrés
		assertTrue(monAccord.equals(accordMajeur7));
		
	}

	@Test
	public void testEqualsAccordMajeurDifferentDeAccordMajeur7() {
		Accord monAccord;
		NoteNom maNote;
		Integer[] accordMajeur7 = { 1, 5, 8, 13 };

		maNote = new NoteNom("C");
		monAccord = new Accord(maNote, accordMajeur7);

		Integer[] autreAccord = { 1, 5, 8 }; // teste nb degrés différents
		assertTrue(!monAccord.equals(autreAccord));
	}

	@Test
	public void testEqualsSurAccordDegresDifferents() {
		Accord monAccord;
		NoteNom maNote;
		Integer[] accordMajeur7 = { 1, 5, 8, 13 };

		maNote = new NoteNom("C");
		monAccord = new Accord(maNote, accordMajeur7);

		Integer[] autreAccord2 = { 1, 3, 8, 13 };// teste degrés différents
		assertTrue(!monAccord.equals(autreAccord2));
	}

	@Test
	public void testEqualsSurObjetNull() {
		Accord monAccord;
		NoteNom maNote;
		Integer[] accordMajeur7 = { 1, 5, 8, 13 };

		maNote = new NoteNom("C");
		monAccord = new Accord(maNote, accordMajeur7);

		Accord monAccord2 = null; // teste objet null
		assertTrue(!monAccord.equals(monAccord2));
	}
	
	@Test
	public void testCompareSurAccordA3degres() {
		Integer[] accordMajeur7 = { 1, 5, 8, 13 };
		Accord monAccord;
		NoteNom maNote;

		maNote = new NoteNom("C");
		monAccord = new Accord(maNote, accordMajeur7);

		Integer[] autreAccord = { 1, 5, 8 };
		assertTrue(!monAccord.equals(autreAccord));
	}
	
	
	@Test
	public void testCompareSurAccordA4degres() {
		Integer[] accordMajeur7 = { 1, 5, 8, 13 };
		Accord monAccord;
		NoteNom maNote;

		maNote = new NoteNom("C");
		monAccord = new Accord(maNote, accordMajeur7);

		// Teste la méthode egale pour un accord identique à 4 degrés
		assertTrue(monAccord.equals(accordMajeur7));

	}
	
	@Test
	public void testCompareSurAccord4degresDifferent() {
		Integer[] accordMajeur7 = { 1, 5, 8, 13 };
		Accord monAccord;
		NoteNom maNote;

		maNote = new NoteNom("C");
		monAccord = new Accord(maNote, accordMajeur7);

		Integer[] autreAccord2 = { 1, 3, 8, 13 };
		assertTrue(!monAccord.equals(autreAccord2));
	}
	
	@Test
	public void testCalculeNote() {
		Integer[] accordMajeur7 = { 1, 5, 8, 13 };
		Accord monAccord;
		NoteNom maNote;

		maNote = new NoteNom("C");
		monAccord = new Accord(maNote, accordMajeur7);

		// Teste la méthode egale pour un accord identique à 4 degrés
		assertTrue(monAccord.equals(accordMajeur7));

		Integer[] autreAccord = { 1, 5, 8 };
		assertTrue(!monAccord.equals(autreAccord));

		Integer[] autreAccord2 = { 1, 3, 8, 13 };
		assertTrue(!monAccord.equals(autreAccord2));
	}
	
	@Test
	public void testeChercheTypeAccord(){
		AccordNomFamille.creeCatalogueAccords();
		
		Integer[] tabDegres = new Integer[] {1,4,8};
		Accord monAccord = new Accord(new NoteNom("C"), tabDegres);
		assertEquals ("Cm", monAccord.nomAbrege());
		
		tabDegres = new Integer[] {1,4,8,10};
		monAccord = new Accord(new NoteNom("C"), tabDegres);
		assertEquals("Cm6", monAccord.nomAbrege());
		
		tabDegres = new Integer[] {1,4,8,11};
		monAccord = new Accord(new NoteNom("C"), tabDegres);
		assertEquals (monAccord.nomAbrege(),"Cm7");

		tabDegres = new Integer[] {1,5,8,11};
		monAccord = new Accord(new NoteNom("C"), tabDegres);
		assertEquals (monAccord.nomAbrege(),"C7");

		tabDegres = new Integer[] {1,5,8,10};
		monAccord = new Accord(new NoteNom("C"), tabDegres);
		assertEquals (monAccord.nomAbrege(),"C6");

		tabDegres = new Integer[] {1,4,7,10};
		monAccord = new Accord(new NoteNom("C"), tabDegres);
		assertEquals (monAccord.nomAbrege(),"Cdim7");
	}
	
	@Test
	// Teste la classe en affichant une collection d'accords C D E F G A B
	public  void testeAccordsMajeurs() {
		Accord monAccord;
		NoteNom maNote;
		Integer [] tabNotes = {1,5,8,13};
		maNote = new NoteNom("C");
		monAccord = new Accord(maNote,tabNotes);
		monAccord.simplifie();
		assertEquals("Accord : C - C4 E4 G4 \t", monAccord.toString());
		maNote.monter(2);
		monAccord = new Accord(maNote,tabNotes);
		monAccord.simplifie();
		assertEquals("Accord : D - D4 F#4 A4 \t", monAccord.toString());
		maNote.monter(2);
		monAccord = new Accord(maNote,tabNotes);
		monAccord.simplifie();
		assertEquals("Accord : E - E4 G#4 B4 \t", monAccord.toString());
		maNote.monter(1);
		monAccord = new Accord(maNote,tabNotes);
		monAccord.simplifie();
		assertEquals("Accord : F - F4 A4 C5 \t", monAccord.toString());
		maNote.monter(2);
		monAccord = new Accord(maNote,tabNotes);
		monAccord.simplifie();
		assertEquals("Accord : G - G4 B4 D5 \t", monAccord.toString());
		maNote.monter(2);
		monAccord = new Accord(maNote,tabNotes);
		monAccord.simplifie();
		assertEquals("Accord : A - A4 C#5 E5 \t", monAccord.toString());
		maNote.monter(2);
		monAccord.simplifie();
		monAccord = new Accord(maNote,tabNotes);
		monAccord.simplifie();
		assertEquals("Accord : B - B4 D#5 F#5 \t", monAccord.toString());
		maNote.monter(1);
		monAccord = new Accord(maNote,tabNotes);
		monAccord.simplifie();
		assertEquals("Accord : C - C4 E4 G4 \t", monAccord.toString());
		
		maNote = new NoteNom("Bb");
		Integer[] tabNotes2 = {1,5,8};
		monAccord = new Accord(maNote,tabNotes2);
		assertEquals("Bb", monAccord.nomAbrege());

		monAccord = new Accord("Bb");
		assertEquals("Bb", monAccord.nomAbrege());
	}
	
	@Test
	// Teste la classe en affichant une collection d'accords C D E F G A B
	public  void testeAccordsMineurs() {
		Accord monAccord;
		NoteNom maNote;
		Integer[] tabNotes = {1,4,8};
		maNote = new NoteNom("Cm");
		monAccord = new Accord(maNote,tabNotes);
		assertEquals("Accord : Cm - C4 D#4 G4 \t", monAccord.toString());
		maNote.monter(2);
		monAccord = new Accord(maNote,tabNotes);
		assertEquals("Accord : Dm - D4 F4 A4 \t", monAccord.toString());
		maNote.monter(2);
		monAccord = new Accord(maNote,tabNotes);
		assertEquals("Accord : Em - E4 G4 B4 \t", monAccord.toString());
		maNote.monter(1);
		monAccord = new Accord(maNote,tabNotes);
		assertEquals("Accord : Fm - F4 G#4 C5 \t", monAccord.toString());
		maNote.monter(2);
		monAccord = new Accord(maNote,tabNotes);
		assertEquals("Accord : Gm - G4 A#4 D5 \t", monAccord.toString());
		maNote.monter(2);
		monAccord = new Accord(maNote,tabNotes);
		assertEquals("Accord : Am - A4 C5 E5 \t", monAccord.toString());
		maNote.monter(1);
		monAccord = new Accord(maNote,tabNotes);
		assertEquals("Accord : A#m - A#4 C#5 F5 \t", monAccord.toString());
		maNote.monter(1);
		monAccord = new Accord(maNote,tabNotes);
		assertEquals("Accord : Bm - B4 D5 F#5 \t", monAccord.toString());
		maNote.monter(1);
		monAccord = new Accord(maNote,tabNotes);
		assertEquals("Accord : Cm - C4 D#4 G4 \t", monAccord.toString());
	}
	
	
	@Test
	public void testeRenverseAccord(){
		
		Integer[] accordMineur7 = {1,4,8,11};

		Accord monAccord;
		NoteNom maNote = new NoteNom("A");
		monAccord = new Accord(maNote, accordMineur7);
		assertEquals("Accord : Am7;C6 - A4 C5 E5 G5 \t", monAccord.toString());
		monAccord.renverseAccord();
		assertEquals("Accord : C6;Am7 - C4 E4 G4 A4 \t", monAccord.toString());
		monAccord.renverseAccord();
		assertEquals("Accord : Am7;C6 - E4 G4 A4 C5 \t", monAccord.toString());
		monAccord.renverseAccord();
		assertEquals("Accord : Am7;C6 - G4 A4 C5 E5 \t", monAccord.toString());
	}

	@Test
	// Teste la classe en affichant une collection d'accords C D E F G A B
	public  void testeAccordsMineurs7() {
		Accord monAccord;
		NoteNom maNote;
		Integer[] tabNotes = {1,4,8,11};
		maNote = new NoteNom("Cm7");
		monAccord = new Accord(maNote,tabNotes);
		assertEquals("Accord : Cm7;D#6 - C4 D#4 G4 A#4 \t", monAccord.toString());
		maNote.monter(2);
		monAccord = new Accord(maNote,tabNotes);
		assertEquals("Accord : Dm7;F6 - D4 F4 A4 C5 \t", monAccord.toString());
		maNote.monter(2);
		monAccord = new Accord(maNote,tabNotes);
		assertEquals("Accord : Em7;G6 - E4 G4 B4 D5 \t", monAccord.toString());
		maNote.monter(1);
		monAccord = new Accord(maNote,tabNotes);
		assertEquals("Accord : Fm7;G#6 - F4 G#4 C5 D#5 \t", monAccord.toString());
		maNote.monter(2);
		monAccord = new Accord(maNote,tabNotes);
		assertEquals("Accord : Gm7;A#6 - G4 A#4 D5 F5 \t", monAccord.toString());
		maNote.monter(2);
		monAccord = new Accord(maNote,tabNotes);
		assertEquals("Accord : Am7;C6 - A4 C5 E5 G5 \t", monAccord.toString());
		maNote.monter(2);
		monAccord = new Accord(maNote,tabNotes);
		assertEquals("Accord : Bm7;D6 - B4 D5 F#5 A5 \t", monAccord.toString());
		maNote.monter(1);
		monAccord = new Accord(maNote,tabNotes);
		assertEquals("Accord : Cm7;D#6 - C4 D#4 G4 A#4 \t", monAccord.toString());
	}
	
	@Test
	public void testTransposition()
	{
		Accord monAccord = new Accord ("Cm7");

		// Tests transposer
		teste_Transposer(monAccord,2,"Dm7");
	}

	@Test
	public void testeSimplifie()
	{
		// C Eb G C ==> C Eb G
		NoteNom maNote = new NoteNom("Cm7");
		Accord monAccord = new Accord (maNote, new Integer[]{1, 4, 8, 13});
		Accord monAccordSimplifie = new Accord (maNote, new Integer[]{1, 4, 8});
		monAccord.simplifie();
		assertEquals(monAccordSimplifie, monAccord);

		// C Eb G  ==> C Eb G
		 maNote = new NoteNom("Cm");
		 monAccord = new Accord (maNote, new Integer[]{1, 4, 8});
		 monAccord.simplifie();
		 assertEquals(monAccordSimplifie, monAccord);

		// C Eb G Bb C ==> C Eb G Bb
		maNote = new NoteNom("C7");
		monAccord = new Accord (maNote, new Integer[]{1, 5, 8, 11, 13});
		monAccordSimplifie = new Accord (maNote, new Integer[]{1, 5, 8, 11});
		monAccord.simplifie();
		assertEquals(monAccordSimplifie, monAccord);

	}


	public static void teste_Transposer(Accord monAccord, int transpose, String resultat)
	{
		Accord autreAccord = new Accord(monAccord);
		autreAccord.transpose(transpose);
		
		if (autreAccord.nomAbrege().equals(resultat))
			System.out.println(resultat + " trouvé");
		else
			System.err.println("Erreur pour trouver " + monAccord.nomAbrege() + " ==>" + resultat  +  " résultat trouvé : " + autreAccord.nomAbrege());
		assertEquals(resultat, autreAccord.nomAbrege());
	}

}
