package entity.player;


/**
 * Player class describing the player in the game.
 * @author milter
 *
 */
public class Player {
		
	
	private String name, id;


	private int position;
	private boolean turn, bankrupt, inJail;
	private Account account;

	public Player(String n)
	{
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
		return bankrupt;
	}

	public void setDead(boolean isDead) {
		this.bankrupt = isDead;
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

	public boolean isBankrupt() {
		return bankrupt;
	}

	public void setBankrupt(boolean bankrupt) {
		this.bankrupt = bankrupt;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
}

