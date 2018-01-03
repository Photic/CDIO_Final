package entity.player;


/**
 * Player class describing the player in the game.
 * @author milter
 *
 */
public class Player {
		
	
	private String name;
	private int position, walkedSquares;
	private boolean isDead, inJail;
	private Account account = new Account();

	public Player(String n)
	{
		this.name = n;
		this.isDead = false;
		this.inJail = false;
		this.position = 0;
		this.walkedSquares = 0;
	}
	
	//--------------------------------------------------------
	//
	//                   Getters & Setters!
	//
	//--------------------------------------------------------
	public String getName() 
	{
		return name;
	}

	public int getBalance() 
	{
		return account.getBalance();
	}

	public void setBalance(int credit) 
	{
		account.setBalance(credit);
	}	

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	public boolean isInJail() {
		return inJail;
	}

	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}

	public int getWalkedSquares() {
		return walkedSquares;
	}

	public void setWalkedSquares(int walkedSquares) {
		this.walkedSquares = walkedSquares;
	}
	
}
