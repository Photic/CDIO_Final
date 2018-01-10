package boundary;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {

	private AudioLibrary alibrary;
	
	public AudioPlayer() {
		alibrary = new AudioLibrary();
	}
	
	public static void playSound(File fileName) {
		try {
			System.out.println("It worked");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(fileName));
			clip.start();
			// Makes the game sleep for lenght of clip. Sinece clip.get length is in microseconds, devide then by 1000.
			Thread.sleep(clip.getMicrosecondLength()/1000);

		} catch (Exception e) {
			System.out.println(e + " MY ASS");
			// TODO: handle exception
		}
	}
	
	public void playJailSound() {
		File file =new File ("src/main/rsc/Jail.wav"); 
		
		System.out.println("It worked");
		playSound(file);
	}
	//			File jail = new File("Jail.wav");
	//			playSound(jail);
	
}
