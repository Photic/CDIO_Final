package entity.player;

public class Account {
	
	private int balance = 30000;
	private int actives = 0;
	
	
	public int getActives() {
		return actives;
	}

	public void setActives(int actives) {
		this.actives = actives;
	}

	public int getAntiJailCards() {
		return antiJailCards;
	}

	public void setAntiJailCards(int antiJailCards) {
		this.antiJailCards = antiJailCards;
	}

	private int antiJailCards = 0;

	public int getBalance() {
		return balance;
	}

	public void setBalance(int credit) {
		this.balance = this.balance + credit;
	}
	

}
