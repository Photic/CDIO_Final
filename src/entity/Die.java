package entity;

import java.util.Random;

public class Die {
	
	private int value;
	
	public Die() {}
	
	/**
	 * ruller terningen
	 * 
	 */
	public void rollDie() {
		Random rng = new Random();
		this.value = rng.nextInt(6) + 1;
	}

	//--------------------------------------------------------
	//
	//                   Getters & Setters!
	//
	//--------------------------------------------------------
	public int getValue() {
		
		return this.value;
		
	}

}
