package boundary;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import main.Main;

public class AudioLibrary {

	/*
	 * Sounds to be used.
	 */
	private File jail;
	private AudioInputStream ais;
	private InputStream defaultSound;
	
	public AudioInputStream getAis() {
		return ais;
	}

	public AudioLibrary() {
		defaultSound = Main.class.getResourceAsStream(("rsc/Jail.wav"));
		try {
			ais = AudioSystem.getAudioInputStream(defaultSound);
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
	public File getJailSound() {
		return this.jail;
	}
	
	

}
