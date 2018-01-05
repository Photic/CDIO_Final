package entity.player;

import entity.deck.Card;

public class Account {
	

	private int balance, actives, housesowned, hotelsowned, companies, shipping, amountOfCards;
	private Card antiJailCard;
	

	public Account() {
		this.balance = 300;
		this.actives = 0;
		this.companies = 0;
		this.shipping = 0;

	}
	
	public void recieveAntiJaulCard(Card cardrecieved) {
		this.setAntiJailCard(cardrecieved);
		this.amountOfCards++;
	}
	
	public void removeAntiJaulCard() {
		if (this.amountOfCards > 1) {
			this.amountOfCards--;
		}
		else {
			this.amountOfCards--;
			this.antiJailCard = null;
		}
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
		addBalance(-price);
		addActives(price);
	}
	
//	public void payRent(int rent, Player owner) {
//		
//		owner.getAccount().setBalance(rent + owner.getAccount().getBalance());
//		
//		setBalance(getBalance() - rent);
//		
//	}
	
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

	public void addBalance(int credit) {
		this.balance = this.balance + credit;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
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
	
	public int getCompanies() {
		return companies;
	}


	public void setCompanies(int companies) {
		this.companies = companies;
	}

	public Card getAntiJailCard() {
		return antiJailCard;
	}

	public void setAntiJailCard(Card antiJailCard) {
		this.antiJailCard = antiJailCard;
	}

	public int getShipping() {
		return shipping;
	}

	public void setShipping(int shipping) {
		this.shipping = shipping;
	}

}
