package boundary;

import java.io.File;

public class AudioLibrary {

	/*
	 * Sounds to be used.
	 */
	private File jail;
	private File[] move;
	private File[] dieThrow;
	private File birthday;

	public AudioLibrary() {
		this.jail = new File("src/main/rsc/Jail.wav");
		this.move = new File[] {
				new File ("src/main/rsc/move1.wav"), 
				new File ("src/main/rsc/move2.wav"), 
				new File ("src/main/rsc/move3.wav")
				};
		
		this.dieThrow = new File[] {
				new File ("src/main/rsc/die.wav"),
				new File ("src/main/rsc/die2.wav"),
				new File ("src/main/rsc/die3.wav")
		};
	}
	
	public File getJailSound() {
		return this.jail;
	}
	
	public File getMoveSound(int n) {
		return this.move[n];
	}
	
	public int getMoveLength() {
		return this.move.length;
	}
	
	public File getDieSound(int n) {
		return this.dieThrow[n];
	}
	
	public int getDieLength() {
		return this.dieThrow.length;
	}
	

}
