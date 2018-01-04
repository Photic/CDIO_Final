package entity.player;

public class Account {
	
	private int balance, actives, antiJailCards, housesowned, hotelsowned;

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
	
	/**
	 * Calculates Player Worth and returns the sum.
	 * @param p
	 * @param gameboard
	 * @return
	 */
	public int getPlayerWorth(Player p) {
		return this.balance + this.actives;
	}
	
	public void addActives(int money) {
		this.actives = this.actives + money;
	}
	
	public void buyField(int price) {
		setBalance(-price);
		addActives(price);
	}
	
	//--------------------------------------------------------
	//
	//                   Getters & Setters!
	//
	//--------------------------------------------------------
	public int getActives() {
		return actives;
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


	public int getHousesowned() {
		return housesowned;
	}


	public void setHousesowned(int housesowned) {
		this.housesowned = this.hotelsowned + housesowned;
	}


	public int getHotelsowned() {
		return hotelsowned;
	}


	public void setHotelsowned(int hotelsowned) {
		this.hotelsowned = this.hotelsowned + hotelsowned;
	}

}
