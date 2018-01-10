package boundary;

import java.io.File;

public class AudioLibrary {

	/*
	 * Sounds to be used.
	 */
	private File jail;
	
	public AudioLibrary() {
		this.jail = new File("src/main/rsc/Jail.wav");
	}
	
	public File getJailSound() {
		return this.jail;
	}
	
	

}
