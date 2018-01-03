package entity.player;

public class Account {
	
	private int balance;
	private int actives;
	private int antiJailCards;
	

	public Account() {
		this.balance = 30000;
		this.actives = 0;
		this.antiJailCards = 0;
	}
	
	
	public void addJailCard() {
		this.antiJailCards++;
	}
	
	public void removeJailCard() {
		this.antiJailCards--;
	}
	
	
	//--------------------------------------------------------
	//
	//                   Getters & Setters!
	//
	//--------------------------------------------------------
	
	public int getActives() {
		return actives;
	}

	public void setActives(int actives) {
		this.actives = actives;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int credit) {
		this.balance = this.balance + credit;
	}
	
	public int getAntiJailCards() {
		return antiJailCards;
	}

}
