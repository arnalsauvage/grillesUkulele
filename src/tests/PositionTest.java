package tests;

import musique.NoteNom;
import musique.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PositionTest {

	public PositionTest() {
		// Rien pour le moment
	}

	@Test
	public void testeConstructeurParDefaut_C() {
		Position maPosition;
		maPosition = new Position(0,0,0,3);
		assertEquals("position : 0003", maPosition.toString());

	}

	@Test
	public void testerPositionHaute() {
		Position maPosition;

		// Position haute du do : 3
		maPosition = new Position(0,0,0,3);
		assertEquals(3, maPosition.getPositionHaute());

		// Position basse du do : 3
		maPosition = new Position(0,0,0,3);
		assertEquals(3, maPosition.getPositionBasse());
	}

	@Test
	public void testerCalculDifficulte() {
		Position maPosition;

		// difficulté la mineur : 1
		maPosition = new Position(2,0,0,0);
		assertEquals(1, maPosition.difficulte());

		// difficulté do : 2
		maPosition = new Position(0,0,0,3);
		assertEquals(2, maPosition.difficulte());

		// difficulté du Dm7 barré en 5 : 3
		maPosition = new Position(0,0,0,3);
		assertEquals(3, maPosition.getPositionBasse());

		// difficulté du C#dim7  : 3
		maPosition = new Position(3,4,3,4);
		assertEquals(3, maPosition.getPositionBasse());

		// difficulté du A7 (6,7,5,7)   : 4
		maPosition = new Position(6,7,5,7);
		assertEquals(5, maPosition.getPositionBasse());

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