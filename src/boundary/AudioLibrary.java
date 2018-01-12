package boundary;

import java.io.File;

public class AudioLibrary {

	/*
	 * Sounds to be used.
	 */
	private File jail, move, coin, birthday, car;
	private File[] dieThrow;

	public AudioLibrary() {
		this.jail = new File("src/main/rsc/jail.wav");
		this.move = new File("src/main/rsc/move.wav");
		this.coin = new File("src/main/rsc/coins.wav");
		this.birthday = new File("src/main/rsc/birthday.wav");
		this.car = new File ("src/main/rsc/car.wav");
		
		this.dieThrow = new File[] {
				new File ("src/main/rsc/die.wav"),
				new File ("src/main/rsc/die2.wav"),
				new File ("src/main/rsc/die3.wav")
		};
	}
	
	public File getJailSound() {
		return this.jail;
	}
	
	public File getMoveSound() {
		return this.move;
	}
	
	public File getDieSound(int n) {
		return this.dieThrow[n];
	}
	
	public int getDieLength() {
		return this.dieThrow.length;
	}
	
	public File getBirthday() {
		return this.birthday;
	}
	
	public File getCoinSound() {
		return this.coin;
	}
	
	public File getCarSound() {
		return this.car;
	}
	
}
