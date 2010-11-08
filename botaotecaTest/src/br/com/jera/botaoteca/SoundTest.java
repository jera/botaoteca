package br.com.jera.botaoteca;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.test.AndroidTestCase;
import br.com.jera.botaoteca.sound.EmbeddedSound;
import br.com.jera.botaoteca.sound.Sound;

public class SoundTest extends AndroidTestCase {

	public void testPlayEmbeddedSound() {
		try {
			Sound sound = new EmbeddedSound("serracomedor.mp3", getContext());
			sound.play();
		} catch (Throwable e) {
			fail("should not throw any exceptions");
		}
	}

	public void testFailPlayEmbeddedSound() {

		Sound sound;
		try {

			sound = new EmbeddedSound("serraganhou.mp3", getContext());
			sound.play();
			fail("should throw " + FileNotFoundException.class.getName());

		} catch (IOException e) {
			// do nothing. exception expected
		} catch (Exception e) {
			fail("should throw " + FileNotFoundException.class.getName());
		}

	}

}
