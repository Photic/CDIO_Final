package main;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import controller.GameController;

public class Main {

	public static void main(String[] args) throws IOException {
		
		File jail = new File("Jail.wav");
		playSound(jail);
		
		new GameController().gameControl();
		
	}
	
	static void playSound(File jail) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(jail));
			clip.start();
			
			Thread.sleep(clip.getMicrosecondLength()/1000);
			
		} catch (Exception e) {
			System.out.println(e + " MY ASS");
			// TODO: handle exception
		}
	}
	
}
