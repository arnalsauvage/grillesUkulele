package tests;

import musique.NoteNom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NoteNomTest {

	public NoteNomTest() {
		// Rien pour le moment
	}

	@Test
	public void testeConstructeurParDefaut_C() {
		NoteNom monNoteNom;
		monNoteNom = new NoteNom("C");
		assertEquals("C" , monNoteNom.getNom());
		monNoteNom = new NoteNom("C#");
		assertEquals("C#", monNoteNom.getNom());
		monNoteNom = new NoteNom("Db");
		assertEquals( "Db", monNoteNom.getNom());
		monNoteNom = new NoteNom("D");
		assertEquals("D", monNoteNom.getNom());
		monNoteNom = new NoteNom("D#");
		assertEquals("D#", monNoteNom.getNom());
		monNoteNom = new NoteNom("E");
		assertEquals("E", monNoteNom.getNom());
		monNoteNom = new NoteNom("F");
		assertEquals("F", monNoteNom.getNom());
		monNoteNom = new NoteNom("G");
		assertEquals("G", monNoteNom.getNom());
		monNoteNom = new NoteNom("A");
		assertEquals("A", monNoteNom.getNom());
		monNoteNom = new NoteNom("B");
		assertEquals("B", monNoteNom.getNom());
	}

	@Test
	public void testeMonter() {
		NoteNom monNoteNom;
		monNoteNom = new NoteNom("B");
		monNoteNom.monter(1);
		assertEquals("C", monNoteNom.getNom());
		monNoteNom.monter(1);
		assertEquals("C#",monNoteNom.getNom());
		monNoteNom.monter(1);
		assertEquals("D", monNoteNom.getNom());
		monNoteNom.monter(-5);
		assertEquals("A", monNoteNom.getNom());
		monNoteNom.monter(-5);
		assertEquals("E", monNoteNom.getNom());
	}

	@Test
	public void testeEcritEnDieses() {
		NoteNom monNoteNom;
		monNoteNom = new NoteNom("Db");
		monNoteNom.ecritEnDiese();
		assertEquals("C#", monNoteNom.getNom());
		monNoteNom = new NoteNom("Eb");
		monNoteNom.ecritEnDiese();
		assertEquals("D#", monNoteNom.getNom());
		monNoteNom = new NoteNom("Gb");
		monNoteNom.ecritEnDiese();
		assertEquals("F#", monNoteNom.getNom());
		monNoteNom = new NoteNom("Ab");
		monNoteNom.ecritEnDiese();
		assertEquals("G#", monNoteNom.getNom());
		monNoteNom = new NoteNom("Bb");
		monNoteNom.ecritEnDiese();
		assertEquals("A#", monNoteNom.getNom());
	}

	@Test
	public void testeEcritEnBemol() {
		NoteNom monNoteNom;
		monNoteNom = new NoteNom("C#");
		monNoteNom.ecritEnBemol();
		assertEquals("Db",monNoteNom.getNom());
		monNoteNom = new NoteNom("D#");
		monNoteNom.ecritEnBemol();
		assertEquals("Eb",monNoteNom.getNom());
		monNoteNom = new NoteNom("F#");
		monNoteNom.ecritEnBemol();
		assertEquals("Gb",monNoteNom.getNom());
		monNoteNom = new NoteNom("G#");
		monNoteNom.ecritEnBemol();
		assertEquals("Ab",monNoteNom.getNom());
		monNoteNom = new NoteNom("A#");
		monNoteNom.ecritEnBemol();
		assertEquals("Bb",monNoteNom.getNom());
	}
}