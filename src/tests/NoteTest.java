package tests;

import musique.Note;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NoteTest {

	@Test
	public  void testeEcritBemol()
	{
		Note maNote;

		// Un di�se devient un b�mol
		maNote = new Note("A#",3);
		maNote.ecritEnBemol();
		assertEquals( "Bb" , maNote.getNom()  );

		// Une note non alt�r�e reste non alt�r�e
		maNote = new Note("C",3);
		maNote.ecritEnBemol();
		assertEquals( "C" , maNote.getNom()  );

		// On teste toutes les notes
		maNote = new Note("G#",3);
		maNote.ecritEnBemol();
		assertEquals( "Ab" , maNote.getNom()  );

		maNote = new Note("F#",3);
		maNote.ecritEnBemol();
		assertEquals( "Gb" , maNote.getNom()  );

		maNote = new Note("C#",3);
		maNote.ecritEnBemol();
		assertEquals( "Db" , maNote.getNom()  );

		maNote = new Note("D#",3);
		maNote.ecritEnBemol();
		assertEquals( "Eb" , maNote.getNom()  );
	}

	@Test
	public void testeEcritDiese()
	{
		Note maNote;

		// Un b�mol devient un di�se
		maNote = new Note("Bb",3);
		maNote.ecritEnDiese();
		assertEquals( "A#" , maNote.getNom()  );

		// Une note non alt�r�e reste non alt�r�e
		maNote = new Note("C",3);
		maNote.ecritEnDiese();
		assertEquals( "C" , maNote.getNom()  );

		// On faite toutes les notes
		maNote = new Note("Ab",3);
		maNote.ecritEnDiese();
		assertEquals( "G#" , maNote.getNom()  );

		maNote = new Note("Gb",3);
		maNote.ecritEnDiese();
		assertEquals( "F#" , maNote.getNom()  );

		maNote = new Note("Db",3);
		maNote.ecritEnDiese();
		assertEquals( "C#" , maNote.getNom()  );

		maNote = new Note("Eb",3);
		maNote.ecritEnDiese();
		assertEquals( "D#" , maNote.getNom()  );
	}

	@Test
	public void testeMonterNote()
	{
		Note maNote;

		maNote = new Note("A",3);

		// On passe � l'octave sup�rieure
		maNote.monter(12);
		assertEquals("A", maNote.getNom());
		assertEquals(4, maNote.getOctave());

		// On passe � trois octaves sup�rieures
		maNote.monter(36);
		assertEquals("A", maNote.getNom());
		assertEquals(7, maNote.getOctave());

		// On ne peut pas d�passer l'octave 7
		maNote.monter(12);
		assertEquals("A", maNote.getNom());
		assertEquals(7, maNote.getOctave());

		// On redescend  l'octave 1
		maNote.monter(-72);
		assertEquals("A", maNote.getNom());
		assertEquals(1, maNote.getOctave());

		// On redescend  l'octave 0
		maNote.monter(-12);
		assertEquals("A", maNote.getNom());
		assertEquals(0, maNote.getOctave());

		// On ne peut pas redescendre en dessous de l'octave 0
		maNote.monter(-12);
		assertEquals("A", maNote.getNom());
		assertEquals(0, maNote.getOctave());

		// On monte d'une quinte
		maNote.monter(7);
		assertEquals("E", maNote.getNom());
		assertEquals(1, maNote.getOctave());
	}
	@Test
	public void testeMajNote() {
		Note maNote;

		maNote = new Note("A",3);
		maNote.setNom("D#");
		assertEquals("D#", maNote.getNom());

		// un nom incoh�rent ne modifie pas la note
		maNote.setNom("A#b#");
		assertEquals("D#", maNote.getNom());
		maNote.setNom("R");
		assertEquals("D#", maNote.getNom());
		maNote.setNom("a");
		assertEquals("D#", maNote.getNom());

		// Un nom avec di�se fonctionne
		maNote.setNom("A#");
		assertEquals("A#", maNote.getNom());

		// Les minuscules ne sont pas correctes
		maNote.setNom("b");
		assertEquals("A#", maNote.getNom());

		// Si di�se devient do
		maNote.setNom("B#");
		assertEquals("C", maNote.getNom());

	}

}