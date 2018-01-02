package entity;


/**
 * Player class describing the player in the game.
 * @author milter
 *
 */
public class Player {
		
	
	private String name;
	private int money, position, walkedSquares;
	private boolean isDead, inJail;

	public Player(String n, int startMoney)
	{
		this.name = n;
		this.money = startMoney;
		this.isDead = false;
		this.inJail = false;
		this.position = 0;
		this.walkedSquares = 0;
	}

	/**
	 * Roll 2 dice and return the sum of their facevalues.
	 * @param die1
	 * @param die2
	 * @return
	 * Sum of facevalues.
	 */
	public int rollDice(Die die1, Die die2) 
	{
		die1.rollDie();
		die2.rollDie();
		setWalkedSquares(walkedSquares + (die1.getFaceValue()+die2.getFaceValue()));
		return die1.getFaceValue()+die2.getFaceValue();
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
		return money;
	}

	public void setBalance(int score) 
	{
		this.money = score;
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

