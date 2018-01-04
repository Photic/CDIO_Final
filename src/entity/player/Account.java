package entity.player;

import entity.gameboard.GameBoard;

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
	
	public void calculateActives(Player p, GameBoard gameboard) {
		
		this.actives = 0;
		
		for (int i = 0; i <= gameboard.getLength(); i++) {
			if (gameboard.getField(i).getOwnerName() == p.getName()) {
				addActives(gameboard.getField(i).getPrice());
			}
		}
	}
	
	public void addActives(int actives) {
		this.actives = this.actives + actives;
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

}
