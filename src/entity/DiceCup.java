package entity;

import boundary.AudioPlayer;

public class DiceCup {
	
	
	private Die d1;
	private Die d2;
	
	
	public DiceCup() {
		this.d1 = new Die();
		this.d2 = new Die();
	}
	
	public void shake(AudioPlayer dac) {
		dac.playDieSound();
		this.d1.rollDie();
		this.d2.rollDie();	
	}
	
	
/**
 * 
 * @return
 * Summen af to terninger.
 */
	public int sum() {
		return this.d1.getValue() + this.d2.getValue();
	}
	
	public boolean equalsDice() {
		
		boolean equals;
		
		if (this.d1.getValue() == this.d2.getValue()) {
			
			equals = true;
			
		} else {
			
			equals = false;
			
		}
		
		return equals;
		
		
	}
	
	//--------------------------------------------------------
	//
	//                   Getters & Setters!
	//
	//--------------------------------------------------------
	
	
	public Die getD1() {
		return d1;
	}

	public Die getD2() {
		return d2;
	}
	

	

}
