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

	public AudioLibrary() {
		
		jail = new File("src/main/rsc/Jail.wav");

	}
	
	public File getJailSound() {
		return this.jail;
	}
	
	

}
