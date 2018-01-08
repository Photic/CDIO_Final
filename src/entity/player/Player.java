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
	private int jailCounter;



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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isTurn() {
		return turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
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

	public int getJailCounter() {
		return jailCounter;
	}

	public void setJailCounter(int jailCounter) {
		this.jailCounter = jailCounter;
	}
	
}

