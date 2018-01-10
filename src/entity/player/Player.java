package entity.player;


/**
 * Player class describing the player in the game.
 * @author milter
 *
 */
public class Player {
		
	private final int GAMEBOARDLENGTH = 40;
	private String name, id;
	private int position;
	private boolean turn, bankrupt, inJail;
	private Account account;
	private int numberOfEqualDice;
	private int jailCounterDice; 


	public Player(String n) {
		this.name = n;
		this.account = new Account();
		this.bankrupt = false;
		this.inJail = false;
		this.position = 0;
	}

	//--------------------------------------------------------
	//
	//                   Getters & Setters!
	//
	//--------------------------------------------------------
	public String getName() {
		return name;
	}

	public boolean isBankrupt() {
		return bankrupt;
	}

	public void setBankrupt(boolean isDead) {
		this.bankrupt = isDead;
	}
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position % GAMEBOARDLENGTH;
	}
	
	public boolean isInJail() {
		return inJail;
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

