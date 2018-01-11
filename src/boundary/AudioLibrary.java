package boundary;

import java.io.File;

public class AudioLibrary {

	/*
	 * Sounds to be used.
	 */
	private File jail;
	private File move;
	private File transaction;
	private File birthday;

	public AudioLibrary() {
		jail = new File("src/main/rsc/Jail.wav");
	}
	
	public File getJailSound() {
		return this.jail;
	}
	
	

}
