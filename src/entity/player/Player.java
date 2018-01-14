package entity.player;

import controller.AccountController;
import controller.FieldController;

/**
 * Player class describing the player in the game.
 * @author milter
 *
 */
public class Player {
		
	private int position, numberOfEqualDice, jailCounterDice;
	private boolean bankrupt, inJail;
	private String name;
	private Account account;
	private AccountController ac;

	public Player(String n) {
		this.name = n;
		this.account = new Account();
		this.bankrupt = false;
		this.inJail = false;
		this.position = 0;
		this.ac = new AccountController(this);
	}
	
	//--------------------------------------------------------
	//
	//                   Getters & Setters!
	//
	//--------------------------------------------------------
	public AccountController getAc() {
		return this.ac;
	}
	
	public String getName() {
		return this.name;
	}

	public boolean isBankrupt() {
		return this.bankrupt;
	}

	public void setBankrupt(boolean isDead) {
		this.bankrupt = isDead;
	}
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position, FieldController fc) {
		this.position = position % fc.getBoardLength();
	}
	
	public boolean isInJail() {
		return this.inJail;
	}

	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}

	public Account getAccount() {
		return account;
	}
	public int getNumberOfEqualDice() {
		return numberOfEqualDice;
	}

	public void setNumberOfEqualDice(int numberOfEqualDice) {
		this.numberOfEqualDice = numberOfEqualDice;
	}
	
	public int getJailCounterDice() {
		return jailCounterDice;
	}

	public void setJailCounterDice(int jailCounterDice) {
		this.jailCounterDice = jailCounterDice;
	}
}