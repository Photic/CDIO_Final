package boundary;

import java.io.File;

public class AudioLibrary {

	/*
	 * Sounds to be used.
	 */
	private File jail, move, coin, birthday, car, win, yay;
	private File[] dieThrow, hello;

	public AudioLibrary() {
		this.jail = new File("src/main/rsc/Jail.wav");
		this.move = new File("src/main/rsc/move.wav");
		this.coin = new File("src/main/rsc/coins.wav");
		this.birthday = new File("src/main/rsc/birthday.wav");
		this.car = new File ("src/main/rsc/car.wav");
		this.win = new File ("src/main/rsc/win.wav");
		this.yay = new File ("src/main/rsc/yay.wav");

		this.dieThrow = new File[] {
				new File ("src/main/rsc/die.wav"),
				new File ("src/main/rsc/die2.wav"),
				new File ("src/main/rsc/die3.wav")
				};

		this.hello = new File[] {
				new File ("src/main/rsc/hello.wav"),
				new File ("src/main/rsc/hello2.wav"),
				new File ("src/main/rsc/hello3.wav"),
				new File ("src/main/rsc/hello4.wav")
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

	public File getHelloSound(int n) {
		return this.hello[n];
	}

	public int getHelloLenght() {
		return this.hello.length;
	}

	public File getWinSound() {
		return this.win;
	}

	public File getYaySound() {
		return this.yay;
	}

}
