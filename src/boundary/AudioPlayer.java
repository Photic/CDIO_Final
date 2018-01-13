package boundary;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {

	private AudioLibrary alibrary;
		
	public AudioPlayer() {
		this.alibrary = new AudioLibrary();
	}
	
	/**
	 * Jail door closes shut sound.
	 */
	public void playJailSound() {
		playSound(this.alibrary.getJailSound());
	}
	
	public void playMoveSound() {
		playSound(this.alibrary.getMoveSound());
	}
	
	public void playDieSound() {
		playSound(this.alibrary.getDieSound((int)(Math.random()*this.alibrary.getDieLength())));
	}
	
	public void playCoinSound() {
		playSound(this.alibrary.getCoinSound());
	}
	
	public void playBirthdaySound() {
		playSound(this.alibrary.getBirthday());
	}
	
	public void playCarSound() {
		playSound(this.alibrary.getCarSound());
	}
	
	public void playHelloSound() {
		playSound(this.alibrary.getHelloSound((int)(Math.random()*this.alibrary.getHelloLenght())));
	}
	
	public void playWinSound() {
		playSound(this.alibrary.getWinSound());
	}
	
	public void playYaySound() {
		playSound(this.alibrary.getYaySound());
	}
	
	/**
	 * Play the inputed file, sleeps for the durations of the sound.
	 * @param fileName
	 */
	private void playSound(File file) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
			// Makes the game sleep for lenght of clip. Sinece clip.get length is in microseconds, devide then by 1000.
			Thread.sleep(clip.getMicrosecondLength()/1000);

		} catch (Exception e) {
			System.out.println(e + " MY ASS");
			// TODO: handle exception
		}
		
	}
}
